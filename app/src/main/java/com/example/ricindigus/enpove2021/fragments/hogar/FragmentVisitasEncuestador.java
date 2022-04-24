package com.example.ricindigus.enpove2021.fragments.hogar;


import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.SQLException;
import android.graphics.Color;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
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
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.example.ricindigus.enpove2021.R;
import com.example.ricindigus.enpove2021.adapters.HogarMixtoAdapter;
import com.example.ricindigus.enpove2021.adapters.VisitaEncuestadorAdapter;
import com.example.ricindigus.enpove2021.modelo.DAOUtils;
import com.example.ricindigus.enpove2021.modelo.Data;
import com.example.ricindigus.enpove2021.modelo.SQLConstantes;
import com.example.ricindigus.enpove2021.modelo.pojos.Hogar;
import com.example.ricindigus.enpove2021.modelo.pojos.Modulo1H;
import com.example.ricindigus.enpove2021.modelo.pojos.Modulo6;
import com.example.ricindigus.enpove2021.modelo.pojos.ResVisitaEncuestador;
import com.example.ricindigus.enpove2021.modelo.pojos.Residente;
import com.example.ricindigus.enpove2021.modelo.pojos.VisitaEncuestador;

import com.example.ricindigus.enpove2021.util.AppConfiguration;
import com.example.ricindigus.enpove2021.util.FragmentPagina;
import com.example.ricindigus.enpove2021.util.InputFilterSoloLetras;
import com.example.ricindigus.enpove2021.util.UtilsMethods;
import com.example.ricindigus.enpove2021.util.UtilsMethodsInputs;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResult;
import com.google.android.gms.location.LocationSettingsStatusCodes;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentVisitasEncuestador extends FragmentPagina implements GoogleApiClient.OnConnectionFailedListener,GoogleApiClient.ConnectionCallbacks, LocationListener {
    private LinearLayoutManager linearLayoutManager;
    private VisitaEncuestadorAdapter visitaEncuestadorAdapter;
    private RecyclerView recyclerView;
    private FloatingActionButton btnAgregar;
    private TextView txtFechaFinal;
    private TextView txtResultadoFinal;
    private TextView txtNombreUsuario;

    //AGREGADO PARA EL DIALOG DE RECYCLER
    HogarMixtoAdapter hogarmixtoAdapter;
    ArrayList<Residente> residentes;
    ArrayList<Residente> residentesedad;
    ArrayList<Modulo6> modulo6s;
    RecyclerView.LayoutManager layoutManager;
    //ResidenteAdapter residenteAdapter;
    //GPS
    private static final String LOGTAG = "android-localizacion";
    private static final int PETICION_PERMISO_LOCALIZACION = 101;
    private static final int PETICION_CONFIG_UBICACION = 201;
    private GoogleApiClient apiClient;
    private LocationRequest locRequest;

    private View rootView;
    private CardView cvLongitud, cvAltitud, cvLatitud;
    private ToggleButton btnGPS;
    private TextView txtLatitud, txtLongitud, txtAltitud;
    //private RadioGroup Radioingresos;

    private String idHogar;
    private String idVivienda;
    private String idCargo;
    private String user;
    private String idEncuestado;

    private Context context;
    private VisitaEncuestador visita;
    private Data dataTablas;
    private Cursor cursor;
    private VisitaEncuestadorAdapter.OnItemClickListener onItemClickListener;


    int cob;


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

    int estadoRevisar=0;

    ArrayList<VisitaEncuestador> visitas;
    int cant_visitas=0;



    ////VARIABLES PARA MOSTRAR HOGAR MIXTO Y NO MIXTO
    String p638_1_1;
    String p638_2_1;
    String p638_3_1;
    String p638_4_1;

    String p638m_1_2;
    String p638m_2_2;
    String p638m_3_2;
    String p638m_4_2;

    int p638m_1=0;
    int p638m_2=0;
    int p638m_3=0;
    int p638m_4=0;

//////////ING NO LAB//////////////
    String ING_NO_LAB1 ="";
    String ING_NO_LAB2 ="";
    String ING_NO_LAB3 ="";
    String ING_NO_LAB4 ="";

////////////ING DEPENDIENTE
    String ING_DEP_MON ="";
    String ING_DEP_ESP ="";

/////////// ING INDEPENDIENTE
    String ING_INDEP_MON ="";
    String ING_INDEP_ESP ="";

/////////// ING SECUNDARIO
    String ING_SEC_MON ="";
    String ING_SEC_ESP ="";


    int edad = 0;

    int conteo1 = 0;
    int conteo2 = 0;
    int conteoedad = 0;
    int conteonumerorecycler = 0;

    int conteoradiorecycler = 0;

    int conteoMONTOS = 0;

    String migro;
    String p212;
    String p208;
    String radiogroup="";
    String obs="";
    String _id;





    public FragmentVisitasEncuestador() {
        // Required empty public constructor
    }

    @SuppressLint("ValidFragment")
    public FragmentVisitasEncuestador(String idHogar, String idVivienda,GoogleApiClient apiClient, Context context, String idCargo,String user,String idEncuestado) {
        this.idHogar = idHogar;
        this.idVivienda = idVivienda;
        this.apiClient = apiClient;
        this.context = context;
        this.idCargo = idCargo;
        this.user = user;
        this.idEncuestado = idEncuestado;

        Data data = new Data(context);
        data.open();
//        Modulo1V modulo1V = data.getModulo1V(idVivienda);
//        p212 = modulo1V.getC1_p101();

        Hogar hogar =  data.getHogar(idHogar);
        p212 =  hogar.getApe_materno();

        modulo6s = new ArrayList<>();
        residentes = new ArrayList<>();

        residentesedad = new ArrayList<>();
        ((AppConfiguration) context.getApplicationContext()).setResidentesedad(residentesedad);


        cargarDatos1();

        for (Residente r : residentes){
            p208 = r.getC2_p208();
            edad = Integer.parseInt(r.getC2_p205_a());

            if (p208.equals("1") ){
                 conteo1++;
            }
            if(p208.equals("2")){
                conteo2++;

            }
            if(edad >= 5){
                conteoedad++;
                if(p208.equals("2")){
                conteonumerorecycler++;
                r.setNumero(String.valueOf(conteonumerorecycler));
                residentesedad.add(r);}
            }



            Log.e("migro?",""+p208);
            Log.e("edad",""+edad);
        }

        ((AppConfiguration) context.getApplicationContext()).setResidentesedad(residentesedad);

        Log.e("AppConfiguratio","getResisendets().size: "+((AppConfiguration) context.getApplicationContext()).getResidentesedad().size());

        Log.e("conteo1p208",""+conteo1);
        Log.e("conteo2p208",""+conteo2);
        Log.e("conteoedad",""+conteoedad);


        for (Modulo6 m : modulo6s){

            ING_NO_LAB1 = m.getING_NO_LAB1P638();
            ING_NO_LAB2 = m.getING_NO_LAB2P638();
            ING_NO_LAB3 = m.getING_NO_LAB3P638();
            ING_NO_LAB4 = m.getING_NO_LAB4P638();

            ING_DEP_MON = m.getING_DEP_MON621();
            ING_DEP_ESP = m.getING_DEP_ESP621();

            ING_INDEP_MON = m.getING_INDEP_MONP623();
            ING_INDEP_ESP = m.getING_INDEP_ESPP623();

            ING_SEC_MON = m.getING_SEC_MONP624();
            ING_SEC_ESP = m.getING_SEC_ESPP624();



            if(!ING_NO_LAB1.equals("") || !ING_NO_LAB2.equals("") || !ING_NO_LAB3.equals("") || !ING_NO_LAB4.equals("")
                    || !ING_DEP_MON.equals("") || !ING_DEP_ESP.equals("") || !ING_INDEP_MON.equals("") || !ING_INDEP_ESP.equals("")
                    || !ING_SEC_MON.equals("") || !ING_SEC_ESP.equals("")){

                conteoMONTOS++;

            }
            Log.e("CONTEOMONTOS",""+conteoMONTOS);


            Log.e("modulo6_1",""+m.get_id()+":"+ m.getING_NO_LAB1P638());
            Log.e("modulo6_2",""+m.get_id()+":"+ m.getING_NO_LAB2P638());
            Log.e("modulo6_3",""+m.get_id()+":"+ m.getING_NO_LAB3P638());
            Log.e("modulo6_4",""+m.get_id()+":"+ m.getING_NO_LAB4P638());
            Log.e("ING_DEP_MON",""+m.get_id()+":"+ m.getING_DEP_MON621());
            Log.e("ING_DEP_ESP",""+m.get_id()+":"+ m.getING_DEP_ESP621());
            Log.e("ING_INDEP_MON",""+m.get_id()+":"+ m.getING_INDEP_MONP623());
            Log.e("ING_INDEP_ESP",""+m.get_id()+":"+ m.getING_INDEP_ESPP623());
            Log.e("ING_SEC_MON",""+m.get_id()+":"+ m.getING_SEC_MONP624());
            Log.e("ING_SEC_ESP",""+m.get_id()+":"+ m.getING_SEC_ESPP624());
        }







 //       Log.e("idencuestado",""+idEncuestado);

  /*      Residente residente = data.getResidente(idEncuestado);
        Modulo6 modulo6 = data.getModulo6(idEncuestado);





        if(residente == null || modulo6 == null){
            Log.e("null","residente");

        }else{
            edad = residente.getC2_p205_a();
            p638_1_1 = modulo6.getC6_p638_1_frec();
            p638_2_1 = modulo6.getC6_p638_2_frec();
            p638_3_1 = modulo6.getC6_p638_3_frec();
            p638_4_1 = modulo6.getC6_p638_4_frec();
        }*/

  /*      try{
            edad = data.getResidente(idEncuestado).getC2_p205_a();
            p638_1_1 = data.getModulo6(idEncuestado).getC6_p638_1_frec();
            p638_2_1 = data.getModulo6(idEncuestado).getC6_p638_2_frec();
            p638_3_1 = data.getModulo6(idEncuestado).getC6_p638_3_frec();
            p638_4_1 = data.getModulo6(idEncuestado).getC6_p638_4_frec();

        }catch (Exception e){
            edad = "0";
            p638_1_1 = "0";
            p638_2_1 = "0";
            p638_3_1 = "0";
            p638_4_1 = "0";
        }

        Log.e("vamoss",edad);
        Log.e("vamos",p212);


        Log.e("valor1_1",p638_1_1);
        Log.e("valor2_1",p638_2_1);
        Log.e("valor3_1",p638_3_1);
        Log.e("valor4_1",p638_4_1);*/

        visitas = data.getAllVisitasHogar(idHogar);




        double A = 5.7;
        double B = 6.22;

        double C = A + B;

        Log.e("SUMADEDECIMALES",""+C);


        if(visitas!=null) cant_visitas = visitas.size();

        data.close();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_visitas_encuestador, container, false);
        recyclerView = (RecyclerView) rootView.findViewById(R.id.visita_recycler);
        txtResultadoFinal = (TextView) rootView.findViewById(R.id.visitas_resultado_final);
        txtFechaFinal = (TextView) rootView.findViewById(R.id.visitas_fecha_final);
        txtNombreUsuario = (TextView) rootView.findViewById(R.id.txt_entrevistador);
        btnAgregar = (FloatingActionButton) rootView.findViewById(R.id.visitas_btnAgregarVisita);
       // Radioingresos = (RadioGroup)rootView.findViewById(R.id.item_hogarmixto_ingresos);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        txtNombreUsuario.setText("12.ENTREVISTA Y SUPERVISIÓN");

        cob = DAOUtils.getValidacionCoberturaPersona(idHogar,context);
        //Log.e("cobertura0",""+cob);

        residentes = new ArrayList<>();
        //CAMBIADO POR OBS 125
        //txtNombreUsuario.setText("12.ENTREVISTA Y SUPERVISIÓN ( "+DAOUtils.getUsuario(user,context).getNombre()+" )");
        if (idCargo.equals("2")) btnAgregar.setVisibility(View.GONE);
        cargarDatos();
        recyclerView.setHasFixedSize(true);
        linearLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);

        onItemClickListener = new VisitaEncuestadorAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, final int pos) {
                Data data = new Data(context);
                data.open();

                visitas = data.getAllVisitasHogar(idHogar);

                if(visitas!=null) cant_visitas = visitas.size();

                data.close();
                cursor.moveToPosition(pos);
                Data dTablas = new Data(context);
                dTablas.open();
                String resultadoVisita = cursor.getString(cursor.getColumnIndex(SQLConstantes.visita_encuestador_vis_resu));
                if (resultadoVisita == null) resultadoVisita = "";
                dTablas.close();
                if((pos+1)==cant_visitas && !idCargo.equals("2")){
                    if(resultadoVisita==""){
                        PopupMenu popupMenu = new PopupMenu(context,view);
                        popupMenu.getMenuInflater().inflate(R.menu.menu_visita,popupMenu.getMenu());
                        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                            @Override
                            public boolean onMenuItemClick(MenuItem item) {
                                switch(item.getItemId()){
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
                    }else{
                        PopupMenu popupMenu = new PopupMenu(context,view);
                        popupMenu.getMenuInflater().inflate(R.menu.menu_visita2,popupMenu.getMenu());
                        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                            @Override
                            public boolean onMenuItemClick(MenuItem item) {
                                switch(item.getItemId()){
                                    case R.id.opcion_editar:
                                         modificarVisita(pos);
                                        break;
                                    case R.id.opcion_revisar:
                                        estadoRevisar=1;
                                        Toast.makeText(getContext(),"DEBE PRESIONAR SIGUIENTE PARA REVISAR ",Toast.LENGTH_LONG).show();
                                        break;
                                    case R.id.opcion_eliminar:
                                        eliminarVisita(pos);
                                        break;
                                }
                                return true;
                            }
                        });
                        popupMenu.show();
                    }
                }
            }
        };

        try{
            dataTablas = new Data(context);
            dataTablas.open();
            cursor = dataTablas.getCursorVisitas(getIdTablaParte1(), idHogar);
            dataTablas.close();
            if(cursor != null){
                visitaEncuestadorAdapter = new VisitaEncuestadorAdapter(visita, context, cursor, onItemClickListener);
                recyclerView.setAdapter(visitaEncuestadorAdapter);
            }
        }catch (SQLException e){}



        btnAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Data data = new Data(context);
                data.open();
                String resultadoFinal = data.getValor(getIdTablaParte2(),SQLConstantes.resultado_supervisor_vis_resultado_final,idHogar);
                data.close();
                if (!resultadoFinal.equals("1")){
                    if(cursor.getCount() > 0) {
                        cursor.moveToPosition(cursor.getCount() - 1);
                        String resultadoAnterior = cursor.getString(cursor.getColumnIndex(SQLConstantes.visita_encuestador_vis_resu));
                        if( resultadoAnterior != null && !resultadoAnterior.equals("")) agregarVisita();
                        else mostrarMensaje("DEBE FINALIZAR LA VISITA ACTUAL, ANTES DE AGREGAR UNA NUEVA");
                    }else{agregarVisita();}
                }else{
                        mostrarMensaje("LA ENCUESTA YA FINALIZÓ COMPLETA");
                };

            }
        });

        //Construcción cliente API Google
       /* apiClient = new GoogleApiClient.Builder(getActivity().getApplicationContext())
                .enableAutoManage(getActivity(), this)
                .addConnectionCallbacks(this)
                .addApi(LocationServices.API)
                .build();*/
    }


    public void agregarVisita(){
        AlertDialog.Builder alert = new AlertDialog.Builder(getActivity());
        final View dialogView = getActivity().getLayoutInflater().inflate(R.layout.dialog_agregar_visita, null);
        final LinearLayout lytDialog = (LinearLayout) dialogView.findViewById(R.id.dialog_agregar_visita_lyt);
        final TextView txtNumero = (TextView) dialogView.findViewById(R.id.dialog_agregar_visita_txtNumero);
        final TextView txtFechaI = (TextView) dialogView.findViewById(R.id.dialog_agregar_visita_txtFI);
        final TextView txtHoraI  = (TextView) dialogView.findViewById(R.id.dialog_agregar_visita_txtHI);

        cvAltitud = (CardView) dialogView.findViewById(R.id.gps_layout_altitud);
        cvLatitud = (CardView) dialogView.findViewById(R.id.gps_layout_latitud);
        cvLongitud = (CardView) dialogView.findViewById(R.id.gps_layout_longitud);
        txtAltitud = (TextView) dialogView.findViewById(R.id.gps_txt_altitud);
        txtLatitud = (TextView) dialogView.findViewById(R.id.gps_txt_latitud);
        txtLongitud = (TextView) dialogView.findViewById(R.id.gps_txt_longitud);
        btnGPS = (ToggleButton) dialogView.findViewById(R.id.gps_btn_captura);

        btnGPS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toggleLocationUpdates(btnGPS.isChecked());
                if(!btnGPS.isChecked()){
                    txtAltitud.setText("99.999999");
                    txtLatitud.setText("99.999999");
                    txtLongitud.setText("99.999999");
                }
            }
        });


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

                txtNumero.setText("VISITA N° " + checkDigito((visitaEncuestadorAdapter.getItemCount() + 1)));
                txtFechaI.setText(checkDigito(diaInicio) + "/" + checkDigito(mesInicio) + "/" + checkDigito(anioInicio));
                txtHoraI.setText(checkDigito(horaInicio) + ":" + checkDigito(minutoInicio));
                Button btnAdd = alertDialog.getButton(AlertDialog.BUTTON_POSITIVE);
                btnAdd.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        // TODO Do something
                        boolean valido = true;
                        String mensaje = "";
                        boolean vFechaInicio = true, vHoraInicio = true, estado=true;
                        if(cursor.getCount() > 0){
                            cursor.moveToPosition(cursor.getCount()-1);
                            int y = Integer.parseInt(cursor.getString(cursor.getColumnIndex(SQLConstantes.visita_encuestador_vis_fecha_aa)));
                            int m = Integer.parseInt(cursor.getString(cursor.getColumnIndex(SQLConstantes.visita_encuestador_vis_fecha_mm)));
                            int d = Integer.parseInt(cursor.getString(cursor.getColumnIndex(SQLConstantes.visita_encuestador_vis_fecha_dd)));

                            int compHora = Integer.parseInt(cursor.getString(cursor.getColumnIndex(SQLConstantes.visita_encuestador_vis_hor_ini)));
                            int compMinuto = Integer.parseInt(cursor.getString(cursor.getColumnIndex(SQLConstantes.visita_encuestador_vis_min_ini)));

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
                        if(txtLatitud.getText().toString().trim().length() < 3 || txtLongitud.getText().toString().trim().length() < 3){
                            estado = false;
                            if(mensaje.equals("")) mensaje = "DEBE CAPTURAR LATITUD Y LONGITUD";
                        }
                        valido = vFechaInicio && vHoraInicio && estado;
                        if(valido){
                            ContentValues contentValues = new ContentValues();
                            contentValues.put(SQLConstantes.visita_encuestador_id, idHogar + "visita" + String.valueOf(cursor.getCount()+1));
                            contentValues.put(SQLConstantes.visita_encuestador_id_hogar, idHogar);
                            contentValues.put(SQLConstantes.visita_encuestador_id_vivienda, idVivienda);
                            contentValues.put(SQLConstantes.visita_encuestador_numero,String.valueOf(cursor.getCount()+1));
                            contentValues.put(SQLConstantes.visita_encuestador_vis_fecha_dd,diaInicio+"");
                            contentValues.put(SQLConstantes.visita_encuestador_vis_fecha_mm,mesInicio+"");
                            contentValues.put(SQLConstantes.visita_encuestador_vis_fecha_aa,anioInicio+"");
                            contentValues.put(SQLConstantes.visita_encuestador_vis_hor_ini,horaInicio+"");
                            contentValues.put(SQLConstantes.visita_encuestador_vis_min_ini,minutoInicio+"");
                            contentValues.put(SQLConstantes.visita_encuestador_latitud,txtLatitud.getText().toString()+"");
                            contentValues.put(SQLConstantes.visita_encuestador_longitud,txtLongitud.getText().toString()+"");
                            contentValues.put(SQLConstantes.visita_encuestador_altura,"0000");

                            try{
                                Data dTablas = new Data(context);
                                dTablas.open();
                                dTablas.insertarElemento(getIdTablaParte1(),contentValues);
                                cursor = dTablas.getCursorVisitas(getIdTablaParte1(), idHogar);
                                dTablas.close();
                                if(cursor != null){
                                    visitaEncuestadorAdapter = new VisitaEncuestadorAdapter(visita, context, cursor, onItemClickListener);
                                    recyclerView.setAdapter(visitaEncuestadorAdapter);
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
                diaInicio = Integer.parseInt(cursor.getString(cursor.getColumnIndex(SQLConstantes.visita_encuestador_vis_fecha_dd)));
                mesInicio = Integer.parseInt(cursor.getString(cursor.getColumnIndex(SQLConstantes.visita_encuestador_vis_fecha_mm)));
                anioInicio = Integer.parseInt(cursor.getString(cursor.getColumnIndex(SQLConstantes.visita_encuestador_vis_fecha_aa)));
                horaInicio = Integer.parseInt(cursor.getString(cursor.getColumnIndex(SQLConstantes.visita_encuestador_vis_hor_ini)));
                minutoInicio = Integer.parseInt(cursor.getString(cursor.getColumnIndex(SQLConstantes.visita_encuestador_vis_min_ini)));

                txtNumero.setText("VISITA N° " + checkDigito(Integer.parseInt(cursor.getString(cursor.getColumnIndex(SQLConstantes.visita_encuestador_numero)))));
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
                            int d = Integer.parseInt(cursor.getString(cursor.getColumnIndex(SQLConstantes.visita_encuestador_vis_fecha_dd)));
                            int m = Integer.parseInt(cursor.getString(cursor.getColumnIndex(SQLConstantes.visita_encuestador_vis_fecha_mm)));
                            int y = Integer.parseInt(cursor.getString(cursor.getColumnIndex(SQLConstantes.visita_encuestador_vis_fecha_aa)));

                            int compHora = Integer.parseInt(cursor.getString(cursor.getColumnIndex(SQLConstantes.visita_encuestador_vis_hor_ini)));
                            int compMinuto = Integer.parseInt(cursor.getString(cursor.getColumnIndex(SQLConstantes.visita_encuestador_vis_min_ini)));

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
                            contentValues.put(SQLConstantes.visita_encuestador_vis_fecha_dd,diaInicio);
                            contentValues.put(SQLConstantes.visita_encuestador_vis_fecha_mm,mesInicio);
                            contentValues.put(SQLConstantes.visita_encuestador_vis_fecha_aa,anioInicio);
                            contentValues.put(SQLConstantes.visita_encuestador_vis_hor_ini,horaInicio);
                            contentValues.put(SQLConstantes.visita_encuestador_vis_min_ini,minutoInicio);
                            contentValues.put(SQLConstantes.visita_encuestador_vis_fecha_dd,diaInicio);
                            contentValues.put(SQLConstantes.visita_encuestador_vis_fecha_dd,diaInicio);
                            try{
                                cursor.moveToPosition(posicion);
                                String idVisita = cursor.getString(cursor.getColumnIndex("_id"));
                                dataTablas = new Data(context);
                                dataTablas.open();
                                dataTablas.actualizarElemento(getIdTablaParte1(),contentValues,idVisita);
                                cursor = dataTablas.getCursorVisitas(getIdTablaParte1(), idHogar);
                                dataTablas.close();
                                if(cursor != null){
                                    visitaEncuestadorAdapter = new VisitaEncuestadorAdapter(visita, context, cursor, onItemClickListener);
                                    recyclerView.setAdapter(visitaEncuestadorAdapter);
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
        builder.setMessage("¿Está seguro que desea eliminar la visita? (no podrá revertir el cambio)"+posicion)
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
                                if(posicion>0){
                                    try{
                                        cursor.moveToPosition(posicion);
                                        String idVisita = cursor.getString(cursor.getColumnIndex("_id"));
                                        dataTablas = new Data(context);
                                        dataTablas.open();
                                        dataTablas.eliminarDato(getIdTablaParte1(),idVisita);
                                        cursor = dataTablas.getCursorVisitas(getIdTablaParte1(), idHogar);
                                        dataTablas.close();
                                        if(cursor != null){
                                            visitaEncuestadorAdapter = new VisitaEncuestadorAdapter(visita, context, cursor, onItemClickListener);
                                            recyclerView.setAdapter(visitaEncuestadorAdapter);
                                        }
                                    }catch (SQLException e){}
                                    getActualizarResultadoFinal();
                                }else{
                                    try{
                                        cursor.moveToPosition(posicion);
                                        String idVisita = cursor.getString(cursor.getColumnIndex("_id"));
                                        dataTablas = new Data(context);
                                        dataTablas.open();
                                        dataTablas.eliminarDato(getIdTablaParte1(),idVisita);
                                        dataTablas.eliminarDato(getIdTablaParte2(),idHogar);
                                        cursor = dataTablas.getCursorVisitas(getIdTablaParte1(), idHogar);
                                        dataTablas.close();
                                        if(cursor != null){
                                            visitaEncuestadorAdapter = new VisitaEncuestadorAdapter(visita, context, cursor, onItemClickListener);
                                            recyclerView.setAdapter(visitaEncuestadorAdapter);
                                        }
                                    }catch (SQLException e){}
                                    getActualizarEstadoHogar();
                                    txtResultadoFinal.setText("");
                                    txtFechaFinal.setText("");
                                }
                            }
                        });
        AlertDialog alert = builder.create();
        alert.show();
    }

    public void finalizarVisita(final int posicion){
        AlertDialog.Builder alert = new AlertDialog.Builder(getActivity());
        final View dialogView = getActivity().getLayoutInflater().inflate(R.layout.dialog_finalizar_visita_encuestador, null);
        final LinearLayout lytDialog = (LinearLayout) dialogView.findViewById(R.id.dialog_finalizar_visita_lyt);
        final TextView txtNumero = (TextView) dialogView.findViewById(R.id.dialog_finalizar_visita_txtNumero);
        final TextView txtHoraF = (TextView) dialogView.findViewById(R.id.dialog_finalizar_visita_txtHoraFin);
        final Spinner spResultado = (Spinner) dialogView.findViewById(R.id.dialog_finalizar_visita_spResultado);
        final EditText edtEspecifique = (EditText) dialogView.findViewById(R.id.dialog_finalizar_visita_edtEspecifique);
        final CheckBox ckProxVisita = (CheckBox) dialogView.findViewById(R.id.dialog_finalizar_visita_ckProximaVisita);
        final TextView txtFechaProxVisita = (TextView) dialogView.findViewById(R.id.dialog_finalizar_visita_txtFechaProx);
        final TextView txtHoraProxVisita = (TextView) dialogView.findViewById(R.id.dialog_finalizar_visita_txtHoraProx);
        final CardView cardViewEspecifique = (CardView) dialogView.findViewById(R.id.dialog_cardview_finalizar_especifique);
        final CardView cardViewProxVisita = (CardView) dialogView.findViewById(R.id.dialog_cardview_finalizar_proxVisita);
        final CardView cardViewProxFecha= (CardView) dialogView.findViewById(R.id.dialog_cardview_proxFecha);
        final CardView cardViewProxHora = (CardView) dialogView.findViewById(R.id.dialog_cardview_proxHora);
        final RadioGroup rgTipoEntrevista = (RadioGroup) dialogView.findViewById(R.id.dialog_finalizar_visita_rg_tipo_entrevista);
        final EditText   edtTipoEntrevista = (EditText) dialogView.findViewById(R.id.visita_txt_presencial_o);
        String especifique = "";

        cardViewProxVisita.setVisibility(View.GONE);

        cvAltitud = (CardView) dialogView.findViewById(R.id.gps_layout_altitud);
        cvLatitud = (CardView) dialogView.findViewById(R.id.gps_layout_latitud);
        cvLongitud = (CardView) dialogView.findViewById(R.id.gps_layout_longitud);
        txtAltitud = (TextView) dialogView.findViewById(R.id.gps_txt_altitud);
        txtLatitud = (TextView) dialogView.findViewById(R.id.gps_txt_latitud);
        txtLongitud = (TextView) dialogView.findViewById(R.id.gps_txt_longitud);
        btnGPS = (ToggleButton) dialogView.findViewById(R.id.gps_btn_captura);

        btnGPS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toggleLocationUpdates(btnGPS.isChecked());
                if(!btnGPS.isChecked()){
                    txtAltitud.setText("99.999999");
                    txtLatitud.setText("99.999999");
                    txtLongitud.setText("99.999999");
                }
            }
        });

        UtilsMethodsInputs.setupEditText(edtEspecifique,getContext(),1,50);

        edtTipoEntrevista.setFilters(new InputFilter[]{new InputFilter.AllCaps(),new InputFilter.LengthFilter(100), new InputFilterSoloLetras()});



        //cob = DAOUtils.getValidacionCoberturaPersona(idHogar,context);

        spResultado.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long l) {
                if(pos == 2 || pos == 3 || pos == 4 || pos==6){
                    cardViewProxVisita.setVisibility(View.VISIBLE);
                    ckProxVisita.setEnabled(true);
                    ckProxVisita.setChecked(true);
                }else{
                    cardViewProxVisita.setVisibility(View.GONE);
                    ckProxVisita.setChecked(false);
                    ckProxVisita.setEnabled(false);
                }
                if(pos == 9){
                    //if(pos==8){ Toast.makeText(getContext(),"Especifique:¿Cuánto tiempo  permaneció en la vivienda?",Toast.LENGTH_LONG).show();}
                    edtEspecifique.setEnabled(true);
                    cardViewEspecifique.setVisibility(View.VISIBLE);
                }else{
                    if(edtEspecifique.isEnabled()){
                        edtEspecifique.setText("");
                        cardViewEspecifique.setVisibility(View.GONE);
                        edtEspecifique.setEnabled(false);
                    }
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {}
        });

        ckProxVisita.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    txtFechaProxVisita.setEnabled(true);
                    txtHoraProxVisita.setEnabled(true);
                    txtFechaProxVisita.setClickable(true);
                    txtHoraProxVisita.setClickable(true);
                    cardViewProxFecha.setCardBackgroundColor(Color.WHITE);
                    cardViewProxHora.setCardBackgroundColor(Color.WHITE);
                }else{
                    cardViewProxFecha.setCardBackgroundColor(Color.GRAY);
                    cardViewProxHora.setCardBackgroundColor(Color.GRAY);
                    txtFechaProxVisita.setClickable(false);
                    txtHoraProxVisita.setClickable(false);
                    txtFechaProxVisita.setEnabled(false);
                    txtHoraProxVisita.setEnabled(false);
                }
            }
        });

        txtFechaProxVisita.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar calendario = Calendar.getInstance();
                int yy = calendario.get(Calendar.YEAR);
                int mm = calendario.get(Calendar.MONTH);
                int dd = calendario.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePicker = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        diaProx = dayOfMonth;
                        mesProx = monthOfYear + 1;
                        anioProx = year;
                        String fecha = checkDigito(diaProx) +"/"+checkDigito(mesProx)
                                +"/"+checkDigito(anioProx);
                        txtFechaProxVisita.setText(fecha);
                    }
                }, yy, mm, dd);
                datePicker.show();
            }
        });
        txtHoraProxVisita.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar calendario = Calendar.getInstance();
                int hh = calendario.get(Calendar.HOUR_OF_DAY);
                int mm = calendario.get(Calendar.MINUTE);

                TimePickerDialog timePicker = new TimePickerDialog(context, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int hourofDay, int minute) {
                        String hora = checkDigito(hourofDay) +":"+checkDigito(minute);
                        txtHoraProxVisita.setText(hora);
                        horaProx = hourofDay;
                        minutoProx = minute;
                    }
                }, hh, mm,true);
                timePicker.show();
            }
        });

        rgTipoEntrevista.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                controlarEspecifiqueRadio(group, checkedId,3,edtTipoEntrevista);
            }
        });

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
                txtNumero.setText("VISITA N° " + checkDigito(Integer.parseInt(cursor.getString(cursor.getColumnIndex(SQLConstantes.visita_encuestador_numero)))));
                txtHoraF.setText(checkDigito(horaFin) + ":" + checkDigito(minutoFin));
                txtFechaProxVisita.setText(checkDigito(diaProx + 1) + "/" + checkDigito(mesProx) + "/" + checkDigito(anioProx));
                txtHoraProxVisita.setText(checkDigito(horaProx) + ":" + checkDigito(minutoProx));
                Button btnFinalizarVisita = alertDialog.getButton(AlertDialog.BUTTON_POSITIVE);
                btnFinalizarVisita.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        boolean valido = false;
                        boolean vHoraFin = true, vResultado = true, vEspecifique = true, vFechaProxima = true, vHoraProxima = true, estado = true;
                        boolean estadoVerificar = false;
                        String mensaje = "";
                        cursor.moveToPosition(posicion);
                        String anio1 = cursor.getString(cursor.getColumnIndex(SQLConstantes.visita_encuestador_vis_fecha_aa));
                        String mes1  = cursor.getString(cursor.getColumnIndex(SQLConstantes.visita_encuestador_vis_fecha_mm));
                        String dia1  = cursor.getString(cursor.getColumnIndex(SQLConstantes.visita_encuestador_vis_fecha_dd));
                        int t1 = Integer.parseInt(cursor.getString(cursor.getColumnIndex(SQLConstantes.visita_encuestador_vis_hor_ini)))*60 + Integer.parseInt(cursor.getString(cursor.getColumnIndex(SQLConstantes.visita_encuestador_vis_min_ini)));
                        int t2 = horaFin * 60 + minutoFin;
                        if(UtilsMethods.validateFechas(dia1,mes1,anio1) == -1){
                            estado = false;
                            if(mensaje.equals("")) mensaje = "ERROR EN LAS FECHAS,VERIFIQUE LA HORA DEL SISTEMA";
                        }else if(UtilsMethods.validateFechas(dia1,mes1,anio1) == 0 && t1 >= t2) {
                            estado = false;
                            if(mensaje.equals("")) mensaje = "LA HORA DE FIN DEBE SER MAYOR A LA DE INICIO";
                        }
                        /////////ABRIR EL OTRO ALERT DIALOG EN CASO SEA COMPLETO O INCOMPLETA//////
                        if((spResultado.getSelectedItemPosition() == 1) /*&& ING_NO_LAB1.equals("")&& ING_NO_LAB2.equals("")&& ING_NO_LAB3.equals("")
                                && ING_NO_LAB4.equals("") && ING_DEP_MON.equals("") && ING_DEP_ESP.equals("") && ING_INDEP_MON.equals("")  && ING_INDEP_ESP.equals("") && ING_SEC_MON.equals("")
                                && ING_SEC_ESP.equals("")*/ && conteoMONTOS == 0){
                            if((conteo1 > 0 && conteo2 > 0) && conteoedad > 0){
                                HogarMixto(posicion);
                            }else if(conteo2 == 0 && conteo1 > 0){
                                Ingresos(posicion);
                            }
                        }

