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
import com.example.ricindigus.enpove2021.modelo.DAOUtils;
import com.example.ricindigus.enpove2021.modelo.pojos.Pregunta;

import java.util.ArrayList;


public class PreguntaAdapter extends RecyclerView.Adapter<PreguntaAdapter.ViewHolder>{
    ArrayList<Pregunta> lista;
    OnItemClickListener onItemClickListener;
    Context context;

    public interface OnItemClickListener {
        public void onItemClick(View view, int position);
    }

    public PreguntaAdapter(ArrayList<Pregunta> lista, Context context, OnItemClickListener onItemClickListener) {
        this.lista = lista;
        this.onItemClickListener = onItemClickListener;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.pregunta_item,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        holder.txtNumero.setText(""+(position+1));
        holder.txtUsuario.setText(DAOUtils.getUsuarioId(lista.get(position).getIdUsuario(),context).getUsuario()+"");
        holder.txtNombre.setText(DAOUtils.getUsuarioId(lista.get(position).getIdUsuario(),context).getNombre()+"");
        holder.txtHora.setText(lista.get(position).getHora());
        holder.txtFecha.setText(lista.get(position).getFecha());
        holder.txtPregunta.setText(lista.get(position).getPregunta());

    }

    @Override
    public int getItemCount() {
        return lista.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        TextView txtNumero;
        TextView txtNombre;
        TextView txtUsuario;
        TextView txtPregunta;
        TextView txtFecha;
        TextView txtHora;
        CardView cardView;

        public ViewHolder(View itemView) {
            super(itemView);
            cardView     = itemView.findViewById(R.id.item_pregunta_cardview);
            txtNumero    = itemView.findViewById(R.id.item_pregunta_numero);
            txtUsuario   = itemView.findViewById(R.id.item_pregunta_usuario);
            txtNombre    = itemView.findViewById(R.id.item_pregunta_nombre);
            txtFecha = itemView.findViewById(R.id.item_pregunta_fecha);
            txtHora = itemView.findViewById(R.id.item_pregunta_hora);
            txtPregunta = itemView.findViewById(R.id.item_pregunta);
        }
    }



}

