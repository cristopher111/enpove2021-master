package com.example.ricindigus.enpove2021.adapters;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import com.example.ricindigus.enpove2021.R;
import com.example.ricindigus.enpove2021.modelo.pojos.ExportarItem;

import java.util.ArrayList;



/**
 * Created by RICARDO on 7/09/2017.
 */

public class ExportarAdapter extends RecyclerView.Adapter<ExportarAdapter.ViewHolder> {
    ArrayList<ExportarItem> exportarItems;
    Context context;
    OnCheckedChangeListener onCheckedChangeListener;

    public interface OnCheckedChangeListener{
        public void onCheckedChanged(CompoundButton compoundButton, boolean b, int pos);
    }

    public ExportarAdapter(ArrayList<ExportarItem> exportarItems, Context context, OnCheckedChangeListener onCheckedChangeListener) {
        this.exportarItems = exportarItems;
        this.context = context;
        this.onCheckedChangeListener = onCheckedChangeListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_exportar,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.ckSeleccionado.setOnCheckedChangeListener(null);
        if(exportarItems.get(position).getSeleccionado() == 0) holder.ckSeleccionado.setChecked(false);
        if(exportarItems.get(position).getSeleccionado() == 1) holder.ckSeleccionado.setChecked(true);
        holder.ckSeleccionado.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                onCheckedChangeListener.onCheckedChanged(compoundButton,b,position);
            }
        });

        holder.txtIdVivienda.setText(exportarItems.get(position).getIdVivienda().toString());
        holder.txtAnio.setText(exportarItems.get(position).getAnio().toString());
        holder.txtMes.setText(exportarItems.get(position).getMes().toString());
        holder.txtPeriodo.setText(exportarItems.get(position).getPeriodo().toString());
       // holder.txtConglomerado.setText(exportarItems.get(position).getConglomerado().toString());
    }

    @Override
    public int getItemCount() {
        return exportarItems.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        CardView cardViewItem;
        CheckBox ckSeleccionado;
        TextView txtIdVivienda;
        TextView txtAnio;
        TextView txtMes;
        TextView txtPeriodo;
        TextView txtConglomerado;


        public ViewHolder(View itemView) {
            super(itemView);
            cardViewItem = (CardView) itemView.findViewById(R.id.exportacion_cardview);
            ckSeleccionado = (CheckBox) itemView.findViewById(R.id.exportacion_ckSeleccionar);
            txtIdVivienda = (TextView) itemView.findViewById(R.id.exportacion_txtId);
            txtAnio =(TextView) itemView.findViewById(R.id.exportacion_txtAnio);
            txtMes = (TextView)itemView.findViewById(R.id.exportacion_txtMes);
            txtPeriodo = (TextView)itemView.findViewById(R.id.exportacion_txtPeriodo);
            txtConglomerado = (TextView)itemView.findViewById(R.id.exportacion_txtConglomerado);
        }
    }
}
