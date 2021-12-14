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
import com.example.ricindigus.enpove2021.modelo.pojos.Caratula;
import com.example.ricindigus.enpove2021.util.Evaluacion;
import com.example.ricindigus.enpove2021.util.UtilsMethods;

import java.util.ArrayList;


public class EvaluacionAdapter extends RecyclerView.Adapter<EvaluacionAdapter.ViewHolder>{
    ArrayList<Caratula> lista;
    OnItemClickListener onItemClickListener;
    Context context;

    public interface OnItemClickListener {
        public void onItemClick(View view, int position);
    }

    public EvaluacionAdapter(ArrayList<Caratula> lista, Context context, OnItemClickListener onItemClickListener) {
        this.lista = lista;
        this.onItemClickListener = onItemClickListener;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.residente_evaluacion_item,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        holder.txtNumero.setText(""+position+1);
        holder.txtUsuario.setText(DAOUtils.getUsuarioId(lista.get(position).getUsuario(),context).getUsuario()+"");
        holder.txtNombre.setText(DAOUtils.getUsuarioId(lista.get(position).getUsuario(),context).getNombre()+"");
        holder.txtSeleccionVivienda.setText(lista.get(position).getNro_selec_vivienda());
        holder.txtNroVivienda.setText(lista.get(position).getVivienda());
        holder.txtNota.setText(""+ Evaluacion.getEvaluacion(lista.get(position).get_id(),context));

    }

    @Override
    public int getItemCount() {
        return lista.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        TextView txtNumero;
        TextView txtNombre;
        TextView txtUsuario;
        TextView txtSeleccionVivienda;
        TextView txtNroVivienda;
        TextView txtNota;
        CardView cardView;

        public ViewHolder(View itemView) {
            super(itemView);
            cardView     = itemView.findViewById(R.id.item_cobertura_cardview);
            txtNumero    = itemView.findViewById(R.id.item_evaluacion_numero);
            txtUsuario   = itemView.findViewById(R.id.item_evaluacion_usuario);
            txtNombre    = itemView.findViewById(R.id.item_evaluacion_nombre);
            txtNroVivienda  = itemView.findViewById(R.id.item_evaluacion_nrovivienda);
            txtSeleccionVivienda  = itemView.findViewById(R.id.item_evaluacion_selvivienda);
            txtNota      = itemView.findViewById(R.id.item_evaluacion_nota);
        }
    }



}

