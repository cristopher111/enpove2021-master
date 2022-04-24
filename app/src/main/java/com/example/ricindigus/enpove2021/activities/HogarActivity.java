package com.example.ricindigus.enpove2021.activities;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.location.Location;
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
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.ricindigus.enpove2021.R;
import com.example.ricindigus.enpove2021.fragments.hogar.FragmentFuncionarios;
import com.example.ricindigus.enpove2021.fragments.hogar.FragmentVisitasEncuestador;
import com.example.ricindigus.enpove2021.fragments.hogar.FragmentVisitasSupervisor;
import com.example.ricindigus.enpove2021.fragments.modulo1.FragmentP101P107;
import com.example.ricindigus.enpove2021.fragments.modulo1.FragmentP108P113;
import com.example.ricindigus.enpove2021.fragments.modulo2.FragmentP201P206;
import com.example.ricindigus.enpove2021.modelo.DAOUtils;
import com.example.ricindigus.enpove2021.modelo.Data;
import com.example.ricindigus.enpove2021.modelo.SQLConstantes;
import com.example.ricindigus.enpove2021.modelo.pojos.Hogar;
import com.example.ricindigus.enpove2021.modelo.pojos.Residente;
import com.example.ricindigus.enpove2021.modelo.pojos.Ubicacion;
import com.example.ricindigus.enpove2021.modelo.pojos.Usuario;
import com.example.ricindigus.enpove2021.util.FragmentPagina;
import com.example.ricindigus.enpove2021.util.TipoFragmentHogar;
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

public class HogarActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, GoogleApiClient.OnConnectionFailedListener,
        GoogleApiClient.ConnectionCallbacks,
        LocationListener {

    private static final String LOGTAG = "android-localizacion";
    private static final int PETICION_PERMISO_LOCALIZACION = 101;
    private static final int PETICION_CONFIG_UBICACION = 201;
    private GoogleApiClient apiClient;
    private LocationRequest locRequest;
    String latitud;
    String longitud;

    private final int TIEMPO = 180000;
    Handler handler = new Handler();
    Runnable myRunnable;
    RequestQueue request;
    JsonObjectRequest jsonObjectRequest;
    ///

    private String idHogar;
    private String idVivienda;
    private String nickUsuario;
    private String idUsuario;
    private String idCargo;
    private String nroSegmento;
    private String vivienda_periodo;
    private String conglomerado;
    ////Agregado 17/12/21
    private String idEncuestado;

    private Hogar hogar;
    private TextView btnAtras;
    private TextView btnSiguiente;
    int tFragment = 1;
    int fragment = 1;
    FragmentPagina fragmentActual;
    NavigationView navigationView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hogar);
        btnAtras = (TextView) findViewById(R.id.boton_anterior);
        btnSiguiente = (TextView) findViewById(R.id.boton_siguiente);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        idHogar = getIntent().getExtras().getString("idHogar");
        nickUsuario = getIntent().getExtras().getString("nickUsuario");
        idUsuario = getIntent().getExtras().getString("idUsuario");
        nroSegmento = getIntent().getExtras().getString("segmento");
        vivienda_periodo = getIntent().getExtras().getString("periodo");
        conglomerado = getIntent().getExtras().getString("conglomerado");



        apiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this, this)
                .addConnectionCallbacks(this)
                .addApi(LocationServices.API)
                .build();


        request = Volley.newRequestQueue(getApplicationContext());


        sendUbicacion();



        setUpdateFragmentSupervisor();

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("HOGAR N° " + hogar.getNumero());
        getSupportActionBar().setSubtitle("JEFE: " + hogar.getNom_ape());

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        View headerView = navigationView.getHeaderView(0);
        TextView txtHeaderVivienda = (TextView) headerView.findViewById(R.id.txtTituloHogar);
        TextView txtHeaderUsuario = (TextView) headerView.findViewById(R.id.txtTituloUsuario);
        txtHeaderVivienda.setText("VIVIENDA N° " + idVivienda);
        txtHeaderUsuario.setText(nickUsuario);

        navigationView.setNavigationItemSelectedListener(this);

        tFragment = getIntent().getIntExtra("tfragment",fragment);


       /* if(tFragment == 6){
            habilitarFragment(TipoFragmentHogar.P201P206);
            setFragment(TipoFragmentHogar.P201P206,0);
            Log.e("fragment",""+tFragment);       }*/




        btnSiguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnSiguiente.setEnabled(false);
                ocultarTeclado(btnSiguiente);
                if(fragmentActual.validarDatos()){
                    Log.e("moloko1:",""+fragmentActual.getId());
                    Log.e("moloko2:",""+tFragment);
                    Log.e("moloko3:",""+validarP15());
                    fragmentActual.guardarDatos();
                    tFragment++;
                    Log.e("moloko4:",""+tFragment);
                    if(tFragment == 4 && validarP15()){
                        Log.e("molokoX:","A");
                        finalizarEncuestaP15();
                        tFragment--;
                    }else {
                        Log.e("molokoX:","B");
                        habilitarFragment(tFragment);
                        while(!setFragment(tFragment,1)){
                            Log.e("molokoX:","C");
                            tFragment++;
                            habilitarFragment(tFragment);
                        }
                    }

                }
                btnSiguiente.setEnabled(true);
                Log.e("tFragment",""+tFragment);

            }
        });

        btnAtras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnAtras.setEnabled(false);
                ocultarTeclado(btnAtras);
                tFragment--;
