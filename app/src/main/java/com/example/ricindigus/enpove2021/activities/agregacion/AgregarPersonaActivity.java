package com.example.ricindigus.enpove2021.activities.agregacion;

import android.Manifest;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.location.Location;
import android.os.BatteryManager;
import android.os.Build;
import android.os.Handler;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.InputFilter;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.ricindigus.enpove2021.R;
import com.example.ricindigus.enpove2021.activities.EncuestaActivity;
import com.example.ricindigus.enpove2021.activities.HogarActivity;
import com.example.ricindigus.enpove2021.modelo.DAOUtils;
import com.example.ricindigus.enpove2021.modelo.Data;
import com.example.ricindigus.enpove2021.modelo.SQLConstantes;
import com.example.ricindigus.enpove2021.modelo.pojos.M3Pregunta318;
import com.example.ricindigus.enpove2021.modelo.pojos.Residente;
import com.example.ricindigus.enpove2021.modelo.pojos.Ubicacion;
import com.example.ricindigus.enpove2021.util.NumericKeyBoardTransformationMethod;
import com.example.ricindigus.enpove2021.util.UtilsMethods;
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

import org.json.JSONObject;

import java.lang.reflect.Method;

public class AgregarPersonaActivity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener,
        GoogleApiClient.ConnectionCallbacks,
        LocationListener, Response.Listener<JSONObject>, Response.ErrorListener {

    private static final String LOGTAG = "android-localizacion";
    private static final int PETICION_PERMISO_LOCALIZACION = 101;
    private static final int PETICION_CONFIG_UBICACION = 201;
    private GoogleApiClient apiClient;
    private LocationRequest locRequest;
    String latitud;
    String longitud;

    private final int TIEMPO = 20000;
    Handler handler = new Handler();
    Runnable myRunnable;

    RequestQueue request;
    JsonObjectRequest jsonObjectRequest;
    ///


    Spinner spParentesco;
    RadioGroup rgSexo;
    EditText edtEdad;
    RadioGroup rgSiNo;

    Toolbar toolbar;
    LinearLayout lyt1,lyt2,lyt3,lyt4;

    String numero, idEncuestado,idVivienda, _id, idUsuario, nroSegmento, vivienda_periodo, conglomerado;


    private int c3_p318_f;
    private int c3_p318_s;
    private String c3_p318_e;
    private int c3_p318_p;

    int edad=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_persona);

        _id = getIntent().getExtras().getString("id");
        idEncuestado = getIntent().getExtras().getString("idEncuestado");
        idVivienda = getIntent().getExtras().getString("idVivienda");
        idUsuario = getIntent().getExtras().getString("idUsuario");
        numero = getIntent().getExtras().getString("numero");
        nroSegmento = getIntent().getExtras().getString("segmento");
        vivienda_periodo = getIntent().getExtras().getString("periodo");
        conglomerado = getIntent().getExtras().getString("conglomerado");

        spParentesco = (Spinner) findViewById(R.id.agregar_persona_sp_parentesco);
        rgSexo = (RadioGroup) findViewById(R.id.agregar_persona_rg_sexo);
        edtEdad = (EditText) findViewById(R.id.agregar_persona_edt_edad);
        rgSiNo = (RadioGroup) findViewById(R.id.agregar_persona_rg_sino);
        lyt1 = (LinearLayout) findViewById(R.id.agregar_personas_lyt1);
        lyt2 = (LinearLayout) findViewById(R.id.agregar_personas_lyt2);
        lyt3 = (LinearLayout) findViewById(R.id.agregar_personas_lyt3);
        lyt4 = (LinearLayout) findViewById(R.id.agregar_personas_lyt4);

        apiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this, this)
                .addConnectionCallbacks(this)
                .addApi(LocationServices.API)
                .build();

        request = Volley.newRequestQueue(getApplicationContext());

        sendUbicacion();

        toolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("AGREGAR PERSONA DEJADA EN VENEZUELA");
        getSupportActionBar().setSubtitle("PERSONA Nº " + numero);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        configurarEditText(edtEdad,lyt3,2,2);

        cargarDatos();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        sendUbicacion();
    }

    public void cargarDatos(){
        Data data = new Data(this);
        data.open();
        if(data.existeElemento(getNombreTabla(),_id)){
            M3Pregunta318 m3Pregunta318 = data.getM3Pregunta318(_id);
            spParentesco.setSelection(Integer.parseInt(m3Pregunta318.getC3_p318_f()));
            edtEdad.setText(m3Pregunta318.getC3_p318_e());
            if(!m3Pregunta318.getC3_p318_s().equals("-1"))((RadioButton)rgSexo.getChildAt(Integer.parseInt(m3Pregunta318.getC3_p318_s()))).setChecked(true);
            if(!m3Pregunta318.getC3_p318_p().equals("-1"))((RadioButton)rgSiNo.getChildAt(Integer.parseInt(m3Pregunta318.getC3_p318_p()))).setChecked(true);
        }
        Data data2 = new Data(this);
        data2.open();
        Residente residente = data2.getResidente(idEncuestado);
        if(residente.getC2_p205_a()=="") edad = 0; else edad = Integer.parseInt(residente.getC2_p205_a());
        data2.close();
    }

    public void mostrarMensaje(String m){
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(m);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.dismiss();
            }
        });
        final AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
    private void configurarEditText(final EditText editText, final View view, int tipo,int longitud){
        if (tipo == 1) editText.setFilters(new InputFilter[]{new InputFilter.AllCaps(), new InputFilter.LengthFilter(longitud)});

        editText.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int keyCode, KeyEvent keyEvent) {
                if ((keyEvent.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    ocultarTeclado(editText);
                    view.requestFocus();
                    return true;
                }
                return false;
            }
        });
        if (tipo == 2) {
            editText.setFilters(new InputFilter[]{new InputFilter.LengthFilter(longitud)});
            editText.setTransformationMethod(new NumericKeyBoardTransformationMethod());
        }
    }

    public void ocultarTeclado(View view){
        InputMethodManager mgr = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        mgr.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_agregar_persona, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.action_guardar_persona:
                llenarVariables();

                /* if(spParentesco.getSelectedItemPosition() != 0){
                    guardarDatos();
                    finish();
                }else{
                    mostrarMensaje("ESCOGER UN PARENTESCO ANTES DE GUARDAR");
                }*/

                if (validarDatos()){
                    guardarDatos();
                    finish();

                }

                return true;


            case R.id.action_siguiente_persona:
                M3Pregunta318 m3Pregunta318 = new M3Pregunta318();


                if (!getExistePersona(_id)){
                    Toast.makeText(this,"DEBE COMPLETAR P313_1 Y GUARDAR PARA CONTINUAR",Toast.LENGTH_SHORT).show();
                }//else if((getExistePersona(_id) && !getJefeHogar().getC3_p204().equals("")) || (_id.equals(getJefeHogar().get_id()))){
                    else if(getExistePersona(_id) || (_id.equals(m3Pregunta318.getNumero()))){
                    lyt1.setVisibility(View.GONE);
                    lyt2.setVisibility(View.VISIBLE);
                    lyt3.setVisibility(View.VISIBLE);
                    lyt4.setVisibility(View.VISIBLE);
                }
                    return true;
////////////////////////////
              /*  if(spParentesco.getSelectedItemPosition() == 0){
                    mostrarMensaje("ESCOGER UN PARENTESCO");
                }
                if(numero.trim().equals("")){
                    mostrarMensaje("DEBE COMPLETAR P313_1 Y GUARDAR PARA CONTINUAR");
                }
                if(!numero.trim().equals("")){
                    lyt1.setVisibility(View.GONE);
                    lyt2.setVisibility(View.VISIBLE);
                    lyt3.setVisibility(View.VISIBLE);
                    lyt4.setVisibility(View.VISIBLE);
                }

                return true;*/
/////////////////////
              /* if(spParentesco.getSelectedItemPosition() == 0){
                    mostrarMensaje("ESCOGER UN PARENTESCO");
                }else {
                    lyt1.setVisibility(View.GONE);
                    lyt2.setVisibility(View.VISIBLE);
                    lyt3.setVisibility(View.VISIBLE);
                    lyt4.setVisibility(View.VISIBLE);
                }
                return true;*/

            case R.id.action_anterior_persona:
                lyt1.setVisibility(View.VISIBLE);
                lyt2.setVisibility(View.GONE);
                lyt3.setVisibility(View.GONE);
                lyt4.setVisibility(View.GONE);
                return true;
            default:
                return super.onOptionsItemSelected(item);


        }
    }

    public void llenarVariables(){
        c3_p318_f = spParentesco.getSelectedItemPosition();
        c3_p318_s = rgSexo.indexOfChild(rgSexo.findViewById(rgSexo.getCheckedRadioButtonId()));
        c3_p318_e = edtEdad.getText().toString().trim();
        c3_p318_p = rgSiNo.indexOfChild(rgSiNo.findViewById(rgSiNo.getCheckedRadioButtonId()));
    }

    public boolean validarDatos(){
        llenarVariables();

        if(lyt1.getVisibility() == View.VISIBLE){
            if (c3_p318_f == 0){mostrarMensaje("FAMILIAR: DEBE SELECCIONAR UNA OPCIÓN"); return false;}
        }
        if(lyt2.getVisibility() == View.VISIBLE && lyt3.getVisibility() == View.VISIBLE && lyt4.getVisibility() == View.VISIBLE){
            if (c3_p318_s == -1){mostrarMensaje("SEXO: DEBE MARCAR UNA OPCIÓN"); return false;}
            if (c3_p318_e.equals("")){mostrarMensaje("EDAD: DEBE INDICAR SU EDAD"); return false;}
            if (c3_p318_f==1 && c3_p318_s!=1){mostrarMensaje("SELECCIONO PAPÁ, SEXO DEBE SER HOMBRE"); return false;}
            if (c3_p318_f==2 && c3_p318_s!=2){mostrarMensaje("SELECCIONO MAMÁ, SEXO DEBE SER MUJER"); return false;}
            if (c3_p318_p == -1){mostrarMensaje("PIENSA TRAER A SU FAMILIAR: DEBE MARCAR UNA OPCIÓN"); return false;}

            if((c3_p318_f == 1 || c3_p318_f == 2) && Integer.parseInt(c3_p318_e)-edad<12 ){
                mostrarMensaje("VERIFIQUE LA RELACION DE PARENTESCO DEL FAMILIAR QUE DEJÓ EN VENEZUELA Y SU EDAD"); return false;
            }else if(c3_p318_f == 4 && edad-Integer.parseInt(c3_p318_e)<12 ){
                mostrarMensaje("VERIFIQUE LA RELACION DE PARENTESCO DEL FAMILIAR QUE DEJÓ EN VENEZUELA Y SU EDAD"); return false;
            }else if(c3_p318_f == 6 && edad-Integer.parseInt(c3_p318_e)<24 ){
                mostrarMensaje("VERIFIQUE LA RELACION DE PARENTESCO DEL FAMILIAR QUE DEJÓ EN VENEZUELA Y SU EDAD"); return false;
            }else if(c3_p318_f == 7 && Integer.parseInt(c3_p318_e)-edad<12 ){
                mostrarMensaje("VERIFIQUE LA RELACION DE PARENTESCO DEL FAMILIAR QUE DEJÓ EN VENEZUELA Y SU EDAD"); return false;
            }
        }


//        if(c3_p318_f==1 || c3_p318_f==2){
//            if(Integer.parseInt(c3_p318_e)<edad){ mostrarMensaje("EDAD: DEBE SER MAYOR O IGUAL A SU EDAD("+edad+")"); return false;}
//        }
        return true;
    }

    public void guardarDatos(){
        Data data = new Data(this);
        data.open();
        ContentValues contentValues = new ContentValues();
        contentValues.put(SQLConstantes.modulo3_c3_p318_f,c3_p318_f);
        contentValues.put(SQLConstantes.modulo3_c3_p318_s,c3_p318_s);
        contentValues.put(SQLConstantes.modulo3_c3_p318_e,c3_p318_e);
        contentValues.put(SQLConstantes.modulo3_c3_p318_p,c3_p318_p);

        if(!data.existeElemento(getNombreTabla(),_id)){
            M3Pregunta318 m3Pregunta318 =  new M3Pregunta318();
            m3Pregunta318.set_id(_id);
            m3Pregunta318.setIdEncuestado(idEncuestado);
            m3Pregunta318.setId_vivienda(idVivienda);
            m3Pregunta318.setNumero(numero);
            data.insertarElemento(getNombreTabla(),m3Pregunta318.toValues());
        }
        data.actualizarElemento(getNombreTabla(),contentValues,_id);
        data.actualizarValor(SQLConstantes.tablamodulo3,SQLConstantes.modulo3_c3_p313,"1",idEncuestado);
        data.close();
    }



    public boolean getExistePersona(String idPersona){
        boolean respuesta = false;
        Data data = new Data(this);
        data.open();
        if(data.existeElemento(getNombreTabla(),idPersona)){
            respuesta = true;
        }
        data.close();
        return respuesta;
    }



    public String getNombreTabla() {
        return SQLConstantes.tablam3p318personas;
    }

    ////////////////////////////////////////////TRACKING////////////////

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
                            status.startResolutionForResult(AgregarPersonaActivity.this, PETICION_CONFIG_UBICACION);
                        } catch (IntentSender.SendIntentException e) {
                            Log.i(LOGTAG, "Error al intentar solucionar configuración de ubicación");
                        }
                        break;
                    case LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE:
                        Log.i(LOGTAG, "No se puede cumplir la configuración de ubicación necesaria");
                        break;
                }
            }
        });
    }

    private void disableLocationUpdates() {
        try {
            LocationServices.FusedLocationApi.removeLocationUpdates(apiClient, this);
        } catch (Exception e) {
        }

    }

    public void startLocationUpdates( ) {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            //Ojo: estamos suponiendo que ya tenemos concedido el permiso.
            //Sería recomendable implementar la posible petición en caso de no tenerlo.
            Log.i(LOGTAG, "Inicio de recepción de ubicaciones");
            LocationServices.FusedLocationApi.requestLocationUpdates(apiClient, locRequest, this);
        }
    }
    public void sendUbicacion() {
        // handler =  new Handler();
        //handler.postDelayed(myRunnable, 3000);

        myRunnable = new Runnable() {
            @Override
            public void run() {


                try{

                    enableLocationUpdates();


                    if (ActivityCompat.checkSelfPermission(AgregarPersonaActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                        ActivityCompat.requestPermissions(AgregarPersonaActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, PETICION_PERMISO_LOCALIZACION);
                    }

                    Location loc = LocationServices.FusedLocationApi.getLastLocation(apiClient);

                    if(String.valueOf(loc.getLongitude()) == null){
                        longitud = "";
                    }else{
                        longitud = String.valueOf(loc.getLongitude());
                    }

                    if(String.valueOf(loc.getLatitude()) == null){
                        latitud = "";
                    }else{
                        latitud = String.valueOf(loc.getLatitude());
                    }




                    //Log.e("Longitud", "" + longitud);
                    //Log.e("Latitud", "" + latitud);
                    getLatitud(latitud);
                    getLongitud(longitud);


                    Ubicacion ubicacion = new Ubicacion();
                    ubicacion.setId(idVivienda);
                    ubicacion.setIdUsuario(idUsuario);
                    ubicacion.setUsuario(DAOUtils.getUsuarioId(idUsuario,AgregarPersonaActivity.this).getNombre());
                    ubicacion.setDni(DAOUtils.getUsuarioId(idUsuario, AgregarPersonaActivity.this).getDni());
                    ubicacion.setCargo(DAOUtils.getUsuarioId(idUsuario,AgregarPersonaActivity.this).getUsuario());
                    ubicacion.setIdDispositivo(getIdDispositivo());
                    //ubicacion.setSerial(getSerialNumber());
                    ubicacion.setLongitud(getLatitud(longitud));
                    ubicacion.setLatitud(getLongitud(latitud));
                    //ubicacion.setLongitud(longitud);
                    //ubicacion.setLatitud(latitud);
                    ubicacion.setFechaTablet(UtilsMethods.getFechaNow().getDiaInicio()+"/"+UtilsMethods.getFechaNow().getMesInicio()+"/"+UtilsMethods.getFechaNow().getAnioInicio()+"-"+UtilsMethods.getFechaNow().getHoraInicio()+":"+UtilsMethods.getFechaNow().getMinutoInicio());
                    ubicacion.setVersionApk(UtilsMethods.getVersion(AgregarPersonaActivity.this));
                    ubicacion.setBateria(""+getBateria());
                    ubicacion.setConexion("Activo");
                    ubicacion.setSegmento(nroSegmento);///
                    ubicacion.setPeriodo(vivienda_periodo);///
                    ubicacion.setConglomerado(conglomerado);///

                    //Toast.makeText(AgregarPersonaActivity.this, "LONGITUD ES"+ubicacion.getLongitud() +"\n LATITUD ES"+ubicacion.getLatitud(), Toast.LENGTH_LONG).show();


                    Log.e("prueba","***************************");
                    Log.e("prueba-Idx:",ubicacion.getId());
                    Log.e("prueba-Id Usuariox:",ubicacion.getIdUsuario());
                    Log.e("prueba-Usuariox:",ubicacion.getUsuario());
                    Log.e("prueba-DNIUsuario:",ubicacion.getDni());
                    Log.e("prueba-CargoUsuario:",ubicacion.getCargo());
                    Log.e("prueba-Dispositivo:",ubicacion.getIdDispositivo());
                    //Log.e("prueba-Serial:",ubicacion.getSerial());
                    Log.e("prueba-Latitud:",ubicacion.getLatitud());
                    Log.e("prueba-Longitud:",ubicacion.getLongitud());
                    Log.e("prueba-Fecha:",ubicacion.getFechaTablet());
                    Log.e("prueba-Version:",ubicacion.getVersionApk());
                    Log.e("prueba-Bateria:",ubicacion.getBateria());
                    Log.e("prueba-Conexion:",ubicacion.getConexion());
                    Log.e("prueba-Segmento:",ubicacion.getSegmento());
                    Log.e("prueba-Periodo:",ubicacion.getPeriodo());
                    Log.e("prueba-Conglomerado:",ubicacion.getConglomerado());
                    Log.e("prueba","***************************");

//DESARROLLO
/*                    String url =  "http://200.123.3.26/wsenpove/2022/USP_WSENPOVE2022_TRACKING_USUARIO_DEV.php?ID_VIVIENDA="+ubicacion.getId()+"&ID_USUARIO="+ubicacion.getIdUsuario()
                            +"&NOM_USUARIO="+ubicacion.getUsuario()+"&CARGO_USUARIO="+ubicacion.getCargo()+"&GPS_LATITUD="+ubicacion.getLatitud()+"&GPS_LONGITUD="+ubicacion.getLongitud()+"&FECHA_REGISTRO="+ubicacion.getFechaTablet()
                            +"&VER_APK="+ubicacion.getVersionApk()+"&BATERIA_STATUS="+ubicacion.getBateria()+"&CONEXION="+ubicacion.getConexion()+"&SEGMENTO="+ubicacion.getSegmento()+"&PERIODO="+ubicacion.getPeriodo()+"&CONGLOMERADO="+ubicacion.getConglomerado();*/
//PRODUCCION
                    String url =  "http://200.123.3.26/wsenpove/2022/USP_WSENPOVE2022_TRACKING_USUARIO.php?ID_VIVIENDA="+ubicacion.getId()+"&ID_USUARIO="+ubicacion.getIdUsuario()
                            +"&NOM_USUARIO="+ubicacion.getUsuario()+"&CARGO_USUARIO="+ubicacion.getCargo()+"&GPS_LATITUD="+ubicacion.getLatitud()+"&GPS_LONGITUD="+ubicacion.getLongitud()+"&FECHA_REGISTRO="+ubicacion.getFechaTablet()
                            +"&VER_APK="+ubicacion.getVersionApk()+"&BATERIA_STATUS="+ubicacion.getBateria()+"&CONEXION="+ubicacion.getConexion()+"&SEGMENTO="+ubicacion.getSegmento()+"&PERIODO="+ubicacion.getPeriodo()+"&CONGLOMERADO="+ubicacion.getConglomerado();


                    url = url.replace(" ","%20");

                    Log.e ("LINK", ""+url);

                    jsonObjectRequest= new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject jsonObject) {
                            //Toast.makeText(getApplicationContext(),"Se pudo mano", Toast.LENGTH_LONG).show();

                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError volleyError) {
                            //Toast.makeText(getApplicationContext(),"Con fe", Toast.LENGTH_LONG).show();

                        }
                    });

                    request.add(jsonObjectRequest);

                    handler.postDelayed(myRunnable, TIEMPO);

                }catch (Exception e){
                    //Toast.makeText(AgregarResidenteActivity.this, "se cayo", Toast.LENGTH_LONG).show();
                }


            }
        };
        handler.postDelayed(myRunnable, TIEMPO);

