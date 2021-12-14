package com.example.ricindigus.enpove2021.adapters;

/**
 * Created by otin016 on 27/06/2017.
 */

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.ricindigus.enpove2021.R;
import com.example.ricindigus.enpove2021.modelo.pojos.M3Pregunta309;

import java.util.ArrayList;


public class M3Pregunta309Adapter extends RecyclerView.Adapter<M3Pregunta309Adapter.ViewHolder>{
    ArrayList<M3Pregunta309> m3Pregunta309s;
    Context context;
    OnItemClickListener onItemClickListener;

    public interface OnItemClickListener {
        public void onItemClick(View view, int position);
    }

    public M3Pregunta309Adapter(ArrayList<M3Pregunta309> m3Pregunta309s, Context context, OnItemClickListener onItemClickListener) {
        this.m3Pregunta309s = m3Pregunta309s;
        this.context = context;
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.ruta_item,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        holder.txtPais.setText(String.valueOf(m3Pregunta309s.get(position).getC3_p309_p_nom()));
        holder.txtNumero.setText(String.valueOf(m3Pregunta309s.get(position).getNumero()));
        holder.txtCiudad.setText(String.valueOf(m3Pregunta309s.get(position).getC3_p309_c()));
       // holder.txtModoTransito.setText(context.getResources().getStringArray(R.array.modulo_3_p309_array_modo_transito)[Integer.parseInt(m3Pregunta309s.get(position).getC3_p309_mod())]);
        holder.txtFecha.setText(String.valueOf(m3Pregunta309s.get(position).getC3_p309_m() + "/" +String.valueOf(m3Pregunta309s.get(position).getC3_p309_a())));
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onItemClickListener.onItemClick(view,position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return m3Pregunta309s.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        TextView txtPais;
        TextView txtNumero;
        TextView txtCiudad;
        TextView txtModoTransito;
        TextView txtFecha;
        CardView cardView;

        public ViewHolder(View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.item_ruta_cardview);
            txtPais = itemView.findViewById(R.id.txtpais);
            txtCiudad = itemView.findViewById(R.id.txtciudad);
            txtModoTransito = itemView.findViewById(R.id.txtmodotransito);
            txtFecha = itemView.findViewById(R.id.txtfecha);
            txtNumero = itemView.findViewById(R.id.txtnumero);

        }
    }
}

