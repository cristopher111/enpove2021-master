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
import android.text.InputFilter;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
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
import com.example.ricindigus.enpove2021.adapters.ExpandListAdapter;
import com.example.ricindigus.enpove2021.fragments.modulo3.FragmentP301P305;
import com.example.ricindigus.enpove2021.fragments.modulo3.FragmentP306P308;
import com.example.ricindigus.enpove2021.fragments.modulo3.FragmentP309;
import com.example.ricindigus.enpove2021.fragments.modulo3.FragmentP310P312;
import com.example.ricindigus.enpove2021.fragments.modulo3.FragmentP313P317;
import com.example.ricindigus.enpove2021.fragments.modulo3.FragmentP318;
import com.example.ricindigus.enpove2021.fragments.modulo4.FragmentP401P404;
import com.example.ricindigus.enpove2021.fragments.modulo4.FragmentP405P407;
import com.example.ricindigus.enpove2021.fragments.modulo4.FragmentP408P410;
import com.example.ricindigus.enpove2021.fragments.modulo4.FragmentP411P416;
import com.example.ricindigus.enpove2021.fragments.modulo5.FragmentP501P505;
import com.example.ricindigus.enpove2021.fragments.modulo5.FragmentP506P507;
import com.example.ricindigus.enpove2021.fragments.modulo5.FragmentP508P511;
import com.example.ricindigus.enpove2021.fragments.modulo5.FragmentP512P513;
import com.example.ricindigus.enpove2021.fragments.modulo6.FragmentP601P604;
import com.example.ricindigus.enpove2021.fragments.modulo6.FragmentP605P608;
import com.example.ricindigus.enpove2021.fragments.modulo6.FragmentP609P612;
import com.example.ricindigus.enpove2021.fragments.modulo6.FragmentP613P617;
import com.example.ricindigus.enpove2021.fragments.modulo6.FragmentP618P621;
import com.example.ricindigus.enpove2021.fragments.modulo6.FragmentP622P625;
import com.example.ricindigus.enpove2021.fragments.modulo6.FragmentP626P629;
import com.example.ricindigus.enpove2021.fragments.modulo6.FragmentP630;
import com.example.ricindigus.enpove2021.fragments.modulo7.FragmentP701P705;
import com.example.ricindigus.enpove2021.fragments.modulo7.FragmentP706P709;
import com.example.ricindigus.enpove2021.fragments.modulo8.FragmentP801P804;
import com.example.ricindigus.enpove2021.fragments.modulo8.FragmentP805P808;
import com.example.ricindigus.enpove2021.fragments.modulo8.FragmentP809P812;
import com.example.ricindigus.enpove2021.modelo.DAOUtils;
import com.example.ricindigus.enpove2021.modelo.Data;
import com.example.ricindigus.enpove2021.modelo.SQLConstantes;
import com.example.ricindigus.enpove2021.modelo.pojos.Hogar;
import com.example.ricindigus.enpove2021.modelo.pojos.Residente;
import com.example.ricindigus.enpove2021.modelo.pojos.ResultadoResidente;
import com.example.ricindigus.enpove2021.modelo.pojos.Ubicacion;
import com.example.ricindigus.enpove2021.util.FragmentPagina;
import com.example.ricindigus.enpove2021.util.InputFilterSoloLetras;
import com.example.ricindigus.enpove2021.util.InterfazEncuesta;
import com.example.ricindigus.enpove2021.util.TipoFragmentEncuestado;
import com.example.ricindigus.enpove2021.util.TipoFragmentVivienda;
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

