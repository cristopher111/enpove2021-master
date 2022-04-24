package com.example.ricindigus.enpove2021.adapters;

/**
 * Created by otin016 on 27/06/2017.
 */

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.ricindigus.enpove2021.R;
import com.example.ricindigus.enpove2021.modelo.pojos.Hogar;

import java.util.ArrayList;


public class HogarAdapter extends RecyclerView.Adapter<HogarAdapter.ViewHolder>{
    ArrayList<Hogar> hogars;
    Context context;
    OnItemClickListener onItemClickListener;

    public interface OnItemClickListener {
        public void onItemClick(View view, int position);
    }

    public HogarAdapter(ArrayList<Hogar> hogars, Context context, OnItemClickListener onItemClickListener) {
        this.hogars = hogars;
        this.context = context;
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.hogar_item,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        holder.txtNumero.setText(hogars.get(position).getNumero());
        holder.txtJefe.setText(hogars.get(position).getNom_ape()+" "+hogars.get(position).getApe_paterno()+" "+hogars.get(position).getApe_materno());
        holder.txtEstado.setText(hogars.get(position).getEstado());
        holder.txtNumeroViven.setText(hogars.get(position).getNroviven());
        if(!hogars.get(position).getEstado().equals("0")){
            if(!hogars.get(position).getEstado().equals("")) {
                try {
                    holder.txtEstado.setText(context.getResources().getStringArray(R.array.visita_array_resultados)[Integer.parseInt(hogars.get(position).getEstado())]);
                } catch (Exception e) {
                    Log.e("Error", "HOGAR"+e.toString());
                }
            }
            else{holder.txtEstado.setText("Sin estado");}
        }else{
            holder.txtEstado.setText("Sin estado");
        }
        if(hogars.get(position).getPrincipal().equals("1")){
            holder.txtPrincipal.setText("SI");
        }else{
            holder.txtPrincipal.setText("NO");
        }
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onItemClickListener.onItemClick(view,position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return hogars.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        TextView txtNumero;
        TextView txtNumeroViven;
        TextView txtJefe;
        TextView txtEstado;
        TextView txtPrincipal;
        CardView cardView;

        public ViewHolder(View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.item_hogar_cardview);
            txtNumero = itemView.findViewById(R.id.item_hogar_textview_numero);
            txtNumeroViven = itemView.findViewById(R.id.item_hogar_textview_numero_viven);
            txtJefe = itemView.findViewById(R.id.item_hogar_textview_jefe);
            txtEstado = itemView.findViewById(R.id.item_hogar_textview_estado);
            txtPrincipal = itemView.findViewById(R.id.item_hogar_textview_principal);
        }
    }
}