//                setFragment(tFragment,-1);
                while(!setFragment(tFragment,-1)){
                    tFragment--;
                }
                btnAtras.setEnabled(true);
            }
        });

        setFragment(TipoFragmentVivienda.CARATULA,1);
    }

    @Override
    protected void onStart() {
        super.onStart();
       // sendUbicacion();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            salirActivityHogar();
        }
    }

    public void habilitarFragment(int tipoFragment){
        Data data =  new Data(this);
        data.open();
        switch (tipoFragment){
            case TipoFragmentHogar.VISITAS_ENCUESTADOR:
                if (data.getValor(SQLConstantes.tablafragmentshogar,SQLConstantes.fragments_hogar_visitas_encuestador,idHogar).equals("0"))
                    data.actualizarValor(SQLConstantes.tablafragmentshogar,SQLConstantes.fragments_hogar_visitas_encuestador,"1",idHogar);
                break;
            case TipoFragmentHogar.VISITAS_SUPERVISOR:
                if (data.getValor(SQLConstantes.tablafragmentshogar,SQLConstantes.fragments_hogar_visitas_supervisor,idHogar).equals("0"))
                    data.actualizarValor(SQLConstantes.tablafragmentshogar,SQLConstantes.fragments_hogar_visitas_supervisor,"1",idHogar);
                break;
            case TipoFragmentHogar.FUNCIONARIOS:
                if (data.getValor(SQLConstantes.tablafragmentshogar,SQLConstantes.fragments_hogar_funcionarios,idHogar).equals("0"))
                    data.actualizarValor(SQLConstantes.tablafragmentshogar,SQLConstantes.fragments_hogar_funcionarios,"1",idHogar);
                break;
            case TipoFragmentHogar.P101P107:
                if (data.getValor(SQLConstantes.tablafragmentshogar,SQLConstantes.fragments_hogar_p101p107,idHogar).equals("0"))
                    data.actualizarValor(SQLConstantes.tablafragmentshogar,SQLConstantes.fragments_hogar_p101p107,"1",idHogar);
                break;
            case TipoFragmentHogar.P108P113:
                if (data.getValor(SQLConstantes.tablafragmentshogar,SQLConstantes.fragments_hogar_p108p113,idHogar).equals("0"))
                    data.actualizarValor(SQLConstantes.tablafragmentshogar,SQLConstantes.fragments_hogar_p108p113,"1",idHogar);
                break;
            case TipoFragmentHogar.P201P206:
                if (data.getValor(SQLConstantes.tablafragmentshogar,SQLConstantes.fragments_hogar_p201p207,idHogar).equals("0"))
                    data.actualizarValor(SQLConstantes.tablafragmentshogar,SQLConstantes.fragments_hogar_p201p207,"1",idHogar);
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
            case TipoFragmentHogar.VISITAS_ENCUESTADOR:
                if (data.getValor(SQLConstantes.tablafragmentshogar,SQLConstantes.fragments_hogar_visitas_encuestador,idHogar).equals("0")) valido = false;
                break;
            case TipoFragmentHogar.VISITAS_SUPERVISOR:
                if (data.getValor(SQLConstantes.tablafragmentshogar,SQLConstantes.fragments_hogar_visitas_supervisor,idHogar).equals("0")
                        || data.getValor(SQLConstantes.tablafragmentshogar,SQLConstantes.fragments_hogar_visitas_supervisor,idHogar).equals("-1")) valido = false;
                break;
            case TipoFragmentHogar.FUNCIONARIOS:
                if (data.getValor(SQLConstantes.tablafragmentshogar,SQLConstantes.fragments_hogar_funcionarios,idHogar).equals("0")) valido = false;
                break;
            case TipoFragmentHogar.P101P107:
                if (data.getValor(SQLConstantes.tablafragmentshogar,SQLConstantes.fragments_hogar_p101p107,idHogar).equals("0")
                        || data.getValor(SQLConstantes.tablafragmentshogar,SQLConstantes.fragments_hogar_p101p107,idHogar).equals("-1")) valido = false;
                break;
            case TipoFragmentHogar.P108P113:
                if (data.getValor(SQLConstantes.tablafragmentshogar,SQLConstantes.fragments_hogar_p108p113,idHogar).equals("0")) valido = false;
                break;
            case TipoFragmentHogar.P201P206:
                if (data.getValor(SQLConstantes.tablafragmentshogar,SQLConstantes.fragments_hogar_p201p207,idHogar).equals("0")) valido = false;
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
                case TipoFragmentHogar.VISITAS_ENCUESTADOR:
                    btnAtras.setVisibility(View.GONE);
                    btnSiguiente.setVisibility(View.VISIBLE);
                    FragmentVisitasEncuestador fragmentVisitasEncuestador = new FragmentVisitasEncuestador(idHogar,idVivienda,apiClient,HogarActivity.this, idCargo,nickUsuario,idEncuestado);
                    fragmentTransaction.replace(R.id.fragment_layout, fragmentVisitasEncuestador);
                    fragmentActual = fragmentVisitasEncuestador; tFragment = TipoFragmentHogar.VISITAS_ENCUESTADOR;
                    navigationView.setCheckedItem(R.id.nav_visita_encuestador);break;
                case TipoFragmentHogar.VISITAS_SUPERVISOR:
                    FragmentVisitasSupervisor fragmentVisitasSupervisor = new FragmentVisitasSupervisor(idHogar,idVivienda,HogarActivity.this);
                    fragmentTransaction.replace(R.id.fragment_layout, fragmentVisitasSupervisor);
                    fragmentActual = fragmentVisitasSupervisor;tFragment = TipoFragmentHogar.VISITAS_SUPERVISOR;
                    navigationView.setCheckedItem(R.id.nav_visita_supervisor);break;
                case TipoFragmentHogar.FUNCIONARIOS:
                    FragmentFuncionarios fragmentFuncionarios = new FragmentFuncionarios(idHogar,idVivienda,apiClient,HogarActivity.this);
                    fragmentTransaction.replace(R.id.fragment_layout, fragmentFuncionarios);
                    fragmentActual = fragmentFuncionarios;tFragment = TipoFragmentHogar.FUNCIONARIOS;
                    navigationView.setCheckedItem(R.id.nav_funcionario);break;
                case TipoFragmentHogar.P101P107:
                    FragmentP101P107 fragmentP101P107 = new FragmentP101P107(idHogar,idVivienda,HogarActivity.this);
                    fragmentTransaction.replace(R.id.fragment_layout, fragmentP101P107);
                    fragmentActual = fragmentP101P107;tFragment = TipoFragmentHogar.P101P107;
                    navigationView.setCheckedItem(R.id.nav_p101_p107);break;
                case TipoFragmentHogar.P108P113:
                    FragmentP108P113 fragmentP108P113 = new FragmentP108P113(idHogar,idVivienda,HogarActivity.this);
                    fragmentTransaction.replace(R.id.fragment_layout, fragmentP108P113);
                    fragmentActual = fragmentP108P113;tFragment = TipoFragmentHogar.P108P113;
                    navigationView.setCheckedItem(R.id.nav_p108_p113);break;
                case TipoFragmentHogar.P201P206:
                    btnSiguiente.setVisibility(View.GONE);
                    FragmentP201P206 fragmentP201P206 = new FragmentP201P206(idHogar,idVivienda,idUsuario,nickUsuario,nroSegmento,vivienda_periodo,conglomerado,HogarActivity.this);
                    fragmentTransaction.replace(R.id.fragment_layout, fragmentP201P206);
                    fragmentActual = fragmentP201P206;tFragment = TipoFragmentHogar.P201P206;
                    navigationView.setCheckedItem(R.id.nav_p201_p206);break;
            }
            fragmentTransaction.commit();
            return true;
        }
        return false;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.hogar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_volver_hogares) {
            salirActivityHogar();
            return true;
        }else if (id == R.id.action_finalizar_hogar) {
            if(tFragment>1)
            {tFragment = 1;
                setFragment(tFragment,-1);}
            return true;
        }else if (id == R.id.action_cobertura) {
            Intent intent = new Intent(getApplicationContext(), EvaluacionActivity.class);
            intent.putExtra("idHogar", idHogar);
            intent.putExtra("idVivienda", idVivienda);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        boolean correcto = true;
        switch (id){
            case R.id.nav_visita_encuestador:
                if (!setFragment(TipoFragmentHogar.VISITAS_ENCUESTADOR,0))correcto=false;
                break;
            case R.id.nav_visita_supervisor:
                if (!setFragment(TipoFragmentHogar.VISITAS_SUPERVISOR,0))correcto=false;
                break;
            case R.id.nav_funcionario:
                if (!setFragment(TipoFragmentHogar.FUNCIONARIOS,0))correcto=false;
                break;
            case R.id.nav_p101_p107:
                if (!setFragment(TipoFragmentHogar.P101P107,0))correcto=false;
                break;
            case R.id.nav_p108_p113:
                if (!setFragment(TipoFragmentHogar.P108P113,0))correcto=false;
                break;
            case R.id.nav_p201_p206:
                if (!setFragment(TipoFragmentHogar.P201P206,0))correcto=false;
                break;
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return correcto;
    }

    public void salirActivityHogar(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("¿Está seguro que desea volver a la lista de hogares?")
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
                                finish();
                            }
                        });
        AlertDialog alert = builder.create();
        alert.show();
    }

    public boolean validarP15() {
        boolean estado = false;
        Data data =  new Data(getApplicationContext());
        data.open();
        Hogar hogar = data.getHogar(idHogar);
        if (hogar.getP15().equals("2")){
            estado= true;
        }
        return estado;

    }

    public void finalizarEncuestaP15(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("SE CONCLUIRA LA ENTREVISTA EN ESTE HOGAR, YA QUE NO EXISTE POBLACION VENEZOLANA")
                .setTitle("AVISO:")
                .setCancelable(false)
                .setNegativeButton("ACEPTAR",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                tFragment = 1;
                                setFragment(tFragment,-1);
                                dialog.cancel();
                                //cleanModulos();
                            }
                        })
                .setPositiveButton("CANCELAR",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.dismiss();
                            }
                        });
        AlertDialog alert = builder.create();
        alert.show();
    }

    public void cleanModulos(){
        Data data = new Data(getApplicationContext());
        data.open();
        ArrayList<Residente> residentes = data.getAllResidentesHogar(idHogar);
        for (Residente residente: residentes){
            String idDelEncuestado = residente.get_id();
            data.eliminarDato(SQLConstantes.tablaresidentes,idDelEncuestado);
            data.eliminarDato(SQLConstantes.tablamodulo3,idDelEncuestado);
            data.eliminarDatos(SQLConstantes.tablam3p309rutas,SQLConstantes.modulo3_p309_id_encuestado,idDelEncuestado);
            data.eliminarDatos(SQLConstantes.tablam3p318personas,SQLConstantes.modulo3_p318_idEncuestado,idDelEncuestado);
            data.eliminarDato(SQLConstantes.tablamodulo4,idDelEncuestado);
            data.eliminarDato(SQLConstantes.tablamodulo5,idDelEncuestado);
            data.eliminarDato(SQLConstantes.tablamodulo6,idDelEncuestado);
            data.eliminarDato(SQLConstantes.tablamodulo7,idDelEncuestado);
            data.eliminarDato(SQLConstantes.tablamodulo8,idDelEncuestado);
            data.eliminarDato(SQLConstantes.tablafragments,idDelEncuestado);
            data.eliminarDato(SQLConstantes.tablacoberturafragments,idDelEncuestado);
        }
        data.eliminarDato(SQLConstantes.tablamodulo1h,idHogar);
        // data.eliminarDato(SQLConstantes.tablamodulo1v,idVivienda);
        data.eliminarDato(SQLConstantes.tablafragmentshogar,idHogar);
        data.eliminarDato(SQLConstantes.tablafragmentshogar,idHogar+"_sup");
        data.close();
    }

    public void setUpdateFragmentSupervisor(){
        Data data = new Data(this);
        data.open();
        hogar = data.getHogar(idHogar);
        Usuario usuario = data.getUsuario(nickUsuario);
        idCargo = usuario.getCargo_id();
        if (idCargo.equals("2")){
            data.actualizarValor(SQLConstantes.tablafragmentshogar,SQLConstantes.fragments_hogar_visitas_supervisor,"1",idHogar);
        }
        else {
            data.actualizarValor(SQLConstantes.tablafragmentshogar,SQLConstantes.fragments_hogar_visitas_supervisor,"-1",idHogar);
        }
        data.close();
        idVivienda = hogar.getId_vivienda();
    }
