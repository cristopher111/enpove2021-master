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
import com.example.ricindigus.enpove2021.modelo.pojos.M3Pregunta318;

import java.util.ArrayList;


public class M3Pregunta318Adapter extends RecyclerView.Adapter<M3Pregunta318Adapter.ViewHolder>{
    ArrayList<M3Pregunta318> m3Pregunta318s;
    Context context;
    OnItemClickListener onItemClickListener;

    public interface OnItemClickListener {
        public void onItemClick(View view, int position);
    }

    public M3Pregunta318Adapter(ArrayList<M3Pregunta318> m3Pregunta318s, Context context, OnItemClickListener onItemClickListener) {
        this.m3Pregunta318s = m3Pregunta318s;
        this.context = context;
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public M3Pregunta318Adapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.persona_item,parent,false);
        M3Pregunta318Adapter.ViewHolder viewHolder = new M3Pregunta318Adapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(M3Pregunta318Adapter.ViewHolder holder, final int position) {
        holder.txtEdad.setText(String.valueOf(m3Pregunta318s.get(position).getC3_p318_e()));
        if(Integer.parseInt(m3Pregunta318s.get(position).getC3_p318_s()) == 1) holder.txtSexo.setText("HOMBRE");
        else if(Integer.parseInt(m3Pregunta318s.get(position).getC3_p318_s()) == 2) holder.txtSexo.setText("MUJER");
        holder.txtParentesco.setText(context.getResources().getStringArray(R.array.modulo_3_p313_array_parentesco)[Integer.parseInt(m3Pregunta318s.get(position).getC3_p318_f())]);
        if(Integer.parseInt(m3Pregunta318s.get(position).getC3_p318_p()) == 1) holder.txtSino.setText("SÍ");
        else if(Integer.parseInt(m3Pregunta318s.get(position).getC3_p318_p()) == 2) holder.txtSino.setText("NO");
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onItemClickListener.onItemClick(view,position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return m3Pregunta318s.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        TextView txtParentesco;
        TextView txtSexo;
        TextView txtEdad;
        TextView txtSino;
        CardView cardView;

        public ViewHolder(View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.item_persona_cardview);
            txtParentesco = itemView.findViewById(R.id.persona_item_txtParentesco);
            txtSexo = itemView.findViewById(R.id.persona_item_txtSexo);
            txtEdad = itemView.findViewById(R.id.persona_item_txtEdad);
            txtSino = itemView.findViewById(R.id.persona_item_txtSiNo);
        }
    }
}

