package com.example.ricindigus.enpove2021.adapters;


import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.ricindigus.enpove2021.R;
import com.example.ricindigus.enpove2021.modelo.DAOUtils;
import com.example.ricindigus.enpove2021.modelo.Data;
import com.example.ricindigus.enpove2021.modelo.pojos.Residente;
import com.example.ricindigus.enpove2021.util.AppConfiguration;
import com.example.ricindigus.enpove2021.util.UtilsMethods;

import java.util.ArrayList;


public class HogarMixtoAdapter extends RecyclerView.Adapter<HogarMixtoAdapter.ViewHolder> {

    ArrayList<Residente> residentes;
    HogarMixtoAdapter.OnItemClickListener onItemClickListener;
    Context context;
    int valor;
    String parentesco;



    public HogarMixtoAdapter(ArrayList<Residente> residentes, Context context) {
        this.residentes = residentes;
        this.onItemClickListener = onItemClickListener;
        this.context = context;
    }

    public interface OnItemClickListener {
        public void onItemClick(View view, int position);
    }



    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.hogarmixto_item,parent,false);
        HogarMixtoAdapter.ViewHolder viewHolder = new HogarMixtoAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, @SuppressLint("RecyclerView") final int position) {



            holder.txtNumero.setText(residentes.get(position).getNumero());
            holder.txtNombre.setText(residentes.get(position).getC2_p202());

            holder.txtParentesco.setText(context.getResources().getStringArray(R.array.modulo_2_p203_parentescos)[Integer.parseInt(residentes.get(position).getC2_p203())]);

            //       holder.txtSexo.setText(String.valueOf(residentes.get(position).getC2_p204()));

//////COMENTADO PARA TRAER VALORES
            if (!residentes.get(position).getC2_p204().equals(""))
                holder.txtSexo.setText(context.getResources().getStringArray(R.array.modulo_2_p204_sexo)[Integer.parseInt(residentes.get(position).getC2_p204())]);
            else holder.txtSexo.setText("-----");

            if (residentes.get(position).getC2_p205_a().equals("") && residentes.get(position).getC2_p205_m().equals("")) {
                holder.txtEdad.setText("");
            } else {
                if (!residentes.get(position).getC2_p205_a2().equals("")) {
                    holder.txtEdad.setText(String.valueOf(residentes.get(position).getC2_p205_a2()) + " Años");
                } else if (!residentes.get(position).getC2_p205_m().equals("")) {
                    holder.txtEdad.setText(String.valueOf(residentes.get(position).getC2_p205_m()) + " Meses");
                } else {
                    holder.txtEdad.setText("-----");
                }
            }

            if (!residentes.get(position).getC2_p206().equals("") && Integer.parseInt(residentes.get(position).getC2_p206()) > 0)
                holder.txtEstadoCivil.setText(context.getResources().getStringArray(R.array.modulo_2_p206_estado_civil)[Integer.parseInt(residentes.get(position).getC2_p206())]);
            else holder.txtEstadoCivil.setText("-----");

            holder.cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onItemClickListener.onItemClick(view, position);
                }
            });

            if (residentes.get(position).getC2_p208().equals("1"))
                holder.txtLlegoVenezuela.setText("SI");
            else if (residentes.get(position).getC2_p208().equals("2"))
                holder.txtLlegoVenezuela.setText("NO");
            else holder.txtLlegoVenezuela.setText("-----");

            if( residentes.get(position).getP200_aportante() != null && !residentes.get(position).getP200_aportante().equals("")){
                ((RadioButton)holder.RadioIngresos.getChildAt(Integer.parseInt(
                        residentes.get(position).getP200_aportante()
                ))).setChecked(true);
            }

            holder.RadioIngresos.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                int seleccionado = group.indexOfChild(group.findViewById(checkedId));
                Log.e("RADIO",""+residentes.get(position).get_id()+":"+seleccionado);
                residentes.get(position).p200_aportante = ""+seleccionado;
                ((AppConfiguration) context.getApplicationContext()).setResidentesedad(residentes);

            }
            });






//Log.e("parentesco",""+context.getResources().getStringArray(R.array.modulo_2_p203_parentescos)[Integer.parseInt(residentes.get(position).getC2_p203())]);
//Log.e("sexo",""+context.getResources().getStringArray(R.array.modulo_2_p204_sexo)[Integer.parseInt(residentes.get(position).getC2_p204())]);





