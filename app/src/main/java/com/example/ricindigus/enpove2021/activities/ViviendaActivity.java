package com.example.ricindigus.enpove2021.activities;

import android.Manifest;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.location.Location;
import android.net.Uri;
import android.os.BatteryManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.text.InputFilter;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.ricindigus.enpove2021.R;
import com.example.ricindigus.enpove2021.activities.agregacion.AgregarResidenteActivity;
import com.example.ricindigus.enpove2021.fragments.vivienda.FragmentCaratula;
import com.example.ricindigus.enpove2021.fragments.vivienda.FragmentHogares;
import com.example.ricindigus.enpove2021.modelo.DAOUtils;
import com.example.ricindigus.enpove2021.modelo.Data;
import com.example.ricindigus.enpove2021.modelo.SQLConstantes;
import com.example.ricindigus.enpove2021.modelo.pojos.Caratula;
import com.example.ricindigus.enpove2021.modelo.pojos.Hogar;
import com.example.ricindigus.enpove2021.modelo.pojos.Ubicacion;
import com.example.ricindigus.enpove2021.modelo.pojos.ViviendaItem;
import com.example.ricindigus.enpove2021.util.FragmentPagina;
import com.example.ricindigus.enpove2021.util.InputFilterSoloLetras;
import com.example.ricindigus.enpove2021.util.TipoFragmentVivienda;
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
import java.util.ArrayList;
import java.util.Calendar;

public class ViviendaActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, GoogleApiClient.OnConnectionFailedListener,
        GoogleApiClient.ConnectionCallbacks,
        LocationListener, Response.Listener<JSONObject>, Response.ErrorListener {

    private static final String LOGTAG = "android-localizacion";
    private static final int PETICION_PERMISO_LOCALIZACION = 101;
    private static final int PETICION_CONFIG_UBICACION = 201;
    private GoogleApiClient apiClient;
    private LocationRequest locRequest;

    private final int TIEMPO = 60000;
    //private final int TIEMPO = 5000;
    Handler handler = new Handler();
    Runnable myRunnable;

    RequestQueue request;
    JsonObjectRequest jsonObjectRequest;

    //

    private String idVivienda;
    public static String vivienda_anio;
    public static String vivienda_mes;
    public static String vivienda_periodo;
    public static String vivienda_zona;
    public static String nroVivienda;
    public static String nroSegmento;
    public static String conglomerado;
    private String nickUsuario;
    private String idUsuario;

    ArrayList<ViviendaItem> listaViviendas;





    private TextView btnAtras;
    private TextView btnSiguiente;
    int tFragment = 1;
    int tFragmentAnterior = 1;
    FragmentPagina fragmentActual;
    NavigationView navigationView;

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

    private Data dataTablas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vivienda);
        btnAtras = (TextView) findViewById(R.id.boton_anterior);
        btnSiguiente = (TextView) findViewById(R.id.boton_siguiente);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        idVivienda = getIntent().getExtras().getString("idVivienda");
        nickUsuario = getIntent().getExtras().getString("nickUsuario");
        idUsuario = getIntent().getExtras().getString("idUsuario");
        vivienda_anio = getIntent().getExtras().getString("vivienda_anio");
        vivienda_mes = getIntent().getExtras().getString("vivienda_mes");
        vivienda_periodo = getIntent().getExtras().getString("vivienda_periodo");
        vivienda_zona = getIntent().getExtras().getString("vivienda_zona");
        nroVivienda = getIntent().getExtras().getString("nroVivienda");
        nroSegmento = getIntent().getExtras().getString("nroSegmento");
        conglomerado = getIntent().getExtras().getString("conglomerado");


        apiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this, this)
                .addConnectionCallbacks(this)
                .addApi(LocationServices.API)
                .build();

        request = Volley.newRequestQueue(getApplicationContext());

        sendUbicacion();


        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("VIVIENDA N° " + nroVivienda);
        //getSupportActionBar().setSubtitle("ZONA N° " + vivienda_zona);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        View headerView = navigationView.getHeaderView(0);
        TextView txtHeaderVivienda = (TextView) headerView.findViewById(R.id.txtTituloVivienda);
        TextView txtHeaderUsuario = (TextView) headerView.findViewById(R.id.txtTituloUsuario);
        txtHeaderVivienda.setText("VIVIENDA N° " + nroVivienda);
        txtHeaderUsuario.setText(nickUsuario);
        navigationView.setNavigationItemSelectedListener(this);

        btnSiguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ocultarTeclado(btnSiguiente);
                if(fragmentActual.validarDatos()){
                    fragmentActual.guardarDatos();
                    tFragment++;
                    habilitarFragment(tFragment);
                    while(!setFragment(tFragment,1)){
                        tFragment++;
                        habilitarFragment(tFragment);
                    }
                }
            }
        });

        btnAtras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ocultarTeclado(btnAtras);
                tFragment--;
                setFragment(tFragment,-1);

            }
        });
        setFragment(TipoFragmentVivienda.CARATULA,1);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        sendUbicacion();

    }

    public String getNickUsuario() {
        return nickUsuario;
    }