////////////////////////////////////////////////////////////

                        //VALIDACIONES ANTES DE GUARDADO
                        cursor.moveToPosition(posicion);
                        int y = Integer.parseInt(cursor.getString(cursor.getColumnIndex(SQLConstantes.visita_encuestador_vis_fecha_aa)));
                        int m = Integer.parseInt(cursor.getString(cursor.getColumnIndex(SQLConstantes.visita_encuestador_vis_fecha_mm)));
                        int d = Integer.parseInt(cursor.getString(cursor.getColumnIndex(SQLConstantes.visita_encuestador_vis_fecha_dd)));
                        Date fi1 = new Date(y,m,d);
                        Date fi2 = new Date(anioProx,mesProx,diaProx);
                        /*Validaciones cuando es diferente de completa*/
                        if(spResultado.getSelectedItemPosition() != 1){
                            if(spResultado.getSelectedItemPosition() == 0){
                                estado = false;
                                if(mensaje.equals("")) mensaje = "DEBE INDICAR EL RESULTADO DE LA VISITAS";
//                            }else if(spResultado.getSelectedItemPosition() == 4 && !ckProxVisita.isChecked()){
//                                estado = false;
//                                if(mensaje.equals("")) mensaje = "DEBE REGISTRAR INFORMACION DE LA PROXIMA VISITA";
                            }else if(edtEspecifique.isEnabled() && edtEspecifique.getText().toString().trim().length() < 3){
                                estado = false;
                                if(mensaje.equals("")) mensaje = "RESULTADO DE VISITA: ESPECIFIQUE NO DEBE SER MENOR A 3 CARACTERES";
                            }else if(ckProxVisita.isChecked() && fi2.before(fi1)){
                                estado = false;
                                if(mensaje.equals("")) mensaje = "FECHA: LA FECHA DE LA PROXIMA VISITA NO DEBE SER MENOR A LA VISITA ACTUAL";
                           }else if(rgTipoEntrevista.indexOfChild(rgTipoEntrevista.findViewById(rgTipoEntrevista.getCheckedRadioButtonId()))== -1){
                                estado = false;
                                if(mensaje.equals("")) mensaje = "DEBE INDICAR EL TIPO DE ENTREVISTA";
                            }else if (rgTipoEntrevista.indexOfChild(rgTipoEntrevista.findViewById(rgTipoEntrevista.getCheckedRadioButtonId()))== 3 && edtTipoEntrevista.getText().toString().trim().length() < 3){
                                estado = false;
                                if(mensaje.equals("")) mensaje = "TIPO DE ENTREVISTA: ESPECIFIQUE NO DEBE SER MENOR A 3 CARACTERES";
                            }else if(txtLatitud.getText().toString().trim().length() < 3 || txtLongitud.getText().toString().trim().length() < 3){
                                estado = false;
                                if(mensaje.equals("")) mensaje = "DEBE CAPTURAR LATITUD Y LONGITUD";
                            }else if(spResultado.getSelectedItemPosition()== 4 && rgTipoEntrevista.indexOfChild(rgTipoEntrevista.findViewById(rgTipoEntrevista.getCheckedRadioButtonId()))== 2){
                                estado = false;
                                if(mensaje.equals("")) mensaje = "LA ENTREVISTA SE REALIZO POR TELEFONO ENTONCES NO DEBE SER AUSENTE RESULTADO DE VISITA";
                            }else if(!getHogar().getP15_o().equals(getHogar().getP17())){
                                estadoVerificar = true;
                                if(mensaje.equals("")) mensaje = "EL NUMERO DE PERSONA QUE LLEGARON DE VENEZUELA (P15) NO COINCIDE CON LOS REGISTRADOS EN EL CAPITULO 200";
                            }
                        }
                        /*Validaciones cuando es completa*/
                        if(spResultado.getSelectedItemPosition() == 1){
                            Log.e("cobertura1",""+cob);
                            if(rgTipoEntrevista.indexOfChild(rgTipoEntrevista.findViewById(rgTipoEntrevista.getCheckedRadioButtonId()))== -1){
                                estado = false;
                                if(mensaje.equals("")) mensaje = "DEBE INDICAR EL TIPO DE ENTREVISTA";
                            }else if (rgTipoEntrevista.indexOfChild(rgTipoEntrevista.findViewById(rgTipoEntrevista.getCheckedRadioButtonId()))== 3 && edtTipoEntrevista.getText().toString().trim().length() < 3){
                                estado = false;
                                if(mensaje.equals("")) mensaje = "DEBE ESPECIFICAR EL TIPO DE ENTREVISTA";
                            }else if(txtLatitud.getText().toString().trim().length() < 3 || txtLongitud.getText().toString().trim().length() < 3){
                                estado = false;
                                if(mensaje.equals("")) mensaje = "DEBE CAPTURAR LATITUD Y LONGITUD";
                            }else if(!getHogar().getP15_o().equals(getHogar().getP17())){
                                estado = false;
                                if(mensaje.equals("")) mensaje = "EL NUMERO DE PERSONA QUE LLEGARON DE VENEZUELA (P15) NO COINCIDE CON LOS REGISTRADOS EN EL CAPITULO 200";
                            }else if(cob>0){
                                estado = false;
                                Log.e("cobertura2",""+cob);
                                //Toast.makeText(context,"FALTA COMPLETAR ALGUN CAPITULO DE LAS PERSONAS QUE MIGRARON DE VENEZUELA",Toast.LENGTH_LONG).show();
                                if(mensaje.equals("")) mensaje = "FALTA COMPLETAR ALGUN CAPITULO DE LAS PERSONAS QUE MIGRARON DE VENEZUELA";
                            }else if(getHogar().getP15().equals("")){
                                estado = false;
                                if(mensaje.equals("")) mensaje = "FALTA COMPLETAR PREGUNTAS (P15) EN LA SIGUIENTE PANTALLA";
                            }else if(getHogar().getP15().equals("2")){
                                estado = false;
                                if(mensaje.equals("")) mensaje = "EL RESULTADO NO PUEDE SER 'COMPLETA' YA QUE NO EXISTE NINGUN RESIDENTE VENEZOLANO";
                            }
                        }

                        valido = estado;

                        if(valido){
                                //CONTENVALUES DE VISITA_ENCUESTADOR
                                ContentValues contentValues = new ContentValues();
                                contentValues.put(SQLConstantes.visita_encuestador_vis_resu,String.valueOf(spResultado.getSelectedItemPosition()));
                                if(edtEspecifique.isEnabled()){
                                    contentValues.put(SQLConstantes.visita_encuestador_vis_resu_esp,edtEspecifique.getText().toString());
                                }else{contentValues.put(SQLConstantes.visita_encuestador_vis_resu_esp,"");}
                                contentValues.put(SQLConstantes.visita_encuestador_vis_hor_fin,horaFin);
                                contentValues.put(SQLConstantes.visita_encuestador_vis_min_fin,minutoFin);
                                //falta guardar el especifique del resultado otro
                                if(ckProxVisita.isChecked()){
                                    contentValues.put(SQLConstantes.visita_encuestador_prox_vis_fecha_dd,checkDigito(diaProx));
                                    contentValues.put(SQLConstantes.visita_encuestador_prox_vis_fecha_mm,checkDigito(mesProx));
                                    contentValues.put(SQLConstantes.visita_encuestador_prox_vis_fecha_aa,checkDigito(anioProx));
                                    contentValues.put(SQLConstantes.visita_encuestador_prox_vis_hor,checkDigito(horaProx));
                                    contentValues.put(SQLConstantes.visita_encuestador_prox_vis_min,checkDigito(minutoProx));
                                }else{
                                    contentValues.put(SQLConstantes.visita_encuestador_prox_vis_fecha_dd,"");
                                    contentValues.put(SQLConstantes.visita_encuestador_prox_vis_fecha_mm,"");
                                    contentValues.put(SQLConstantes.visita_encuestador_prox_vis_fecha_aa,"");
                                    contentValues.put(SQLConstantes.visita_encuestador_prox_vis_hor,"");
                                    contentValues.put(SQLConstantes.visita_encuestador_prox_vis_min,"");
                                }
                                contentValues.put(SQLConstantes.visita_encuestador_tipo_entrevista,rgTipoEntrevista.indexOfChild(rgTipoEntrevista.findViewById(rgTipoEntrevista.getCheckedRadioButtonId())));
                                if(edtTipoEntrevista.isEnabled()){
                                    contentValues.put(SQLConstantes.visita_encuestador_tipo_entrevista_o,edtTipoEntrevista.getText().toString());
                                }else{contentValues.put(SQLConstantes.visita_encuestador_tipo_entrevista_o,"");}
                                contentValues.put(SQLConstantes.visita_encuestador_latitud,txtLatitud.getText().toString()+"");
                                contentValues.put(SQLConstantes.visita_encuestador_longitud,txtLongitud.getText().toString()+"");

                                try{
                                    dataTablas = new Data(context);
                                    dataTablas.open();
                                    cursor.moveToPosition(posicion);
                                    //actualiza la visita con los datos de finalizacion
                                    dataTablas.actualizarElemento(getIdTablaParte1(),contentValues,cursor.getString(cursor.getColumnIndex("_id")));
                                    //recupero un cursor con informacion d ela ultima visita guardada
                                    cursor = dataTablas.getCursorVisitas(getIdTablaParte1(), idHogar);
                                    dataTablas.close();
                                    if(cursor != null){
                                        //seteo adapter
                                        visitaEncuestadorAdapter = new VisitaEncuestadorAdapter(visita, context, cursor, onItemClickListener);
                                        recyclerView.setAdapter(visitaEncuestadorAdapter);
                                    }
                                }catch (SQLException e){}

//                            //CONTENVALUES DE VISITA RESULTADO FINAL
                                final Calendar cal = Calendar.getInstance();
                                cursor.moveToPosition(posicion);
                                //obtengo la fecha de la ultima visita
                                int yy = Integer.parseInt(cursor.getString(cursor.getColumnIndex(SQLConstantes.visita_encuestador_vis_fecha_aa)));
                                int mm = Integer.parseInt(cursor.getString(cursor.getColumnIndex(SQLConstantes.visita_encuestador_vis_fecha_mm)));
                                int dd = Integer.parseInt(cursor.getString(cursor.getColumnIndex(SQLConstantes.visita_encuestador_vis_fecha_dd)));

                                ContentValues contentValuesFinal = new ContentValues();
                                cursor.moveToPosition(posicion);
                                contentValuesFinal.put(SQLConstantes.resultado_encuestador_vis_resultado_final,cursor.getString(cursor.getColumnIndex(SQLConstantes.visita_encuestador_vis_resu)));
                                contentValuesFinal.put(SQLConstantes.resultado_encuestador_vis_resultado_final_o,cursor.getString(cursor.getColumnIndex(SQLConstantes.visita_encuestador_vis_resu_esp)));
                                //FALTA GUARDAR RESULTADO ESPECIFIQUE DE OTRO
                                contentValuesFinal.put(SQLConstantes.resultado_encuestador_vis_fecha_final_dd,dd);
                                contentValuesFinal.put(SQLConstantes.resultado_encuestador_vis_fecha_final_mm,mm);
                                contentValuesFinal.put(SQLConstantes.resultado_encuestador_vis_fecha_final_aa,yy);
                                dataTablas = new Data(context);
                                dataTablas.open();
                                if(!dataTablas.existeElemento(getIdTablaParte2(), idHogar)){
                                    contentValuesFinal.put(SQLConstantes.resultado_encuestador_id, idHogar);
                                    contentValuesFinal.put(SQLConstantes.resultado_encuestador_id_vivienda, idVivienda);
                                    dataTablas.insertarElemento(getIdTablaParte2(),contentValuesFinal);

                                    txtResultadoFinal.setText(getResources().getStringArray(R.array.visita_array_resultados)[spResultado.getSelectedItemPosition()]);
                                    txtFechaFinal.setText(checkDigito(dd) + "/" + checkDigito(mm) + "/" + checkDigito(yy));
                                    dataTablas.actualizarValor(SQLConstantes.tablahogares, SQLConstantes.hogar_estado, spResultado.getSelectedItemPosition()+"", idHogar);
                                    dataTablas.actualizarValor(SQLConstantes.tablacaratula, SQLConstantes.caratula_resultado_final, updateResultadoVivienda()+"", idVivienda);
                                }else{
                                        dataTablas.actualizarElemento(getIdTablaParte2(),contentValuesFinal, idHogar);
                                        txtResultadoFinal.setText(getResources().getStringArray(R.array.visita_array_resultados)[spResultado.getSelectedItemPosition()]);
                                        txtFechaFinal.setText(checkDigito(dd) + "/" + checkDigito(mm) + "/" + checkDigito(yy));
                                        dataTablas.actualizarValor(SQLConstantes.tablahogares,SQLConstantes.hogar_estado,spResultado.getSelectedItemPosition()+"",idHogar);
                                        dataTablas.actualizarValor(SQLConstantes.tablacaratula, SQLConstantes.caratula_resultado_final, updateResultadoVivienda()+"", idVivienda);
                                }
                                //ACTUALIZA EL ESTADO DEL HOGAR
                                //dataTablas.actualizarValor(SQLConstantes.tablahogares, SQLConstantes.hogar_estado, dataTablas.getValor(getIdTablaParte2(), SQLConstantes.resultado_supervisor_vis_resultado_final,idHogar), idHogar);
                                //ACTUALIZA COBERTURA
                                //dataTablas.actualizarValor(SQLConstantes.tablahogares, SQLConstantes.hogar_cobertura, cobertura, idHogar);
                                //ACTUALIZAR RESULTADO DE VIVIENDA

                                dataTablas.close();
                                showMensaje(estadoVerificar,mensaje);
                                alertDialog.dismiss();
                        }else {
                            showMensaje(true,mensaje);
                        }
                    }
                });
            }
        });
        alertDialog.show();
    }