////////////////////////////////////////////TRACKING//////////////////////

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
                        status.startResolutionForResult(HogarActivity.this, PETICION_CONFIG_UBICACION);
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


                    if (ActivityCompat.checkSelfPermission(HogarActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                        ActivityCompat.requestPermissions(HogarActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, PETICION_PERMISO_LOCALIZACION);
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
                    ubicacion.setUsuario(DAOUtils.getUsuarioId(idUsuario,HogarActivity.this).getNombre());
                    ubicacion.setDni(DAOUtils.getUsuarioId(idUsuario,HogarActivity.this).getDni());
                    ubicacion.setCargo(DAOUtils.getUsuarioId(idUsuario,HogarActivity.this).getUsuario());
                    ubicacion.setIdDispositivo(getIdDispositivo());
                    //ubicacion.setSerial(getSerialNumber());
                    ubicacion.setLongitud(getLatitud(longitud));
                    ubicacion.setLatitud(getLongitud(latitud));
                    //ubicacion.setLongitud(longitud);
                    //ubicacion.setLatitud(latitud);
                    ubicacion.setFechaTablet(UtilsMethods.getFechaNow().getDiaInicio()+"/"+UtilsMethods.getFechaNow().getMesInicio()+"/"+UtilsMethods.getFechaNow().getAnioInicio()+"-"+UtilsMethods.getFechaNow().getHoraInicio()+":"+UtilsMethods.getFechaNow().getMinutoInicio());
                    ubicacion.setVersionApk(UtilsMethods.getVersion(HogarActivity.this));
                    ubicacion.setBateria(""+getBateria());
                    ubicacion.setConexion("Activo");
                    ubicacion.setSegmento(nroSegmento);///
                    ubicacion.setPeriodo(vivienda_periodo);///
                    ubicacion.setConglomerado(conglomerado);///

                  //  Toast.makeText(HogarActivity.this, "LONGITUD ES"+ubicacion.getLongitud() +"\n LATITUD ES"+ubicacion.getLatitud(), Toast.LENGTH_LONG).show();


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
                    String url =  "http://200.123.3.26/wsenpove/2022/USP_WSENPOVE2022_TRACKING_USUARIO_DEV.php?ID_VIVIENDA="+ubicacion.getId()+"&ID_USUARIO="+ubicacion.getIdUsuario()
                            +"&NOM_USUARIO="+ubicacion.getUsuario()+"&CARGO_USUARIO="+ubicacion.getCargo()+"&GPS_LATITUD="+ubicacion.getLatitud()+"&GPS_LONGITUD="+ubicacion.getLongitud()+"&FECHA_REGISTRO="+ubicacion.getFechaTablet()
                            +"&VER_APK="+ubicacion.getVersionApk()+"&BATERIA_STATUS="+ubicacion.getBateria()+"&CONEXION="+ubicacion.getConexion()+"&SEGMENTO="+ubicacion.getSegmento()+"&PERIODO="+ubicacion.getPeriodo()+"&CONGLOMERADO="+ubicacion.getConglomerado();
 //PRODUCCION
    /*             String url =  "http://200.123.3.26/wsenpove/2022/USP_WSENPOVE2022_TRACKING_USUARIO.php?ID_VIVIENDA="+ubicacion.getId()+"&ID_USUARIO="+ubicacion.getIdUsuario()
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
                            // Toast.makeText(getApplicationContext(),"Con fe", Toast.LENGTH_LONG).show();
                        }
                    });

                    request.add(jsonObjectRequest);

                    handler.postDelayed(myRunnable, TIEMPO);

                }catch (Exception e){
                   //Toast.makeText(HogarActivity.this, "se cayo", Toast.LENGTH_LONG).show();
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
}
