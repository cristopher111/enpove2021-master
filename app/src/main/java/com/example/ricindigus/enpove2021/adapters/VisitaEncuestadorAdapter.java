package com.example.ricindigus.enpove2021.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import com.example.ricindigus.enpove2021.R;
import com.example.ricindigus.enpove2021.modelo.SQLConstantes;
import com.example.ricindigus.enpove2021.modelo.pojos.VisitaEncuestador;

/**
 * Created by otin016 on 28/06/2017.
 */

public class VisitaEncuestadorAdapter extends RecyclerView.Adapter<VisitaEncuestadorAdapter.ViewHolder>{
    Context context;
    OnItemClickListener onItemClickListener;
    CursorAdapter cursorAdapter;
    VisitaEncuestador visita;

    public VisitaEncuestadorAdapter(final VisitaEncuestador visita, Context context, Cursor cursor, OnItemClickListener onItemClickListener) {
        this.visita = visita;
        this.context = context;
        this.onItemClickListener = onItemClickListener;
        this.cursorAdapter = new CursorAdapter(context,cursor,0) {
            @Override
            public View newView(Context context, Cursor cursor, ViewGroup parent) {
                return LayoutInflater.from(parent.getContext()).inflate(R.layout.item_visita_encuestador,parent,false);
            }

            @Override
            public void bindView(View view, Context context, Cursor cursor) {
                TextView txtNumero = (TextView) view.findViewById(R.id.txt_item_visita_numero);
                TextView txtFecha = (TextView) view.findViewById(R.id.txt_item_visita_fecha);
                TextView txtHoraInicio = (TextView) view.findViewById(R.id.txt_item_visita_horai);
                TextView txtHoraFinal = (TextView) view.findViewById(R.id.txt_item_visita_horaf);
                TextView txtResultado = (TextView) view.findViewById(R.id.txt_item_visita_resultado);
                TextView txtFechaProxVisita = (TextView) view.findViewById(R.id.txt_item_visita_fprox);
                TextView txtHoraProxVisita = (TextView) view.findViewById(R.id.txt_item_visita_hprox);

                txtNumero.setText(cursor.getString(cursor.getColumnIndex(SQLConstantes.visita_encuestador_numero)));
                txtFecha.setText(
                        checkDigito(Integer.parseInt(cursor.getString(cursor.getColumnIndex(SQLConstantes.visita_encuestador_vis_fecha_dd))))+
                                "/" + checkDigito(Integer.parseInt(cursor.getString(cursor.getColumnIndex(SQLConstantes.visita_encuestador_vis_fecha_mm)))) +
                                "/" + checkDigito(Integer.parseInt(cursor.getString(cursor.getColumnIndex(SQLConstantes.visita_encuestador_vis_fecha_aa))))
                );
                txtHoraInicio.setText(
                        checkDigito(Integer.parseInt(cursor.getString(cursor.getColumnIndex(SQLConstantes.visita_encuestador_vis_hor_ini)))) +
                                ":"+ checkDigito(Integer.parseInt(cursor.getString(cursor.getColumnIndex(SQLConstantes.visita_encuestador_vis_min_ini))))
                );

                String horafinal = cursor.getString(cursor.getColumnIndex(SQLConstantes.visita_encuestador_vis_hor_fin));
                if(horafinal != null && !horafinal.equals("")) txtHoraFinal.setText(checkDigito(Integer.parseInt(cursor.getString(cursor.getColumnIndex(SQLConstantes.visita_encuestador_vis_hor_fin)))) +
                                ":"+ checkDigito(Integer.parseInt(cursor.getString(cursor.getColumnIndex(SQLConstantes.visita_encuestador_vis_min_fin)))));
                else txtHoraFinal.setText("-:-");

                String resultado = cursor.getString(cursor.getColumnIndex(SQLConstantes.visita_encuestador_vis_resu));
                if(resultado != null && !resultado.equals(""))txtResultado.setText(context.getResources().getStringArray(R.array.visita_array_resultados)[Integer.parseInt(resultado)]);
                else txtResultado.setText("NO FINALIZADO");

                String diaProxV = cursor.getString(cursor.getColumnIndex(SQLConstantes.visita_encuestador_prox_vis_fecha_dd));
                if(diaProxV != null && !diaProxV.equals("")) {
                    txtFechaProxVisita.setText(
                            checkDigito(Integer.parseInt(diaProxV)) +
                                    "/" + checkDigito(Integer.parseInt(cursor.getString(cursor.getColumnIndex(SQLConstantes.visita_encuestador_prox_vis_fecha_mm)))) +
                                    "/" + checkDigito(Integer.parseInt(cursor.getString(cursor.getColumnIndex(SQLConstantes.visita_encuestador_prox_vis_fecha_aa))))
                    );
                    txtHoraProxVisita.setText(
                            checkDigito(Integer.parseInt(cursor.getString(cursor.getColumnIndex(SQLConstantes.visita_encuestador_prox_vis_hor)))) +
                                    ":" + checkDigito(Integer.parseInt(cursor.getString(cursor.getColumnIndex(SQLConstantes.visita_encuestador_prox_vis_min))))
                    );
                }else{
                        txtFechaProxVisita.setText("-/-/-");
                        txtHoraProxVisita.setText("-:-");
                }
            }
        };
    }

    public interface OnItemClickListener {
        public void onItemClick(View view, int i);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View  view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_visita_encuestador,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        cursorAdapter.getCursor().moveToPosition(position);
        cursorAdapter.bindView(holder.itemView,context,cursorAdapter.getCursor());
        holder.cardViewVisita.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onItemClickListener.onItemClick(view,position);
            }
        });
    }


    public static class ViewHolder extends RecyclerView.ViewHolder{
        CardView cardViewVisita;
        public ViewHolder(View itemView) {
            super(itemView);
            cardViewVisita = (CardView) itemView.findViewById(R.id.cardview_visita);
        }
    }

    @Override
    public int getItemCount() {
        return cursorAdapter.getCount();
    }

    public String checkDigito (int number) {
        return number <= 9 ? "0" + number : String.valueOf(number);
    }
}