/*    public String getIdUsuario() {
        return idUsuario;
    }*/

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            salirActivityVivienda();
        }
    }

    public void habilitarFragment(int tipoFragment){
        Data data =  new Data(this);
        data.open();
        switch (tipoFragment){
            case TipoFragmentVivienda.CARATULA:
                if (data.getValor(SQLConstantes.tablafragmentsvivienda,SQLConstantes.fragments_vivienda_caratula,idVivienda).equals("0"))
                    data.actualizarValor(SQLConstantes.tablafragmentsvivienda,SQLConstantes.fragments_vivienda_caratula,"1",idVivienda);
                break;
            case TipoFragmentVivienda.HOGARES:
                if (data.getValor(SQLConstantes.tablafragmentsvivienda,SQLConstantes.fragments_vivienda_hogares,idVivienda).equals("0"))
                    data.actualizarValor(SQLConstantes.tablafragmentsvivienda,SQLConstantes.fragments_vivienda_hogares,"1",idVivienda);
                break;
        }
    }

    public void ocultarTeclado(View view){
        InputMethodManager mgr = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        mgr.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    public boolean seteoValido(int tipoFragment){
        boolean valido = true;
        Data data =  new Data(this);
        data.open();
        switch (tipoFragment){
            case TipoFragmentVivienda.CARATULA:
                if (data.getValor(SQLConstantes.tablafragmentsvivienda,SQLConstantes.fragments_vivienda_caratula,idVivienda).equals("0")) valido = false;
                break;
            case TipoFragmentVivienda.HOGARES:
                if (data.getValor(SQLConstantes.tablafragmentsvivienda,SQLConstantes.fragments_vivienda_hogares,idVivienda).equals("0")) valido = false;
                break;
        }
        return valido;
    }

    public boolean setFragment(int tipoFragment, int direccion){
        if (seteoValido(tipoFragment)){
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            if(direccion != 0){
                if(direccion > 0){
                    fragmentTransaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left);
                }else{
                    fragmentTransaction.setCustomAnimations(R.anim.enter_from_left, R.anim.exit_to_right);
                }
            }
            btnAtras.setVisibility(View.VISIBLE);
            btnSiguiente.setVisibility(View.VISIBLE);
            switch (tipoFragment){
                case TipoFragmentVivienda.CARATULA:
                    btnAtras.setVisibility(View.GONE);
                    FragmentCaratula fragmentCaratula = new FragmentCaratula(idVivienda,vivienda_mes,vivienda_anio, vivienda_zona,vivienda_periodo,idUsuario,nroSegmento,apiClient,ViviendaActivity.this);
                    fragmentTransaction.replace(R.id.fragment_layout, fragmentCaratula);
                    fragmentActual = fragmentCaratula;
                    tFragment = TipoFragmentVivienda.CARATULA;
                    navigationView.setCheckedItem(R.id.nav_caratula);
                    break;
                case TipoFragmentVivienda.HOGARES:
                    btnSiguiente.setVisibility(View.GONE);
                    FragmentHogares fragmentHogares = new FragmentHogares(idVivienda,idUsuario,nroSegmento,vivienda_periodo,conglomerado,ViviendaActivity.this);
                    fragmentTransaction.replace(R.id.fragment_layout, fragmentHogares);
                    fragmentActual = fragmentHogares;
                    tFragment = TipoFragmentVivienda.HOGARES;
                    navigationView.setCheckedItem(R.id.nav_hogares);
                    break;
            }
            fragmentTransaction.commit();
            return true;
        }
        return false;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.vivienda, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
//        int id = item.getItemId();
//        if (id == R.id.action_volver_marco) {
//            salirActivityVivienda();
//            return true;
//        }
//        return super.onOptionsItemSelected(item);

        final int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_volver_marco) {
            salirActivityVivienda();
            return true;
        }else if (id == R.id.action_registrar_observacion) {
            AlertDialog.Builder dialog = new AlertDialog.Builder(this);
            final View dialogView = this.getLayoutInflater().inflate(R.layout.dialog_observaciones, null);
            LinearLayout lytObservaciones = dialogView.findViewById(R.id.dialog_lytObservaciones);
            final EditText edtObservaciones = dialogView.findViewById(R.id.dialog_edtObservaciones);
            edtObservaciones.setFilters(new InputFilter[]{new InputFilter.AllCaps()});
            dialog.setView(dialogView);
            dialog.setTitle("OBSERVACIONES CARÁTULA");
            dialog.setPositiveButton("Guardar", null);
            dialog.setNegativeButton("Cancelar", null);
            final AlertDialog alertDialog = dialog.create();
            alertDialog.setOnShowListener(new DialogInterface.OnShowListener() {
                @Override
                public void onShow(DialogInterface dialogInterface) {
                    Data data = new Data(ViviendaActivity.this);
                    data.open();
                    edtObservaciones.setText(data.getValor(SQLConstantes.tablacaratula,SQLConstantes.caratula_observaciones,idVivienda));
                    data.close();
                    Button b = alertDialog.getButton(AlertDialog.BUTTON_POSITIVE);
                    b.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Data data = new Data(ViviendaActivity.this);
                            data.open();
                            data.actualizarValor(SQLConstantes.tablacaratula,SQLConstantes.caratula_observaciones,edtObservaciones.getText().toString(),idVivienda);
                            data.close();
                            alertDialog.dismiss();
                        }
                    });
                }
            });
            alertDialog.show();
            return true;
        }else if (id == R.id.action_finalizar_vivienda){
            Data data = new Data(ViviendaActivity.this);
            data.open();
            if (coberturaViviendaConMensaje()){
                data.actualizarValor(SQLConstantes.tablamarco,SQLConstantes.marco_estado,"1",idVivienda);
                finish();
            }
            else data.actualizarValor(SQLConstantes.tablamarco,SQLConstantes.marco_estado,"0'",idVivienda);
            data.close();
            return true;
        }else if (id == R.id.action_resultado_vivienda){
             finalizarVisita();
            return true;
        }else if (id == R.id.action_ubicacion_vivienda){
            Intent intent = new Intent(getApplicationContext(), UbicacionViviendaActivity.class);
            intent.putExtra("idVivienda",idVivienda);
            intent.putExtra("vivienda", nroVivienda);
            intent.putExtra("idUsuario", idUsuario);
            intent.putExtra("segmento", nroSegmento);
            intent.putExtra("periodo", vivienda_periodo);
            intent.putExtra("conglomerado", conglomerado);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);

    }

    public boolean coberturaViviendaConMensaje(){
        Data data = new Data(ViviendaActivity.this);
        data.open();
        String cobCaratula = data.getValor(SQLConstantes.tablacaratula,SQLConstantes.caratula_cobertura,idVivienda);
        if (!cobCaratula.equals("1")){
            mostrarMensaje("FALTA COBERTURAR LA CARÁTULA, NO SE PUEDE FINALIZAR VIVIENDA");return false;
        }
        if (data.getAllHogaresVivienda(idVivienda).size() > 0){
//            for (Hogar hogar : data.getAllHogaresVivienda(idVivienda)){
//                if (!hogar.getCobertura().equals("1")) {
//                    mostrarMensaje("FALTA COBERTURAR EL HOHAR " + hogar.getNumero());return false;
//                }
//            }
        }else {
            mostrarMensaje("NO REGISTRO HOGARES, NO SE PUEDE COBERTURAR ESTE MODULO");return false;
        }
        data.close();
        return true;
    }

    public boolean coberturaVivienda(){
        Data data = new Data(ViviendaActivity.this);
        data.open();
        String cobCaratula = data.getValor(SQLConstantes.tablacaratula,SQLConstantes.caratula_cobertura,idVivienda);
        if (!cobCaratula.equals("1")) return false;
        if (data.getAllHogaresVivienda(idVivienda).size() > 0){
            for (Hogar hogar : data.getAllHogaresVivienda(idVivienda)){
                if (!hogar.getCobertura().equals("1")) return false;
            }
        }else return false;
        data.close();
        return true;
    }

    public void mostrarMensaje(String m){
        final AlertDialog.Builder builder = new AlertDialog.Builder(ViviendaActivity.this);
        builder.setMessage(m);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.dismiss();
            }
        });
        final AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        boolean correcto = true;
        switch (id){
            case R.id.nav_caratula:
                if (!setFragment(TipoFragmentVivienda.CARATULA,0))correcto=false;
                break;
            case R.id.nav_hogares:
                if (!setFragment(TipoFragmentVivienda.HOGARES,0))correcto=false;
                break;
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return correcto;
    }

    public void salirActivityVivienda(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("¿Está seguro que desea volver al marco y salir de la encuesta?")
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
                                Data data = new Data(ViviendaActivity.this);
                                data.open();
                                if (coberturaVivienda()) data.actualizarValor(SQLConstantes.tablamarco,SQLConstantes.marco_estado,"1",idVivienda);
                                else data.actualizarValor(SQLConstantes.tablamarco,SQLConstantes.marco_estado,"0",idVivienda);
                                data.close();
                                finish();
                            }
                        });
        AlertDialog alert = builder.create();
        alert.show();
    }

    public void finalizarVisita(){
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        final View dialogView = this.getLayoutInflater().inflate(R.layout.dialog_finalizar_visita_vivienda, null);
        final LinearLayout lytDialog = (LinearLayout) dialogView.findViewById(R.id.dialog_finalizar_visita_lyt_viv);
        final Spinner spResultado = (Spinner) dialogView.findViewById(R.id.dialog_finalizar_visita_spResultado_viv);
        final EditText edtEspecifique = (EditText) dialogView.findViewById(R.id.dialog_finalizar_visita_edtEspecifique_viv);
        final CardView cardViewEspecifique = (CardView) dialogView.findViewById(R.id.dialog_cardview_finalizar_especifique_viv);
        String especifique = "";



        edtEspecifique.setFilters(new InputFilter[]{new InputFilter.AllCaps(),new InputFilter.LengthFilter(100), new InputFilterSoloLetras()});

        spResultado.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long l) {
                if(pos == 12){
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



        alert.setTitle("RESULTADO FINAL DE VIVIENDA");
        alert.setView(dialogView);
        alert.setPositiveButton("Finalizar",null);
        alert.setNegativeButton("Cancelar",null);
        final AlertDialog alertDialog = alert.create();

        alertDialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialogInterface) {
                ocultarTeclado(lytDialog);

                Button btnFinalizarVisita = alertDialog.getButton(AlertDialog.BUTTON_POSITIVE);
                btnFinalizarVisita.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        boolean valido = false;
                        boolean estado = true;
                        String mensaje = "";

                        /*Validaciones cuando es diferente de completa*/
                        if(spResultado.getSelectedItemPosition() != 1){
                            if(spResultado.getSelectedItemPosition() == 0){
                                estado = false;
                                if(mensaje.equals("")) mensaje = "DEBE INDICAR EL RESULTADO DE LA VISITAS";
                            }
                        }

                        valido = estado;

                        if(valido){
                            boolean finalizacion = true;

                            if (finalizacion){
//                            //CONTENVALUES DE VISITA RESULTADO FINAL
                                final Calendar cal = Calendar.getInstance();

                                ContentValues contentValuesFinal = new ContentValues();
                                contentValuesFinal.put(SQLConstantes.caratula_fecha_final_aa, UtilsMethods.getFechaActual().getAnioInicio());
                                contentValuesFinal.put(SQLConstantes.caratula_fecha_final_mm,UtilsMethods.getFechaActual().getMesInicio());
                                contentValuesFinal.put(SQLConstantes.caratula_fecha_final_dd,UtilsMethods.getFechaActual().getDiaInicio());
                                contentValuesFinal.put(SQLConstantes.caratula_resultado_final,spResultado.getSelectedItemPosition()+"");
                                contentValuesFinal.put(SQLConstantes.caratula_resultado_final_o,edtEspecifique.getText().toString()+"");
                                //FALTA GUARDAR RESULTADO ESPECIFIQUE DE OTRO

                                dataTablas = new Data(getApplicationContext());
                                dataTablas.open();

                                if(!dataTablas.existeElemento(SQLConstantes.tablacaratula, idVivienda)){
                                    dataTablas.actualizarElemento(SQLConstantes.tablacaratula,contentValuesFinal, idVivienda);
                                }else{

                                }
                                dataTablas.close();
                                alertDialog.dismiss();
                            }
                        }else Toast.makeText(getApplicationContext(), mensaje, Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
        alertDialog.show();
    }
    public static String checkDigito (int number) {
        return number <= 9 ? "0" + number : String.valueOf(number);
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
///////////////////////////////////////TRACKING////////////////////////////////
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
                        status.startResolutionForResult(ViviendaActivity.this, PETICION_CONFIG_UBICACION);
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


                if (ActivityCompat.checkSelfPermission(ViviendaActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(ViviendaActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, PETICION_PERMISO_LOCALIZACION);
                }

                Location loc = LocationServices.FusedLocationApi.getLastLocation(apiClient);


                String longitud = String.valueOf(loc.getLongitude());
                String latitud = String.valueOf(loc.getLatitude());
                //Log.e("Longitud", "" + longitud);
                //Log.e("Latitud", "" + latitud);
                getLatitud(latitud);
                getLongitud(longitud);

                Ubicacion ubicacion = new Ubicacion();
                ubicacion.setId(idVivienda);
                ubicacion.setIdUsuario(idUsuario);
                ubicacion.setUsuario(DAOUtils.getUsuarioId(idUsuario,ViviendaActivity.this).getNombre());
                ubicacion.setDni(DAOUtils.getUsuarioId(idUsuario,ViviendaActivity.this).getDni());
                ubicacion.setCargo(DAOUtils.getUsuarioId(idUsuario,ViviendaActivity.this).getUsuario());
                ubicacion.setIdDispositivo(getIdDispositivo());
                //ubicacion.setSerial(getSerialNumber());
                ubicacion.setLongitud(getLatitud(longitud));
                ubicacion.setLatitud(getLongitud(latitud));
                //ubicacion.setLongitud(longitud);
                //ubicacion.setLatitud(latitud);
                ubicacion.setFechaTablet(UtilsMethods.getFechaNow().getDiaInicio()+"/"+UtilsMethods.getFechaNow().getMesInicio()+"/"+UtilsMethods.getFechaNow().getAnioInicio()+"-"+UtilsMethods.getFechaNow().getHoraInicio()+":"+UtilsMethods.getFechaNow().getMinutoInicio());
                ubicacion.setVersionApk(UtilsMethods.getVersion(ViviendaActivity.this));
                ubicacion.setBateria(""+getBateria());
                ubicacion.setConexion("Activo");
                ubicacion.setSegmento(nroSegmento);///
                ubicacion.setPeriodo(vivienda_periodo);///
                ubicacion.setConglomerado(conglomerado);///

                //Toast.makeText(ViviendaActivity.this, "LONGITUD ES"+ubicacion.getLongitud() +"\n LATITUD ES"+ubicacion.getLatitud(), Toast.LENGTH_LONG).show();


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


// DESARROLLO
                String url =  "http://200.123.3.26/wsenpove/2022/USP_WSENPOVE2022_TRACKING_USUARIO_DEV.php?ID_VIVIENDA="+ubicacion.getId()+"&ID_USUARIO="+ubicacion.getIdUsuario()
                            +"&NOM_USUARIO="+ubicacion.getUsuario()+"&CARGO_USUARIO="+ubicacion.getCargo()+"&GPS_LATITUD="+ubicacion.getLatitud()+"&GPS_LONGITUD="+ubicacion.getLongitud()+"&FECHA_REGISTRO="+ubicacion.getFechaTablet()
                            +"&VER_APK="+ubicacion.getVersionApk()+"&BATERIA_STATUS="+ubicacion.getBateria()+"&CONEXION="+ubicacion.getConexion()+"&SEGMENTO="+ubicacion.getSegmento()+"&PERIODO="+ubicacion.getPeriodo()+"&CONGLOMERADO="+ubicacion.getConglomerado();

//PRODUCCION
/*              String url =  "http://200.123.3.26/wsenpove/2022/USP_WSENPOVE2022_TRACKING_USUARIO.php?ID_VIVIENDA="+ubicacion.getId()+"&ID_USUARIO="+ubicacion.getIdUsuario()
                            +"&NOM_USUARIO="+ubicacion.getUsuario()+"&CARGO_USUARIO="+ubicacion.getCargo()+"&GPS_LATITUD="+ubicacion.getLatitud()+"&GPS_LONGITUD="+ubicacion.getLongitud()+"&FECHA_REGISTRO="+ubicacion.getFechaTablet()
                            +"&VER_APK="+ubicacion.getVersionApk()+"&BATERIA_STATUS="+ubicacion.getBateria()+"&CONEXION="+ubicacion.getConexion()+"&SEGMENTO="+ubicacion.getSegmento()+"&PERIODO="+ubicacion.getPeriodo()+"&CONGLOMERADO="+ubicacion.getConglomerado();*/


                url = url.replace(" ","%20");

                Log.e ("LINK", ""+url);

                jsonObjectRequest= new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                       // Toast.makeText(getApplicationContext(),"Se pudo mano", Toast.LENGTH_LONG).show();

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
                Log.e("error", ""+e.toString());
                //Toast.makeText(getActivity().getApplicationContext(), "se cayo", Toast.LENGTH_LONG).show();
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