/////////////////////////ALERT DIALOG  VARIABLE P200_OBS_INGRESOS///////////////////////////////

    public void Ingresos(final int posicion){
        AlertDialog.Builder alert = new AlertDialog.Builder(getActivity());
        final View dialogView = getActivity().getLayoutInflater().inflate(R.layout.dialog_p200_obs, null);
        final LinearLayout lytDialog = (LinearLayout) dialogView.findViewById(R.id.dialog_p200_obs_ingresos);
        final EditText edtObsIngresos = (EditText) dialogView.findViewById(R.id.edtObservaciones_p200_ingresos);
        /////

        UtilsMethodsInputs.setupEditText(edtObsIngresos,getContext(),4,2000);

        alert.setView(dialogView);
        alert.setPositiveButton("Finalizar",null);
        alert.setNegativeButton("Cancelar",null);
        final AlertDialog alertDialog = alert.create();

        alertDialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialogInterface) {

                ocultarTeclado(lytDialog);
                cursor.moveToPosition(posicion);


                Button btnFinalizarIngreso = alertDialog.getButton(AlertDialog.BUTTON_POSITIVE);
                btnFinalizarIngreso.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        dataTablas = new Data(context);
                        dataTablas.open();
                        Modulo1H modulo1H = dataTablas.getModulo1H(idHogar);
                        //edtObsIngresos.setText(modulo1H.getCOB100B()+"\n");


                        String mensaje = modulo1H.getCOB100B();
                        Log.e("OBS100APORTANTE",mensaje);
                        dataTablas.close();


                        obs = edtObsIngresos.getText().toString();
                        String obs100 = modulo1H.getCOB100B()+"\n";

                        if(obs.equals("")){
                            mostrarMensaje( "DEBE INFORMAR COMO LOGRAN CUBRIR SUS GASTOS DEL HOGAR");

                        }else{
                            dataTablas = new Data(context);
                            dataTablas.open();
                            ContentValues contentValues = new ContentValues();
                            contentValues.put(SQLConstantes.modulo1_h_COB100B,obs100 + edtObsIngresos.getText().toString());
                            dataTablas.actualizarElemento(SQLConstantes.tablamodulo1h,contentValues,idHogar);
                            dataTablas.close();
                            alertDialog.dismiss();
                            mostrarMensaje("INFORMACION GUARDADA");
                        }

                    }
                });

            }

        });
        alertDialog.show();
        alertDialog.getWindow().setLayout(1700,900);

    }

    public void HogarMixto(final int posicion){
        AlertDialog.Builder alert = new AlertDialog.Builder(getActivity());
        final View dialogView = getActivity().getLayoutInflater().inflate(R.layout.dialog_p200aportante, null);
        final LinearLayout lytDialog = (LinearLayout) dialogView.findViewById(R.id.dialog_p200aportante_ingresos);
        final RecyclerView recyclerView = (RecyclerView) dialogView.findViewById(R.id.hogarmixto_recyclerview);
        final RadioGroup radioGroup = (RadioGroup) dialogView.findViewById(R.id.item_hogarmixto_ingresos);



        residentes = new ArrayList<>();
        recyclerView.setHasFixedSize(true);

        //linearLayoutManager = new LinearLayoutManager(getActivity());
        layoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(layoutManager);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        cargarDatos1();

       //Log.e("residentessize",""+residentesedad.size());
         hogarmixtoAdapter = new HogarMixtoAdapter(residentesedad,context);
        recyclerView.setAdapter(hogarmixtoAdapter);

        alert.setView(dialogView);
        alert.setPositiveButton("Finalizar",null);
        alert.setNegativeButton("Cancelar",null);
        final AlertDialog alertDialog = alert.create();

        alertDialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialogInterface) {

                ocultarTeclado(lytDialog);
                cursor.moveToPosition(posicion);


                Button btnFinalizarIngreso = alertDialog.getButton(AlertDialog.BUTTON_POSITIVE);
                btnFinalizarIngreso.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                      //  String radio = radioGroup.indexOfChild(radioGroup.findViewById(radioGroup.getCheckedRadioButtonId()))+"";
                        //Log.e("radiovalor",""+radio);
                            conteoradiorecycler = 0; //TOTAL MARCADOS RADIOS = 1
                            int value = 0; //TOTAL FILAS = 2


                            for (Residente r : ((AppConfiguration) context.getApplicationContext()).getResidentesedad()) {
                                value++;
                            }

                            for (Residente r : ((AppConfiguration) context.getApplicationContext()).getResidentesedad()) {

                                radiogroup = r.p200_aportante;


                                _id = r.get_id();

                                if(radiogroup == null){
                                    radiogroup = "";
                                }

                                if(radiogroup.equals("1") || radiogroup.equals("2")){
                                    conteoradiorecycler++;
                                }

                                Log.e("conteoradiorecycler",""+conteoradiorecycler);


                               /* if (!radiogroup.equals("") && conteoradiorecycler == value ){

                                    Data data = new Data(context);
                                    data.open();
                                    ContentValues contentValues = new ContentValues();
                                    contentValues.put(SQLConstantes.residentes_p200_aportante, radiogroup);
                                    data.actualizarElemento(SQLConstantes.tablaresidentes, contentValues, _id);


                                    Log.e("GUARDADO", "" + r.getP200_aportante());
                                    Log.e("NOMBRERESIDENTE", "" + r.getC2_p202());


                                    data.close();
                                    mostrarMensaje("INFORMACION DE LOS HOGARES SIN INGRESOS GUARDADA");
                                    alertDialog.dismiss();

                                }else{
                                    mostrarMensaje("DEBE INFORMAR SI TIENEN INGRESOS LOS MIEMBROS DEL HOGAR NO MIGRANTES");
                                }     */

                            }

                        if (!radiogroup.equals("") && conteoradiorecycler == value ){

                        Data data = new Data(context);
                        data.open();
                        ContentValues contentValues = new ContentValues();
                        contentValues.put(SQLConstantes.residentes_p200_aportante, radiogroup);
                        data.actualizarElemento(SQLConstantes.tablaresidentes, contentValues, _id);

                     //   Log.e("GUARDADO", "" + r.getP200_aportante());
                     //   Log.e("NOMBRERESIDENTE", "" + r.getC2_p202());
                        data.close();
                        mostrarMensaje("INFORMACION DE LOS HOGARES SIN INGRESOS GUARDADA");
                        alertDialog.dismiss();
                        }else{
                            mostrarMensaje("DEBE INFORMAR SI TIENEN INGRESOS LOS MIEMBROS DEL HOGAR NO MIGRANTES");
                        }
                    }
                });
            }
        });
        alertDialog.show();
        alertDialog.getWindow().setLayout(1700,900);

    }

    private void cargarDatos1() {
        residentes = new ArrayList<>();
        Data data =  new Data(context);
        data.open();
        residentes = data.getAllResidentesHogar(idHogar);
        modulo6s = data.getAllModulo6Hogar(idHogar);
        data.close();
    }