import org.json.JSONObject;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EncuestaActivity extends AppCompatActivity implements InterfazEncuesta, GoogleApiClient.OnConnectionFailedListener,
        GoogleApiClient.ConnectionCallbacks,
        LocationListener, Response.Listener<JSONObject>, Response.ErrorListener {

    private static final String LOGTAG = "android-localizacion";
    private static final int PETICION_PERMISO_LOCALIZACION = 101;
    private static final int PETICION_CONFIG_UBICACION = 201;
    private GoogleApiClient apiClient;
    private LocationRequest locRequest;
    String latitud;
    String longitud;

    //private final int TIEMPO = 1000;
    private final int TIEMPO = 500000;
    Handler handler = new Handler();
    Runnable myRunnable;

    RequestQueue request;
    JsonObjectRequest jsonObjectRequest;
    ///

    private ArrayList<String> listDataHeader;
    private ExpandableListView expListView;
    private HashMap<String, List<String>> listDataChild;
    private ExpandListAdapter listAdapter;
    private String idEncuestado;
    private String idVivienda;
    private String idHogar;
    private String numero;
    private String idUsuario;
    private String nroSegmento;
    private String vivienda_periodo;
    private String conglomerado;

    private TextView btnAtras;
    private TextView btnSiguiente;
    int tFragment = 1;
    FragmentPagina fragmentActual;
    int moduloActual = 3;
    boolean ir_modulo8=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_encuesta);
        btnAtras = (TextView) findViewById(R.id.boton_anterior);
        btnSiguiente = (TextView) findViewById(R.id.boton_siguiente);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        idVivienda   = getIntent().getExtras().getString("idVivienda");
        idHogar      = getIntent().getExtras().getString("idHogar");
        idEncuestado = getIntent().getExtras().getString("idEncuestado");
        idUsuario    = getIntent().getExtras().getString("idUsuario");
        numero       = getIntent().getExtras().getString("numero");
        nroSegmento  = getIntent().getExtras().getString("segmento");
        vivienda_periodo  = getIntent().getExtras().getString("periodo");
        conglomerado = getIntent().getExtras().getString("conglomerado");

        apiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this, this)
                .addConnectionCallbacks(this)
                .addApi(LocationServices.API)
                .build();

        request = Volley.newRequestQueue(getApplicationContext());

        sendUbicacion();


        Data data =  new Data(this);
        data.open();
        Hogar hogar = data.getHogar(idHogar);
        Residente residente = data.getResidente(idEncuestado);
        data.close();

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("RESIDENTE N° " + numero + " - HOGAR N° " + hogar.getNumero());

        if (residente.getC2_p205_a().equals("")) getSupportActionBar().setSubtitle(residente.getC2_p202()+" "+residente.getC2_p202_pat()+ " ("+ residente.getC2_p205_m() +" meses)");
        else getSupportActionBar().setSubtitle(residente.getC2_p202()+" "+residente.getC2_p202_pat()+ " ("+ residente.getC2_p205_a() +" años)");

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        View headerView = navigationView.getHeaderView(0);
        TextView txtHeaderResidente = (TextView) headerView.findViewById(R.id.tv_title_header_encuesta);
        txtHeaderResidente.setText("" + residente.getC2_p202()+" "+residente.getC2_p202_pat());
        enableExpandableList();


        btnSiguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnSiguiente.setEnabled(false);
                ocultarTeclado(btnSiguiente);
                if(fragmentActual.validarDatos()){
                    fragmentActual.guardarDatos();
                    tFragment++;
                    //if (tFragment==5)tFragment++;
                    //Ultimo Fragmento 800
                    if(tFragment == 28) {
                        Log.e("juxex1", ""+tFragment);
                        updateResultadoResultado();
                        tFragment--;
                    }else if(tFragment == 11 && (Integer.parseInt(DAOUtils.getResidente(idEncuestado,getApplicationContext()).getC2_p205_a())<3 || DAOUtils.convertNumberEdad(DAOUtils.getResidente(idEncuestado,getApplicationContext()).getC2_p205_m())>=0)){
                        updateResultadoResultado();
                        tFragment--;
                    }else{//Otros Fragmentos

                        Log.e("juxex2", ""+tFragment);
                        habilitarFragment(tFragment);
                        while(!setFragment(tFragment,1)){
                            Log.e("juxex3", ""+tFragment);
                            tFragment++;
                            if(tFragment == 28) {
                                updateResultadoResultado();
                                //tFragment--;
                            }
                            Log.e("juxex4", ""+tFragment);
                            habilitarFragment(tFragment);
                        }
                    }

                }
                btnSiguiente.setEnabled(true);
            }
        });

        btnAtras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnAtras.setEnabled(false);
                ocultarTeclado(btnAtras);
                tFragment--;
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
        sendUbicacion();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            salirActivityEncuestado();
        }
    }

    public void setIr_modulo8(boolean ir){ir_modulo8=ir;}

    public void habilitarFragment(int tipoFragment){
        Data data =  new Data(this);
        data.open();
        switch (tipoFragment){
            case TipoFragmentEncuestado.P301P305:
                if (data.getValor(SQLConstantes.tablafragments,SQLConstantes.fragments_p301p305,idEncuestado).equals("0"))
                data.actualizarValor(SQLConstantes.tablafragments,SQLConstantes.fragments_p301p305,"1",idEncuestado);
                break;
            case TipoFragmentEncuestado.P306P308:
                if (data.getValor(SQLConstantes.tablafragments,SQLConstantes.fragments_p306p308,idEncuestado).equals("0"))
                data.actualizarValor(SQLConstantes.tablafragments,SQLConstantes.fragments_p306p308,"1",idEncuestado);
                break;
            case TipoFragmentEncuestado.P309:
                if (data.getValor(SQLConstantes.tablafragments,SQLConstantes.fragments_p309,idEncuestado).equals("0"))
                data.actualizarValor(SQLConstantes.tablafragments,SQLConstantes.fragments_p309,"1",idEncuestado);
                break;
            case TipoFragmentEncuestado.P310P312:
                if (data.getValor(SQLConstantes.tablafragments,SQLConstantes.fragments_p310p312,idEncuestado).equals("0"))
                data.actualizarValor(SQLConstantes.tablafragments,SQLConstantes.fragments_p310p312,"1",idEncuestado);
                break;
            case TipoFragmentEncuestado.P313P317:
                if (data.getValor(SQLConstantes.tablafragments,SQLConstantes.fragments_p313p317,idEncuestado).equals("0"))
                data.actualizarValor(SQLConstantes.tablafragments,SQLConstantes.fragments_p313p317,"1",idEncuestado);
                break;
            case TipoFragmentEncuestado.P318:
                if (data.getValor(SQLConstantes.tablafragments,SQLConstantes.fragments_p318,idEncuestado).equals("0"))
                data.actualizarValor(SQLConstantes.tablafragments,SQLConstantes.fragments_p318,"1",idEncuestado);
                break;
            case TipoFragmentEncuestado.P401P404:
                if (data.getValor(SQLConstantes.tablafragments,SQLConstantes.fragments_p401p404,idEncuestado).equals("0"))
                data.actualizarValor(SQLConstantes.tablafragments,SQLConstantes.fragments_p401p404,"1",idEncuestado);
                break;
            case TipoFragmentEncuestado.P405P407:
                if (data.getValor(SQLConstantes.tablafragments,SQLConstantes.fragments_p405p407,idEncuestado).equals("0"))
                    data.actualizarValor(SQLConstantes.tablafragments,SQLConstantes.fragments_p405p407,"1",idEncuestado);
                break;
            case TipoFragmentEncuestado.P408P410:
                if (data.getValor(SQLConstantes.tablafragments,SQLConstantes.fragments_p408p410,idEncuestado).equals("0"))
                    data.actualizarValor(SQLConstantes.tablafragments,SQLConstantes.fragments_p408p410,"1",idEncuestado);
                break;
            case TipoFragmentEncuestado.P411P416:
                if (data.getValor(SQLConstantes.tablafragments,SQLConstantes.fragments_p411p416,idEncuestado).equals("0"))
                    data.actualizarValor(SQLConstantes.tablafragments,SQLConstantes.fragments_p411p416,"1",idEncuestado);
                break;
            case TipoFragmentEncuestado.P501P505:
                if (data.getValor(SQLConstantes.tablafragments,SQLConstantes.fragments_p501p505,idEncuestado).equals("0"))
                    data.actualizarValor(SQLConstantes.tablafragments,SQLConstantes.fragments_p501p505,"1",idEncuestado);
                break;
            case TipoFragmentEncuestado.P506P507:
                if (data.getValor(SQLConstantes.tablafragments,SQLConstantes.fragments_p506p507,idEncuestado).equals("0"))
                data.actualizarValor(SQLConstantes.tablafragments,SQLConstantes.fragments_p506p507,"1",idEncuestado);
                break;
            case TipoFragmentEncuestado.P508P511:
                if (data.getValor(SQLConstantes.tablafragments,SQLConstantes.fragments_p508p511,idEncuestado).equals("0"))
                data.actualizarValor(SQLConstantes.tablafragments,SQLConstantes.fragments_p508p511,"1",idEncuestado);
                break;
            case TipoFragmentEncuestado.P512P513:
                if (data.getValor(SQLConstantes.tablafragments,SQLConstantes.fragments_p512p513,idEncuestado).equals("0"))
                data.actualizarValor(SQLConstantes.tablafragments,SQLConstantes.fragments_p512p513,"1",idEncuestado);
                break;
            case TipoFragmentEncuestado.P601P604:
                if (data.getValor(SQLConstantes.tablafragments,SQLConstantes.fragments_p601p604,idEncuestado).equals("0"))
                data.actualizarValor(SQLConstantes.tablafragments,SQLConstantes.fragments_p601p604,"1",idEncuestado);
                break;
            case TipoFragmentEncuestado.P605P608:
                if (data.getValor(SQLConstantes.tablafragments,SQLConstantes.fragments_p605p608,idEncuestado).equals("0"))
                data.actualizarValor(SQLConstantes.tablafragments,SQLConstantes.fragments_p605p608,"1",idEncuestado);
                break;
            case TipoFragmentEncuestado.P609P612:
                if (data.getValor(SQLConstantes.tablafragments,SQLConstantes.fragments_p609p612,idEncuestado).equals("0"))
                data.actualizarValor(SQLConstantes.tablafragments,SQLConstantes.fragments_p609p612,"1",idEncuestado);
                break;
            case TipoFragmentEncuestado.P613P618:
                if (data.getValor(SQLConstantes.tablafragments,SQLConstantes.fragments_p613p617,idEncuestado).equals("0"))
                data.actualizarValor(SQLConstantes.tablafragments,SQLConstantes.fragments_p613p617,"1",idEncuestado);
                break;
            case TipoFragmentEncuestado.P619P622:
                if (data.getValor(SQLConstantes.tablafragments,SQLConstantes.fragments_p618p621,idEncuestado).equals("0"))
                data.actualizarValor(SQLConstantes.tablafragments,SQLConstantes.fragments_p618p621,"1",idEncuestado);
                break;
            case TipoFragmentEncuestado.P623P625:
                if (data.getValor(SQLConstantes.tablafragments,SQLConstantes.fragments_p622p625,idEncuestado).equals("0"))
                data.actualizarValor(SQLConstantes.tablafragments,SQLConstantes.fragments_p622p625,"1",idEncuestado);
                break;
            case TipoFragmentEncuestado.P626P629:
                if (data.getValor(SQLConstantes.tablafragments,SQLConstantes.fragments_p626p629,idEncuestado).equals("0"))
                data.actualizarValor(SQLConstantes.tablafragments,SQLConstantes.fragments_p626p629,"1",idEncuestado);
                break;
            case TipoFragmentEncuestado.P630:
                if (data.getValor(SQLConstantes.tablafragments,SQLConstantes.fragments_p630,idEncuestado).equals("0"))
                data.actualizarValor(SQLConstantes.tablafragments,SQLConstantes.fragments_p630,"1",idEncuestado);
                break;
            case TipoFragmentEncuestado.P701P705:
                if (data.getValor(SQLConstantes.tablafragments,SQLConstantes.fragments_p701p705,idEncuestado).equals("0"))
                data.actualizarValor(SQLConstantes.tablafragments,SQLConstantes.fragments_p701p705,"1",idEncuestado);
                break;
            case TipoFragmentEncuestado.P706P709:
                if (data.getValor(SQLConstantes.tablafragments,SQLConstantes.fragments_p706p709,idEncuestado).equals("0"))
                data.actualizarValor(SQLConstantes.tablafragments,SQLConstantes.fragments_p706p709,"1",idEncuestado);
                break;
            case TipoFragmentEncuestado.P801P804:
                if (data.getValor(SQLConstantes.tablafragments,SQLConstantes.fragments_p801p804,idEncuestado).equals("0"))
                data.actualizarValor(SQLConstantes.tablafragments,SQLConstantes.fragments_p801p804,"1",idEncuestado);
                break;
            case TipoFragmentEncuestado.P805P808:
                if (data.getValor(SQLConstantes.tablafragments,SQLConstantes.fragments_p805p808,idEncuestado).equals("0"))
                data.actualizarValor(SQLConstantes.tablafragments,SQLConstantes.fragments_p805p808,"1",idEncuestado);
                break;
            case TipoFragmentEncuestado.P809P812:
                if (data.getValor(SQLConstantes.tablafragments,SQLConstantes.fragments_p809p812,idEncuestado).equals("0"))
                data.actualizarValor(SQLConstantes.tablafragments,SQLConstantes.fragments_p809p812,"1",idEncuestado);
                break;
//            case TipoFragmentEncuestado.P813P816:
//                if (data.getValor(SQLConstantes.tablafragments,SQLConstantes.fragments_p813p816,idEncuestado).equals("0"))
//                data.actualizarValor(SQLConstantes.tablafragments,SQLConstantes.fragments_p813p816,"1",idEncuestado);
//                break;
//            case TipoFragmentEncuestado.P817P820:
//                if (data.getValor(SQLConstantes.tablafragments,SQLConstantes.fragments_p817p820,idEncuestado).equals("0"))
//                data.actualizarValor(SQLConstantes.tablafragments,SQLConstantes.fragments_p817p820,"1",idEncuestado);
//                break;
//            case TipoFragmentEncuestado.P821P823:
//                if (data.getValor(SQLConstantes.tablafragments,SQLConstantes.fragments_p821p823,idEncuestado).equals("0"))
//                data.actualizarValor(SQLConstantes.tablafragments,SQLConstantes.fragments_p821p823,"1",idEncuestado);
//                break;
        }
    }

    public void ocultarTeclado(View view){
        InputMethodManager mgr = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        mgr.hideSoftInputFromWindow(view.getWindowToken(), 0);
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
            if(tipoFragment == TipoFragmentEncuestado.P809P812) btnSiguiente.setText("Finalizar");
            else btnSiguiente.setText("Siguiente");
            switch (tipoFragment){
                case TipoFragmentEncuestado.P301P305:
                    Log.e("cesar1", "onClick: "+ tFragment);
                    btnAtras.setVisibility(View.GONE);
                    FragmentP301P305 fragmentP301P305 = new FragmentP301P305(idEncuestado,EncuestaActivity.this);
                    fragmentTransaction.replace(R.id.fragment_layout, fragmentP301P305);
                    tFragment = TipoFragmentEncuestado.P301P305;
                    fragmentActual = fragmentP301P305;
                    moduloActual = 3;
                    break;
                case TipoFragmentEncuestado.P306P308:
                    Log.e("cesar2", "onClick: "+ tFragment);
                    FragmentP306P308 fragmentP306P308 = new FragmentP306P308(idEncuestado,EncuestaActivity.this);
                    fragmentTransaction.replace(R.id.fragment_layout, fragmentP306P308);
                    tFragment = TipoFragmentEncuestado.P306P308;
                    fragmentActual = fragmentP306P308;
                    moduloActual = 3;
                    break;
                case TipoFragmentEncuestado.P309:
                    Log.e("cesar3", "onClick: "+ tFragment);
                    FragmentP309 fragmentP309 = new FragmentP309(idEncuestado,idVivienda,EncuestaActivity.this);
                    fragmentTransaction.replace(R.id.fragment_layout, fragmentP309);
                    tFragment = TipoFragmentEncuestado.P309;
                    fragmentActual = fragmentP309;
                    moduloActual = 3;
                    break;
                case TipoFragmentEncuestado.P310P312:
                    FragmentP310P312 fragmentP310P312 = new FragmentP310P312(idEncuestado,EncuestaActivity.this);
                    fragmentTransaction.replace(R.id.fragment_layout, fragmentP310P312);
                    tFragment = TipoFragmentEncuestado.P310P312;
                    fragmentActual = fragmentP310P312;
                    moduloActual = 3;
                    break;
                case TipoFragmentEncuestado.P313P317:
                    FragmentP313P317 fragmentP313P317 = new FragmentP313P317(idEncuestado,EncuestaActivity.this);
                    fragmentTransaction.replace(R.id.fragment_layout, fragmentP313P317);
                    tFragment = TipoFragmentEncuestado.P313P317;
                    fragmentActual = fragmentP313P317;
                    moduloActual = 3;
                    break;
                case TipoFragmentEncuestado.P318:
                    FragmentP318 fragmentP318 = new FragmentP318(idEncuestado,idVivienda,idUsuario,nroSegmento,vivienda_periodo,conglomerado,EncuestaActivity.this);
                    fragmentTransaction.replace(R.id.fragment_layout, fragmentP318);
                    tFragment = TipoFragmentEncuestado.P318;
                    fragmentActual = fragmentP318;
                    moduloActual = 3;
                    break;
                case TipoFragmentEncuestado.P401P404:
                    FragmentP401P404 fragmentP401P404 = new FragmentP401P404(idEncuestado,EncuestaActivity.this);
                    fragmentTransaction.replace(R.id.fragment_layout, fragmentP401P404);
                    tFragment = TipoFragmentEncuestado.P401P404;
                    fragmentActual = fragmentP401P404;
                    moduloActual = 4;
                    break;
                case TipoFragmentEncuestado.P405P407:
                    FragmentP405P407 fragmentP405P407 = new FragmentP405P407(idEncuestado,EncuestaActivity.this);
                    fragmentTransaction.replace(R.id.fragment_layout, fragmentP405P407);
                    tFragment = TipoFragmentEncuestado.P405P407;
                    fragmentActual = fragmentP405P407;
                    moduloActual = 4;
                    break;
                case TipoFragmentEncuestado.P408P410:
                    FragmentP408P410 fragmentP408P410 = new FragmentP408P410(idEncuestado,EncuestaActivity.this);
                    fragmentTransaction.replace(R.id.fragment_layout, fragmentP408P410);
                    tFragment = TipoFragmentEncuestado.P408P410;
                    fragmentActual = fragmentP408P410;
                    moduloActual = 4;
                    break;
                case TipoFragmentEncuestado.P411P416:
                    FragmentP411P416 fragmentP411P416 = new FragmentP411P416(idHogar,idEncuestado,EncuestaActivity.this);
                    fragmentTransaction.replace(R.id.fragment_layout, fragmentP411P416);
                    tFragment = TipoFragmentEncuestado.P411P416;
                    fragmentActual = fragmentP411P416;
                    moduloActual = 4;
                    break;
                case TipoFragmentEncuestado.P501P505:
                    FragmentP501P505 fragmentP501P505 = new FragmentP501P505(idEncuestado,EncuestaActivity.this);
                    fragmentTransaction.replace(R.id.fragment_layout, fragmentP501P505);
                    tFragment = TipoFragmentEncuestado.P501P505;
                    fragmentActual = fragmentP501P505;
                    moduloActual = 5;
                    break;
                case TipoFragmentEncuestado.P506P507:
                    FragmentP506P507 fragmentP506P507 = new FragmentP506P507(idEncuestado,EncuestaActivity.this);
                    fragmentTransaction.replace(R.id.fragment_layout, fragmentP506P507);
                    tFragment = TipoFragmentEncuestado.P506P507;
                    fragmentActual = fragmentP506P507;
                    moduloActual = 5;
                    break;
                case TipoFragmentEncuestado.P508P511:
                    FragmentP508P511 fragmentP508P511 = new FragmentP508P511(idEncuestado,EncuestaActivity.this);
                    fragmentTransaction.replace(R.id.fragment_layout, fragmentP508P511);
                    tFragment = TipoFragmentEncuestado.P508P511;
                    fragmentActual = fragmentP508P511;
                    moduloActual = 5;
                    break;
                case TipoFragmentEncuestado.P512P513:
                    FragmentP512P513 fragmentP512P513 = new FragmentP512P513(idEncuestado,EncuestaActivity.this);
                    fragmentTransaction.replace(R.id.fragment_layout, fragmentP512P513);
                    tFragment = TipoFragmentEncuestado.P512P513;
                    fragmentActual = fragmentP512P513;
                    moduloActual = 5;
                    break;
                case TipoFragmentEncuestado.P601P604:
                    FragmentP601P604 fragmentP601P604 = new FragmentP601P604(idEncuestado,EncuestaActivity.this);
                    fragmentTransaction.replace(R.id.fragment_layout, fragmentP601P604);
                    tFragment = TipoFragmentEncuestado.P601P604;
                    fragmentActual = fragmentP601P604;
                    moduloActual = 6;
                    break;
                case TipoFragmentEncuestado.P605P608:
                    FragmentP605P608 fragmentP605P608 = new FragmentP605P608(idEncuestado,EncuestaActivity.this);
                    fragmentTransaction.replace(R.id.fragment_layout, fragmentP605P608);
                    tFragment = TipoFragmentEncuestado.P605P608;
                    fragmentActual = fragmentP605P608;
                    moduloActual = 6;
                    break;
                case TipoFragmentEncuestado.P609P612:
                    FragmentP609P612 fragmentP609P612 = new FragmentP609P612(idEncuestado,EncuestaActivity.this);
                    fragmentTransaction.replace(R.id.fragment_layout, fragmentP609P612);
                    tFragment = TipoFragmentEncuestado.P609P612;
                    fragmentActual = fragmentP609P612;
                    moduloActual = 6;
                    break;
                case TipoFragmentEncuestado.P613P618:
                    FragmentP613P617 fragmentP613P617 = new FragmentP613P617(idEncuestado,EncuestaActivity.this);
                    fragmentTransaction.replace(R.id.fragment_layout, fragmentP613P617);
                    tFragment = TipoFragmentEncuestado.P613P618;
                    fragmentActual = fragmentP613P617;
                    moduloActual = 6;
                    break;
                case TipoFragmentEncuestado.P619P622:
                    FragmentP618P621 fragmentP618P621 = new FragmentP618P621(idEncuestado,EncuestaActivity.this);
                    fragmentTransaction.replace(R.id.fragment_layout, fragmentP618P621);
                    tFragment = TipoFragmentEncuestado.P619P622;
                    fragmentActual = fragmentP618P621;
                    moduloActual = 6;
                    break;
                case TipoFragmentEncuestado.P623P625:
                    FragmentP622P625 fragmentP622P625 = new FragmentP622P625(idEncuestado,EncuestaActivity.this);
                    fragmentTransaction.replace(R.id.fragment_layout, fragmentP622P625);
                    tFragment = TipoFragmentEncuestado.P623P625;
                    fragmentActual = fragmentP622P625;
                    moduloActual = 6;
                    break;
                case TipoFragmentEncuestado.P626P629:
                    FragmentP626P629 fragmentP626P629 = new FragmentP626P629(idEncuestado,EncuestaActivity.this);
                    fragmentTransaction.replace(R.id.fragment_layout, fragmentP626P629);
                    tFragment = TipoFragmentEncuestado.P626P629;
                    fragmentActual = fragmentP626P629;
                    moduloActual = 6;
                    break;
                case TipoFragmentEncuestado.P630:
                    FragmentP630 fragmentP630 = new FragmentP630(idEncuestado,EncuestaActivity.this);
                    fragmentTransaction.replace(R.id.fragment_layout, fragmentP630);
                    tFragment = TipoFragmentEncuestado.P630;
                    fragmentActual = fragmentP630;
                    moduloActual = 6;
                    break;
                case TipoFragmentEncuestado.P701P705:
                    FragmentP701P705 fragmentP701P705 = new FragmentP701P705(idEncuestado, EncuestaActivity.this);
                    fragmentTransaction.replace(R.id.fragment_layout, fragmentP701P705);
                    tFragment = TipoFragmentEncuestado.P701P705;
                    fragmentActual = fragmentP701P705;
                    moduloActual = 7;
                    break;
                case TipoFragmentEncuestado.P706P709:
                    FragmentP706P709 fragmentP706P709 = new FragmentP706P709(idEncuestado, EncuestaActivity.this);
                    fragmentTransaction.replace(R.id.fragment_layout, fragmentP706P709);
                    tFragment = TipoFragmentEncuestado.P706P709;
                    fragmentActual = fragmentP706P709;
                    moduloActual = 7;
                    break;
                case TipoFragmentEncuestado.P801P804:
                    Log.e("cesar66", "onClick: "+ tFragment);
                    FragmentP801P804 fragmentP801P804 = new FragmentP801P804(idEncuestado, EncuestaActivity.this);
                    fragmentTransaction.replace(R.id.fragment_layout, fragmentP801P804);
                    tFragment = TipoFragmentEncuestado.P801P804;
                    fragmentActual = fragmentP801P804;
                    moduloActual = 8;
                    break;
                case TipoFragmentEncuestado.P805P808:
                    FragmentP805P808 fragmentP805P808 = new FragmentP805P808(idEncuestado, EncuestaActivity.this);
                    fragmentTransaction.replace(R.id.fragment_layout, fragmentP805P808);
                    tFragment = TipoFragmentEncuestado.P805P808;
                    fragmentActual = fragmentP805P808;
                    moduloActual = 8;
                    break;
                case TipoFragmentEncuestado.P809P812:
                    FragmentP809P812 fragmentP809P812 = new FragmentP809P812(idEncuestado, EncuestaActivity.this);
                    fragmentTransaction.replace(R.id.fragment_layout, fragmentP809P812);
                    tFragment = TipoFragmentEncuestado.P809P812;
                    fragmentActual = fragmentP809P812;
                    moduloActual = 8;
                    break;
//                case TipoFragmentEncuestado.P813P816:
//                    FragmentP813P816 fragmentP813P816 = new FragmentP813P816(idEncuestado, EncuestaActivity.this);
//                    fragmentTransaction.replace(R.id.fragment_layout, fragmentP813P816);
//                    tFragment = TipoFragmentEncuestado.P813P816;
//                    fragmentActual = fragmentP813P816;
//                    moduloActual = 8;
//                    break;
//                case TipoFragmentEncuestado.P817P820:
//                    FragmentP817P820 fragmentP817P820 = new FragmentP817P820(idEncuestado, EncuestaActivity.this);
//                    fragmentTransaction.replace(R.id.fragment_layout, fragmentP817P820);
//                    tFragment = TipoFragmentEncuestado.P817P820;
//                    fragmentActual = fragmentP817P820;
//                    moduloActual = 8;
//                    break;
//                case TipoFragmentEncuestado.P821P823:
//                    FragmentP821P823 fragmentP821P823 = new FragmentP821P823(idEncuestado, EncuestaActivity.this);
//                    fragmentTransaction.replace(R.id.fragment_layout, fragmentP821P823);
//                    tFragment = TipoFragmentEncuestado.P821P823;
//                    fragmentActual = fragmentP821P823;
//                    moduloActual = 8;
//                    break;
            }
            fragmentTransaction.commit();
            return true;
        }
        return false;

    }

