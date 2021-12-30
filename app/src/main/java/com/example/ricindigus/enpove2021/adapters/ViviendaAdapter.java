package com.example.ricindigus.enpove2021.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.example.ricindigus.enpove2021.R;
import com.example.ricindigus.enpove2021.modelo.pojos.ViviendaItem;
import com.example.ricindigus.enpove2021.util.UtilsMethods;

import java.util.ArrayList;


public class ViviendaAdapter extends RecyclerView.Adapter<ViviendaAdapter.ViewHolder> {
    ArrayList<ViviendaItem> listaViviendas;
    Context context;
    OnCheckedChangeListener onCheckedChangeListener;
    OnItemClickListener onItemClickListener;
    int vista=0;

   // String segmentoo;

    public interface OnItemClickListener{
        public void onItemClick(View view,int position);
    }

    public interface OnCheckedChangeListener{
        public void onCheckedChanged(CompoundButton compoundButton, boolean b, int pos);
    }

    public ViviendaAdapter(ArrayList<ViviendaItem> listaViviendas, Context context, OnCheckedChangeListener onCheckedChangeListener,OnItemClickListener onItemClickListener,int vista) {
        this.listaViviendas = listaViviendas;
        this.context = context;
        this.onCheckedChangeListener = onCheckedChangeListener;
        this.onItemClickListener = onItemClickListener;
        this.vista = vista;
        Log.e("juxe:",""+listaViviendas.size());

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_item_vivienda,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(ViewHolder holder, @SuppressLint("RecyclerView") final int position) {

        if (vista==1){
            holder.cardViewItem.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view) {
                    onItemClickListener.onItemClick(view,position);
                }
            });

            holder.ckSeleccionado.setVisibility(View.GONE);


            holder.txtNroVivienda.setText(listaViviendas.get(position).getNroVivienda().toString());
            holder.txtConglomerado.setText(listaViviendas.get(position).getConglomerado().toString());
            holder.txtNroSelecVivienda.setText(listaViviendas.get(position).getNroSelecVivienda().toString());
            ////////////AGREGAR SEGMENTACION////////////////////
            holder.txtTipoSelecVivienda.setText(listaViviendas.get(position).getNrosegmento()+"");
            ///////////QUITAR TIPO SELECCION///////////////////
            //holder.txtTipoSelecVivienda.setText(UtilsMethods.getTipoSeleccionVivienda(listaViviendas.get(position).getTipoSelecVivienda().toString()));
            holder.txtReemplazoVivienda.setText(UtilsMethods.getViviendaReemplazo(listaViviendas.get(position).getReemplazoVivienda().toString()));
            holder.txtCentroPoblado.setText(listaViviendas.get(position).getCentroPoblado().toString());
            holder.txtPeriodo.setText(listaViviendas.get(position).getPeriodo().toString());
            holder.txtResultado.setVisibility(View.GONE);
            holder.txtEstado.setVisibility(View.GONE);

          //  segmentoo = holder.txtTipoSelecVivienda.getText().toString();

            String segmento = listaViviendas.get(position).getNrosegmento()+"";
            String posicion = String.valueOf(position);
            Log.e("SEGMENTO1:",segmento);
            Log.e("POSICION1",posicion);



            if(listaViviendas.get(position).getReemplazoVivienda().equals("1")){
                holder.cardViewItem.setCardBackgroundColor(Color.rgb(255,223,186));
            }else {
                holder.cardViewItem.setCardBackgroundColor(Color.rgb(201,242,193));
            }

        }else {
            holder.ckSeleccionado.setOnCheckedChangeListener(null);
            if (listaViviendas.get(position).getSeleccionado() == 0)
                holder.ckSeleccionado.setChecked(false);
            if (listaViviendas.get(position).getSeleccionado() == 1)
                holder.ckSeleccionado.setChecked(true);
            holder.ckSeleccionado.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    onCheckedChangeListener.onCheckedChanged(compoundButton, b, position);
                }
            });

            String conglomerado = listaViviendas.get(position).getConglomerado();
            holder.txtNroVivienda.setText(listaViviendas.get(position).getNroVivienda().toString());
            holder.txtConglomerado.setText(listaViviendas.get(position).getConglomerado().toString());
            holder.txtNroSelecVivienda.setText(listaViviendas.get(position).getNroSelecVivienda().toString());
            //holder.txtTipoSelecVivienda.setText(segmentoo);
            holder.txtTipoSelecVivienda.setText(listaViviendas.get(position).getNrosegmento().toString());
            //holder.txtTipoSelecVivienda.setText(UtilsMethods.getTipoSeleccionVivienda(listaViviendas.get(position).getTipoSelecVivienda().toString()));
            holder.txtReemplazoVivienda.setText(UtilsMethods.getViviendaReemplazo(listaViviendas.get(position).getReemplazoVivienda().toString()));
            holder.txtCentroPoblado.setText(listaViviendas.get(position).getCentroPoblado().toString());
            holder.txtPeriodo.setText(listaViviendas.get(position).getPeriodo().toString());
            holder.txtResultado.setText(listaViviendas.get(position).getResultado().toString());
            holder.txtEstado.setText(listaViviendas.get(position).getEstado().toString());
            holder.txtResultado.setVisibility(View.GONE);
            holder.txtEstado.setVisibility(View.GONE);

            String posicion = String.valueOf(position);
            Log.e("CONGLOMERADO:",conglomerado);
          //  String segmento = listaViviendas.get(1).getNrosegmento()+"";

          //  Log.e("SEGMENTO2:",segmentoo);
            Log.e("POSICION2",posicion);
            Log.e("PRUEBADELVACIO","");
            Log.e("PRUEBADEvista",""+vista);
        }

        Log.e("POSICIONafuera",""+position);



    }

    @Override
    public int getItemCount() {
        return listaViviendas.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        CardView cardViewItem;
        CheckBox ckSeleccionado;
        TextView txtNroVivienda;
        TextView txtConglomerado;
        TextView txtNroSelecVivienda;
        TextView txtTipoSelecVivienda;
        TextView txtReemplazoVivienda;
        TextView txtCentroPoblado;
        TextView txtPeriodo;
        TextView txtResultado;
        TextView txtEstado;

        public ViewHolder(View itemView) {
            super(itemView);
            cardViewItem         = (CardView) itemView.findViewById(R.id.vivienda_item_cv);
            ckSeleccionado       = (CheckBox) itemView.findViewById(R.id.vivienda_item_cb_seleccionado);
            txtNroVivienda       = (TextView) itemView.findViewById(R.id.vivienda_item_txt_nroVivienda);
            txtConglomerado      = (TextView) itemView.findViewById(R.id.vivienda_item_txt_conglomerado);
            txtNroSelecVivienda  = (TextView) itemView.findViewById(R.id.vivienda_item_txt_nroSelecVivienda);
            txtTipoSelecVivienda = (TextView) itemView.findViewById(R.id.vivienda_item_txt_tipoSelecVivienda);
            txtReemplazoVivienda = (TextView) itemView.findViewById(R.id.vivienda_item_txt_reemplazoVivienda);
            txtCentroPoblado     = (TextView) itemView.findViewById(R.id.vivienda_item_txt_centroPoblado);
            txtPeriodo           = (TextView) itemView.findViewById(R.id.vivienda_item_txt_periodo);
            txtResultado         = (TextView) itemView.findViewById(R.id.vivienda_item_txt_resultado);
            txtEstado            = (TextView) itemView.findViewById(R.id.vivienda_item_txt_estado);
        }
    }
}