//    private void cargarDatos1() {
//        Data data = new Data(context);
//        data.open();
//        if (data.existeElemento(SQLConstantes.tablahogares,idHogar)){
//            Hogar hogar = data.getHogar(idHogar);
//
//        }
//        data.close();
//    }


    public void modificarVisita(final int posicion){
        AlertDialog.Builder alert = new AlertDialog.Builder(getActivity());
        final View dialogView = getActivity().getLayoutInflater().inflate(R.layout.dialog_finalizar_visita_encuestador, null);
        final LinearLayout lytDialog = (LinearLayout) dialogView.findViewById(R.id.dialog_finalizar_visita_lyt);
        final TextView txtNumero = (TextView) dialogView.findViewById(R.id.dialog_finalizar_visita_txtNumero);
        final TextView txtHoraF = (TextView) dialogView.findViewById(R.id.dialog_finalizar_visita_txtHoraFin);
        final Spinner spResultado = (Spinner) dialogView.findViewById(R.id.dialog_finalizar_visita_spResultado);
        final EditText edtEspecifique = (EditText) dialogView.findViewById(R.id.dialog_finalizar_visita_edtEspecifique);
        final CheckBox ckProxVisita = (CheckBox) dialogView.findViewById(R.id.dialog_finalizar_visita_ckProximaVisita);
        final TextView txtFechaProxVisita = (TextView) dialogView.findViewById(R.id.dialog_finalizar_visita_txtFechaProx);
        final TextView txtHoraProxVisita = (TextView) dialogView.findViewById(R.id.dialog_finalizar_visita_txtHoraProx);
        final CardView cardViewEspecifique = (CardView) dialogView.findViewById(R.id.dialog_cardview_finalizar_especifique);
        final CardView cardViewProxVisita = (CardView) dialogView.findViewById(R.id.dialog_cardview_finalizar_proxVisita);
        final CardView cardViewProxFecha= (CardView) dialogView.findViewById(R.id.dialog_cardview_proxFecha);
        final CardView cardViewProxHora = (CardView) dialogView.findViewById(R.id.dialog_cardview_proxHora);
        final RadioGroup rgTipoEntrevista = (RadioGroup) dialogView.findViewById(R.id.dialog_finalizar_visita_rg_tipo_entrevista);
        final EditText   edtTipoEntrevista = (EditText) dialogView.findViewById(R.id.visita_txt_presencial_o);
        String especifique = "";

        cardViewProxVisita.setVisibility(View.GONE);

        cvAltitud = (CardView) dialogView.findViewById(R.id.gps_layout_altitud);
        cvLatitud = (CardView) dialogView.findViewById(R.id.gps_layout_latitud);
        cvLongitud = (CardView) dialogView.findViewById(R.id.gps_layout_longitud);
        txtAltitud = (TextView) dialogView.findViewById(R.id.gps_txt_altitud);
        txtLatitud = (TextView) dialogView.findViewById(R.id.gps_txt_latitud);
        txtLongitud = (TextView) dialogView.findViewById(R.id.gps_txt_longitud);
        btnGPS = (ToggleButton) dialogView.findViewById(R.id.gps_btn_captura);

        btnGPS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toggleLocationUpdates(btnGPS.isChecked());
                if(!btnGPS.isChecked()){
                    txtAltitud.setText("99.999999");
                    txtLatitud.setText("99.999999");
                    txtLongitud.setText("99.999999");
                }
            }
        });

        UtilsMethodsInputs.setupEditText(edtEspecifique,getContext(),1,50);

        edtTipoEntrevista.setFilters(new InputFilter[]{new InputFilter.AllCaps(),new InputFilter.LengthFilter(100), new InputFilterSoloLetras()});

        spResultado.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long l) {
                if(pos == 2 || pos == 3 || pos == 4 || pos == 6){
                    cardViewProxVisita.setVisibility(View.VISIBLE);
                    //ckProxVisita.setEnabled(true);
                    //ckProxVisita.setChecked(true);
                }else{
                    cardViewProxVisita.setVisibility(View.GONE);
                    ckProxVisita.setChecked(false);
                    //ckProxVisita.setEnabled(false);
                    txtFechaProxVisita.setText("");
                    txtHoraProxVisita.setText("");
                }
                if(pos == 9 ){ ///////SE QUITO || POS == 8 31/12/21
                    if(pos==9){ Toast.makeText(getContext(),"Especifique:¿Cuánto tiempo  permaneció en la vivienda?",Toast.LENGTH_LONG).show();} //SE CAMBIO POS==8 POR POS==9
                    edtEspecifique.setEnabled(true);
                    cardViewEspecifique.setVisibility(View.VISIBLE);
                }else{
                    if(edtEspecifique.isEnabled()){
                        edtEspecifique.setText("");
                        cardViewEspecifique.setVisibility(View.GONE);
                        edtEspecifique.setEnabled(false);
                    }
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {}
        });

        ckProxVisita.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    txtFechaProxVisita.setEnabled(true);
                    txtHoraProxVisita.setEnabled(true);
                    txtFechaProxVisita.setClickable(true);
                    txtHoraProxVisita.setClickable(true);
                    cardViewProxFecha.setCardBackgroundColor(Color.WHITE);
                    cardViewProxHora.setCardBackgroundColor(Color.WHITE);
                }else{
                    cardViewProxFecha.setCardBackgroundColor(Color.GRAY);
                    cardViewProxHora.setCardBackgroundColor(Color.GRAY);
                    txtFechaProxVisita.setClickable(false);
                    txtHoraProxVisita.setClickable(false);
                    txtFechaProxVisita.setEnabled(false);
                    txtHoraProxVisita.setEnabled(false);
                    txtFechaProxVisita.setText("");
                    txtHoraProxVisita.setText("");
                }
            }
        });

        txtFechaProxVisita.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar calendario = Calendar.getInstance();
                int yy = calendario.get(Calendar.YEAR);
                int mm = calendario.get(Calendar.MONTH);
                int dd = calendario.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePicker = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        diaProx = dayOfMonth;
                        mesProx = monthOfYear + 1;
                        anioProx = year;
                        String fecha = checkDigito(diaProx) +"/"+checkDigito(mesProx)
                                +"/"+checkDigito(anioProx);
                        txtFechaProxVisita.setText(fecha);
                    }
                }, yy, mm, dd);
                datePicker.show();
            }
        });
        txtHoraProxVisita.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar calendario = Calendar.getInstance();
                int hh = calendario.get(Calendar.HOUR_OF_DAY);
                int mm = calendario.get(Calendar.MINUTE);

                TimePickerDialog timePicker = new TimePickerDialog(context, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int hourofDay, int minute) {
                        String hora = checkDigito(hourofDay) +":"+checkDigito(minute);
                        txtHoraProxVisita.setText(hora);
                        horaProx = hourofDay;
                        minutoProx = minute;
                    }
                }, hh, mm,true);
                timePicker.show();
            }
        });

        rgTipoEntrevista.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                controlarEspecifiqueRadio(group, checkedId,3,edtTipoEntrevista);
            }
        });

        alert.setTitle("EDITAR VISITA");
        alert.setView(dialogView);
        alert.setPositiveButton("Modificar",null);
        alert.setNegativeButton("Cancelar",null);
        final AlertDialog alertDialog = alert.create();

        cursor.moveToPosition(posicion);
        VisitaEncuestador visitaEncuestador = DAOUtils.getVisitaEncuestador(cursor.getString(cursor.getColumnIndex("_id")),getContext());
        if(visitaEncuestador!=null){
            Log.e("dato","A");
            if(!visitaEncuestador.getVis_resu().equals("")){
                spResultado.setSelection(Integer.parseInt(visitaEncuestador.getVis_resu()));
                edtEspecifique.setText(visitaEncuestador.getVis_resu_esp());
                if(!visitaEncuestador.getTipo_ent().equals("-1") && !visitaEncuestador.getTipo_ent().equals(""))((RadioButton) rgTipoEntrevista.getChildAt(Integer.parseInt(visitaEncuestador.getTipo_ent()))).setChecked(true);
                edtTipoEntrevista.setText(visitaEncuestador.getTipo_ent_o());
                txtLatitud.setText(visitaEncuestador.getLatitud());
                txtLongitud.setText(visitaEncuestador.getLongitud());
                if(visitaEncuestador.getVis_hor_fin()!=null || !visitaEncuestador.getVis_hor_fin().equals("")){txtHoraF.setText(checkDigito(Integer.parseInt(visitaEncuestador.getVis_hor_fin())) + ":" + checkDigito(Integer.parseInt(visitaEncuestador.getVis_min_fin())));}
                if(!visitaEncuestador.getProx_vis_fecha_dd().equals("")){
                    //ckProxVisita.setEnabled(true);
                    ckProxVisita.setChecked(true);
                    txtFechaProxVisita.setText(checkDigito(Integer.parseInt(visitaEncuestador.getProx_vis_fecha_dd()) + 1) + "/" + checkDigito(Integer.parseInt(visitaEncuestador.getProx_vis_fecha_mm())) + "/" + checkDigito(Integer.parseInt(visitaEncuestador.getProx_vis_fecha_aa())));}
                if(!visitaEncuestador.getProx_vis_hor().equals("")){
                    //ckProxVisita.setEnabled(true);
                    ckProxVisita.setChecked(true);
                    txtHoraProxVisita.setText(checkDigito(Integer.parseInt(visitaEncuestador.getProx_vis_hor())) + ":" + checkDigito(Integer.parseInt(visitaEncuestador.getProx_vis_min())));}
            }

        }


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
                //cursor.moveToPosition(posicion);
                txtNumero.setText("VISITA N° " + checkDigito(Integer.parseInt(cursor.getString(cursor.getColumnIndex(SQLConstantes.visita_encuestador_numero)))));