//    public boolean setFragment_ir_800(int tipoFragment, int direccion){
//        //if (seteoValido(tipoFragment)){
//            FragmentManager fragmentManager = getSupportFragmentManager();
//            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//            if(direccion != 0){
//                if(direccion > 0){
//                    fragmentTransaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left);
//                }else{
//                    fragmentTransaction.setCustomAnimations(R.anim.enter_from_left, R.anim.exit_to_right);
//                }
//            }
//            btnAtras.setVisibility(View.VISIBLE);
//            btnSiguiente.setVisibility(View.VISIBLE);
//            if(tipoFragment == TipoFragmentEncuestado.P821P823) btnSiguiente.setText("Finalizar");
//            else btnSiguiente.setText("Siguiente");
//            switch (tipoFragment){
//                case TipoFragmentEncuestado.P301P305:
//                    Log.e("cesar1", "onClick: "+ tFragment);
//                    btnAtras.setVisibility(View.GONE);
//                    FragmentP301P305 fragmentP301P305 = new FragmentP301P305(idEncuestado,EncuestaActivity.this);
//                    fragmentTransaction.replace(R.id.fragment_layout, fragmentP301P305);
//                    tFragment = TipoFragmentEncuestado.P301P305;
//                    fragmentActual = fragmentP301P305;
//                    moduloActual = 3;
//                    break;
//                case TipoFragmentEncuestado.P306P308:
//                    Log.e("cesar2", "onClick: "+ tFragment);
//                    FragmentP306P308 fragmentP306P308 = new FragmentP306P308(idEncuestado,EncuestaActivity.this);
//                    fragmentTransaction.replace(R.id.fragment_layout, fragmentP306P308);
//                    tFragment = TipoFragmentEncuestado.P306P308;
//                    fragmentActual = fragmentP306P308;
//                    moduloActual = 3;
//                    break;
//                case TipoFragmentEncuestado.P309:
//                    Log.e("cesar3", "onClick: "+ tFragment);
//                    FragmentP309 fragmentP309 = new FragmentP309(idEncuestado,idVivienda,EncuestaActivity.this);
//                    fragmentTransaction.replace(R.id.fragment_layout, fragmentP309);
//                    tFragment = TipoFragmentEncuestado.P309;
//                    fragmentActual = fragmentP309;
//                    moduloActual = 3;
//                    break;
//                case TipoFragmentEncuestado.P310P312:
//                    FragmentP310P312 fragmentP310P312 = new FragmentP310P312(idEncuestado,EncuestaActivity.this);
//                    fragmentTransaction.replace(R.id.fragment_layout, fragmentP310P312);
//                    tFragment = TipoFragmentEncuestado.P310P312;
//                    fragmentActual = fragmentP310P312;
//                    moduloActual = 3;
//                    break;
//                case TipoFragmentEncuestado.P313P317:
//                    FragmentP313P317 fragmentP313P317 = new FragmentP313P317(idEncuestado,EncuestaActivity.this);
//                    fragmentTransaction.replace(R.id.fragment_layout, fragmentP313P317);
//                    tFragment = TipoFragmentEncuestado.P313P317;
//                    fragmentActual = fragmentP313P317;
//                    moduloActual = 3;
//                    break;
//                case TipoFragmentEncuestado.P318:
//                    FragmentP318 fragmentP318 = new FragmentP318(idEncuestado,idVivienda,EncuestaActivity.this);
//                    fragmentTransaction.replace(R.id.fragment_layout, fragmentP318);
//                    tFragment = TipoFragmentEncuestado.P318;
//                    fragmentActual = fragmentP318;
//                    moduloActual = 3;
//                    break;
//                case TipoFragmentEncuestado.P401P404:
//                    FragmentP401P404 fragmentP401P404 = new FragmentP401P404(idEncuestado,EncuestaActivity.this);
//                    fragmentTransaction.replace(R.id.fragment_layout, fragmentP401P404);
//                    tFragment = TipoFragmentEncuestado.P401P404;
//                    fragmentActual = fragmentP401P404;
//                    moduloActual = 4;
//                    break;
//                case TipoFragmentEncuestado.P405P407:
//                    FragmentP405P407 fragmentP405P407 = new FragmentP405P407(idEncuestado,EncuestaActivity.this);
//                    fragmentTransaction.replace(R.id.fragment_layout, fragmentP405P407);
//                    tFragment = TipoFragmentEncuestado.P405P407;
//                    fragmentActual = fragmentP405P407;
//                    moduloActual = 4;
//                    break;
//                case TipoFragmentEncuestado.P408P410:
//                    FragmentP408P410 fragmentP408P410 = new FragmentP408P410(idEncuestado,EncuestaActivity.this);
//                    fragmentTransaction.replace(R.id.fragment_layout, fragmentP408P410);
//                    tFragment = TipoFragmentEncuestado.P408P410;
//                    fragmentActual = fragmentP408P410;
//                    moduloActual = 4;
//                    break;
//                case TipoFragmentEncuestado.P411P416:
//                    FragmentP411P416 fragmentP411P416 = new FragmentP411P416(idEncuestado,EncuestaActivity.this);
//                    fragmentTransaction.replace(R.id.fragment_layout, fragmentP411P416);
//                    tFragment = TipoFragmentEncuestado.P411P416;
//                    fragmentActual = fragmentP411P416;
//                    moduloActual = 4;
//                    break;
//                case TipoFragmentEncuestado.P501P505:
//                    FragmentP501P505 fragmentP501P505 = new FragmentP501P505(idEncuestado,EncuestaActivity.this);
//                    fragmentTransaction.replace(R.id.fragment_layout, fragmentP501P505);
//                    tFragment = TipoFragmentEncuestado.P501P505;
//                    fragmentActual = fragmentP501P505;
//                    moduloActual = 5;
//                    break;
//                case TipoFragmentEncuestado.P506P507:
//                    FragmentP506P507 fragmentP506P507 = new FragmentP506P507(idEncuestado,EncuestaActivity.this);
//                    fragmentTransaction.replace(R.id.fragment_layout, fragmentP506P507);
//                    tFragment = TipoFragmentEncuestado.P506P507;
//                    fragmentActual = fragmentP506P507;
//                    moduloActual = 5;
//                    break;
//                case TipoFragmentEncuestado.P508P511:
//                    FragmentP508P511 fragmentP508P511 = new FragmentP508P511(idEncuestado,EncuestaActivity.this);
//                    fragmentTransaction.replace(R.id.fragment_layout, fragmentP508P511);
//                    tFragment = TipoFragmentEncuestado.P508P511;
//                    fragmentActual = fragmentP508P511;
//                    moduloActual = 5;
//                    break;
//                case TipoFragmentEncuestado.P512P513:
//                    FragmentP512P513 fragmentP512P513 = new FragmentP512P513(idEncuestado,EncuestaActivity.this);
//                    fragmentTransaction.replace(R.id.fragment_layout, fragmentP512P513);
//                    tFragment = TipoFragmentEncuestado.P512P513;
//                    fragmentActual = fragmentP512P513;
//                    moduloActual = 5;
//                    break;
//                case TipoFragmentEncuestado.P601P604:
//                    FragmentP601P604 fragmentP601P604 = new FragmentP601P604(idEncuestado,EncuestaActivity.this);
//                    fragmentTransaction.replace(R.id.fragment_layout, fragmentP601P604);
//                    tFragment = TipoFragmentEncuestado.P601P604;
//                    fragmentActual = fragmentP601P604;
//                    moduloActual = 6;
//                    break;
//                case TipoFragmentEncuestado.P605P608:
//                    FragmentP605P608 fragmentP605P608 = new FragmentP605P608(idEncuestado,EncuestaActivity.this);
//                    fragmentTransaction.replace(R.id.fragment_layout, fragmentP605P608);
//                    tFragment = TipoFragmentEncuestado.P605P608;
//                    fragmentActual = fragmentP605P608;
//                    moduloActual = 6;
//                    break;
//                case TipoFragmentEncuestado.P609P612:
//                    FragmentP609P612 fragmentP609P612 = new FragmentP609P612(idEncuestado,EncuestaActivity.this);
//                    fragmentTransaction.replace(R.id.fragment_layout, fragmentP609P612);
//                    tFragment = TipoFragmentEncuestado.P609P612;
//                    fragmentActual = fragmentP609P612;
//                    moduloActual = 6;
//                    break;
//                case TipoFragmentEncuestado.P613P618:
//                    FragmentP613P617 fragmentP613P617 = new FragmentP613P617(idEncuestado,EncuestaActivity.this);
//                    fragmentTransaction.replace(R.id.fragment_layout, fragmentP613P617);
//                    tFragment = TipoFragmentEncuestado.P613P618;
//                    fragmentActual = fragmentP613P617;
//                    moduloActual = 6;
//                    break;
//                case TipoFragmentEncuestado.P619P622:
//                    FragmentP618P621 fragmentP618P621 = new FragmentP618P621(idEncuestado,EncuestaActivity.this);
//                    fragmentTransaction.replace(R.id.fragment_layout, fragmentP618P621);
//                    tFragment = TipoFragmentEncuestado.P619P622;
//                    fragmentActual = fragmentP618P621;
//                    moduloActual = 6;
//                    break;
//                case TipoFragmentEncuestado.P623P625:
//                    FragmentP622P625 fragmentP622P625 = new FragmentP622P625(idEncuestado,EncuestaActivity.this);
//                    fragmentTransaction.replace(R.id.fragment_layout, fragmentP622P625);
//                    tFragment = TipoFragmentEncuestado.P623P625;
//                    fragmentActual = fragmentP622P625;
//                    moduloActual = 6;
//                    break;
//                case TipoFragmentEncuestado.P626P629:
//                    FragmentP626P629 fragmentP626P629 = new FragmentP626P629(idEncuestado,EncuestaActivity.this);
//                    fragmentTransaction.replace(R.id.fragment_layout, fragmentP626P629);
//                    tFragment = TipoFragmentEncuestado.P626P629;
//                    fragmentActual = fragmentP626P629;
//                    moduloActual = 6;
//                    break;
//                case TipoFragmentEncuestado.P630:
//                    FragmentP630 fragmentP630 = new FragmentP630(idEncuestado,EncuestaActivity.this);
//                    fragmentTransaction.replace(R.id.fragment_layout, fragmentP630);
//                    tFragment = TipoFragmentEncuestado.P630;
//                    fragmentActual = fragmentP630;
//                    moduloActual = 6;
//                    break;
//                case TipoFragmentEncuestado.P701P705:
//                    FragmentP701P705 fragmentP701P705 = new FragmentP701P705(idEncuestado, EncuestaActivity.this);
//                    fragmentTransaction.replace(R.id.fragment_layout, fragmentP701P705);
//                    tFragment = TipoFragmentEncuestado.P701P705;
//                    fragmentActual = fragmentP701P705;
//                    moduloActual = 7;
//                    break;
//                case TipoFragmentEncuestado.P706P709:
//                    FragmentP706P709 fragmentP706P709 = new FragmentP706P709(idEncuestado, EncuestaActivity.this);
//                    fragmentTransaction.replace(R.id.fragment_layout, fragmentP706P709);
//                    tFragment = TipoFragmentEncuestado.P706P709;
//                    fragmentActual = fragmentP706P709;
//                    moduloActual = 7;
//                    break;
//                case TipoFragmentEncuestado.P801P804:
//                    Log.e("cesar66", "onClick: "+ tFragment);
//                    FragmentP801P804 fragmentP801P804 = new FragmentP801P804(idEncuestado, EncuestaActivity.this);
//                    fragmentTransaction.replace(R.id.fragment_layout, fragmentP801P804);
//                    tFragment = TipoFragmentEncuestado.P801P804;
//                    fragmentActual = fragmentP801P804;
//                    moduloActual = 8;
//                    break;
//                case TipoFragmentEncuestado.P805P808:
//                    FragmentP805P808 fragmentP805P808 = new FragmentP805P808(idEncuestado, EncuestaActivity.this);
//                    fragmentTransaction.replace(R.id.fragment_layout, fragmentP805P808);
//                    tFragment = TipoFragmentEncuestado.P805P808;
//                    fragmentActual = fragmentP805P808;
//                    moduloActual = 8;
//                    break;
//                case TipoFragmentEncuestado.P809P812:
//                    FragmentP809P812 fragmentP809P812 = new FragmentP809P812(idEncuestado, EncuestaActivity.this);
//                    fragmentTransaction.replace(R.id.fragment_layout, fragmentP809P812);
//                    tFragment = TipoFragmentEncuestado.P809P812;
//                    fragmentActual = fragmentP809P812;
//                    moduloActual = 8;
//                    break;
////                case TipoFragmentEncuestado.P813P816:
////                    FragmentP813P816 fragmentP813P816 = new FragmentP813P816(idEncuestado, EncuestaActivity.this);
////                    fragmentTransaction.replace(R.id.fragment_layout, fragmentP813P816);
////                    tFragment = TipoFragmentEncuestado.P813P816;
////                    fragmentActual = fragmentP813P816;
////                    moduloActual = 8;
////                    break;
////                case TipoFragmentEncuestado.P817P820:
////                    FragmentP817P820 fragmentP817P820 = new FragmentP817P820(idEncuestado, EncuestaActivity.this);
////                    fragmentTransaction.replace(R.id.fragment_layout, fragmentP817P820);
////                    tFragment = TipoFragmentEncuestado.P817P820;
////                    fragmentActual = fragmentP817P820;
////                    moduloActual = 8;
////                    break;
////                case TipoFragmentEncuestado.P821P823:
////                    FragmentP821P823 fragmentP821P823 = new FragmentP821P823(idEncuestado, EncuestaActivity.this);
////                    fragmentTransaction.replace(R.id.fragment_layout, fragmentP821P823);
////                    tFragment = TipoFragmentEncuestado.P821P823;
////                    fragmentActual = fragmentP821P823;
////                    moduloActual = 8;
////                    break;
//            }
//            fragmentTransaction.commit();
//            return true;
////        }
////        return false;
//
//    }

    public boolean seteoValido(int tipoFragment){
        boolean valido = true;
        Data data =  new Data(this);
        data.open();
        switch (tipoFragment){
            case TipoFragmentEncuestado.P301P305:
                Log.e("cesar-P301P305", "onClick: "+ tFragment);
                if (data.getValor(SQLConstantes.tablafragments,SQLConstantes.fragments_p301p305,idEncuestado).equals("0") ||
                        data.getValor(SQLConstantes.tablafragments,SQLConstantes.fragments_p301p305,idEncuestado).equals("-1")) valido = false;
                break;
            case TipoFragmentEncuestado.P306P308:
                Log.e("cesar-P306P308", "onClick: "+ tFragment);
                if (data.getValor(SQLConstantes.tablafragments,SQLConstantes.fragments_p306p308,idEncuestado).equals("0") ||
                        data.getValor(SQLConstantes.tablafragments,SQLConstantes.fragments_p306p308,idEncuestado).equals("-1")) valido = false;
                break;
            case TipoFragmentEncuestado.P309:
                Log.e("cesar-P309", "onClick: "+ tFragment);
                if (data.getValor(SQLConstantes.tablafragments,SQLConstantes.fragments_p309,idEncuestado).equals("0") ||
                        data.getValor(SQLConstantes.tablafragments,SQLConstantes.fragments_p309,idEncuestado).equals("-1")) valido = false;
                break;
            case TipoFragmentEncuestado.P310P312:
                Log.e("cesar-P310P312", "onClick: "+ tFragment);
                if (data.getValor(SQLConstantes.tablafragments,SQLConstantes.fragments_p310p312,idEncuestado).equals("0") ||
                        data.getValor(SQLConstantes.tablafragments,SQLConstantes.fragments_p310p312,idEncuestado).equals("-1")) valido = false;
                break;
            case TipoFragmentEncuestado.P313P317:
                if (data.getValor(SQLConstantes.tablafragments,SQLConstantes.fragments_p313p317,idEncuestado).equals("0") ||
                        data.getValor(SQLConstantes.tablafragments,SQLConstantes.fragments_p313p317,idEncuestado).equals("-1")) valido = false;
                break;
            case TipoFragmentEncuestado.P318:
                if (data.getValor(SQLConstantes.tablafragments,SQLConstantes.fragments_p318,idEncuestado).equals("0") ||
                        data.getValor(SQLConstantes.tablafragments,SQLConstantes.fragments_p318,idEncuestado).equals("-1")) valido = false;
                break;
            case TipoFragmentEncuestado.P401P404:
                if (data.getValor(SQLConstantes.tablafragments,SQLConstantes.fragments_p401p404,idEncuestado).equals("0") ||
                        data.getValor(SQLConstantes.tablafragments,SQLConstantes.fragments_p401p404,idEncuestado).equals("-1")) valido = false;
                break;
            case TipoFragmentEncuestado.P405P407:
                if (data.getValor(SQLConstantes.tablafragments,SQLConstantes.fragments_p405p407,idEncuestado).equals("0") ||
                        data.getValor(SQLConstantes.tablafragments,SQLConstantes.fragments_p405p407,idEncuestado).equals("-1")) valido = false;
                break;
            case TipoFragmentEncuestado.P408P410:
                if (data.getValor(SQLConstantes.tablafragments,SQLConstantes.fragments_p408p410,idEncuestado).equals("0") ||
                        data.getValor(SQLConstantes.tablafragments,SQLConstantes.fragments_p408p410,idEncuestado).equals("-1")) valido = false;
                break;
            case TipoFragmentEncuestado.P411P416:
                if (data.getValor(SQLConstantes.tablafragments,SQLConstantes.fragments_p411p416,idEncuestado).equals("0") ||
                        data.getValor(SQLConstantes.tablafragments,SQLConstantes.fragments_p411p416,idEncuestado).equals("-1")) valido = false;
                break;
            case TipoFragmentEncuestado.P501P505:
                if (data.getValor(SQLConstantes.tablafragments,SQLConstantes.fragments_p501p505,idEncuestado).equals("0") ||
                        data.getValor(SQLConstantes.tablafragments,SQLConstantes.fragments_p501p505,idEncuestado).equals("-1")) valido = false;
                break;
            case TipoFragmentEncuestado.P506P507:
                if (data.getValor(SQLConstantes.tablafragments,SQLConstantes.fragments_p506p507,idEncuestado).equals("0") ||
                        data.getValor(SQLConstantes.tablafragments,SQLConstantes.fragments_p506p507,idEncuestado).equals("-1")) valido = false;
                break;
            case TipoFragmentEncuestado.P508P511:
                if (data.getValor(SQLConstantes.tablafragments,SQLConstantes.fragments_p508p511,idEncuestado).equals("0") ||
                        data.getValor(SQLConstantes.tablafragments,SQLConstantes.fragments_p508p511,idEncuestado).equals("-1")) valido = false;
                break;
            case TipoFragmentEncuestado.P512P513:
                if (data.getValor(SQLConstantes.tablafragments,SQLConstantes.fragments_p512p513,idEncuestado).equals("0") ||
                        data.getValor(SQLConstantes.tablafragments,SQLConstantes.fragments_p512p513,idEncuestado).equals("-1")) valido = false;
                break;
            case TipoFragmentEncuestado.P601P604:
                if (data.getValor(SQLConstantes.tablafragments,SQLConstantes.fragments_p601p604,idEncuestado).equals("0") ||
                        data.getValor(SQLConstantes.tablafragments,SQLConstantes.fragments_p601p604,idEncuestado).equals("-1")) valido = false;
                break;
            case TipoFragmentEncuestado.P605P608:
                if (data.getValor(SQLConstantes.tablafragments,SQLConstantes.fragments_p605p608,idEncuestado).equals("0") ||
                        data.getValor(SQLConstantes.tablafragments,SQLConstantes.fragments_p605p608,idEncuestado).equals("-1")) valido = false;
                break;
            case TipoFragmentEncuestado.P609P612:
                if (data.getValor(SQLConstantes.tablafragments,SQLConstantes.fragments_p609p612,idEncuestado).equals("0") ||
                        data.getValor(SQLConstantes.tablafragments,SQLConstantes.fragments_p609p612,idEncuestado).equals("-1")) valido = false;
                break;
            case TipoFragmentEncuestado.P613P618:
                if (data.getValor(SQLConstantes.tablafragments,SQLConstantes.fragments_p613p617,idEncuestado).equals("0") ||
                        data.getValor(SQLConstantes.tablafragments,SQLConstantes.fragments_p613p617,idEncuestado).equals("-1")) valido = false;
                break;
            case TipoFragmentEncuestado.P619P622:
                if (data.getValor(SQLConstantes.tablafragments,SQLConstantes.fragments_p618p621,idEncuestado).equals("0") ||
                        data.getValor(SQLConstantes.tablafragments,SQLConstantes.fragments_p618p621,idEncuestado).equals("-1")) valido = false;
                break;
            case TipoFragmentEncuestado.P623P625:
                if (data.getValor(SQLConstantes.tablafragments,SQLConstantes.fragments_p622p625,idEncuestado).equals("0") ||
                        data.getValor(SQLConstantes.tablafragments,SQLConstantes.fragments_p622p625,idEncuestado).equals("-1")) valido = false;
                break;
            case TipoFragmentEncuestado.P626P629:
                if (data.getValor(SQLConstantes.tablafragments,SQLConstantes.fragments_p626p629,idEncuestado).equals("0") ||
                        data.getValor(SQLConstantes.tablafragments,SQLConstantes.fragments_p626p629,idEncuestado).equals("-1")) valido = false;
                break;
            case TipoFragmentEncuestado.P630:
                if (data.getValor(SQLConstantes.tablafragments,SQLConstantes.fragments_p630,idEncuestado).equals("0") ||
                        data.getValor(SQLConstantes.tablafragments,SQLConstantes.fragments_p630,idEncuestado).equals("-1")) valido = false;
                break;
           case TipoFragmentEncuestado.P701P705:
               if (data.getValor(SQLConstantes.tablafragments,SQLConstantes.fragments_p701p705,idEncuestado).equals("0") ||
                        data.getValor(SQLConstantes.tablafragments,SQLConstantes.fragments_p701p705,idEncuestado).equals("-1")) valido = false;
                break;
            case TipoFragmentEncuestado.P706P709:
                if (data.getValor(SQLConstantes.tablafragments,SQLConstantes.fragments_p706p709,idEncuestado).equals("0") ||
                        data.getValor(SQLConstantes.tablafragments,SQLConstantes.fragments_p706p709,idEncuestado).equals("-1")) valido = false;
                break;
            case TipoFragmentEncuestado.P801P804:
               Log.e("cesar-P801P804", "onClick: "+ tFragment);
               if (data.getValor(SQLConstantes.tablafragments,SQLConstantes.fragments_p801p804,idEncuestado).equals("0") ||
                        data.getValor(SQLConstantes.tablafragments,SQLConstantes.fragments_p801p804,idEncuestado).equals("-1")) valido = false;
                break;
            case TipoFragmentEncuestado.P805P808:
                if (data.getValor(SQLConstantes.tablafragments,SQLConstantes.fragments_p805p808,idEncuestado).equals("0") ||
                        data.getValor(SQLConstantes.tablafragments,SQLConstantes.fragments_p805p808,idEncuestado).equals("-1")) valido = false;
                break;
            case TipoFragmentEncuestado.P809P812:
                if (data.getValor(SQLConstantes.tablafragments,SQLConstantes.fragments_p809p812,idEncuestado).equals("0") ||
                        data.getValor(SQLConstantes.tablafragments,SQLConstantes.fragments_p809p812,idEncuestado).equals("-1")) valido = false;
                break;
//            case TipoFragmentEncuestado.P813P816:
//                if (data.getValor(SQLConstantes.tablafragments,SQLConstantes.fragments_p813p816,idEncuestado).equals("0") ||
//                        data.getValor(SQLConstantes.tablafragments,SQLConstantes.fragments_p813p816,idEncuestado).equals("-1")) valido = false;
//                break;
//            case TipoFragmentEncuestado.P817P820:
//                if (data.getValor(SQLConstantes.tablafragments,SQLConstantes.fragments_p817p820,idEncuestado).equals("0") ||
//                        data.getValor(SQLConstantes.tablafragments,SQLConstantes.fragments_p817p820,idEncuestado).equals("-1")) valido = false;
//                break;
//            case TipoFragmentEncuestado.P821P823:
//                if (data.getValor(SQLConstantes.tablafragments,SQLConstantes.fragments_p821p823,idEncuestado).equals("0") ||
//                        data.getValor(SQLConstantes.tablafragments,SQLConstantes.fragments_p821p823,idEncuestado).equals("-1")) valido = false;
//                break;
        }
        return valido;
    }

    private void enableExpandableList() {
        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, List<String>>();
        expListView = (ExpandableListView) findViewById(R.id.left_drawer);

        prepareListData(listDataHeader, listDataChild);
        listAdapter = new ExpandListAdapter(this, listDataHeader, listDataChild);
        expListView.setAdapter(listAdapter);

        expListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                switch (groupPosition){
                    case 0:
                        switch (childPosition){
                            case 0: setFragment(TipoFragmentEncuestado.P301P305,0);break;
                            case 1: setFragment(TipoFragmentEncuestado.P306P308,0);break;
                            case 2: setFragment(TipoFragmentEncuestado.P309,0);break;
                            case 3: setFragment(TipoFragmentEncuestado.P310P312,0);break;
                            case 4: setFragment(TipoFragmentEncuestado.P313P317,0);break;
                            case 5: setFragment(TipoFragmentEncuestado.P318,0);break;
                        }break;
                    case 1:
                        switch (childPosition){
                            case 0: setFragment(TipoFragmentEncuestado.P401P404,0);break;
                            case 1: setFragment(TipoFragmentEncuestado.P405P407,0);break;
                            case 2: setFragment(TipoFragmentEncuestado.P408P410,0);break;
                            case 3: setFragment(TipoFragmentEncuestado.P411P416,0);break;
                        }break;
                    case 2:
                        switch (childPosition){
                            case 0: setFragment(TipoFragmentEncuestado.P501P505,0);break;
                            case 1: setFragment(TipoFragmentEncuestado.P506P507,0);break;
                            case 2: setFragment(TipoFragmentEncuestado.P508P511,0);break;
                            case 3: setFragment(TipoFragmentEncuestado.P512P513,0);break;
                        }break;
                    case 3:
                        switch (childPosition){
                            case 0: setFragment(TipoFragmentEncuestado.P601P604,0);break;
                            case 1: setFragment(TipoFragmentEncuestado.P605P608,0);break;
                            case 2: setFragment(TipoFragmentEncuestado.P609P612,0);break;
                            case 3: setFragment(TipoFragmentEncuestado.P613P618,0);break;
                            case 4: setFragment(TipoFragmentEncuestado.P619P622,0);break;
                            case 5: setFragment(TipoFragmentEncuestado.P623P625,0);break;
                            case 6: setFragment(TipoFragmentEncuestado.P626P629,0);break;
                            case 7: setFragment(TipoFragmentEncuestado.P630,0);break;
                        }break;
                    case 4:
                        switch (childPosition){
                            case 0: setFragment(TipoFragmentEncuestado.P701P705,0);break;
                            case 1: setFragment(TipoFragmentEncuestado.P706P709,0);break;
                        }break;
                    case 5:
                        switch (childPosition){
                            case 0: setFragment(TipoFragmentEncuestado.P801P804,0);break;
                            case 1: setFragment(TipoFragmentEncuestado.P805P808,0);break;
                           case 2: setFragment(TipoFragmentEncuestado.P809P812,0);break;
//                            case 3: setFragment(TipoFragmentEncuestado.P813P816,0);break;
//                            case 4: setFragment(TipoFragmentEncuestado.P817P820,0);break;
//                            case 5: setFragment(TipoFragmentEncuestado.P821P823,0);break;
                        }break;
                }
                DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                drawer.closeDrawer(GravityCompat.START);
                return false;
            }
        });
    }

    private void prepareListData(List<String> listDataHeader, Map<String, List<String>> listDataChild) {
        listDataHeader.add(getString(R.string.modulo_3_menu));
        listDataHeader.add(getString(R.string.modulo_4_menu));
        listDataHeader.add(getString(R.string.modulo_5_menu));
        listDataHeader.add(getString(R.string.modulo_6_menu));
        listDataHeader.add(getString(R.string.modulo_7_menu));
        listDataHeader.add(getString(R.string.modulo_8_menu));

        List<String> modulo3 = new ArrayList<String>();
        modulo3.add(getString(R.string.modulo_3_submenu_1));
        modulo3.add(getString(R.string.modulo_3_submenu_2));
        modulo3.add(getString(R.string.modulo_3_submenu_3));
        modulo3.add(getString(R.string.modulo_3_submenu_4));
//        modulo3.add(getString(R.string.modulo_3_submenu_5));
        modulo3.add(getString(R.string.modulo_3_submenu_6));

        List<String> modulo4 = new ArrayList<String>();
        modulo4.add(getString(R.string.modulo_4_submenu_1));
        modulo4.add(getString(R.string.modulo_4_submenu_2));
//        modulo4.add(getString(R.string.modulo_4_submenu_3));
//        modulo4.add(getString(R.string.modulo_4_submenu_4));
        modulo4.add("Preguntas 408 - 412");
        modulo4.add("Preguntas 413 - 418");

        List<String> modulo5 = new ArrayList<String>();
        modulo5.add(getString(R.string.modulo_5_submenu_1));
        modulo5.add(getString(R.string.modulo_5_submenu_2));
        modulo5.add(getString(R.string.modulo_5_submenu_3));
        modulo5.add("Preguntas 512 - 516");
//        modulo5.add(getString(R.string.modulo_5_submenu_4));

        List<String> modulo6 = new ArrayList<String>();
        modulo6.add(getString(R.string.modulo_6_submenu_1));
        modulo6.add(getString(R.string.modulo_6_submenu_2));
        modulo6.add("Preguntas 609 - 614");
        modulo6.add("Preguntas 615 - 619");
        modulo6.add("Preguntas 620 - 624");
        modulo6.add("Preguntas 625 - 629");
        modulo6.add("Preguntas 625 - 629");
        modulo6.add("Preguntas 630 - 634");
        modulo6.add("Preguntas 635 - 639");
//        modulo6.add(getString(R.string.modulo_6_submenu_3));
//        modulo6.add(getString(R.string.modulo_6_submenu_4));
//        modulo6.add(getString(R.string.modulo_6_submenu_5));
//        modulo6.add(getString(R.string.modulo_6_submenu_6));
//        modulo6.add(getString(R.string.modulo_6_submenu_7));
//        modulo6.add(getString(R.string.modulo_6_submenu_8));

        List<String> modulo7 = new ArrayList<String>();
        modulo7.add(getString(R.string.modulo_7_submenu_1));
        modulo7.add(getString(R.string.modulo_7_submenu_2));

        List<String> modulo8 = new ArrayList<String>();
        modulo8.add("Preguntas 801 - 802");
        modulo8.add("Preguntas 803 - 806");
        modulo8.add("Preguntas 807");
//        modulo8.add(getString(R.string.modulo_8_submenu_1));
//        modulo8.add(getString(R.string.modulo_8_submenu_2));
//        modulo8.add(getString(R.string.modulo_8_submenu_3));
//        modulo8.add(getString(R.string.modulo_8_submenu_4));
//        modulo8.add(getString(R.string.modulo_8_submenu_5));
//        modulo8.add(getString(R.string.modulo_8_submenu_6));

        listDataChild.put(listDataHeader.get(0), modulo3);
        listDataChild.put(listDataHeader.get(1), modulo4);
        listDataChild.put(listDataHeader.get(2), modulo5);
        listDataChild.put(listDataHeader.get(3), modulo6);
        listDataChild.put(listDataHeader.get(4), modulo7);
        listDataChild.put(listDataHeader.get(5), modulo8);


    }

    @Override
    public void setFragmentFromOtherFragment(int tipoFragment, String idHogar, String idEncuestado) {
        this.tFragment = tipoFragment;
        this.idHogar = idHogar;
        this.idEncuestado = idEncuestado;
        setFragment(tipoFragment,1);
        btnSiguiente.setVisibility(View.VISIBLE);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.encuesta, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        final int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_volver_residentes) {
            salirActivityEncuestado();
            return true;
        }else if (id == R.id.action_registrar_observacion) {
            AlertDialog.Builder dialog = new AlertDialog.Builder(this);
            final View dialogView = this.getLayoutInflater().inflate(R.layout.dialog_observaciones, null);
            LinearLayout lytObservaciones = dialogView.findViewById(R.id.dialog_lytObservaciones);
            final EditText edtObservaciones = dialogView.findViewById(R.id.dialog_edtObservaciones);
            edtObservaciones.setFilters(new InputFilter[]{new InputFilter.AllCaps()});
            dialog.setView(dialogView);
            dialog.setTitle("OBSERVACIONES MODULO " + moduloActual);
            dialog.setPositiveButton("Guardar", null);
            dialog.setNegativeButton("Cancelar", null);
            final AlertDialog alertDialog = dialog.create();
            alertDialog.setOnShowListener(new DialogInterface.OnShowListener() {
                @Override
                public void onShow(DialogInterface dialogInterface) {
                    Data data = new Data(EncuestaActivity.this);
                    data.open();
                    switch (moduloActual){
                        case 3:edtObservaciones.setText(data.getValor(SQLConstantes.tablamodulo3,SQLConstantes.modulo3_obs_cap3,idEncuestado));break;
                        case 4:edtObservaciones.setText(data.getValor(SQLConstantes.tablamodulo4,SQLConstantes.modulo4_obs_cap4,idEncuestado));break;
                        case 5:edtObservaciones.setText(data.getValor(SQLConstantes.tablamodulo5,SQLConstantes.modulo5_obs_cap5,idEncuestado));break;
                        case 6:edtObservaciones.setText(data.getValor(SQLConstantes.tablamodulo6,SQLConstantes.modulo6_obs_cap6,idEncuestado));break;
                        case 7:edtObservaciones.setText(data.getValor(SQLConstantes.tablamodulo7,SQLConstantes.modulo7_obs_cap7,idEncuestado));break;
                        case 8:edtObservaciones.setText(data.getValor(SQLConstantes.tablamodulo8,SQLConstantes.modulo8_obs_cap8,idEncuestado));break;
                    }
                    data.close();
                    Button b = alertDialog.getButton(AlertDialog.BUTTON_POSITIVE);
                    b.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Data data = new Data(EncuestaActivity.this);
                            data.open();
                            switch (moduloActual){
                                case 3: data.actualizarValor(SQLConstantes.tablamodulo3,SQLConstantes.modulo3_obs_cap3,edtObservaciones.getText().toString(),idEncuestado);
                                    break;
                                case 4: data.actualizarValor(SQLConstantes.tablamodulo4,SQLConstantes.modulo4_obs_cap4,edtObservaciones.getText().toString(),idEncuestado);
                                    break;
                                case 5: data.actualizarValor(SQLConstantes.tablamodulo5,SQLConstantes.modulo5_obs_cap5,edtObservaciones.getText().toString(),idEncuestado);
                                    break;
                                case 6: data.actualizarValor(SQLConstantes.tablamodulo6,SQLConstantes.modulo6_obs_cap6,edtObservaciones.getText().toString(),idEncuestado);
                                    break;
                                case 7: data.actualizarValor(SQLConstantes.tablamodulo7,SQLConstantes.modulo7_obs_cap7,edtObservaciones.getText().toString(),idEncuestado);
                                    break;
                                case 8: data.actualizarValor(SQLConstantes.tablamodulo8,SQLConstantes.modulo8_obs_cap8,edtObservaciones.getText().toString(),idEncuestado);
                                    break;
                            }
                            data.close();
                            alertDialog.dismiss();
                        }
                    });
                }
            });
            alertDialog.show();
            return true;
        }else if(id == R.id.action_finalizar_encuestado){
            updateResultadoResultado();
//            if (verificarCobertura()){
//                Data data = new Data(EncuestaActivity.this);
//                data.open();
//                data.actualizarValor(SQLConstantes.tablaresidentes,SQLConstantes.residentes_encuestado_cobertura,"1",idEncuestado);
//                data.close();
//                finish();
//            }
        }
        return super.onOptionsItemSelected(item);
    }

    public void salirActivityEncuestado(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("¿Está seguro que desea salir de la encuesta y volver a la lista de los residentes del hogar?")
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
                                Data data = new Data(EncuestaActivity.this);
                                data.open();
                                if (verificarCoberturaSinMensaje())
                                    data.actualizarValor(SQLConstantes.tablaresidentes,SQLConstantes.residentes_encuestado_cobertura,"1",idEncuestado);
                                data.close();
                                finish();
                            }
                        });
        AlertDialog alert = builder.create();
        alert.show();
    }

    public void salirEncuestaFinalizada(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Se terminó la encuesta para este residente. ¿Desea continuar revisando los datos o finalizar la encuesta del residente?")
                .setTitle("Aviso")
                .setCancelable(false)
                .setNegativeButton("CONTINUAR",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                tFragment = 1;
                                setFragment(tFragment,1);
                                dialog.cancel();
                            }
                        })
                .setPositiveButton("FINALIZAR",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                if (verificarCobertura()){
                                    Data data = new Data(EncuestaActivity.this);
                                    data.open();
                                    data.actualizarValor(SQLConstantes.tablaresidentes,SQLConstantes.residentes_encuestado_cobertura,"1",idEncuestado);
                                    data.close();
                                    finish();
                                }
                                else {
                                    dialog.dismiss();
                                }
                            }
                        });
        AlertDialog alert = builder.create();
        alert.show();
    }

    public boolean verificarCobertura(){
//        Data data = new Data(EncuestaActivity.this);
//        data.open();
//        if (data.getValor(SQLConstantes.tablamodulo3,SQLConstantes.modulo3_COB300,idEncuestado).equals("0")){
//            mostrarMensaje("Falta coberturar el modulo 3 para poder finalizar");
//            tFragment = TipoFragmentEncuestado.P301P305;
//            setFragment(tFragment,1);
//            return false;
//        }
//        if (data.getValor(SQLConstantes.tablamodulo4,SQLConstantes.modulo4_COB400,idEncuestado).equals("0")){
//            mostrarMensaje("Falta coberturar el modulo 4 para poder finalizar");
//            tFragment = TipoFragmentEncuestado.P401P404;
//            setFragment(tFragment,1);
//            return false;
//        }
//        if (data.getValor(SQLConstantes.tablamodulo5,SQLConstantes.modulo5_COB500,idEncuestado).equals("0")){
//            mostrarMensaje("Falta coberturar el modulo 5 para poder finalizar");
//            tFragment = TipoFragmentEncuestado.P501P505;
//            setFragment(tFragment,1);
//            return false;
//        }
//        if (data.getValor(SQLConstantes.tablamodulo6,SQLConstantes.modulo6_COB600,idEncuestado).equals("0")){
//            mostrarMensaje("Falta coberturar el modulo 6 para poder finalizar");
//            tFragment = TipoFragmentEncuestado.P601P604;
//            setFragment(tFragment,1);
//            return false;
//        }
//        if (data.getValor(SQLConstantes.tablamodulo7,SQLConstantes.modulo7_COB700,idEncuestado).equals("0")){
//            mostrarMensaje("Falta coberturar el modulo 7 para poder finalizar");
//            tFragment = TipoFragmentEncuestado.P701P705;
//            setFragment(tFragment,1);
//            return false;
//        }
//        if (data.getValor(SQLConstantes.tablamodulo8,SQLConstantes.modulo8_COB800,idEncuestado).equals("0")){
//            mostrarMensaje("Falta coberturar el modulo 8 para poder finalizar");
//            tFragment = TipoFragmentEncuestado.P801P804;
//            setFragment(tFragment,1);
//            return false;
//        }
//        data.close();
        return true;
    }

    public boolean verificarCoberturaSinMensaje(){
        Data data = new Data(EncuestaActivity.this);
        data.open();
        if((data.getValor(SQLConstantes.tablamodulo3,SQLConstantes.modulo3_COB300,idEncuestado).equals("0")) ||
        (data.getValor(SQLConstantes.tablamodulo4,SQLConstantes.modulo4_COB400,idEncuestado).equals("0"))||
         (data.getValor(SQLConstantes.tablamodulo5,SQLConstantes.modulo5_COB500,idEncuestado).equals("0"))||
        (data.getValor(SQLConstantes.tablamodulo6,SQLConstantes.modulo6_COB600,idEncuestado).equals("0"))||
        (data.getValor(SQLConstantes.tablamodulo7,SQLConstantes.modulo7_COB700,idEncuestado).equals("0"))||
        (data.getValor(SQLConstantes.tablamodulo8,SQLConstantes.modulo8_COB800,idEncuestado).equals("0")))
            return false;
        data.close();
        return true;
    }

    public void mostrarMensaje(String m){
        final AlertDialog.Builder builder = new AlertDialog.Builder(EncuestaActivity.this);
        builder.setMessage(m);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.dismiss();
            }
        });
        final AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    public void updateResultadoResultado(){
        android.support.v7.app.AlertDialog.Builder alert = new android.support.v7.app.AlertDialog.Builder(this);
        final View dialogView = this.getLayoutInflater().inflate(R.layout.dialog_resultado_entrevista_persona, null);
        final LinearLayout lytDialog       = (LinearLayout) dialogView.findViewById(R.id.dialog_resultado_persona_ly);
        final Spinner spResultado          = (Spinner) dialogView.findViewById(R.id.dialog_resultado_persona_sp);
        final EditText edtEspecifique      = (EditText) dialogView.findViewById(R.id.dialog_resultado_persona_edt);
        final RadioGroup rgTipoEntrevista1  = (RadioGroup) dialogView.findViewById(R.id.dialog_tipo_persona_rg);
        final EditText   edtTipoEntrevista1 = (EditText) dialogView.findViewById(R.id.dialog_tipo_persona_edt);
        final RadioGroup rgTipoEntrevista2  = (RadioGroup) dialogView.findViewById(R.id.dialog_tipo2_persona_rg);
        final EditText   edtTipoEntrevista2 = (EditText) dialogView.findViewById(R.id.dialog_tipo2_persona_edt);
        final RadioButton rb1 = (RadioButton) dialogView.findViewById(R.id.dialog_entrevistado_rb1);
        final RadioButton rb2 = (RadioButton) dialogView.findViewById(R.id.dialog_entrevistado_rb2);
        final RadioButton rb3 = (RadioButton) dialogView.findViewById(R.id.dialog_entrevistado_rb3);

        String especifique = "";
        edtEspecifique.setFilters(new InputFilter[]{new InputFilter.AllCaps(),new InputFilter.LengthFilter(100), new InputFilterSoloLetras()});
        edtTipoEntrevista1.setFilters(new InputFilter[]{new InputFilter.AllCaps(),new InputFilter.LengthFilter(100), new InputFilterSoloLetras()});
        edtTipoEntrevista2.setFilters(new InputFilter[]{new InputFilter.AllCaps(),new InputFilter.LengthFilter(100), new InputFilterSoloLetras()});


        spResultado.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long l) {
                UtilsMethodsInputs.setupSpinnerEspecifique2(pos,3,13,edtEspecifique);
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {}
        });

        rgTipoEntrevista1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                int seleccionado = group.indexOfChild(group.findViewById(checkedId));
                UtilsMethodsInputs.setupRadioGroupEspecifique(group, checkedId,3,edtTipoEntrevista1);
                switch (seleccionado){
                    case 1:
                        rb1.setEnabled(true);
                        rb2.setEnabled(true);
                        rb3.setEnabled(true);
                        break;
                    default:
                        rb1.setEnabled(false);
                        rb2.setEnabled(false);
                        rb3.setEnabled(false);
                        rgTipoEntrevista2.clearCheck();
                        edtTipoEntrevista2.setText("");
                        break;
                }
            }
        });

        rgTipoEntrevista2.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                UtilsMethodsInputs.setupRadioGroupEspecifique(group, checkedId,3,edtTipoEntrevista2);
            }
        });

        alert.setTitle("RESIDENTE:");
        alert.setView(dialogView);
        alert.setPositiveButton("Finalizar",null);
        alert.setNegativeButton("Cancelar",null);
        final android.support.v7.app.AlertDialog alertDialog = alert.create();
        ResultadoResidente resultado = DAOUtils.getResultadoRedidente(idEncuestado,getApplicationContext());
        if(resultado!=null){
            Log.e("dato","A");
            if(!resultado.getResultado_entrevista().equals("")){
                spResultado.setSelection(Integer.parseInt(resultado.getResultado_entrevista()));
                edtEspecifique.setText(resultado.getResultado_entrevista_o());
                if(!resultado.getTipo_entrevista().equals("-1") && !resultado.getTipo_entrevista().equals(""))((RadioButton) rgTipoEntrevista1.getChildAt(Integer.parseInt(resultado.getTipo_entrevista()))).setChecked(true);
                edtTipoEntrevista1.setText(resultado.getTipo_entrevista_o());
                if(!resultado.getDonde_entrevista().equals("-1") && !resultado.getDonde_entrevista().equals(""))((RadioButton) rgTipoEntrevista2.getChildAt(Integer.parseInt(resultado.getDonde_entrevista()))).setChecked(true);
                edtTipoEntrevista2.setText(resultado.getDonde_entrevista_o());
                Log.e("dato","B");
            }

        }

        alertDialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialogInterface) {
                ocultarTeclado(lytDialog);
                Button btnFinalizarVisita = alertDialog.getButton(android.support.v7.app.AlertDialog.BUTTON_POSITIVE);
                btnFinalizarVisita.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        boolean valido = false;
                        boolean estado = true;
                        int estadoMensaje = 0;
                        String mensaje = "";

                        if(spResultado.getSelectedItemPosition() == 0){
                            estado = false;
                            if(mensaje.equals("")) mensaje = "DEBE INDICAR EL RESULTADO DE LA PERSONA";
                        }else if((spResultado.getSelectedItemPosition() == 3 || spResultado.getSelectedItemPosition() == 13  ) && edtEspecifique.getText().toString().trim().length()==0){
                            estado = false;
                            if(mensaje.equals("")) mensaje = "FALTA DETALLAR EL ESPECIFIQUE DEL RESULTADO DE LA ENTREVISTA POR PERSONA\n";
                        }else if((spResultado.getSelectedItemPosition() == 3 || spResultado.getSelectedItemPosition() == 13  ) && edtEspecifique.getText().toString().trim().length() < 3){
                            estado = false;
                            if(mensaje.equals("")) mensaje = "EL ESPECIFIQUE NO DEBE SER MENOR A 3 CARACTERES";
                        }else if(rgTipoEntrevista1.indexOfChild(rgTipoEntrevista1.findViewById(rgTipoEntrevista1.getCheckedRadioButtonId()))== -1){
                            estado = false;
                            if(mensaje.equals("")) mensaje = "DEBE INDICAR EL TIPO DE ENTREVISTA";
                        }else if (rgTipoEntrevista1.indexOfChild(rgTipoEntrevista1.findViewById(rgTipoEntrevista1.getCheckedRadioButtonId()))== 3 && edtTipoEntrevista1.getText().toString().trim().length() < 3){
                            estado = false;
                            if(mensaje.equals("")) mensaje = "DEBE ESPECIFICAR EL TIPO DE ENTREVISTA";
                        }else if(rgTipoEntrevista1.indexOfChild(rgTipoEntrevista1.findViewById(rgTipoEntrevista1.getCheckedRadioButtonId()))== 1 && rgTipoEntrevista2.indexOfChild(rgTipoEntrevista2.findViewById(rgTipoEntrevista2.getCheckedRadioButtonId()))== -1){
                            estado = false;
                            if(mensaje.equals("")) mensaje = "DEBE INDICAR DONDE FUE LA ENTREVISTA";
                        }else if (rgTipoEntrevista2.indexOfChild(rgTipoEntrevista2.findViewById(rgTipoEntrevista2.getCheckedRadioButtonId()))== 3 && edtTipoEntrevista2.getText().toString().trim().length() < 3){
                            estado = false;
                            if(mensaje.equals("")) mensaje = "DEBE ESPECIFICAR DONDE FUE LA ENTREVISTA";
                        }else if(DAOUtils.getCoberturaPersona(idEncuestado,getApplicationContext())==1 && spResultado.getSelectedItemPosition()!=1){
                            estado = true;
                            estadoMensaje = 1;
                            if(mensaje.equals("")) mensaje = "SI LOS CAPITULOS ESTAN COMPLETOS EL RESULTADO POR PERSONA DEBE SER COMPLETO(1)";
                      /*  }else if(DAOUtils.getCoberturaPersona(idEncuestado,getApplicationContext())==2 && spResultado.getSelectedItemPosition()==1){
                            estado = true;
                            estadoMensaje = 1;
                            if(mensaje.equals("")) mensaje = "SI LOS CAPITULOS ESTAN INCOMPLETOS EL RESULTADO POR PERSONA NO PUEDE SER COMPLETO(1)";
                        }*/

                      //////////////////MODIFICADO 19/11/21 OBSERVACION 101////////////////////////////////////
                        }else if(DAOUtils.getCoberturaPersona(idEncuestado,getApplicationContext())==2 && spResultado.getSelectedItemPosition()==1){
                            estado = false;
                            estadoMensaje = 1;
                            if(mensaje.equals("")) mensaje = "SI LOS CAPITULOS ESTAN INCOMPLETOS EL RESULTADO POR PERSONA NO PUEDE SER COMPLETO(1)";
                        }
                        //////////////////////////////////////////////////////////////////
                        valido = estado;
                        if(valido && estadoMensaje==0){
                            String rg_donde = rgTipoEntrevista2.indexOfChild(rgTipoEntrevista2.findViewById(rgTipoEntrevista2.getCheckedRadioButtonId()))+"";
                            ContentValues contentValuesFinal = new ContentValues();
                            contentValuesFinal.put(SQLConstantes.resultado_residente_hor_fin,UtilsMethods.getFechaActual().getHoraInicio());
                            contentValuesFinal.put(SQLConstantes.resultado_residente_min_fin,UtilsMethods.getFechaActual().getMinutoInicio());
                            contentValuesFinal.put(SQLConstantes.resultado_residente_resultado_entrevista,spResultado.getSelectedItemPosition()+"");
                            contentValuesFinal.put(SQLConstantes.resultado_residente_resultado_entrevista_o,edtEspecifique.getText().toString()+"");
                            contentValuesFinal.put(SQLConstantes.resultado_residente_tipo_entrevista,rgTipoEntrevista1.indexOfChild(rgTipoEntrevista1.findViewById(rgTipoEntrevista1.getCheckedRadioButtonId()))+"");
                            contentValuesFinal.put(SQLConstantes.resultado_residente_tipo_entrevista_o,edtTipoEntrevista1.getText().toString()+"");
                            if(!rg_donde.equals("-1")){
                                contentValuesFinal.put(SQLConstantes.resultado_residente_donde_entrevista,rg_donde);
                            }else {
                                contentValuesFinal.put(SQLConstantes.resultado_residente_donde_entrevista,"");
                            }
                            contentValuesFinal.put(SQLConstantes.resultado_residente_donde_entrevista_o,edtTipoEntrevista2.getText().toString()+"");
                            ContentValues contentValuesResidente = new ContentValues();
                            contentValuesResidente.put(SQLConstantes.residentes_encuestado_cobertura,DAOUtils.getCoberturaPersona(idEncuestado,getApplicationContext()));
                            Data dataTablas = new Data(EncuestaActivity.this);
                            dataTablas.open();
                            dataTablas.actualizarElemento(SQLConstantes.tablaresultadoresidente,contentValuesFinal, idEncuestado);
                            dataTablas.actualizarElemento(SQLConstantes.tablaresidentes,contentValuesResidente, idEncuestado);
                            dataTablas.close();
                            alertDialog.dismiss();
                            finish();
                        }else if(valido && estadoMensaje==1){
                            String rg_donde = rgTipoEntrevista2.indexOfChild(rgTipoEntrevista2.findViewById(rgTipoEntrevista2.getCheckedRadioButtonId()))+"";
                            ContentValues contentValuesFinal = new ContentValues();
                            contentValuesFinal.put(SQLConstantes.resultado_residente_hor_fin,UtilsMethods.getFechaActual().getHoraInicio());
                            contentValuesFinal.put(SQLConstantes.resultado_residente_min_fin,UtilsMethods.getFechaActual().getMinutoInicio());
                            contentValuesFinal.put(SQLConstantes.resultado_residente_resultado_entrevista,spResultado.getSelectedItemPosition()+"");
                            contentValuesFinal.put(SQLConstantes.resultado_residente_resultado_entrevista_o,edtEspecifique.getText().toString()+"");
                            contentValuesFinal.put(SQLConstantes.resultado_residente_tipo_entrevista,rgTipoEntrevista1.indexOfChild(rgTipoEntrevista1.findViewById(rgTipoEntrevista1.getCheckedRadioButtonId()))+"");
                            contentValuesFinal.put(SQLConstantes.resultado_residente_tipo_entrevista_o,edtTipoEntrevista1.getText().toString()+"");
                            if(!rg_donde.equals("-1")){
                                contentValuesFinal.put(SQLConstantes.resultado_residente_donde_entrevista,rg_donde);
                            }else {
                                contentValuesFinal.put(SQLConstantes.resultado_residente_donde_entrevista,"");
                            }
                            contentValuesFinal.put(SQLConstantes.resultado_residente_donde_entrevista_o,edtTipoEntrevista2.getText().toString()+"");
                            ContentValues contentValuesResidente = new ContentValues();
                            contentValuesResidente.put(SQLConstantes.residentes_encuestado_cobertura,DAOUtils.getCoberturaPersona(idEncuestado,getApplicationContext()));
                            Data dataTablas = new Data(EncuestaActivity.this);
                            dataTablas.open();
                            dataTablas.actualizarElemento(SQLConstantes.tablaresultadoresidente,contentValuesFinal, idEncuestado);
                            dataTablas.actualizarElemento(SQLConstantes.tablaresidentes,contentValuesResidente, idEncuestado);
                            dataTablas.close();
                            //alertDialog.dismiss();
                            mostrarMensaje2(mensaje);
                        }else {
                            Toast.makeText(getApplicationContext(), mensaje, Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
        alertDialog.show();
    }

    public void mostrarMensaje2(String m){
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(m);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.dismiss();
               // finish();
            }
        });
        final AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Residente residente = DAOUtils.getResidente(idEncuestado,getApplicationContext());
        if (residente.getC2_p205_a().equals("")) getSupportActionBar().setSubtitle(residente.getC2_p202()+" "+residente.getC2_p202_pat()+ " ("+ residente.getC2_p205_m() +" meses)");
        else getSupportActionBar().setSubtitle(residente.getC2_p202()+" "+residente.getC2_p202_pat()+ " ("+ residente.getC2_p205_a() +" años)");

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
                            status.startResolutionForResult(EncuestaActivity.this, PETICION_CONFIG_UBICACION);
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


                    if (ActivityCompat.checkSelfPermission(EncuestaActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                        ActivityCompat.requestPermissions(EncuestaActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, PETICION_PERMISO_LOCALIZACION);
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
                    ubicacion.setUsuario(DAOUtils.getUsuarioId(idUsuario,EncuestaActivity.this).getNombre());
                    ubicacion.setDni(DAOUtils.getUsuarioId(idUsuario,EncuestaActivity.this).getDni());
                    ubicacion.setCargo(DAOUtils.getUsuarioId(idUsuario,EncuestaActivity.this).getUsuario());
                    ubicacion.setIdDispositivo(getIdDispositivo());
                    //ubicacion.setSerial(getSerialNumber());
                    ubicacion.setLongitud(getLatitud(longitud));
                    ubicacion.setLatitud(getLongitud(latitud));
                    //ubicacion.setLongitud(longitud);
                    //ubicacion.setLatitud(latitud);
                    ubicacion.setFechaTablet(UtilsMethods.getFechaNow().getDiaInicio()+"/"+UtilsMethods.getFechaNow().getMesInicio()+"/"+UtilsMethods.getFechaNow().getAnioInicio()+"-"+UtilsMethods.getFechaNow().getHoraInicio()+":"+UtilsMethods.getFechaNow().getMinutoInicio());
                    ubicacion.setVersionApk(UtilsMethods.getVersion(EncuestaActivity.this));
                    ubicacion.setBateria(""+getBateria());
                    ubicacion.setConexion("Activo");
                    ubicacion.setSegmento(nroSegmento);///
                    ubicacion.setPeriodo(vivienda_periodo);///
                    ubicacion.setConglomerado(conglomerado);///

                    //Toast.makeText(EncuestaActivity.this, "LONGITUD ES"+ubicacion.getLongitud() +"\n LATITUD ES"+ubicacion.getLatitud(), Toast.LENGTH_LONG).show();


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
/*                   String url =  "http://200.123.3.26/wsenpove/2022/USP_WSENPOVE2022_TRACKING_USUARIO.php?ID_VIVIENDA="+ubicacion.getId()+"&ID_USUARIO="+ubicacion.getIdUsuario()
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
