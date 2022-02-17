package com.example.ricindigus.enpove2021.fragments.hogar;


import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.SQLException;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.text.InputFilter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ricindigus.enpove2021.R;
import com.example.ricindigus.enpove2021.adapters.VisitaSupervisorAdapter;
import com.example.ricindigus.enpove2021.modelo.Data;
import com.example.ricindigus.enpove2021.modelo.SQLConstantes;
import com.example.ricindigus.enpove2021.modelo.pojos.VisitaEncuestador;
import com.example.ricindigus.enpove2021.modelo.pojos.VisitaSupervisor;
import com.example.ricindigus.enpove2021.util.FragmentPagina;
import com.example.ricindigus.enpove2021.util.InputFilterSoloLetras;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentVisitasSupervisor extends FragmentPagina {
    private LinearLayoutManager linearLayoutManager;
    private VisitaSupervisorAdapter visitaSupervisorAdapter;
    private RecyclerView recyclerView;
    private FloatingActionButton btnAgregar;
    private TextView txtFechaFinal;
    private TextView txtResultadoFinal;

    private String idHogar;
    private String idVivienda;
    private Context context;
    private VisitaEncuestador visita;
    private Data dataTablas;
    private Cursor cursor;
    private VisitaSupervisorAdapter.OnItemClickListener onItemClickListener;


    int diaInicio;
    int mesInicio;
    int anioInicio;

    int horaInicio;
    int minutoInicio;
    int horaFin;
    int minutoFin;

    int diaProx;
    int mesProx;
    int anioProx;

    int horaProx;
    int minutoProx;

    private String RESFIN_ID;
    private String RESFIN_DIA;
    private String RESFIN_MES;
    private String RESFIN_ANIO;
    private String RESFIN_MIN;
    private String RESFIN_HORA;
    private int RESFIN;
    private String RESFIN_O;

    public FragmentVisitasSupervisor() {
        // Required empty public constructor
    }

    @SuppressLint("ValidFragment")
    public FragmentVisitasSupervisor(String idHogar, String idVivienda, Context context) {
        this.idHogar = idHogar;
        this.idVivienda = idVivienda;
        this.context = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_visitas_supervisor, container, false);
        recyclerView = (RecyclerView) rootView.findViewById(R.id.visita_recycler);
        txtResultadoFinal = (TextView) rootView.findViewById(R.id.visitas_resultado_final);
        txtFechaFinal = (TextView) rootView.findViewById(R.id.visitas_fecha_final);
        btnAgregar = (FloatingActionButton) rootView.findViewById(R.id.visitas_btnAgregarVisita);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        cargarDatos();
        recyclerView.setHasFixedSize(true);
        linearLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);

        onItemClickListener = new VisitaSupervisorAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, final int pos) {
                cursor.moveToPosition(pos);
                Data dTablas = new Data(context);
                dTablas.open();
                String resultadoVisita = cursor.getString(cursor.getColumnIndex(SQLConstantes.visita_encuestador_vis_resu));
                dTablas.close();
                if(resultadoVisita == null){
                    PopupMenu popupMenu = new PopupMenu(context,view);
                    popupMenu.getMenuInflater().inflate(R.menu.menu_visita,popupMenu.getMenu());
                    popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                        @Override
                        public boolean onMenuItemClick(MenuItem item) {
                            switch(item.getItemId()){
                                case R.id.opcion_editar:
                                            editarVisita(pos);
                                    break;
                                case R.id.opcion_eliminar:
                                            eliminarVisita(pos);
                                    break;
                                case R.id.opcion_finalizar:
                                    finalizarVisita(pos);
                                    break;
                            }
                            return true;
                        }
                    });
                    popupMenu.show();
                }
            }
        };

        try{
            dataTablas = new Data(context);
            dataTablas.open();
            cursor = dataTablas.getCursorVisitas(getIdTablaParte1(), idHogar);
            dataTablas.close();
            if(cursor != null){
                visitaSupervisorAdapter = new VisitaSupervisorAdapter(visita, context, cursor, onItemClickListener);
                recyclerView.setAdapter(visitaSupervisorAdapter);
            }
        }catch (SQLException e){}



        btnAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(cursor.getCount() > 0) {
                    cursor.moveToPosition(cursor.getCount() - 1);
                    if(cursor.getString(cursor.getColumnIndex(SQLConstantes.visita_supervisor_vis_resu)) != null) agregarVisita();
                    else mostrarMensaje("DEBE FINALIZAR LA VISITA ACTUAL, ANTES DE AGREGAR UNA NUEVA");
                }else{agregarVisita();}
            }
        });
    }


    public void agregarVisita(){
        AlertDialog.Builder alert = new AlertDialog.Builder(getActivity());
        final View dialogView = getActivity().getLayoutInflater().inflate(R.layout.dialog_agregar_visita, null);
        final LinearLayout lytDialog = (LinearLayout) dialogView.findViewById(R.id.dialog_agregar_visita_lyt);
        final TextView txtNumero = (TextView) dialogView.findViewById(R.id.dialog_agregar_visita_txtNumero);
        final TextView txtFechaI = (TextView) dialogView.findViewById(R.id.dialog_agregar_visita_txtFI);
        final TextView txtHoraI = (TextView) dialogView.findViewById(R.id.dialog_agregar_visita_txtHI);
        final LinearLayout ly_gps = (LinearLayout) dialogView.findViewById(R.id.gps_ly);
        ly_gps.setVisibility(dialogView.GONE);

        alert.setTitle("AGREGAR VISITA");
        alert.setView(dialogView);
        alert.setPositiveButton("Agregar",null);
        alert.setNegativeButton("Cancelar",null);
        final AlertDialog alertDialog = alert.create();
//        alertDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        alertDialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialogInterface) {
                Calendar c = Calendar.getInstance();
                diaInicio = c.get(Calendar.DAY_OF_MONTH);
                mesInicio = c.get(Calendar.MONTH) + 1;
                anioInicio = c.get(Calendar.YEAR);
                horaInicio = c.get(Calendar.HOUR_OF_DAY);
                minutoInicio = c.get(Calendar.MINUTE);

                txtNumero.setText("VISITA N° " + checkDigito((visitaSupervisorAdapter.getItemCount() + 1)));
                txtFechaI.setText(checkDigito(diaInicio) + "/" + checkDigito(mesInicio) + "/" + checkDigito(anioInicio));
                txtHoraI.setText(checkDigito(horaInicio) + ":" + checkDigito(minutoInicio));
                Button btnAdd = alertDialog.getButton(AlertDialog.BUTTON_POSITIVE);
                btnAdd.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        // TODO Do something
                        boolean valido = true;
                        String mensaje = "";
                        boolean vFechaInicio = true, vHoraInicio = true;
                        if(cursor.getCount() > 0){
                            cursor.moveToPosition(cursor.getCount()-1);
                            int y = Integer.parseInt(cursor.getString(cursor.getColumnIndex(SQLConstantes.visita_supervisor_vis_fecha_aa)));
                            int m = Integer.parseInt(cursor.getString(cursor.getColumnIndex(SQLConstantes.visita_supervisor_vis_fecha_mm)));
                            int d = Integer.parseInt(cursor.getString(cursor.getColumnIndex(SQLConstantes.visita_supervisor_vis_fecha_dd)));

                            int compHora = Integer.parseInt(cursor.getString(cursor.getColumnIndex(SQLConstantes.visita_supervisor_vis_hor_ini)));
                            int compMinuto = Integer.parseInt(cursor.getString(cursor.getColumnIndex(SQLConstantes.visita_supervisor_vis_min_ini)));


                            Date fi1 = new Date(y,m,d);
                            Date fi2 = new Date(anioInicio,mesInicio,diaInicio);
                            String sfi1 = checkDigito(d) + "/" + checkDigito(m) + "/" + checkDigito(y);
                            String sfi2 = checkDigito(diaInicio) + "/" + checkDigito(mesInicio) + "/" + checkDigito(anioInicio);
                            if(fi2.before(fi1)){
                                vFechaInicio = false;
                                if(mensaje.equals("")) mensaje = "FECHA: LA FECHA DE LA NUEVA VISITA NO DEBE SER MENOR A LA VISITA ANTERIOR";
                            }else if(d == diaInicio && m == mesInicio && y == anioInicio){
                                if((horaInicio*60 + minutoInicio) <= (compHora*60+compMinuto)){
                                    vHoraInicio = false;
                                    if(mensaje.equals("")) mensaje = "FECHA: SI LA FECHA ES LA MISMA, LA HORA DE LA NUEVA VISITA NO DEBE SER MENOR O IGUAL A LA VISITA ANTERIOR";
                                }
                            }
                        }
                        valido = vFechaInicio && vHoraInicio;
                        if(valido){
                            ContentValues contentValues = new ContentValues();
                            contentValues.put(SQLConstantes.visita_supervisor_id, idHogar + "visita" + String.valueOf(cursor.getCount()+1));
                            contentValues.put(SQLConstantes.visita_supervisor_id_hogar, idHogar);
                            contentValues.put(SQLConstantes.visita_supervisor_id_vivienda, idVivienda);
                            contentValues.put(SQLConstantes.visita_supervisor_numero,String.valueOf(cursor.getCount()+1));
                            contentValues.put(SQLConstantes.visita_supervisor_vis_fecha_dd,diaInicio+"");
                            contentValues.put(SQLConstantes.visita_supervisor_vis_fecha_mm,mesInicio+"");
                            contentValues.put(SQLConstantes.visita_supervisor_vis_fecha_aa,anioInicio+"");
                            contentValues.put(SQLConstantes.visita_supervisor_vis_hor_ini,horaInicio+"");
                            contentValues.put(SQLConstantes.visita_supervisor_vis_min_ini,minutoInicio+"");
                            Log.e("Diasupervisor", ""+diaInicio);
                            Log.e("Messupervisor", ""+mesInicio);
                            Log.e("Añosupervisor", ""+anioInicio);


                            try{
                                Data dTablas = new Data(context);
                                dTablas.open();
                                dTablas.insertarElemento(getIdTablaParte1(),contentValues);
                                cursor = dTablas.getCursorVisitas(getIdTablaParte1(), idHogar);
                                dTablas.close();
                                if(cursor != null){
                                    visitaSupervisorAdapter = new VisitaSupervisorAdapter(visita, context, cursor, onItemClickListener);
                                    recyclerView.setAdapter(visitaSupervisorAdapter);
                                }
                            }catch (SQLException e){}
//                            recyclerView.getAdapter().notifyDataSetChanged();
                            alertDialog.dismiss();
                        }else Toast.makeText(context, mensaje, Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
        alertDialog.show();
    }

    public void editarVisita(final int posicion){
        AlertDialog.Builder alert = new AlertDialog.Builder(getActivity());
        final View dialogView = getActivity().getLayoutInflater().inflate(R.layout.dialog_agregar_visita, null);
        final LinearLayout lytDialog = (LinearLayout) dialogView.findViewById(R.id.dialog_agregar_visita_lyt);
        final TextView txtNumero = (TextView) dialogView.findViewById(R.id.dialog_agregar_visita_txtNumero);
        final TextView txtFechaI = (TextView) dialogView.findViewById(R.id.dialog_agregar_visita_txtFI);
        final TextView txtHoraI = (TextView) dialogView.findViewById(R.id.dialog_agregar_visita_txtHI);

        alert.setTitle("EDITAR VISITA");
        alert.setView(dialogView);
        alert.setPositiveButton("Guardar",null);
        alert.setNegativeButton("Cancelar",null);
        final AlertDialog alertDialog = alert.create();

        alertDialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialogInterface) {
                cursor.moveToPosition(posicion);
                diaInicio = Integer.parseInt(cursor.getString(cursor.getColumnIndex(SQLConstantes.visita_supervisor_vis_fecha_dd)));
                mesInicio = Integer.parseInt(cursor.getString(cursor.getColumnIndex(SQLConstantes.visita_supervisor_vis_fecha_mm)));
                anioInicio = Integer.parseInt(cursor.getString(cursor.getColumnIndex(SQLConstantes.visita_supervisor_vis_fecha_aa)));
                horaInicio = Integer.parseInt(cursor.getString(cursor.getColumnIndex(SQLConstantes.visita_supervisor_vis_hor_ini)));
                minutoInicio = Integer.parseInt(cursor.getString(cursor.getColumnIndex(SQLConstantes.visita_supervisor_vis_min_ini)));

                txtNumero.setText("VISITA N° " + checkDigito(Integer.parseInt(cursor.getString(cursor.getColumnIndex(SQLConstantes.visita_supervisor_numero)))));
                txtFechaI.setText(checkDigito(diaInicio) + "/" + checkDigito(mesInicio) + "/" + checkDigito(anioInicio));
                txtHoraI.setText(checkDigito(horaInicio) + ":" + checkDigito(minutoInicio));
                Button btnAdd = alertDialog.getButton(AlertDialog.BUTTON_POSITIVE);
                btnAdd.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        // TODO Do something
                        boolean valido =true;
                        String mensaje = "";
                        boolean vFechaInicio = true, vHoraInicio = true;

                        if(cursor.getCount() > 1){
                            cursor.moveToPosition(posicion-1);
                            int d = Integer.parseInt(cursor.getString(cursor.getColumnIndex(SQLConstantes.visita_supervisor_vis_fecha_dd)));
                            int m = Integer.parseInt(cursor.getString(cursor.getColumnIndex(SQLConstantes.visita_supervisor_vis_fecha_mm)));
                            int y = Integer.parseInt(cursor.getString(cursor.getColumnIndex(SQLConstantes.visita_supervisor_vis_fecha_aa)));


                            int compHora = Integer.parseInt(cursor.getString(cursor.getColumnIndex(SQLConstantes.visita_supervisor_vis_hor_ini)));
                            int compMinuto = Integer.parseInt(cursor.getString(cursor.getColumnIndex(SQLConstantes.visita_supervisor_vis_min_ini)));

                            Date fi1 = new Date(y,m,d);
                            Date fi2 = new Date(anioInicio,mesInicio,diaInicio);
                            String sfi1 = checkDigito(d) + "/" + checkDigito(m) + "/" + checkDigito(y);
                            String sfi2 = checkDigito(diaInicio) + "/" + checkDigito(mesInicio) + "/" + checkDigito(anioInicio);

                            if(fi2.before(fi1)){
                                vFechaInicio = false;
                                if(mensaje.equals("")) mensaje = "FECHA: LA FECHA DE LA PROXIMA VISITA NO DEBE SER MENOR A LA VISITA ACTUAL";
                            }else if(d == diaInicio && m == mesInicio && y == anioInicio){
                                if((horaInicio*60 + minutoInicio) <= (compHora*60+compMinuto)){
                                    vHoraInicio = false;
                                    if(mensaje.equals("")) mensaje = "FECHA: SI LA FECHA ES LA MISMA, LA HORA DE LA NUEVA VISITA NO DEBE SER MENOR O IGUAL A LA VISITA ANTERIOR";
                                }
                            }
                        }
                        valido = vFechaInicio && vHoraInicio;
                        if(valido){
                            ContentValues contentValues = new ContentValues();
                            contentValues.put(SQLConstantes.visita_supervisor_vis_fecha_dd,diaInicio);
                            contentValues.put(SQLConstantes.visita_supervisor_vis_fecha_mm,mesInicio);
                            contentValues.put(SQLConstantes.visita_supervisor_vis_fecha_aa,anioInicio);
                            contentValues.put(SQLConstantes.visita_supervisor_vis_hor_ini,horaInicio);
                            contentValues.put(SQLConstantes.visita_supervisor_vis_min_ini,minutoInicio);
                            contentValues.put(SQLConstantes.visita_supervisor_vis_fecha_dd,diaInicio);
                            contentValues.put(SQLConstantes.visita_supervisor_vis_fecha_dd,diaInicio);

                            try{
                                cursor.moveToPosition(posicion);
                                String idVisita = cursor.getString(cursor.getColumnIndex("_id"));
                                dataTablas = new Data(context);
                                dataTablas.open();
                                dataTablas.actualizarElemento(getIdTablaParte1(),contentValues,idVisita);
                                cursor = dataTablas.getCursorVisitas(getIdTablaParte1(), idHogar);
                                dataTablas.close();
                                if(cursor != null){
                                    visitaSupervisorAdapter = new VisitaSupervisorAdapter(visita, context, cursor, onItemClickListener);
                                    recyclerView.setAdapter(visitaSupervisorAdapter);
                                }
                            }catch (SQLException e){}
                            alertDialog.dismiss();
                        }else Toast.makeText(context, mensaje, Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
        alertDialog.show();
    }

    public void eliminarVisita(final int posicion){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage("¿Está seguro que desea eliminar la visita? (no podrá revertir el cambio)")
                .setTitle("Aviso")
                .setCancelable(false)
                .setNegativeButton("No",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        })
                .setPositiveButton("Sí",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                try{
                                    cursor.moveToPosition(posicion);
                                    String idVisita = cursor.getString(cursor.getColumnIndex("_id"));
                                    dataTablas = new Data(context);
                                    dataTablas.open();
                                    dataTablas.eliminarDato(getIdTablaParte1(),idVisita);
                                    cursor = dataTablas.getCursorVisitas(getIdTablaParte1(), idHogar);
                                    dataTablas.close();
                                    if(cursor != null){
                                        visitaSupervisorAdapter = new VisitaSupervisorAdapter(visita, context, cursor, onItemClickListener);
                                        recyclerView.setAdapter(visitaSupervisorAdapter);
                                    }
                                }catch (SQLException e){}
                                getActualizarResultadoFinal();
                            }
                        });
        AlertDialog alert = builder.create();
        alert.show();
    }

    public void finalizarVisita(final int posicion){
        AlertDialog.Builder alert = new AlertDialog.Builder(getActivity());
        final View dialogView = getActivity().getLayoutInflater().inflate(R.layout.dialog_finalizar_visita_supervisor, null);
        final LinearLayout lytDialog = (LinearLayout) dialogView.findViewById(R.id.dialog_finalizar_visita_lyt);
        final TextView txtNumero = (TextView) dialogView.findViewById(R.id.dialog_finalizar_visita_txtNumero);
        final TextView txtHoraF = (TextView) dialogView.findViewById(R.id.dialog_finalizar_visita_txtHoraFin);
        final Spinner spResultado = (Spinner) dialogView.findViewById(R.id.dialog_finalizar_visita_spResultado);
        final EditText edtEspecifique = (EditText) dialogView.findViewById(R.id.dialog_finalizar_visita_edtEspecifique);
        final CardView cardViewEspecifique = (CardView) dialogView.findViewById(R.id.dialog_cardview_finalizar_especifique);

        edtEspecifique.setFilters(new InputFilter[]{new InputFilter.AllCaps(),new InputFilter.LengthFilter(100), new InputFilterSoloLetras()});

        alert.setTitle("FINALIZAR VISITA");
        alert.setView(dialogView);
        alert.setPositiveButton("Finalizar",null);
        alert.setNegativeButton("Cancelar",null);
        final AlertDialog alertDialog = alert.create();

        alertDialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialogInterface) {
                ocultarTeclado(lytDialog);
                Calendar c = Calendar.getInstance();
                diaProx = c.get(Calendar.DAY_OF_MONTH);
                mesProx = c.get(Calendar.MONTH) + 1;
                anioProx = c.get(Calendar.YEAR);
                horaProx = c.get(Calendar.HOUR_OF_DAY);
                minutoProx = c.get(Calendar.MINUTE);
                horaFin = horaProx;
                minutoFin = minutoProx;
                cursor.moveToPosition(posicion);
                txtNumero.setText("VISITA N° " + checkDigito(Integer.parseInt(cursor.getString(cursor.getColumnIndex(SQLConstantes.visita_supervisor_numero)))));
                txtHoraF.setText(checkDigito(horaFin) + ":" + checkDigito(minutoFin));
                Button btnFinalizarVisita = alertDialog.getButton(AlertDialog.BUTTON_POSITIVE);
                btnFinalizarVisita.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        // TODO Do something
                        boolean valido = false;
                        boolean vHoraFin = true, vResultado = true, vEspecifique = true, vFechaProxima = true, vHoraProxima = true;
                        String mensaje = "";
                        cursor.moveToPosition(posicion);
                        int t1 = Integer.parseInt(cursor.getString(cursor.getColumnIndex(SQLConstantes.visita_supervisor_vis_hor_ini)))*60 + Integer.parseInt(cursor.getString(cursor.getColumnIndex(SQLConstantes.visita_supervisor_vis_min_ini)));
                        int t2 = horaFin * 60 + minutoFin;

                        if(t1 >= t2){
                            vHoraFin = false;
                            if(mensaje.equals("")) mensaje = "LA HORA DE FIN DEBE SER MAYOR A LA DE INICIO";
                        }

                        if(spResultado.getSelectedItemPosition() == 0){
                            vResultado = false;
                            if(mensaje.equals("")) mensaje = "DEBE INDICAR EL RESULTADO DE LA VISITA";
                        }

                        valido = vHoraFin && vResultado && vEspecifique && vFechaProxima;

                        if(valido){
                            //actualizo visita con datos de finalizar
                            ContentValues contentValues = new ContentValues();
                            contentValues.put(SQLConstantes.visita_supervisor_vis_resu,String.valueOf(setEstadoResultadoSupervisor(spResultado.getSelectedItemPosition())));
                            contentValues.put(SQLConstantes.visita_supervisor_vis_hor_fin,horaFin);
                            contentValues.put(SQLConstantes.visita_supervisor_vis_min_fin,minutoFin);
                            //falta guardar el especifique del resultado otro

                            try{
                                dataTablas = new Data(context);
                                dataTablas.open();
                                cursor.moveToPosition(posicion);
                                dataTablas.actualizarElemento(getIdTablaParte1(),contentValues,cursor.getString(cursor.getColumnIndex("_id")));
                                cursor = dataTablas.getCursorVisitas(getIdTablaParte1(), idHogar);
                                dataTablas.close();
                                if(cursor != null){
                                    visitaSupervisorAdapter = new VisitaSupervisorAdapter(visita, context, cursor, onItemClickListener);
                                    recyclerView.setAdapter(visitaSupervisorAdapter);
                                }
                            }catch (SQLException e){}

//                            //MUESTRO Y GUARDO DATOS DE RESULTADO FINAL
                            final Calendar cal = Calendar.getInstance();
                            cursor.moveToPosition(posicion);
                            int yy = Integer.parseInt(cursor.getString(cursor.getColumnIndex(SQLConstantes.visita_supervisor_vis_fecha_aa)));
                            int mm = Integer.parseInt(cursor.getString(cursor.getColumnIndex(SQLConstantes.visita_supervisor_vis_fecha_mm)));
                            int dd = Integer.parseInt(cursor.getString(cursor.getColumnIndex(SQLConstantes.visita_supervisor_vis_fecha_dd)));


                            //----nuevo content values final
                            ContentValues contentValuesFinal = new ContentValues();
                            cursor.moveToPosition(posicion);
                            contentValuesFinal.put(SQLConstantes.resultado_supervisor_vis_resultado_final,cursor.getString(cursor.getColumnIndex(SQLConstantes.visita_supervisor_vis_resu)));
                            contentValuesFinal.put(SQLConstantes.resultado_supervisor_vis_resultado_final_o,cursor.getString(cursor.getColumnIndex(SQLConstantes.visita_supervisor_vis_resu_esp)));
                            //FALTA GUARDAR RWESULTADO ESPECIFIQUE DE OTRO
                            contentValuesFinal.put(SQLConstantes.resultado_supervisor_vis_fecha_final_dd,dd);
                            contentValuesFinal.put(SQLConstantes.resultado_supervisor_vis_fecha_final_mm,mm);
                            contentValuesFinal.put(SQLConstantes.resultado_supervisor_vis_fecha_final_aa,yy);
//                            contentValuesFinal.put(visita.getVARRESHORA(),horaFin);
//                            contentValuesFinal.put(visita.getVARRESMIN(),minutoFin);
                            dataTablas = new Data(context);
                            dataTablas.open();
                            if(!dataTablas.existeElemento(getIdTablaParte2(), idHogar)){
                                contentValuesFinal.put(SQLConstantes.resultado_supervisor_id, idHogar);
                                contentValuesFinal.put(SQLConstantes.resultado_supervisor_id_vivienda, idHogar);
                                dataTablas.insertarElemento(getIdTablaParte2(),contentValuesFinal);
                                txtResultadoFinal.setText(getResources().getStringArray(R.array.visita_array_supervisor)[spResultado.getSelectedItemPosition()]);
                                txtFechaFinal.setText(checkDigito(dd) + "/" + checkDigito(mm) + "/" + checkDigito(yy));
                            }else{
                               // int res = Integer.parseInt(dataTablas.getValor(getIdTablaParte2(),SQLConstantes.resultado_supervisor_vis_resultado_final,idHogar));
                               // if(Integer.parseInt(cursor.getString(cursor.getColumnIndex(SQLConstantes.visita_supervisor_vis_resu))) <= res){
                                    dataTablas.actualizarElemento(getIdTablaParte2(),contentValuesFinal, idHogar);
                                    txtResultadoFinal.setText(getResources().getStringArray(R.array.visita_array_supervisor)[spResultado.getSelectedItemPosition()]);
                                    txtFechaFinal.setText(checkDigito(dd) + "/" + checkDigito(mm) + "/" + checkDigito(yy));
                               // }
                            }
                            dataTablas.close();

                            alertDialog.dismiss();
                        }else Toast.makeText(context, mensaje, Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
        alertDialog.show();
    }

    public VisitaSupervisor getUltimoResultadoFinal(){
        ArrayList<VisitaSupervisor> listaVisitas = new ArrayList<>();
        VisitaSupervisor visita = new VisitaSupervisor();
        Data data = new Data(context);
        data.open();
        listaVisitas = data.getAllVisitasSupervisorHogar(idHogar);
        if(listaVisitas.size()>0){
            visita = listaVisitas.get(listaVisitas.size()-1);
        }
        data.close();
        return visita;
    }

    public void getActualizarResultadoFinal(){
        ContentValues contentValuesVisita = new ContentValues();
        contentValuesVisita.put(SQLConstantes.resultado_encuestador_id,getUltimoResultadoFinal().getId_hogar());
        contentValuesVisita.put(SQLConstantes.resultado_encuestador_id_vivienda,getUltimoResultadoFinal().getId_vivienda());
        contentValuesVisita.put(SQLConstantes.resultado_encuestador_vis_resultado_final,getUltimoResultadoFinal().getVis_resu());
        contentValuesVisita.put(SQLConstantes.resultado_encuestador_vis_fecha_final_dd,getUltimoResultadoFinal().getVis_fecha_dd());
        contentValuesVisita.put(SQLConstantes.resultado_encuestador_vis_fecha_final_mm,getUltimoResultadoFinal().getVis_fecha_mm());
        contentValuesVisita.put(SQLConstantes.resultado_encuestador_vis_fecha_final_aa,getUltimoResultadoFinal().getVis_fecha_aa());
        Data data = new Data(context);
        data.open();
        data.actualizarElemento(getIdTablaParte2(),contentValuesVisita, idHogar);
        data.actualizarValor(SQLConstantes.tablahogares,SQLConstantes.hogar_estado,getUltimoResultadoFinal().getVis_resu(),idHogar);
        txtResultadoFinal.setText(getUltimoResultadoFinal().getVis_resu());
        txtResultadoFinal.setText(getResources().getStringArray(R.array.visita_array_resultados)[Integer.parseInt(getUltimoResultadoFinal().getVis_resu())]);
        txtFechaFinal.setText(checkDigito(Integer.parseInt(getUltimoResultadoFinal().getVis_fecha_dd())) + "/" + checkDigito(Integer.parseInt(getUltimoResultadoFinal().getVis_fecha_mm())) + "/" + checkDigito(Integer.parseInt(getUltimoResultadoFinal().getVis_fecha_aa())));
        data.close();
    }

    public String setEstadoResultadoSupervisor(int estado){
        String resultado = "";
        switch (estado){
            case 1:
                resultado="13";break;
            case 2:
                resultado="14";break;
            case 3:
                resultado="15";break;
            case 4:
                resultado="16";break;
        }
        return resultado;
    }

    public int setResultadoSupervisor(int estado){
        int resultado = 0;
        switch (estado){
            case 13:
                resultado=1;break;
            case 14:
                resultado=2;break;
            case 15:
                resultado=3;break;
            case 16:
                resultado=4;break;
        }
        return resultado;
    }

    public boolean coberturaCorrecta(){
        boolean correcto = true;
        return correcto;
    }

//    public boolean tieneVisitas(){
//        boolean correcto = true;
//        if(visitas.size() == 0) correcto = false;
//        return correcto;
//    }
//    public boolean finalizacionCorrecta(){
//        boolean correcto = true;
//        if(visitas.get(visitas.size()-1).getV_RESULTADO().equals("")) correcto = false;
//        return correcto;
//    }

    public void cargarDatos(){
        dataTablas = new Data(context);
        dataTablas.open();
        if(dataTablas.existeElemento(getIdTablaParte2(), idHogar)){
            txtResultadoFinal.setText(getResources().getStringArray(R.array.visita_array_supervisor)[setResultadoSupervisor(Integer.parseInt(dataTablas.getValor(getIdTablaParte2(),SQLConstantes.resultado_supervisor_vis_resultado_final, idHogar)))]);
            txtFechaFinal.setText(
                    checkDigito(Integer.parseInt(dataTablas.getValor(getIdTablaParte2(),SQLConstantes.resultado_supervisor_vis_fecha_final_dd, idHogar))) +
                            "/" + checkDigito(Integer.parseInt(dataTablas.getValor(getIdTablaParte2(),SQLConstantes.resultado_supervisor_vis_fecha_final_mm, idHogar))) +
                            "/" + checkDigito(Integer.parseInt(dataTablas.getValor(getIdTablaParte2(),SQLConstantes.resultado_supervisor_vis_fecha_final_aa, idHogar))));
//            txtHorafinal.setText(
//                    checkDigito(Integer.parseInt(dataTablas.getValor(getIdTablaParte2(),visita.getVARRESHORA(),idHogar))) +
//                            ":" + checkDigito(Integer.parseInt(dataTablas.getValor(getIdTablaParte2(),visita.getVARRESMIN(),idHogar))));
        }

        dataTablas.close();
    }

    @Override
    public void llenarVista() {

    }

    @Override
    public void guardarDatos() {

    }

    @Override
    public void llenarVariables() {

    }

    public boolean validarDatos(){
        boolean valido = true;
        String mensaje = "";
        if(cursor.getCount() > 0){
            cursor.moveToPosition(cursor.getCount()-1);
            if(cursor.getString(cursor.getColumnIndex(SQLConstantes.visita_supervisor_vis_resu)) != null){
                valido =  false;
                mensaje = "DEBE INICIAR UNA VISITA ANTES DE CONTINUAR";
            }
        }else{
            valido =  false;
            mensaje = "DEBE INICIAR UNA VISITA ANTES DE CONTINUAR";
        }

        if(!valido){
            mostrarMensaje(mensaje);
        }
        return valido;
    }

    @Override
    public String getNombreTabla() {
        return null;
    }


    public String checkDigito (int number) {
        return number <= 9 ? "0" + number : String.valueOf(number);
    }
    public void ocultarTeclado(View view){
        InputMethodManager mgr = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        mgr.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
    public void mostrarMensaje(String m){
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(m);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.dismiss();
            }
        });
        final AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
    public String getIdTablaParte1(){
        return SQLConstantes.tablavisitassupervisor;
    }
    public String getIdTablaParte2(){
        return SQLConstantes.tablaresultadosupervisor;
    }

}