//                txtHoraF.setText(checkDigito(horaFin) + ":" + checkDigito(minutoFin));
//                txtFechaProxVisita.setText(checkDigito(diaProx + 1) + "/" + checkDigito(mesProx) + "/" + checkDigito(anioProx));
//                txtHoraProxVisita.setText(checkDigito(horaProx) + ":" + checkDigito(minutoProx));
                Button btnFinalizarVisita = alertDialog.getButton(AlertDialog.BUTTON_POSITIVE);
                btnFinalizarVisita.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        boolean valido = false;
                        boolean vHoraFin = true, vResultado = true, vEspecifique = true, vFechaProxima = true, vHoraProxima = true, estado = true;
                        boolean estadoVerificar = false;
                        String mensaje = "";
                        cursor.moveToPosition(posicion);
                        String anio1 = cursor.getString(cursor.getColumnIndex(SQLConstantes.visita_encuestador_vis_fecha_aa));
                        String mes1  = cursor.getString(cursor.getColumnIndex(SQLConstantes.visita_encuestador_vis_fecha_mm));
                        String dia1  = cursor.getString(cursor.getColumnIndex(SQLConstantes.visita_encuestador_vis_fecha_dd));
                        int t1 = Integer.parseInt(cursor.getString(cursor.getColumnIndex(SQLConstantes.visita_encuestador_vis_hor_ini)))*60 + Integer.parseInt(cursor.getString(cursor.getColumnIndex(SQLConstantes.visita_encuestador_vis_min_ini)));
                        int t2 = horaFin * 60 + minutoFin;
                        if(UtilsMethods.validateFechas(dia1,mes1,anio1) == -1){
                            estado = false;
                            if(mensaje.equals("")) mensaje = "ERROR EN LAS FECHAS,VERIFIQUE LA HORA DEL SISTEMA";
                        }else if(UtilsMethods.validateFechas(dia1,mes1,anio1) == 0 && t1 >= t2) {
                            estado = false;
                            if(mensaje.equals("")) mensaje = "LA HORA DE FIN DEBE SER MAYOR A LA DE INICIO";
                        }
                        //VALIDACIONES ANTES DE GUARDADO
                        cursor.moveToPosition(posicion);
                        int y = Integer.parseInt(cursor.getString(cursor.getColumnIndex(SQLConstantes.visita_encuestador_vis_fecha_aa)));
                        int m = Integer.parseInt(cursor.getString(cursor.getColumnIndex(SQLConstantes.visita_encuestador_vis_fecha_mm)));
                        int d = Integer.parseInt(cursor.getString(cursor.getColumnIndex(SQLConstantes.visita_encuestador_vis_fecha_dd)));
                        Date fi1 = new Date(y,m,d);
                        Date fi2 = new Date(anioProx,mesProx,diaProx);
                        /*Validaciones cuando es diferente de completa*/
                        if(spResultado.getSelectedItemPosition() != 1){
                            if(spResultado.getSelectedItemPosition() == 0){
                                estado = false;
                                if(mensaje.equals("")) mensaje = "DEBE INDICAR EL RESULTADO DE LA VISITAS";
//                            }else if(spResultado.getSelectedItemPosition() == 4 && !ckProxVisita.isChecked()){
//                                estado = false;
//                                if(mensaje.equals("")) mensaje = "DEBE REGISTRAR INFORMACION DE LA PROXIMA VISITA";
                            }else if(edtEspecifique.isEnabled() && edtEspecifique.getText().toString().trim().length() < 3){
                                estado = false;
                                if(mensaje.equals("")) mensaje = "RESULTADO DE VISITA: ESPECIFIQUE NO DEBE SER MENOR A 3 CARACTERES";
                            }else if(ckProxVisita.isChecked() && fi2.before(fi1)){
                                estado = false;
                                if(mensaje.equals("")) mensaje = "FECHA: LA FECHA DE LA PROXIMA VISITA NO DEBE SER MENOR A LA VISITA ACTUAL";
                            }else if(rgTipoEntrevista.indexOfChild(rgTipoEntrevista.findViewById(rgTipoEntrevista.getCheckedRadioButtonId()))== -1){
                                estado = false;
                                if(mensaje.equals("")) mensaje = "DEBE INDICAR EL TIPO DE ENTREVISTA";
                            }else if (rgTipoEntrevista.indexOfChild(rgTipoEntrevista.findViewById(rgTipoEntrevista.getCheckedRadioButtonId()))== 3 && edtTipoEntrevista.getText().toString().trim().length() < 3){
                                estado = false;
                                if(mensaje.equals("")) mensaje = "TIPO DE ENTREVISTA: ESPECIFIQUE NO DEBE SER MENOR A 3 CARACTERES";
                            }else if(txtLatitud.getText().toString().trim().length() < 3 || txtLongitud.getText().toString().trim().length() < 3){
                                estado = false;
                                if(mensaje.equals("")) mensaje = "DEBE CAPTURAR LATITUD Y LONGITUD";
                            }else if(spResultado.getSelectedItemPosition()== 4 && rgTipoEntrevista.indexOfChild(rgTipoEntrevista.findViewById(rgTipoEntrevista.getCheckedRadioButtonId()))== 2){
                                estado = false;
                                if(mensaje.equals("")) mensaje = "LA ENTREVISTA SE REALIZO POR TELEFONO ENTONCES NO DEBE SER AUSENTE RESULTADO DE VISITA";
                            }else if(!getHogar().getP15_o().equals(getHogar().getP17())){
                                estadoVerificar = true;
                                if(mensaje.equals("")) mensaje = "EL NUMERO DE PERSONA QUE LLEGARON DE VENEZUELA (P15) NO COINCIDE CON LOS REGISTRADOS EN EL CAPITULO 200";
                            }
                        }
                        /*Validaciones cuando es completa*/
                        if(spResultado.getSelectedItemPosition() == 1){
                            if(rgTipoEntrevista.indexOfChild(rgTipoEntrevista.findViewById(rgTipoEntrevista.getCheckedRadioButtonId()))== -1){
                                estado = false;
                                if(mensaje.equals("")) mensaje = "DEBE INDICAR EL TIPO DE ENTREVISTA";
                            }else if (rgTipoEntrevista.indexOfChild(rgTipoEntrevista.findViewById(rgTipoEntrevista.getCheckedRadioButtonId()))== 3 && edtTipoEntrevista.getText().toString().trim().length() < 3){
                                estado = false;
                                if(mensaje.equals("")) mensaje = "DEBE ESPECIFICAR EL TIPO DE ENTREVISTA";
                            }else if(txtLatitud.getText().toString().trim().length() < 3 || txtLongitud.getText().toString().trim().length() < 3){
                                estado = false;
                                if(mensaje.equals("")) mensaje = "DEBE CAPTURAR LATITUD Y LONGITUD";
                            }else if(!getHogar().getP15_o().equals(getHogar().getP17())){
                                estado = false;
                                if(mensaje.equals("")) mensaje = "EL NUMERO DE PERSONA QUE LLEGARON DE VENEZUELA (P15) NO COINCIDE CON LOS REGISTRADOS EN EL CAPITULO 200";
                            }else if(DAOUtils.getValidacionCoberturaPersona(idHogar,context)>0){
                                estado = false;
                                if(mensaje.equals("")) mensaje = "FALTA COMPLETAR ALGUN CAPITULO DE LAS PERSONAS QUE MIGRARON DE VENEZUELA";
                            }else if(getHogar().getP15().equals("")){
                                estado = false;
                                if(mensaje.equals("")) mensaje = "FALTA COMPLETAR PREGUNTAS (P15) EN LA SIGUIENTE PANTALLA";
                            }else if(getHogar().getP15().equals("2")){
                                estado = false;
                                if(mensaje.equals("")) mensaje = "EL RESULTADO NO PUEDE SER 'COMPLETA' YA QUE NO EXISTE NINGUN RESIDENTE VENEZOLANO";
                            }
                        }

                        valido = estado;

                        if(valido){
//                            String cobertura = "0";
//                            boolean finalizacion = true;
//                            if (spResultado.getSelectedItemPosition() == 1) {
//                                if(!coberturaCorrecta()) finalizacion = false;
//                                else cobertura = "1";
//                            }
//                            if (finalizacion){
                                //CONTENVALUES DE VISITA_ENCUESTADOR
                                ContentValues contentValues = new ContentValues();
                                contentValues.put(SQLConstantes.visita_encuestador_vis_resu,String.valueOf(spResultado.getSelectedItemPosition()));
                                if(edtEspecifique.isEnabled()){
                                    contentValues.put(SQLConstantes.visita_encuestador_vis_resu_esp,edtEspecifique.getText().toString());
                                }else{contentValues.put(SQLConstantes.visita_encuestador_vis_resu_esp,"");}
                                //contentValues.put(SQLConstantes.visita_encuestador_vis_hor_fin,checkDigito(Integer.parseInt(visitaEncuestador.getVis_hor_fin())));
                                //contentValues.put(SQLConstantes.visita_encuestador_vis_min_fin,minutoFin);
                                //falta guardar el especifique del resultado otro
                                if(ckProxVisita.isChecked()){
                                    contentValues.put(SQLConstantes.visita_encuestador_prox_vis_fecha_dd,checkDigito(diaProx));
                                    contentValues.put(SQLConstantes.visita_encuestador_prox_vis_fecha_mm,checkDigito(mesProx));
                                    contentValues.put(SQLConstantes.visita_encuestador_prox_vis_fecha_aa,checkDigito(anioProx));
                                    contentValues.put(SQLConstantes.visita_encuestador_prox_vis_hor,checkDigito(horaProx));
                                    contentValues.put(SQLConstantes.visita_encuestador_prox_vis_min,checkDigito(minutoProx));
                                }else{
                                    contentValues.put(SQLConstantes.visita_encuestador_prox_vis_fecha_dd,"");
                                    contentValues.put(SQLConstantes.visita_encuestador_prox_vis_fecha_mm,"");
                                    contentValues.put(SQLConstantes.visita_encuestador_prox_vis_fecha_aa,"");
                                    contentValues.put(SQLConstantes.visita_encuestador_prox_vis_hor,"");
                                    contentValues.put(SQLConstantes.visita_encuestador_prox_vis_min,"");
                                }
                                contentValues.put(SQLConstantes.visita_encuestador_tipo_entrevista,rgTipoEntrevista.indexOfChild(rgTipoEntrevista.findViewById(rgTipoEntrevista.getCheckedRadioButtonId())));
                                if(edtTipoEntrevista.isEnabled()){
                                    contentValues.put(SQLConstantes.visita_encuestador_tipo_entrevista_o,edtTipoEntrevista.getText().toString());
                                }else{contentValues.put(SQLConstantes.visita_encuestador_tipo_entrevista_o,"");}
                                contentValues.put(SQLConstantes.visita_encuestador_latitud,txtLatitud.getText().toString()+"");
                                contentValues.put(SQLConstantes.visita_encuestador_longitud,txtLongitud.getText().toString()+"");

                                try{
                                    dataTablas = new Data(context);
                                    dataTablas.open();
                                    cursor.moveToPosition(posicion);
                                    //actualiza la visita con los datos de finalizacion
                                    dataTablas.actualizarElemento(getIdTablaParte1(),contentValues,cursor.getString(cursor.getColumnIndex("_id")));
                                    //recupero un cursor con informacion d ela ultima visita guardada
                                    cursor = dataTablas.getCursorVisitas(getIdTablaParte1(), idHogar);
                                    dataTablas.close();
                                    if(cursor != null){
                                        //seteo adapter
                                        visitaEncuestadorAdapter = new VisitaEncuestadorAdapter(visita, context, cursor, onItemClickListener);
                                        recyclerView.setAdapter(visitaEncuestadorAdapter);
                                    }
                                }catch (SQLException e){}

//                            //CONTENVALUES DE VISITA RESULTADO FINAL
                                final Calendar cal = Calendar.getInstance();
                                cursor.moveToPosition(posicion);
                                //obtengo la fecha de la ultima visita
                                int yy = Integer.parseInt(cursor.getString(cursor.getColumnIndex(SQLConstantes.visita_encuestador_vis_fecha_aa)));
                                int mm = Integer.parseInt(cursor.getString(cursor.getColumnIndex(SQLConstantes.visita_encuestador_vis_fecha_mm)));
                                int dd = Integer.parseInt(cursor.getString(cursor.getColumnIndex(SQLConstantes.visita_encuestador_vis_fecha_dd)));

                                ContentValues contentValuesFinal = new ContentValues();
                                cursor.moveToPosition(posicion);
                                contentValuesFinal.put(SQLConstantes.resultado_encuestador_vis_resultado_final,cursor.getString(cursor.getColumnIndex(SQLConstantes.visita_encuestador_vis_resu)));
                                contentValuesFinal.put(SQLConstantes.resultado_encuestador_vis_resultado_final_o,cursor.getString(cursor.getColumnIndex(SQLConstantes.visita_encuestador_vis_resu_esp)));
                                //FALTA GUARDAR RESULTADO ESPECIFIQUE DE OTRO
                                contentValuesFinal.put(SQLConstantes.resultado_encuestador_vis_fecha_final_dd,dd);
                                contentValuesFinal.put(SQLConstantes.resultado_encuestador_vis_fecha_final_mm,mm);
                                contentValuesFinal.put(SQLConstantes.resultado_encuestador_vis_fecha_final_aa,yy);
                                dataTablas = new Data(context);
                                dataTablas.open();
                                if(!dataTablas.existeElemento(getIdTablaParte2(), idHogar)){
                                    contentValuesFinal.put(SQLConstantes.resultado_encuestador_id, idHogar);
                                    contentValuesFinal.put(SQLConstantes.resultado_encuestador_id_vivienda, idVivienda);
                                    dataTablas.insertarElemento(getIdTablaParte2(),contentValuesFinal);

                                    txtResultadoFinal.setText(getResources().getStringArray(R.array.visita_array_resultados)[spResultado.getSelectedItemPosition()]);
                                    txtFechaFinal.setText(checkDigito(dd) + "/" + checkDigito(mm) + "/" + checkDigito(yy));
                                    dataTablas.actualizarValor(SQLConstantes.tablahogares, SQLConstantes.hogar_estado, spResultado.getSelectedItemPosition()+"", idHogar);
                                    dataTablas.actualizarValor(SQLConstantes.tablacaratula, SQLConstantes.caratula_resultado_final, updateResultadoVivienda()+"", idVivienda);
                                }else{
                                    dataTablas.actualizarElemento(getIdTablaParte2(),contentValuesFinal, idHogar);
                                    txtResultadoFinal.setText(getResources().getStringArray(R.array.visita_array_resultados)[spResultado.getSelectedItemPosition()]);
                                    txtFechaFinal.setText(checkDigito(dd) + "/" + checkDigito(mm) + "/" + checkDigito(yy));
                                    dataTablas.actualizarValor(SQLConstantes.tablahogares,SQLConstantes.hogar_estado,spResultado.getSelectedItemPosition()+"",idHogar);
                                    dataTablas.actualizarValor(SQLConstantes.tablacaratula, SQLConstantes.caratula_resultado_final, updateResultadoVivienda()+"", idVivienda);
                                }
                                //ACTUALIZA EL ESTADO DEL HOGAR
                                //dataTablas.actualizarValor(SQLConstantes.tablahogares, SQLConstantes.hogar_estado, dataTablas.getValor(getIdTablaParte2(), SQLConstantes.resultado_supervisor_vis_resultado_final,idHogar), idHogar);
                                //ACTUALIZA COBERTURA
                                //dataTablas.actualizarValor(SQLConstantes.tablahogares, SQLConstantes.hogar_cobertura, cobertura, idHogar);
                                dataTablas.close();
                                showMensaje(estadoVerificar,mensaje);
                                alertDialog.dismiss();
 //                           }
                        }else {
                            showMensaje(true,mensaje);
                        }
                    }
                });
            }
        });
        alertDialog.show();
    }

    public void modificarVisitaCompleta(final int posicion){
        AlertDialog.Builder alert = new AlertDialog.Builder(getActivity());
        final View dialogView = getActivity().getLayoutInflater().inflate(R.layout.dialog_finalizar_visita_encuestador, null);
        final LinearLayout lytDialog = (LinearLayout) dialogView.findViewById(R.id.dialog_finalizar_visita_lyt);
        final TextView txtNumero = (TextView) dialogView.findViewById(R.id.dialog_finalizar_visita_txtNumero);
        final TextView txtHoraF = (TextView) dialogView.findViewById(R.id.dialog_finalizar_visita_txtHoraFin);
        final Spinner spResultado = (Spinner) dialogView.findViewById(R.id.dialog_finalizar_visita_spResultado);
        final EditText edtEspecifique = (EditText) dialogView.findViewById(R.id.dialog_finalizar_visita_edtEspecifique);
        final CheckBox ckProxVisita = (CheckBox) dialogView.findViewById(R.id.dialog_finalizar_visita_ckProximaVisita);
        final TextView txtFechaProxVisita = (TextView) dialogView.findViewById(R.id.dialog_finalizar_visita_txtFechaProx);
        final TextView txtHoraProxVisita = (TextView) dialogView.findViewById(R.id.dialog_finalizar_visita_txtHoraProx);
        final CardView cardViewEspecifique = (CardView) dialogView.findViewById(R.id.dialog_cardview_finalizar_especifique);
        final CardView cardViewProxVisita = (CardView) dialogView.findViewById(R.id.dialog_cardview_finalizar_proxVisita);
        final CardView cardViewProxFecha= (CardView) dialogView.findViewById(R.id.dialog_cardview_proxFecha);
        final CardView cardViewProxHora = (CardView) dialogView.findViewById(R.id.dialog_cardview_proxHora);
        final RadioGroup rgTipoEntrevista = (RadioGroup) dialogView.findViewById(R.id.dialog_finalizar_visita_rg_tipo_entrevista);
        final EditText   edtTipoEntrevista = (EditText) dialogView.findViewById(R.id.visita_txt_presencial_o);
        String especifique = "";

        cardViewProxVisita.setVisibility(View.GONE);


        UtilsMethodsInputs.setupEditText(edtEspecifique,getContext(),1,50);

        edtTipoEntrevista.setFilters(new InputFilter[]{new InputFilter.AllCaps(),new InputFilter.LengthFilter(100), new InputFilterSoloLetras()});

        spResultado.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long l) {
                if(pos == 4 || pos == 2){
                    cardViewProxVisita.setVisibility(View.VISIBLE);
                    //ckProxVisita.setEnabled(true);
                    //ckProxVisita.setChecked(true);
                }else{
                    cardViewProxVisita.setVisibility(View.GONE);
                    ckProxVisita.setChecked(false);
                    //ckProxVisita.setEnabled(false);
                    txtFechaProxVisita.setText("");
                    txtHoraProxVisita.setText("");
                }
                if(pos == 10 || pos == 11){
                    if(pos==10){ Toast.makeText(getContext(),"Especifique:¿Cuánto tiempo  permaneció en la vivienda?",Toast.LENGTH_LONG).show();}
                    edtEspecifique.setEnabled(true);
                    cardViewEspecifique.setVisibility(View.VISIBLE);
                }else{
                    if(edtEspecifique.isEnabled()){
                        edtEspecifique.setText("");
                        cardViewEspecifique.setVisibility(View.GONE);
                        edtEspecifique.setEnabled(false);
                    }
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {}
        });

        ckProxVisita.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    txtFechaProxVisita.setEnabled(true);
                    txtHoraProxVisita.setEnabled(true);
                    txtFechaProxVisita.setClickable(true);
                    txtHoraProxVisita.setClickable(true);
                    cardViewProxFecha.setCardBackgroundColor(Color.WHITE);
                    cardViewProxHora.setCardBackgroundColor(Color.WHITE);
                }else{
                    cardViewProxFecha.setCardBackgroundColor(Color.GRAY);
                    cardViewProxHora.setCardBackgroundColor(Color.GRAY);
                    txtFechaProxVisita.setClickable(false);
                    txtHoraProxVisita.setClickable(false);
                    txtFechaProxVisita.setEnabled(false);
                    txtHoraProxVisita.setEnabled(false);
                    txtFechaProxVisita.setText("");
                    txtHoraProxVisita.setText("");
                }
            }
        });

        txtFechaProxVisita.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar calendario = Calendar.getInstance();
                int yy = calendario.get(Calendar.YEAR);
                int mm = calendario.get(Calendar.MONTH);
                int dd = calendario.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePicker = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        diaProx = dayOfMonth;
                        mesProx = monthOfYear + 1;
                        anioProx = year;
                        String fecha = checkDigito(diaProx) +"/"+checkDigito(mesProx)
                                +"/"+checkDigito(anioProx);
                        txtFechaProxVisita.setText(fecha);
                    }
                }, yy, mm, dd);
                datePicker.show();
            }
        });
        txtHoraProxVisita.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar calendario = Calendar.getInstance();
                int hh = calendario.get(Calendar.HOUR_OF_DAY);
                int mm = calendario.get(Calendar.MINUTE);

                TimePickerDialog timePicker = new TimePickerDialog(context, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int hourofDay, int minute) {
                        String hora = checkDigito(hourofDay) +":"+checkDigito(minute);
                        txtHoraProxVisita.setText(hora);
                        horaProx = hourofDay;
                        minutoProx = minute;
                    }
                }, hh, mm,true);
                timePicker.show();
            }
        });

        rgTipoEntrevista.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                controlarEspecifiqueRadio(group, checkedId,3,edtTipoEntrevista);
            }
        });

        alert.setTitle("EDITAR VISITA");
        alert.setView(dialogView);
        alert.setPositiveButton("Modificar",null);
        alert.setNegativeButton("Cancelar",null);
        final AlertDialog alertDialog = alert.create();

        cursor.moveToPosition(posicion);
        VisitaEncuestador visitaEncuestador = DAOUtils.getVisitaEncuestador(cursor.getString(cursor.getColumnIndex("_id")),getContext());
        if(visitaEncuestador!=null){
            Log.e("dato","A");
            if(!visitaEncuestador.getVis_resu().equals("")){
                spResultado.setSelection(Integer.parseInt(visitaEncuestador.getVis_resu()));
                edtEspecifique.setText(visitaEncuestador.getVis_resu_esp());
                if(!visitaEncuestador.getTipo_ent().equals("-1") && !visitaEncuestador.getTipo_ent().equals(""))((RadioButton) rgTipoEntrevista.getChildAt(Integer.parseInt(visitaEncuestador.getTipo_ent()))).setChecked(true);
                edtTipoEntrevista.setText(visitaEncuestador.getTipo_ent_o());
                txtLatitud.setText(visitaEncuestador.getLatitud());
                txtLongitud.setText(visitaEncuestador.getLongitud());
                if(visitaEncuestador.getVis_hor_fin()!=null || !visitaEncuestador.getVis_hor_fin().equals("")){txtHoraF.setText(checkDigito(Integer.parseInt(visitaEncuestador.getVis_hor_fin())) + ":" + checkDigito(Integer.parseInt(visitaEncuestador.getVis_min_fin())));}
                if(!visitaEncuestador.getProx_vis_fecha_dd().equals("")){
                    //ckProxVisita.setEnabled(true);
                    ckProxVisita.setChecked(true);
                    txtFechaProxVisita.setText(checkDigito(Integer.parseInt(visitaEncuestador.getProx_vis_fecha_dd()) + 1) + "/" + checkDigito(Integer.parseInt(visitaEncuestador.getProx_vis_fecha_mm())) + "/" + checkDigito(Integer.parseInt(visitaEncuestador.getProx_vis_fecha_aa())));}
                if(!visitaEncuestador.getProx_vis_hor().equals("")){
                    //ckProxVisita.setEnabled(true);
                    ckProxVisita.setChecked(true);
                    txtHoraProxVisita.setText(checkDigito(Integer.parseInt(visitaEncuestador.getProx_vis_hor())) + ":" + checkDigito(Integer.parseInt(visitaEncuestador.getProx_vis_min())));}
            }

        }


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
                //cursor.moveToPosition(posicion);
                txtNumero.setText("VISITA N° " + checkDigito(Integer.parseInt(cursor.getString(cursor.getColumnIndex(SQLConstantes.visita_encuestador_numero)))));