////////////////////////////
    /*    if(residentes.get(position).getC2_p208().equals("1") && residentes.get(position).getEncuestado_cobertura().equals("2"))
            holder.txtCobertura.setText("INCOMPLETA");
        else if (residentes.get(position).getC2_p208().equals("1") && residentes.get(position).getEncuestado_cobertura().equals("1"))
            holder.txtCobertura.setText("COMPLETA");
        else if (residentes.get(position).getC2_p208().equals("1") && (residentes.get(position).getEncuestado_cobertura().equals("") || residentes.get(position).getEncuestado_cobertura().equals("0")))
            holder.txtCobertura.setText("INCOMPLETA.");
        else holder.txtCobertura.setText("-----");

        if(DAOUtils.getResultadoRedidente(residentes.get(position).get_id(),context)!=null){
            if(!DAOUtils.getResultadoRedidente(residentes.get(position).get_id(),context).getResultado_entrevista().equals("")){
                holder.txtResultado.setText(UtilsMethods.getNameResult(Integer.parseInt(DAOUtils.getResultadoRedidente(residentes.get(position).get_id(),context).getResultado_entrevista())));}
            else {
                holder.txtResultado.setText("----");
            }
        }else {
            holder.txtResultado.setText("----");
        }
        if(residentes.get(position).getC2_p208().equals("2")){
            holder.ivCapitulo300.setImageResource(R.drawable.baseline_lock_grey_500_24dp);
            holder.ivCapitulo400.setImageResource(R.drawable.baseline_lock_grey_500_24dp);
            holder.ivCapitulo500.setImageResource(R.drawable.baseline_lock_grey_500_24dp);
            holder.ivCapitulo600.setImageResource(R.drawable.baseline_lock_grey_500_24dp);
            holder.ivCapitulo700.setImageResource(R.drawable.baseline_lock_grey_500_24dp);
            holder.ivCapitulo800.setImageResource(R.drawable.baseline_lock_grey_500_24dp);
        }

        if(DAOUtils.getCoberturaModulo200(residentes.get(position).get_id(), context)==1 || residentes.get(position).getC2_p208().equals("2")){
            holder.ivCapitulo200.setImageResource(R.drawable.baseline_check_circle_outline_green_900_24dp);
        }
        if(residentes.get(position).getC2_p208().equals("1") && DAOUtils.getCoberturaModulo300(residentes.get(position).get_id(), context)==1){
            holder.ivCapitulo300.setImageResource(R.drawable.baseline_check_circle_outline_green_900_24dp);
        }
        if(residentes.get(position).getC2_p208().equals("1") && DAOUtils.getCoberturaModulo400(residentes.get(position).get_id(), context)==1){
            holder.ivCapitulo400.setImageResource(R.drawable.baseline_check_circle_outline_green_900_24dp);
        }
        if(residentes.get(position).getC2_p208().equals("1") && DAOUtils.getCoberturaModulo500(residentes.get(position).get_id(), context)==1){
            holder.ivCapitulo500.setImageResource(R.drawable.baseline_check_circle_outline_green_900_24dp);
        }
        if(residentes.get(position).getC2_p208().equals("1") && DAOUtils.getCoberturaModulo600(residentes.get(position).get_id(), context)==1){
            holder.ivCapitulo600.setImageResource(R.drawable.baseline_check_circle_outline_green_900_24dp);
        }
        if(residentes.get(position).getC2_p208().equals("1") && DAOUtils.getCoberturaModulo700(residentes.get(position).get_id(), context)==1){
            holder.ivCapitulo700.setImageResource(R.drawable.baseline_check_circle_outline_green_900_24dp);
        }
        if(residentes.get(position).getC2_p208().equals("1") && DAOUtils.getCoberturaModulo800(residentes.get(position).get_id(), context)==1){
            holder.ivCapitulo800.setImageResource(R.drawable.baseline_check_circle_outline_green_900_24dp);
        } */

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             //   onItemClickListener.onItemClick(view,position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return residentes.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        TextView txtNumero;
        TextView txtNombre;
        TextView txtParentesco;
        TextView txtSexo;
        TextView txtEdad;
        TextView txtEstadoCivil;
        TextView txtLlegoVenezuela;
        RadioGroup RadioIngresos;
       /* TextView txtCobertura;
        TextView txtResultado;
        ImageView ivCapitulo200;
        ImageView ivCapitulo300;
        ImageView ivCapitulo400;
        ImageView ivCapitulo500;
        ImageView ivCapitulo600;
        ImageView ivCapitulo700;
        ImageView ivCapitulo800;*/
        CardView cardView;

        public ViewHolder(View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.item_hogarmixto_cardview);
            txtNumero = itemView.findViewById(R.id.item_hogarmixto_numero);
            txtNombre = itemView.findViewById(R.id.item_hogarmixto_nombre);
            txtParentesco = itemView.findViewById(R.id.item_hogarmixto_parentesco);
            txtSexo = itemView.findViewById(R.id.item_hogarmixto_sexo);
            txtEdad = itemView.findViewById(R.id.item_hogarmixto_edad);
            txtEstadoCivil = itemView.findViewById(R.id.item_hogarmixto_estado_civil);
            txtLlegoVenezuela = itemView.findViewById(R.id.item_hogarmixto_llego_venezuela);
            RadioIngresos = itemView.findViewById(R.id.item_hogarmixto_ingresos);


          /*  txtCobertura = itemView.findViewById(R.id.item_residente_cobertura);
            txtResultado = itemView.findViewById(R.id.item_residente_resultado);
            ivCapitulo200 = itemView.findViewById(R.id.item_cobertura_200);
            ivCapitulo300 = itemView.findViewById(R.id.item_cobertura_300);
            ivCapitulo400 = itemView.findViewById(R.id.item_cobertura_400);
            ivCapitulo500 = itemView.findViewById(R.id.item_cobertura_500);
            ivCapitulo600 = itemView.findViewById(R.id.item_cobertura_600);
            ivCapitulo700 = itemView.findViewById(R.id.item_cobertura_700);
            ivCapitulo800 = itemView.findViewById(R.id.item_cobertura_800);*/
        }
    }



}