//        handler.postDelayed(new Runnable() {
//            public void run() {
//                Ubicacion ubicacion = new Ubicacion();
//                ubicacion.setId(""+null);
//                ubicacion.setIdUsuario(idUsuario);
//                ubicacion.setUsuario(DAOUtils.getUsuarioId(idUsuario,context).getNombre());
//                ubicacion.setIdDispositivo(getIdDispositivo());
//                ubicacion.setSerial(getSerialNumber());
//                ubicacion.setLongitud(getLatitud(longitud));
//                ubicacion.setLatitud(getLongitud(latitud));
//                ubicacion.setFechaTablet(UtilsMethods.getFechaNow().getDiaInicio()+"/"+UtilsMethods.getFechaNow().getMesInicio()+"/"+UtilsMethods.getFechaNow().getAnioInicio()+"-"+UtilsMethods.getFechaNow().getHoraInicio()+":"+UtilsMethods.getFechaNow().getMinutoInicio());
//                ubicacion.setVersionApk(UtilsMethods.getVersion(context));
//                ubicacion.setBateria(""+getBateria());
//                ubicacion.setConexion("Activo");
//
//
//                Log.e("prueba","***************************");
//                Log.e("prueba-Id:",ubicacion.getId());
//                Log.e("prueba-Id Usuario:",ubicacion.getIdUsuario());
//                Log.e("prueba-Usuario:",ubicacion.getUsuario());
//                Log.e("prueba-Dispositivo:",ubicacion.getIdDispositivo());
//                Log.e("prueba-Serial:",ubicacion.getSerial());
//                Log.e("prueba-Latitud:",ubicacion.getLatitud());
//                Log.e("prueba-Longitud:",ubicacion.getLongitud());
//                Log.e("prueba-Fecha:",ubicacion.getFechaTablet());
//                Log.e("prueba-Version:",ubicacion.getVersionApk());
//                Log.e("prueba-Bateria:",ubicacion.getBateria());
//                Log.e("prueba-Conexion:",ubicacion.getConexion());
//                Log.e("prueba","***************************");
//                handler.postDelayed(this, TIEMPO);
//            }
//
//        }, TIEMPO);

    }

    public float getBateria(){
        IntentFilter ifilter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
        Intent batteryStatus = this.registerReceiver(null, ifilter);
        int level = batteryStatus.getIntExtra(BatteryManager.EXTRA_LEVEL, -1);
        int scale = batteryStatus.getIntExtra(BatteryManager.EXTRA_SCALE, -1);

        float batteryPct = level * 100 / (float)scale;
        return batteryPct;
    }
    public static String getSerialNumber() {
        String serialNumber;

        try {
            Class<?> c = Class.forName("android.os.SystemProperties");
            Method get = c.getMethod("get", String.class);

            // (?) Lenovo Tab (https://stackoverflow.com/a/34819027/1276306)
            serialNumber = (String) get.invoke(c, "gsm.sn1");

            if (serialNumber.equals(""))
                // Samsung Galaxy S5 (SM-G900F) : 6.0.1
                // Samsung Galaxy S6 (SM-G920F) : 7.0
                // Samsung Galaxy Tab 4 (SM-T530) : 5.0.2
                // (?) Samsung Galaxy Tab 2 (https://gist.github.com/jgold6/f46b1c049a1ee94fdb52)
                serialNumber = (String) get.invoke(c, "ril.serialnumber");

            if (serialNumber.equals(""))
                // Archos 133 Oxygen : 6.0.1
                // Google Nexus 5 : 6.0.1
                // Hannspree HANNSPAD 13.3" TITAN 2 (HSG1351) : 5.1.1
                // Honor 5C (NEM-L51) : 7.0
                // Honor 5X (KIW-L21) : 6.0.1
                // Huawei M2 (M2-801w) : 5.1.1
                // (?) HTC Nexus One : 2.3.4 (https://gist.github.com/tetsu-koba/992373)
                serialNumber = (String) get.invoke(c, "ro.serialno");

            if (serialNumber.equals(""))
                // (?) Samsung Galaxy Tab 3 (https://stackoverflow.com/a/27274950/1276306)
                serialNumber = (String) get.invoke(c, "sys.serialnumber");

            if (serialNumber.equals(""))
                // Archos 133 Oxygen : 6.0.1
                // Hannspree HANNSPAD 13.3" TITAN 2 (HSG1351) : 5.1.1
                // Honor 9 Lite (LLD-L31) : 8.0
                // Xiaomi Mi 8 (M1803E1A) : 8.1.0
                serialNumber = Build.SERIAL;

            // If none of the methods above worked
            if (serialNumber.equals(Build.UNKNOWN))
                serialNumber = null;
        } catch (Exception e) {
            e.printStackTrace();
            serialNumber = null;
        }

        return serialNumber;
    }
    public String getIdDispositivo(){
        //import android.provider.Settings.Secure;
        String id = Settings.Secure.getString(this.getContentResolver(), Settings.Secure.ANDROID_ID);
        return id;
    }
    public String getLatitud(String latitud){
        String latituds = "99.999999";
        try {
            if(!latitud.equals("") || latitud!=null){
                latituds = latitud;
            }
        }catch (NullPointerException e){

        }

        return latituds;
    }

    public String getLongitud(String longitud){
        String longituds = "99.999999";
        try {
            if(!longitud.equals("") || longitud!=null){
                longituds = longitud;
            }
        }catch (NullPointerException e){

        }

        return longituds;
    }




    @Override
    public void onConnected(@Nullable Bundle bundle) {

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    public void onLocationChanged(Location location) {

    }

    @Override
    public void onErrorResponse(VolleyError volleyError) {

    }

    @Override
    public void onResponse(JSONObject jsonObject) {

    }
}