//                txtHoraF.setText(checkDigito(horaFin) + ":" + checkDigito(minutoFin));
//                txtFechaProxVisita.setText(checkDigito(diaProx + 1) + "/" + checkDigito(mesProx) + "/" + checkDigito(anioProx));
//                txtHoraProxVisita.setText(checkDigito(horaProx) + ":" + checkDigito(minutoProx));
                Button btnFinalizarVisita = alertDialog.getButton(AlertDialog.BUTTON_POSITIVE);
                btnFinalizarVisita.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        boolean valido = false;
                        boolean vHoraFin = true, vResultado = true, vEspecifique = true, vFechaProxima = true, vHoraProxima = true, estado = true;
                        String mensaje = "";
                        cursor.moveToPosition(posicion);
                        String anio1 = cursor.getString(cursor.getColumnIndex(SQLConstantes.visita_encuestador_vis_fecha_aa));
                        String mes1  = cursor.getString(cursor.getColumnIndex(SQLConstantes.visita_encuestador_vis_fecha_mm));
                        String dia1  = cursor.getString(cursor.getColumnIndex(SQLConstantes.visita_encuestador_vis_fecha_dd));
                        int t1 = Integer.parseInt(cursor.getString(cursor.getColumnIndex(SQLConstantes.visita_encuestador_vis_hor_ini)))*60 + Integer.parseInt(cursor.getString(cursor.getColumnIndex(SQLConstantes.visita_encuestador_vis_min_ini)));
                        int t2 = horaFin * 60 + minutoFin;
                        if(UtilsMethods.validateFechas(dia1,mes1,anio1) == -1){
                            estado = false;
                            if(mensaje.equals("")) mensaje = "ERROR EN LAS FECHAS,VERIFIQUE LA HORA DEL SISTEMA";
                        }else if(UtilsMethods.validateFechas(dia1,mes1,anio1) == 0 && t1 >= t2) {
                            estado = false;
                            if(mensaje.equals("")) mensaje = "LA HORA DE FIN DEBE SER MAYOR A LA DE INICIO";
                        }
                        //VALIDACIONES ANTES DE GUARDADO
                        cursor.moveToPosition(posicion);
                        int y = Integer.parseInt(cursor.getString(cursor.getColumnIndex(SQLConstantes.visita_encuestador_vis_fecha_aa)));
                        int m = Integer.parseInt(cursor.getString(cursor.getColumnIndex(SQLConstantes.visita_encuestador_vis_fecha_mm)));
                        int d = Integer.parseInt(cursor.getString(cursor.getColumnIndex(SQLConstantes.visita_encuestador_vis_fecha_dd)));
                        Date fi1 = new Date(y,m,d);
                        Date fi2 = new Date(anioProx,mesProx,diaProx);
                        /*Validaciones cuando es diferente de completa*/
                        if(spResultado.getSelectedItemPosition() != 1){
                            if(spResultado.getSelectedItemPosition() == 0){
                                estado = false;
                                if(mensaje.equals("")) mensaje = "DEBE INDICAR EL RESULTADO DE LA VISITAS";
//                            }else if(spResultado.getSelectedItemPosition() == 4 && !ckProxVisita.isChecked()){
//                                estado = false;
//                                if(mensaje.equals("")) mensaje = "DEBE REGISTRAR INFORMACION DE LA PROXIMA VISITA";
                            }else if(edtEspecifique.isEnabled() && edtEspecifique.getText().toString().trim().length() < 3){
                                estado = false;
                                if(mensaje.equals("")) mensaje = "RESULTADO DE VISITA: ESPECIFIQUE NO DEBE SER MENOR A 3 CARACTERES";
                            }else if(ckProxVisita.isChecked() && fi2.before(fi1)){
                                estado = false;
                                if(mensaje.equals("")) mensaje = "FECHA: LA FECHA DE LA PROXIMA VISITA NO DEBE SER MENOR A LA VISITA ACTUAL";
                            }else if(rgTipoEntrevista.indexOfChild(rgTipoEntrevista.findViewById(rgTipoEntrevista.getCheckedRadioButtonId()))== -1){
                                estado = false;
                                if(mensaje.equals("")) mensaje = "DEBE INDICAR EL TIPO DE ENTREVISTA";
                            }else if (rgTipoEntrevista.indexOfChild(rgTipoEntrevista.findViewById(rgTipoEntrevista.getCheckedRadioButtonId()))== 3 && edtTipoEntrevista.getText().toString().trim().length() < 3){
                                estado = false;
                                if(mensaje.equals("")) mensaje = "TIPO DE ENTREVISTA: ESPECIFIQUE NO DEBE SER MENOR A 3 CARACTERES";
                            }else if(txtLatitud.getText().toString().trim().length() < 3 || txtLongitud.getText().toString().trim().length() < 3){
                                estado = false;
                                if(mensaje.equals("")) mensaje = "DEBE CAPTURAR LATITUD Y LONGITUD";
                            }else if(spResultado.getSelectedItemPosition()== 4 && rgTipoEntrevista.indexOfChild(rgTipoEntrevista.findViewById(rgTipoEntrevista.getCheckedRadioButtonId()))== 2){
                                estado = false;
                                if(mensaje.equals("")) mensaje = "LA ENTREVISTA SE REALIZO POR TELEFONO ENTONCES NO DEBE SER AUSENTE RESULTADO DE VISITA";
                            }
                        }
                        /*Validaciones cuando es completa*/
                        if(spResultado.getSelectedItemPosition() == 1){
                            if(rgTipoEntrevista.indexOfChild(rgTipoEntrevista.findViewById(rgTipoEntrevista.getCheckedRadioButtonId()))== -1){
                                estado = false;
                                if(mensaje.equals("")) mensaje = "DEBE INDICAR EL TIPO DE ENTREVISTA";
                            }else if (rgTipoEntrevista.indexOfChild(rgTipoEntrevista.findViewById(rgTipoEntrevista.getCheckedRadioButtonId()))== 3 && edtTipoEntrevista.getText().toString().trim().length() < 3){
                                estado = false;
                                if(mensaje.equals("")) mensaje = "DEBE ESPECIFICAR EL TIPO DE ENTREVISTA";
                            }else if(txtLatitud.getText().toString().trim().length() < 3 || txtLongitud.getText().toString().trim().length() < 3){
                                estado = false;
                                if(mensaje.equals("")) mensaje = "DEBE CAPTURAR LATITUD Y LONGITUD";
                            } else {
                                if(!coberturaCorrecta()){
                                    estado = false;
                                    if(mensaje.equals("")) mensaje = "LA COBERTURA Y CIERRE ES INCORRECTA NO PUEDE FINALIZAR COMO COMPLETA";
                                }
                            }
                        }

                        valido = estado;

                        if(valido){
                            String cobertura = "0";
                            boolean finalizacion = true;
                            if (spResultado.getSelectedItemPosition() == 1) {
                                if(!coberturaCorrecta()) finalizacion = false;
                                else cobertura = "1";
                            }
                            if (finalizacion){
                                //CONTENVALUES DE VISITA_ENCUESTADOR
                                ContentValues contentValues = new ContentValues();
                                contentValues.put(SQLConstantes.visita_encuestador_vis_resu,String.valueOf(spResultado.getSelectedItemPosition()));
                                if(edtEspecifique.isEnabled()){
                                    contentValues.put(SQLConstantes.visita_encuestador_vis_resu_esp,edtEspecifique.getText().toString());
                                }else{contentValues.put(SQLConstantes.visita_encuestador_vis_resu_esp,"");}
                                //contentValues.put(SQLConstantes.visita_encuestador_vis_hor_fin,checkDigito(Integer.parseInt(visitaEncuestador.getVis_hor_fin())));
                                //contentValues.put(SQLConstantes.visita_encuestador_vis_min_fin,minutoFin);
                                //falta guardar el especifique del resultado otro
                                if(ckProxVisita.isChecked()){
                                    contentValues.put(SQLConstantes.visita_encuestador_prox_vis_fecha_dd,checkDigito(diaProx));
                                    contentValues.put(SQLConstantes.visita_encuestador_prox_vis_fecha_mm,checkDigito(mesProx));
                                    contentValues.put(SQLConstantes.visita_encuestador_prox_vis_fecha_aa,checkDigito(anioProx));
                                    contentValues.put(SQLConstantes.visita_encuestador_prox_vis_hor,checkDigito(horaProx));
                                    contentValues.put(SQLConstantes.visita_encuestador_prox_vis_min,checkDigito(minutoProx));
                                }else{
                                    contentValues.put(SQLConstantes.visita_encuestador_prox_vis_fecha_dd,"");
                                    contentValues.put(SQLConstantes.visita_encuestador_prox_vis_fecha_mm,"");
                                    contentValues.put(SQLConstantes.visita_encuestador_prox_vis_fecha_aa,"");
                                    contentValues.put(SQLConstantes.visita_encuestador_prox_vis_hor,"");
                                    contentValues.put(SQLConstantes.visita_encuestador_prox_vis_min,"");
                                }
                                contentValues.put(SQLConstantes.visita_encuestador_tipo_entrevista,rgTipoEntrevista.indexOfChild(rgTipoEntrevista.findViewById(rgTipoEntrevista.getCheckedRadioButtonId())));
                                if(edtTipoEntrevista.isEnabled()){
                                    contentValues.put(SQLConstantes.visita_encuestador_tipo_entrevista_o,edtTipoEntrevista.getText().toString());
                                }else{contentValues.put(SQLConstantes.visita_encuestador_tipo_entrevista_o,"");}
                                contentValues.put(SQLConstantes.visita_encuestador_latitud,txtLatitud.getText().toString()+"");
                                contentValues.put(SQLConstantes.visita_encuestador_longitud,txtLongitud.getText().toString()+"");

                                try{
                                    dataTablas = new Data(context);
                                    dataTablas.open();
                                    cursor.moveToPosition(posicion);
                                    //actualiza la visita con los datos de finalizacion
                                    dataTablas.actualizarElemento(getIdTablaParte1(),contentValues,cursor.getString(cursor.getColumnIndex("_id")));
                                    //recupero un cursor con informacion d ela ultima visita guardada
                                    cursor = dataTablas.getCursorVisitas(getIdTablaParte1(), idHogar);
                                    dataTablas.close();
                                    if(cursor != null){
                                        //seteo adapter
                                        visitaEncuestadorAdapter = new VisitaEncuestadorAdapter(visita, context, cursor, onItemClickListener);
                                        recyclerView.setAdapter(visitaEncuestadorAdapter);
                                    }
                                }catch (SQLException e){}

//                            //CONTENVALUES DE VISITA RESULTADO FINAL
                                final Calendar cal = Calendar.getInstance();
                                cursor.moveToPosition(posicion);
                                //obtengo la fecha de la ultima visita
                                int yy = Integer.parseInt(cursor.getString(cursor.getColumnIndex(SQLConstantes.visita_encuestador_vis_fecha_aa)));
                                int mm = Integer.parseInt(cursor.getString(cursor.getColumnIndex(SQLConstantes.visita_encuestador_vis_fecha_mm)));
                                int dd = Integer.parseInt(cursor.getString(cursor.getColumnIndex(SQLConstantes.visita_encuestador_vis_fecha_dd)));

                                ContentValues contentValuesFinal = new ContentValues();
                                cursor.moveToPosition(posicion);
                                contentValuesFinal.put(SQLConstantes.resultado_encuestador_vis_resultado_final,cursor.getString(cursor.getColumnIndex(SQLConstantes.visita_encuestador_vis_resu)));
                                contentValuesFinal.put(SQLConstantes.resultado_encuestador_vis_resultado_final_o,cursor.getString(cursor.getColumnIndex(SQLConstantes.visita_encuestador_vis_resu_esp)));
                                //FALTA GUARDAR RESULTADO ESPECIFIQUE DE OTRO
                                contentValuesFinal.put(SQLConstantes.resultado_encuestador_vis_fecha_final_dd,dd);
                                contentValuesFinal.put(SQLConstantes.resultado_encuestador_vis_fecha_final_mm,mm);
                                contentValuesFinal.put(SQLConstantes.resultado_encuestador_vis_fecha_final_aa,yy);
                                dataTablas = new Data(context);
                                dataTablas.open();
                                if(!dataTablas.existeElemento(getIdTablaParte2(), idHogar)){
                                    contentValuesFinal.put(SQLConstantes.resultado_encuestador_id, idHogar);
                                    contentValuesFinal.put(SQLConstantes.resultado_encuestador_id_vivienda, idVivienda);
                                    dataTablas.insertarElemento(getIdTablaParte2(),contentValuesFinal);

                                    txtResultadoFinal.setText(getResources().getStringArray(R.array.visita_array_resultados)[spResultado.getSelectedItemPosition()]);
                                    txtFechaFinal.setText(checkDigito(dd) + "/" + checkDigito(mm) + "/" + checkDigito(yy));
                                    dataTablas.actualizarValor(SQLConstantes.tablahogares, SQLConstantes.hogar_estado, spResultado.getSelectedItemPosition()+"", idHogar);
                                }else{
                                    dataTablas.actualizarElemento(getIdTablaParte2(),contentValuesFinal, idHogar);
                                    txtResultadoFinal.setText(getResources().getStringArray(R.array.visita_array_resultados)[spResultado.getSelectedItemPosition()]);
                                    txtFechaFinal.setText(checkDigito(dd) + "/" + checkDigito(mm) + "/" + checkDigito(yy));
                                    dataTablas.actualizarValor(SQLConstantes.tablahogares,SQLConstantes.hogar_estado,spResultado.getSelectedItemPosition()+"",idHogar);
                                }
                                //ACTUALIZA EL ESTADO DEL HOGAR
                                dataTablas.actualizarValor(SQLConstantes.tablahogares, SQLConstantes.hogar_estado, dataTablas.getValor(getIdTablaParte2(), SQLConstantes.resultado_supervisor_vis_resultado_final,idHogar), idHogar);
                                //ACTUALIZA COBERTURA
                                dataTablas.actualizarValor(SQLConstantes.tablahogares, SQLConstantes.hogar_cobertura, cobertura, idHogar);
                                dataTablas.close();
                                alertDialog.dismiss();
                            }
                        }else Toast.makeText(context, mensaje, Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
        alertDialog.show();
    }

    public VisitaEncuestador getUltimoResultadoFinal(){
        ArrayList<VisitaEncuestador> listaVisitas = new ArrayList<>();
        VisitaEncuestador visita = new VisitaEncuestador();
        Data data = new Data(context);
        data.open();
        listaVisitas = data.getAllVisitasHogar(idHogar);
        if(listaVisitas.size()>0)
        {visita = listaVisitas.get(listaVisitas.size()-1);}
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
        if(!getUltimoResultadoFinal().getVis_resu().equals(""))
        {txtResultadoFinal.setText(getResources().getStringArray(R.array.visita_array_resultados)[Integer.parseInt(getUltimoResultadoFinal().getVis_resu())]);}
        txtFechaFinal.setText(checkDigito(Integer.parseInt(getUltimoResultadoFinal().getVis_fecha_dd())) + "/" + checkDigito(Integer.parseInt(getUltimoResultadoFinal().getVis_fecha_mm())) + "/" + checkDigito(Integer.parseInt(getUltimoResultadoFinal().getVis_fecha_aa())));
        data.close();
    }

    public void getActualizarEstadoHogar(){
        Data data = new Data(context);
        data.open();
        data.actualizarValor(SQLConstantes.tablahogares,SQLConstantes.hogar_estado,"",idHogar);
        data.close();
    }

    public Hogar getHogar(){
        Data data = new Data(context);
        data.open();
        Hogar hogar = data.getHogar(idHogar);
        data.close();

        return hogar;
    }


    public boolean coberturaCorrecta(){
//        Data data = new Data(context);
//        data.open();
//        if (data.getValor(SQLConstantes.tablamodulo1v,SQLConstantes.modulo1_v_COB100A,idVivienda).equals("0")){
//            mostrarMensaje("Falta coberturar modulo 1 vivienda"
//                    + "\nNO PUEDE FINALIZAR COMPLETA AUN");return false;
//        }
//        if (data.getValor(SQLConstantes.tablamodulo1h,SQLConstantes.modulo1_h_COB100B,idHogar).equals("0")){
//            mostrarMensaje("Falta coberturar modulo 1 hogar"
//                    + "\nNO PUEDE FINALIZAR COMPLETA AUN");return false;
//        }
//        for (Residente residente: data.getAllResidentesHogar(idHogar)){
//            if (!residente.getCOB200().equals("1")){
//                mostrarMensaje("Falta coberturar modulo 2 - residente: " + residente.getNumero() + "." + residente.getC2_p202()
//                        + "\nNO PUEDE FINALIZAR COMPLETA AUN");return false;
//            }
//            else if (residente.getC2_p207().equals("1")){
//                if (residente.getEncuestado_cobertura().equals("0")){
//                    mostrarMensaje("Falta coberturar la encuesta del residente: " + residente.getNumero() + "." + residente.getC2_p202()
//                            + "\nNO PUEDE FINALIZAR COMPLETA AUN");return false;
//                }
//            }
//        }
//        data.close();
        return true;
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

    public void cargarDatos() {
        dataTablas = new Data(context);
        dataTablas.open();
        if(dataTablas.existeElemento(getIdTablaParte2(), idHogar)){
            txtResultadoFinal.setText(getResources().getStringArray(R.array.visita_array_resultados)[Integer.parseInt(dataTablas.getValor(getIdTablaParte2(),SQLConstantes.resultado_encuestador_vis_resultado_final, idHogar))]);
            txtFechaFinal.setText(
                    checkDigito(Integer.parseInt(dataTablas.getValor(getIdTablaParte2(),SQLConstantes.resultado_encuestador_vis_fecha_final_dd, idHogar))) +
                            "/" + checkDigito(Integer.parseInt(dataTablas.getValor(getIdTablaParte2(),SQLConstantes.resultado_encuestador_vis_fecha_final_mm, idHogar))) +
                            "/" + checkDigito(Integer.parseInt(dataTablas.getValor(getIdTablaParte2(),SQLConstantes.resultado_encuestador_vis_fecha_final_aa, idHogar))));
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
        if(!idCargo.equals("2")){
            if(cursor.getCount() > 0){
                cursor.moveToPosition(cursor.getCount()-1);
                String resultado = cursor.getString(cursor.getColumnIndex(SQLConstantes.visita_encuestador_vis_resu));
                if(resultado != null && !resultado.equals("")){
                    if (Integer.parseInt(resultado) > 1){
                        valido =  false;
                        mensaje = "DEBE INICIAR UNA VISITA ANTES DE CONTINUAR.";
                    }
                    if (Integer.parseInt(resultado)==1 && estadoRevisar==0){
                        valido =  false;
                        mensaje = "LA ENCUESTA YA FINALIZÓ COMPLETA";
                    }
                }
            }else{
                valido =  false;
                mensaje = "DEBE INICIAR UNA VISITA ANTES DE CONTINUAR";
            }
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
        return SQLConstantes.tablavisitasencuestador;
    }
    public String getIdTablaParte2(){
        return SQLConstantes.tablaresultadoencuestador;
    }

    public String getIdTablaOBS100(){
        return  SQLConstantes.tablacaratula;
    }

    private void toggleLocationUpdates(boolean enable) {
        if (enable) {
            enableLocationUpdates();
        } else {
            disableLocationUpdates();
        }
    }

    private void enableLocationUpdates() {
        locRequest = new LocationRequest();
        locRequest.setInterval(2000);
        locRequest.setFastestInterval(1000);
        locRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);

        LocationSettingsRequest locSettingsRequest = new LocationSettingsRequest.Builder().addLocationRequest(locRequest).build();
        PendingResult<LocationSettingsResult> result = LocationServices.SettingsApi.checkLocationSettings(apiClient, locSettingsRequest);

        result.setResultCallback(new ResultCallback<LocationSettingsResult>() {
            @Override
            public void onResult(LocationSettingsResult locationSettingsResult) {
                final Status status = locationSettingsResult.getStatus();
                switch (status.getStatusCode()) {
                    case LocationSettingsStatusCodes.SUCCESS:
                        Log.i(LOGTAG, "Configuración correcta");
                        startLocationUpdates();
                        break;
                    case LocationSettingsStatusCodes.RESOLUTION_REQUIRED:
                        try {
                            Log.i(LOGTAG, "Se requiere actuación del usuario");
                            status.startResolutionForResult(getActivity(), PETICION_CONFIG_UBICACION);
                            btnGPS.setChecked(false);
                        } catch (IntentSender.SendIntentException e) {
                            btnGPS.setChecked(false);
                            Log.i(LOGTAG, "Error al intentar solucionar configuración de ubicación");
                        }
                        break;
                    case LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE:
                        Log.i(LOGTAG, "No se puede cumplir la configuración de ubicación necesaria");
                        btnGPS.setChecked(false);
                        break;
                }
            }
        });
    }

    private void disableLocationUpdates() {
    /*    try{
            LocationServices.FusedLocationApi.removeLocationUpdates(apiClient, this);
        }catch (Exception e){}*/

    }

    private void startLocationUpdates() {
        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            //Ojo: estamos suponiendo que ya tenemos concedido el permiso.
            //Sería recomendable implementar la posible petición en caso de no tenerlo.
            Log.i(LOGTAG, "Inicio de recepción de ubicaciones");
            LocationServices.FusedLocationApi.requestLocationUpdates(apiClient, locRequest, this);
        }
    }

    @Override
    public void onConnectionFailed(ConnectionResult result) {
        //Se ha producido un error que no se puede resolver automáticamente
        //y la conexión con los Google Play Services no se ha establecido.
        Log.e(LOGTAG, "Error grave al conectar con Google Play Services");
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        //Conectado correctamente a Google Play Services
        if (ActivityCompat.checkSelfPermission(getActivity().getApplicationContext(),Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, PETICION_PERMISO_LOCALIZACION);
        } else {
            Location lastLocation = LocationServices.FusedLocationApi.getLastLocation(apiClient);
//            updateUI(lastLocation);
        }
    }

    @Override
    public void onConnectionSuspended(int i) {
        //Se ha interrumpido la conexión con Google Play Services

        Log.e(LOGTAG, "Se ha interrumpido la conexión con Google Play Services");
    }

    private void updateUI(Location loc) {
        if (loc != null) {
            txtLatitud.setText(String.valueOf(loc.getLatitude()));
            txtLongitud.setText(String.valueOf(loc.getLongitude()));
            txtAltitud.setText(String.valueOf(loc.getAccuracy()));
        } else {
            txtLatitud.setText("99.999999");
            txtLongitud.setText("99.999999");
            txtAltitud.setText("99.999999");
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == PETICION_PERMISO_LOCALIZACION) {
            if (grantResults.length == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                //Permiso concedido
                @SuppressWarnings("MissingPermission")
                Location lastLocation = LocationServices.FusedLocationApi.getLastLocation(apiClient);
                updateUI(lastLocation);

            } else {
                //Permiso denegado:
                //Deberíamos deshabilitar toda la funcionalidad relativa a la localización.

                Log.e(LOGTAG, "Permiso denegado");
            }
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case PETICION_CONFIG_UBICACION:
                switch (resultCode) {
                    case Activity.RESULT_OK:
                        startLocationUpdates();
                        break;
                    case Activity.RESULT_CANCELED:
                        Log.i(LOGTAG, "El usuario no ha realizado los cambios de configuración necesarios");
                        btnGPS.setChecked(false);
                        break;
                }
                break;
        }
    }

    @Override
    public void onLocationChanged(Location location) {
        Log.i(LOGTAG, "Recibida nueva ubicación!");
        disableLocationUpdates();
        btnGPS.setChecked(false);
        //Mostramos la nueva ubicación recibida
        updateUI(location);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
     /*   apiClient.stopAutoManage(getActivity());
        apiClient.disconnect();*/
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    private void controlarEspecifiqueRadio(RadioGroup group, int checkedId, int opcionEsp, EditText editTextEspecifique) {
        int seleccionado = group.indexOfChild(group.findViewById(checkedId));
        if(seleccionado == opcionEsp){
            editTextEspecifique.setBackgroundResource(R.drawable.input_text_enabled);
            editTextEspecifique.setEnabled(true);
        }else{
            editTextEspecifique.setText("");
            editTextEspecifique.setBackgroundResource(R.drawable.input_text_disabled);
            editTextEspecifique.setEnabled(false);
        }
    }

    public void showMensaje(boolean valor,String mensaje){
        if (valor){
            Toast.makeText(getActivity(), mensaje, Toast.LENGTH_SHORT).show();
        }
    }

    public int updateResultadoVivienda(){
        ArrayList<ResVisitaEncuestador> listaHogares = new ArrayList<>();
        ArrayList<String> lista = new ArrayList<>();
        listaHogares = DAOUtils.getListaResultadoVisitas(idVivienda,context);
        for (ResVisitaEncuestador valor: listaHogares) {
            lista.add(valor.getVis_resultado_final());
        }
        int resultado   = 0;
        resultado = UtilsMethods.getResultadoVivienda(lista);

        return resultado;
    }
}
