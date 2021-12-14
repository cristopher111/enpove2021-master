package com.example.ricindigus.enpove2021.activities;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.location.Location;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.BatteryManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ricindigus.enpove2021.R;
import com.example.ricindigus.enpove2021.adapters.MarcoAdapter;
import com.example.ricindigus.enpove2021.adapters.ViviendaAdapter;
import com.example.ricindigus.enpove2021.modelo.Data;
import com.example.ricindigus.enpove2021.modelo.SQLConstantes;
import com.example.ricindigus.enpove2021.modelo.pojos.Carga;
import com.example.ricindigus.enpove2021.modelo.pojos.ItemMarco;
import com.example.ricindigus.enpove2021.modelo.pojos.POJOFragmentVivienda;
import com.example.ricindigus.enpove2021.modelo.pojos.ViviendaItem;
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

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.ArrayList;

import static android.os.Environment.getExternalStorageDirectory;

public class MarcoActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private MarcoAdapter marcoAdapter;
    private ViviendaAdapter viviendaAdapter;
    ArrayList<ViviendaItem> listaViviendas;
    private ArrayList<ItemMarco> itemMarcos;
    private ArrayList<String> anios;
    private ArrayList<String> meses;
    private ArrayList<String> periodos;
    private ArrayList<String> zonas;
    private String nickUsuario;
    private String idUsuario;
    private String idCargo;
    private Spinner spAnio;
    private Spinner spMeses;
    private Spinner spPeriodos;
    private Spinner spZonas;
    private Button btnFiltrar;
    private Button btnMostrarTodo;
    private LinearLayoutManager linearLayoutManager;
    private ImageView ivHeader;
    private TextView txtResultado;
    private TextView txtEstado;
    MenuItem itemEvaluacion;
    private static final String url = "http://aplicaciones.inei.gob.pe/intranetapp/login.htm";

    private static final String LOGTAG = "android-localizacion";
    private static final int PETICION_PERMISO_LOCALIZACION = 101;
    private static final int PETICION_CONFIG_UBICACION = 201;
    private GoogleApiClient apiClient;
    private LocationRequest locRequest;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_marco);
        nickUsuario = getIntent().getExtras().getString("nickUsuario");
        idUsuario   = getIntent().getExtras().getString("idUsuario");
        idCargo     = getIntent().getExtras().getString("idCargo");

        spAnio         = (Spinner) findViewById(R.id.marco_sp_anio);
        spMeses        = (Spinner) findViewById(R.id.marco_sp_mes);
        spPeriodos     = (Spinner) findViewById(R.id.marco_sp_periodo);
        spZonas        = (Spinner) findViewById(R.id.marco_sp_zona);
        btnFiltrar     = (Button) findViewById(R.id.marco_btnFiltrar);
        btnMostrarTodo = (Button) findViewById(R.id.marco_btnMotrarTodo);
        ivHeader       = (ImageView) findViewById(R.id.vivienda_header_iv);
        txtResultado   = (TextView)  findViewById(R.id.vivienda_header_resultado_txt);
        txtEstado      = (TextView)  findViewById(R.id.vivienda_header_estado_txt);
        itemEvaluacion = (MenuItem) findViewById(R.id.opcion_evaluacion);


        ivHeader.setVisibility(View.GONE);
        txtResultado.setVisibility(View.GONE);
        txtEstado.setVisibility(View.GONE);


        Toolbar toolbar =  (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Usuario:"+nickUsuario+"                              "+getResources().getString(R.string.app_name));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MarcoActivity.this);
                builder.setMessage("¿Está seguro que desea salir de la aplicación?")
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
                                        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                                        startActivity(intent);
                                    }
                                });
                AlertDialog alert = builder.create();
                alert.show();
            }
        });

        recyclerView = (RecyclerView) findViewById(R.id.recycler_encuestado);
        recyclerView.setHasFixedSize(true);
        linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(linearLayoutManager);

        spAnio.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(i > 0) obtenerMeses(Integer.parseInt(spAnio.getSelectedItem().toString()));
                if(i == 0) meses = new ArrayList<String>();
                cargarSpinerMeses(meses);
                periodos = new ArrayList<String>();
                cargarSpinerPeriodos(periodos);
                zonas = new ArrayList<String>();
                cargarSpinerZonas(zonas);
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {}
        });

        spMeses.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(i > 0) obtenerPeriodos(Integer.parseInt(spMeses.getSelectedItem().toString()));
                if(i == 0) periodos = new ArrayList<String>();
                cargarSpinerPeriodos(periodos);
                zonas = new ArrayList<String>();
                cargarSpinerZonas(zonas);
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {}
        });

        spPeriodos.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(i > 0) obtenerZonas(Integer.parseInt(spPeriodos.getSelectedItem().toString()));
                if(i == 0) zonas = new ArrayList<String>();
                cargarSpinerZonas(zonas);
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {}
        });

        btnFiltrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(zonas.size() > 1 && spZonas.getSelectedItemPosition() != 0){
                    obtenerMarcoFiltrado(spAnio.getSelectedItem().toString(),
                            spMeses.getSelectedItem().toString(),
                            spPeriodos.getSelectedItem().toString(),
                            spZonas.getSelectedItem().toString());
                }else{
                    Toast.makeText(MarcoActivity.this, "DEBE SELECCIONAR TODOS LOS CAMPOS ANTES DE FILTRAR", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnMostrarTodo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                obtenerMarcoTotal();
                spAnio.setSelection(0);
            }
        });

//        if(idUsuario=="22"){
//            itemEvaluacion.setVisible(true);
//        }

    }

    public void obtenerMarcoFiltrado(String anio,String mes, String periodo, String nroSegmento){
        itemMarcos = new ArrayList<>();
        listaViviendas = new ArrayList<>();
        Data data = new Data(MarcoActivity.this);
        data.open();
        listaViviendas = data.getListMarcoFiltradoXCarga(anio, mes,periodo,nroSegmento,getListaCarga(idUsuario));
        data.close();
        setAdapterMarco();
    }

    public void obtenerMarcoTotal(){
        inicializarDatos();
        cargarSpinerAnios(anios);
        cargarSpinerMeses(meses);
        cargarSpinerPeriodos(periodos);
        cargarSpinerZonas(zonas);
        setAdapterMarco();
    }

    public void obtenerMeses(int anio){
        meses = new ArrayList<String>();
        meses.add("Seleccione");
        for(ViviendaItem viviendaItem : listaViviendas){
            if(Integer.parseInt(viviendaItem.getAnio())== anio){
                if(!meses.contains(viviendaItem.getMes())){
                    meses.add(String.valueOf(viviendaItem.getMes()));
                }
            }
        }
    }

    public void obtenerPeriodos(int mes){
        periodos = new ArrayList<String>();
        periodos.add("Seleccione");
        for(ViviendaItem viviendaItem : listaViviendas){
            if(Integer.parseInt(viviendaItem.getMes())== mes){
                if(!periodos.contains(viviendaItem.getPeriodo())){
                    periodos.add(String.valueOf(viviendaItem.getPeriodo()));
                }
            }
        }
    }

    public void obtenerZonas(int periodo){
        zonas = new ArrayList<String>();
        zonas.add("Seleccione");
        for(ViviendaItem viviendaItem : listaViviendas){
            if(Integer.parseInt(viviendaItem.getPeriodo())== periodo){
                if(!zonas.contains(viviendaItem.getNrosegmento())){
                    zonas.add(String.valueOf(viviendaItem.getNrosegmento()));
                }
            }
        }
    }

    public void cargarSpinerAnios(ArrayList<String> datos){
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,datos);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spAnio.setAdapter(adapter);
    }

    public void cargarSpinerMeses(ArrayList<String> datos){
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,datos);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spMeses.setAdapter(adapter);
    }

    public void cargarSpinerPeriodos(ArrayList<String> datos){
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,datos);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spPeriodos.setAdapter(adapter);
    }

    public void cargarSpinerZonas(ArrayList<String> datos){
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,datos);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spZonas.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_marco,menu);

        if(nickUsuario.equals("ADM")){
            menu.getItem(4).setVisible(true);
        }
//        super.onCreateOptionsMenu(menu);
//        MenuItem item1 = menu.findItem(R.id.opcion_exportar);
//        MenuItem item2= menu.findItem(R.id.opcion_importar);
//        MenuItem item3 = menu.findItem(R.id.opcion_evaluacion);
//        item1.setVisible(true);
//        getMenuInflater().inflate(R.menu.menu_marco,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.opcion_exportar:
                Intent intent = new Intent(MarcoActivity.this,ExportarActivity.class);
                intent.putExtra("idUsuario",idUsuario);
                startActivity(intent);
                return true;
            case R.id.opcion_importar:
                Intent intent1 = new Intent(MarcoActivity.this,ImportarActivity.class);
                startActivity(intent1);
                return true;
            case R.id.opcion_croquis:
                getCroquis(Integer.parseInt(idUsuario));
                return true;
            case R.id.opcion_bpr:
                Uri uri = Uri.parse(url);
                Intent intent2 = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent2);
//                Intent intent2 = new Intent(MarcoActivity.this,BPRActivity.class);
//                intent2.putExtra("idUsuario",""+idUsuario);
//                startActivity(intent2);
                return true;
            case R.id.opcion_evaluacion:
                Intent intent3 = new Intent(MarcoActivity.this,EvaluacionActivity.class);
                startActivity(intent3);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @SuppressLint("NewApi")
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // TODO Auto-generated method stub
        if (keyCode == event.KEYCODE_BACK) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("¿Está seguro que desea salir de la aplicación?")
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
                                    Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                                    startActivity(intent);
                                }
                            });
            AlertDialog alert = builder.create();
            alert.show();
        }
        return super.onKeyDown(keyCode, event);
    }

    private void inicializarDatos() {
        itemMarcos     = new ArrayList<ItemMarco>();
        listaViviendas = new ArrayList<ViviendaItem>();
        anios          = new ArrayList<String>();
        meses          = new ArrayList<String>();
        periodos       = new ArrayList<String>();
        zonas          = new ArrayList<String>();

        listaViviendas = getListaViviendas(idUsuario);
        anios.add("--Seleccione--");
        if (listaViviendas.size()>0) anios.add(String.valueOf(listaViviendas.get(0).getAnio()));

    }

    public void setAdapterMarco(){
        viviendaAdapter = new ViviendaAdapter(listaViviendas, this, null, new ViviendaAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(getApplicationContext(), ViviendaActivity.class);
                intent.putExtra("nickUsuario", nickUsuario);
                intent.putExtra("idVivienda", listaViviendas.get(position).getIdVivienda()+"");
                intent.putExtra("vivienda_zona", "00");
                intent.putExtra("vivienda_mes", "00");
                intent.putExtra("vivienda_anio", "2021");
                intent.putExtra("vivienda_periodo", listaViviendas.get(position).getPeriodo()+"");
                intent.putExtra("idUsuario", idUsuario);
                intent.putExtra("nroVivienda", listaViviendas.get(position).getNroVivienda()+"");
                intent.putExtra("nroSegmento", listaViviendas.get(position).getNrosegmento()+"");
                intent.putExtra("latitud", "-12.2247243");
                intent.putExtra("longitud", "-76.9353625");

                Data data = new Data(MarcoActivity.this);
                POJOFragmentVivienda pojoFragmentVivienda = new POJOFragmentVivienda(listaViviendas.get(position).getIdVivienda()+"");
                data.open();
                if (!data.existeElemento(SQLConstantes.tablafragmentsvivienda,listaViviendas.get(position).getIdVivienda()+"")){
                    data.insertarElemento(SQLConstantes.tablafragmentsvivienda,pojoFragmentVivienda.toValues());
                }
                data.close();
                startActivity(intent);
            }
        },1);
        recyclerView.setAdapter(viviendaAdapter);
    }

    public ArrayList<Carga> getListaCarga(String idUsuario){
        ArrayList<Carga> lista = null;
        Data data = new Data(MarcoActivity.this);
        data.open();
        lista = data.getListCarga(idUsuario);
        data.close();
        return lista;
    }

    public ArrayList<ViviendaItem> getListaViviendas(String idUsuario){
        ArrayList<ViviendaItem> lista = null;
        Data data = new Data(MarcoActivity.this);
        data.open();
        lista = data.getListViviendasXCarga(getListaCarga(idUsuario));
        data.close();
        return lista;
    }

    @Override
    protected void onResume() {
        inicializarDatos();
        cargarSpinerAnios(anios);
        cargarSpinerMeses(meses);
        cargarSpinerPeriodos(periodos);
        cargarSpinerZonas(zonas);
        setAdapterMarco();
        super.onResume();
    }

    public void getCroquis(int idEncuestador) {
        File nuevaCarpeta = new File(getExternalStorageDirectory(), "ENPOVE_CROQUIS");
        nuevaCarpeta.mkdirs();
        copyCroquisEncuestadores(idEncuestador);

    }

    private void CopyRawToSDCard(int id, String path) {
        InputStream in = getResources().openRawResource(id);
        FileOutputStream out = null;
        try {
            out = new FileOutputStream(path);
            byte[] buff = new byte[1024];
            int read = 0;
            while ((read = in.read(buff)) > 0) {
                out.write(buff, 0, read);
            }
            in.close();
            out.close();
            Log.i("TAG", "copyFile, success!");
        } catch (FileNotFoundException e) {
            Log.e("TAG", "copyFile FileNotFoundException " + e.getMessage());
        } catch (IOException e) {
            Log.e("TAG", "copyFile IOException " + e.getMessage());
        }
    }

    public void copyCroquisEncuestadores(int idEncuestador){
        ArrayList<Integer> listaEnc001 = new ArrayList<>();
        ArrayList<Integer> listaEnc002 = new ArrayList<>();
        ArrayList<Integer> listaEnc003 = new ArrayList<>();
        ArrayList<Integer> listaEnc004 = new ArrayList<>();
        ArrayList<Integer> listaEnc005 = new ArrayList<>();
        ArrayList<Integer> listaEnc006 = new ArrayList<>();
        ArrayList<Integer> listaEnc007 = new ArrayList<>();
        ArrayList<Integer> listaEnc008 = new ArrayList<>();
        ArrayList<Integer> listaEnc009 = new ArrayList<>();
        ArrayList<Integer> listaEnc010 = new ArrayList<>();
        ArrayList<Integer> listaEnc011 = new ArrayList<>();
        ArrayList<Integer> listaEnc012 = new ArrayList<>();
        ArrayList<Integer> listaEnc013 = new ArrayList<>();

        ArrayList<String> lista2Enc001 = new ArrayList<>();
        ArrayList<String> lista2Enc002 = new ArrayList<>();
        ArrayList<String> lista2Enc003 = new ArrayList<>();
        ArrayList<String> lista2Enc004 = new ArrayList<>();
        ArrayList<String> lista2Enc005 = new ArrayList<>();
        ArrayList<String> lista2Enc006 = new ArrayList<>();
        ArrayList<String> lista2Enc007 = new ArrayList<>();
        ArrayList<String> lista2Enc008 = new ArrayList<>();
        ArrayList<String> lista2Enc009 = new ArrayList<>();
        ArrayList<String> lista2Enc010 = new ArrayList<>();
        ArrayList<String> lista2Enc011 = new ArrayList<>();
        ArrayList<String> lista2Enc012 = new ArrayList<>();
        ArrayList<String> lista2Enc013 = new ArrayList<>();

        listaEnc001.add(R.raw.enc001_202109387_e);
        listaEnc001.add(R.raw.enc001_202109389);
        listaEnc001.add(R.raw.enc001_202109390);
        listaEnc001.add(R.raw.enc001_202109397_e);
        listaEnc001.add(R.raw.enc001_202109399);
        listaEnc001.add(R.raw.enc001_202109400_e);
        listaEnc001.add(R.raw.enc001_202109403);
        listaEnc001.add(R.raw.enc001_202109405);
        listaEnc001.add(R.raw.enc001_202109410_e);
        listaEnc001.add(R.raw.enc001_202109412);
        listaEnc001.add(R.raw.enc001_202109413_e);
        listaEnc001.add(R.raw.enc001_202109413_e_2);
        listaEnc001.add(R.raw.enc001_202109414_2);
        listaEnc001.add(R.raw.enc001_202109417_2);
        listaEnc001.add(R.raw.enc001_20210942201_e_2);
        listaEnc001.add(R.raw.enc001_202145201_2);
        listaEnc001.add(R.raw.enc001_20210939201);
        listaEnc001.add(R.raw.enc001_20210939501);
        listaEnc001.add(R.raw.enc001_20210939602);
        listaEnc001.add(R.raw.enc001_20210941102);
        listaEnc001.add(R.raw.enc001_listado);
        listaEnc001.add(R.raw.enc001_listado_adic_2);
        listaEnc002.add(R.raw.enc002_202109510_e);
        listaEnc002.add(R.raw.enc002_202109512);
        listaEnc002.add(R.raw.enc002_202109514);
        listaEnc002.add(R.raw.enc002_202109516);
        listaEnc002.add(R.raw.enc002_202109520);
        listaEnc002.add(R.raw.enc002_202109524);
        listaEnc002.add(R.raw.enc002_202145205);
        listaEnc002.add(R.raw.enc002_202145208_e);
        listaEnc002.add(R.raw.enc002_20210950801);
        listaEnc002.add(R.raw.enc002_20210950901);
        listaEnc002.add(R.raw.enc002_20210950902);
        listaEnc002.add(R.raw.enc002_20210951901_e);
        listaEnc002.add(R.raw.enc002_20210952301);
        listaEnc002.add(R.raw.enc002_20210952601);
        listaEnc002.add(R.raw.enc002_20210952602);
        listaEnc002.add(R.raw.enc002_202109528_e_2);
        listaEnc002.add(R.raw.enc002_20210952902_2);
        listaEnc002.add(R.raw.enc002_202109531_2);
        listaEnc002.add(R.raw.enc002_202109532_2);
        listaEnc002.add(R.raw.enc002_listado);
        listaEnc002.add(R.raw.enc002_listado_adic_2);
        listaEnc002.add(R.raw.enc003_202110136);
        listaEnc002.add(R.raw.enc003_202110143);
        listaEnc002.add(R.raw.enc003_202110147_e);
        listaEnc003.add(R.raw.enc003_202110164_e);
        listaEnc003.add(R.raw.enc003_202110169_e);
        listaEnc003.add(R.raw.enc003_202110178);
        listaEnc003.add(R.raw.enc003_202145229_e);
        listaEnc003.add(R.raw.enc003_20211013003);
        listaEnc003.add(R.raw.enc003_20211013701);
        listaEnc003.add(R.raw.enc003_20211014102);
        listaEnc003.add(R.raw.enc003_20211015701);
        listaEnc003.add(R.raw.enc003_20214523101);
        listaEnc003.add(R.raw.enc003_listado);
        listaEnc004.add(R.raw.enc004_202110367);
        listaEnc004.add(R.raw.enc004_202110371);
        listaEnc004.add(R.raw.enc004_202110374);
        listaEnc004.add(R.raw.enc004_202110377);
        listaEnc004.add(R.raw.enc004_202110378);
        listaEnc004.add(R.raw.enc004_20211036401);
        listaEnc004.add(R.raw.enc004_20211036402);
        listaEnc004.add(R.raw.enc004_listado);
        listaEnc005.add(R.raw.enc005_202110556);
        listaEnc005.add(R.raw.enc005_202110581);
        listaEnc005.add(R.raw.enc005_202110595);
        listaEnc005.add(R.raw.enc005_202110604);
        listaEnc005.add(R.raw.enc005_202145275);
        listaEnc005.add(R.raw.enc005_20211058802);
        listaEnc005.add(R.raw.enc005_20211059601);
        listaEnc005.add(R.raw.enc005_20211059702);
        listaEnc005.add(R.raw.enc005_listado);
        listaEnc006.add(R.raw.enc006_202120287);
        listaEnc006.add(R.raw.enc006_202120303);
        listaEnc006.add(R.raw.enc006_202120309);
        listaEnc006.add(R.raw.enc006_202120320);
        listaEnc006.add(R.raw.enc006_202120328);
        listaEnc006.add(R.raw.enc006_202120342);
        listaEnc006.add(R.raw.enc006_202120347);
        listaEnc006.add(R.raw.enc006_202120355);
        listaEnc006.add(R.raw.enc006_listado);
        listaEnc007.add(R.raw.enc007_202117509);
        listaEnc007.add(R.raw.enc007_202117516);
        listaEnc007.add(R.raw.enc007_202117519);
        listaEnc007.add(R.raw.enc007_202149393);
        listaEnc007.add(R.raw.enc007_listado);
        listaEnc008.add(R.raw.enc008_202124903);
        listaEnc008.add(R.raw.enc008_202124904);
        listaEnc008.add(R.raw.enc008_20212490501);
        listaEnc008.add(R.raw.enc008_listado);
        listaEnc009.add(R.raw.enc009_202122462);
        listaEnc009.add(R.raw.enc009_202122517);
        listaEnc009.add(R.raw.enc009_20212244402);
        listaEnc009.add(R.raw.enc009_20212245202);
        listaEnc009.add(R.raw.enc009_20212245502);
        listaEnc009.add(R.raw.enc009_20212245802);
        listaEnc009.add(R.raw.enc009_20212250201);
        listaEnc009.add(R.raw.enc009_listado);
        listaEnc010.add(R.raw.enc010_202127530);
        listaEnc010.add(R.raw.enc010_202127531);
        listaEnc010.add(R.raw.enc010_202127572);
        listaEnc010.add(R.raw.enc010_20212752001);
        listaEnc010.add(R.raw.enc010_20212752101);
        listaEnc010.add(R.raw.enc010_20212757102);
        listaEnc010.add(R.raw.enc010_listado);
        listaEnc011.add(R.raw.enc011_202142108);
        listaEnc011.add(R.raw.enc011_202142120);
        listaEnc011.add(R.raw.enc011_202142138);
        listaEnc011.add(R.raw.enc011_202142146);
        listaEnc011.add(R.raw.enc011_202142164);
        listaEnc011.add(R.raw.enc011_202142167);
        listaEnc011.add(R.raw.enc011_20214209603);
        listaEnc011.add(R.raw.enc011_20214210202);
        listaEnc011.add(R.raw.enc011_listado);
        listaEnc012.add(R.raw.enc012_202142815);
        listaEnc012.add(R.raw.enc012_202142825);
        listaEnc012.add(R.raw.enc012_202146508);
        listaEnc012.add(R.raw.enc012_listado);
        listaEnc013.add(R.raw.enc013_202143296_e);
        listaEnc013.add(R.raw.enc013_202143305);
        listaEnc013.add(R.raw.enc013_202143383_e);
        listaEnc013.add(R.raw.enc013_202143400);
        listaEnc013.add(R.raw.enc013_202143401);
        listaEnc013.add(R.raw.enc013_202143411);
        listaEnc013.add(R.raw.enc013_202143440);
        listaEnc013.add(R.raw.enc013_202143447);
        listaEnc013.add(R.raw.enc013_202143452);
        listaEnc013.add(R.raw.enc013_20214339901);
        listaEnc013.add(R.raw.enc013_20214340602_e);
        listaEnc013.add(R.raw.enc013_20214345701);
        listaEnc013.add(R.raw.enc013_listado);



        lista2Enc001.add("enc001_202109387_e.pdf");
        lista2Enc001.add("enc001_202109389.pdf");
        lista2Enc001.add("enc001_202109390.pdf");
        lista2Enc001.add("enc001_202109397_e.pdf");
        lista2Enc001.add("enc001_202109399.pdf");
        lista2Enc001.add("enc001_202109400_e.pdf");
        lista2Enc001.add("enc001_202109403.pdf");
        lista2Enc001.add("enc001_202109405.pdf");
        lista2Enc001.add("enc001_202109410_e.pdf");
        lista2Enc001.add("enc001_202109412.pdf");
        lista2Enc001.add("enc001_202109413_e.pdf");
        lista2Enc001.add("enc001_202109413_e_2.pdf");
        lista2Enc001.add("enc001_202109414_2.pdf");
        lista2Enc001.add("enc001_202109417_2.pdf");
        lista2Enc001.add("enc001_20210942201_e_2.pdf");
        lista2Enc001.add("enc001_202145201_2.pdf");
        lista2Enc001.add("enc001_20210939201.pdf");
        lista2Enc001.add("enc001_20210939501.pdf");
        lista2Enc001.add("enc001_20210939602.pdf");
        lista2Enc001.add("enc001_20210941102.pdf");
        lista2Enc001.add("enc001_listado.pdf");
        lista2Enc001.add("enc001_listado_adic_2.pdf");
        lista2Enc002.add("enc002_202109510_e.pdf");
        lista2Enc002.add("enc002_202109512.pdf");
        lista2Enc002.add("enc002_202109514.pdf");
        lista2Enc002.add("enc002_202109516.pdf");
        lista2Enc002.add("enc002_202109520.pdf");
        lista2Enc002.add("enc002_202109524.pdf");
        lista2Enc002.add("enc002_202145205.pdf");
        lista2Enc002.add("enc002_202145208_e.pdf");
        lista2Enc002.add("enc002_20210950801.pdf");
        lista2Enc002.add("enc002_20210950901.pdf");
        lista2Enc002.add("enc002_20210950902.pdf");
        lista2Enc002.add("enc002_20210951901_e.pdf");
        lista2Enc002.add("enc002_20210952301.pdf");
        lista2Enc002.add("enc002_20210952601.pdf");
        lista2Enc002.add("enc002_20210952602.pdf");
        lista2Enc002.add("enc002_202109528_e_2.pdf");
        lista2Enc002.add("enc002_20210952902_2.pdf");
        lista2Enc002.add("enc002_202109531_2.pdf");
        lista2Enc002.add("enc002_202109532_2.pdf");
        lista2Enc002.add("enc002_listado.pdf");
        lista2Enc002.add("enc002_listado_adic_2.pdf");
        lista2Enc002.add("enc003_202110136.pdf");
        lista2Enc002.add("enc003_202110143.pdf");
        lista2Enc002.add("enc003_202110147_e.pdf");
        lista2Enc003.add("enc003_202110164_e.pdf");
        lista2Enc003.add("enc003_202110169_e.pdf");
        lista2Enc003.add("enc003_202110178.pdf");
        lista2Enc003.add("enc003_202145229_e.pdf");
        lista2Enc003.add("enc003_20211013003.pdf");
        lista2Enc003.add("enc003_20211013701.pdf");
        lista2Enc003.add("enc003_20211014102.pdf");
        lista2Enc003.add("enc003_20211015701.pdf");
        lista2Enc003.add("enc003_20214523101.pdf");
        lista2Enc003.add("enc003_listado.pdf");
        lista2Enc004.add("enc004_202110367.pdf");
        lista2Enc004.add("enc004_202110371.pdf");
        lista2Enc004.add("enc004_202110374.pdf");
        lista2Enc004.add("enc004_202110377.pdf");
        lista2Enc004.add("enc004_202110378.pdf");
        lista2Enc004.add("enc004_20211036401.pdf");
        lista2Enc004.add("enc004_20211036402.pdf");
        lista2Enc004.add("enc004_listado.pdf");
        lista2Enc005.add("enc005_202110556.pdf");
        lista2Enc005.add("enc005_202110581.pdf");
        lista2Enc005.add("enc005_202110595.pdf");
        lista2Enc005.add("enc005_202110604.pdf");
        lista2Enc005.add("enc005_202145275.pdf");
        lista2Enc005.add("enc005_20211058802.pdf");
        lista2Enc005.add("enc005_20211059601.pdf");
        lista2Enc005.add("enc005_20211059702.pdf");
        lista2Enc005.add("enc005_listado.pdf");
        lista2Enc006.add("enc006_202120287.pdf");
        lista2Enc006.add("enc006_202120303.pdf");
        lista2Enc006.add("enc006_202120309.pdf");
        lista2Enc006.add("enc006_202120320.pdf");
        lista2Enc006.add("enc006_202120328.pdf");
        lista2Enc006.add("enc006_202120342.pdf");
        lista2Enc006.add("enc006_202120347.pdf");
        lista2Enc006.add("enc006_202120355.pdf");
        lista2Enc006.add("enc006_listado.pdf");
        lista2Enc007.add("enc007_202117509.pdf");
        lista2Enc007.add("enc007_202117516.pdf");
        lista2Enc007.add("enc007_202117519.pdf");
        lista2Enc007.add("enc007_202149393.pdf");
        lista2Enc007.add("enc007_listado.pdf");
        lista2Enc008.add("enc008_202124903.pdf");
        lista2Enc008.add("enc008_202124904.pdf");
        lista2Enc008.add("enc008_20212490501.pdf");
        lista2Enc008.add("enc008_listado.pdf");
        lista2Enc009.add("enc009_202122462.pdf");
        lista2Enc009.add("enc009_202122517.pdf");
        lista2Enc009.add("enc009_20212244402.pdf");
        lista2Enc009.add("enc009_20212245202.pdf");
        lista2Enc009.add("enc009_20212245502.pdf");
        lista2Enc009.add("enc009_20212245802.pdf");
        lista2Enc009.add("enc009_20212250201.pdf");
        lista2Enc009.add("enc009_listado.pdf");
        lista2Enc010.add("enc010_202127530.pdf");
        lista2Enc010.add("enc010_202127531.pdf");
        lista2Enc010.add("enc010_202127572.pdf");
        lista2Enc010.add("enc010_20212752001.pdf");
        lista2Enc010.add("enc010_20212752101.pdf");
        lista2Enc010.add("enc010_20212757102.pdf");
        lista2Enc010.add("enc010_listado.pdf");
        lista2Enc011.add("enc011_202142108.pdf");
        lista2Enc011.add("enc011_202142120.pdf");
        lista2Enc011.add("enc011_202142138.pdf");
        lista2Enc011.add("enc011_202142146.pdf");
        lista2Enc011.add("enc011_202142164.pdf");
        lista2Enc011.add("enc011_202142167.pdf");
        lista2Enc011.add("enc011_20214209603.pdf");
        lista2Enc011.add("enc011_20214210202.pdf");
        lista2Enc011.add("enc011_listado.pdf");
        lista2Enc012.add("enc012_202142815.pdf");
        lista2Enc012.add("enc012_202142825.pdf");
        lista2Enc012.add("enc012_202146508.pdf");
        lista2Enc012.add("enc012_listado.pdf");
        lista2Enc013.add("enc013_202143296_e.pdf");
        lista2Enc013.add("enc013_202143305.pdf");
        lista2Enc013.add("enc013_202143383_e.pdf");
        lista2Enc013.add("enc013_202143400.pdf");
        lista2Enc013.add("enc013_202143401.pdf");
        lista2Enc013.add("enc013_202143411.pdf");
        lista2Enc013.add("enc013_202143440.pdf");
        lista2Enc013.add("enc013_202143447.pdf");
        lista2Enc013.add("enc013_202143452.pdf");
        lista2Enc013.add("enc013_20214339901.pdf");
        lista2Enc013.add("enc013_20214340602_e.pdf");
        lista2Enc013.add("enc013_20214345701.pdf");
        lista2Enc013.add("enc013_listado.pdf");

        switch (idEncuestador){
            case 1:
                for (int i = 0; i < listaEnc001.size() ; i++) {
                    CopyRawToSDCard(listaEnc001.get(i), Environment.getExternalStorageDirectory() + "/ENPOVE_CROQUIS/"+lista2Enc001.get(i));
                }
                mensaje();
                break;
            case 2:
                for (int i = 0; i < listaEnc002.size() ; i++) {
                    CopyRawToSDCard(listaEnc002.get(i), Environment.getExternalStorageDirectory() + "/ENPOVE_CROQUIS/"+lista2Enc002.get(i));
                }
                mensaje();
                break;
            case 3:
                for (int i = 0; i < listaEnc003.size() ; i++) {
                    CopyRawToSDCard(listaEnc003.get(i), Environment.getExternalStorageDirectory() + "/ENPOVE_CROQUIS/"+lista2Enc003.get(i));
                }
                mensaje();
                break;
            case 4:
                for (int i = 0; i < listaEnc004.size() ; i++) {
                    CopyRawToSDCard(listaEnc004.get(i), Environment.getExternalStorageDirectory() + "/ENPOVE_CROQUIS/"+lista2Enc004.get(i));
                }
                mensaje();
                break;
            case 5:
            case 16:
                for (int i = 0; i < listaEnc005.size() ; i++) {
                    CopyRawToSDCard(listaEnc005.get(i), Environment.getExternalStorageDirectory() + "/ENPOVE_CROQUIS/"+lista2Enc005.get(i));
                }
                mensaje();
                break;
            case 6:
                for (int i = 0; i < listaEnc006.size() ; i++) {
                    CopyRawToSDCard(listaEnc006.get(i), Environment.getExternalStorageDirectory() + "/ENPOVE_CROQUIS/"+lista2Enc006.get(i));
                }
                mensaje();
                break;
            case 7:
                for (int i = 0; i < listaEnc007.size() ; i++) {
                    CopyRawToSDCard(listaEnc007.get(i), Environment.getExternalStorageDirectory() + "/ENPOVE_CROQUIS/"+lista2Enc007.get(i));
                }
                mensaje();
                break;
            case 8:
                for (int i = 0; i < listaEnc008.size() ; i++) {
                    CopyRawToSDCard(listaEnc008.get(i), Environment.getExternalStorageDirectory() + "/ENPOVE_CROQUIS/"+lista2Enc008.get(i));
                }
                mensaje();
                break;
            case 9:
                for (int i = 0; i < listaEnc009.size() ; i++) {
                    CopyRawToSDCard(listaEnc009.get(i), Environment.getExternalStorageDirectory() + "/ENPOVE_CROQUIS/"+lista2Enc009.get(i));
                }
                mensaje();
                break;
            case 10:
            case 19:
                for (int i = 0; i < listaEnc010.size() ; i++) {
                    CopyRawToSDCard(listaEnc010.get(i), Environment.getExternalStorageDirectory() + "/ENPOVE_CROQUIS/"+lista2Enc010.get(i));
                }
                mensaje();
                break;
            case 11:
                for (int i = 0; i < listaEnc011.size() ; i++) {
                    CopyRawToSDCard(listaEnc011.get(i), Environment.getExternalStorageDirectory() + "/ENPOVE_CROQUIS/"+lista2Enc011.get(i));
                }
                mensaje();
                break;
            case 12:
                for (int i = 0; i < listaEnc012.size() ; i++) {
                    CopyRawToSDCard(listaEnc012.get(i), Environment.getExternalStorageDirectory() + "/ENPOVE_CROQUIS/"+lista2Enc012.get(i));
                }
                mensaje();
                break;
            case 13:
            case 21:
                for (int i = 0; i < listaEnc013.size() ; i++) {
                    CopyRawToSDCard(listaEnc013.get(i), Environment.getExternalStorageDirectory() + "/ENPOVE_CROQUIS/"+lista2Enc013.get(i));
                }
                mensaje();
                break;
            case 14:
                for (int i = 0; i < listaEnc001.size() ; i++) {
                    CopyRawToSDCard(listaEnc001.get(i), Environment.getExternalStorageDirectory() + "/ENPOVE_CROQUIS/"+lista2Enc001.get(i));
                }
                for (int i = 0; i < listaEnc002.size() ; i++) {
                    CopyRawToSDCard(listaEnc002.get(i), Environment.getExternalStorageDirectory() + "/ENPOVE_CROQUIS/"+lista2Enc002.get(i));
                }
                mensaje();
                break;
            case 15:
                for (int i = 0; i < listaEnc003.size() ; i++) {
                    CopyRawToSDCard(listaEnc003.get(i), Environment.getExternalStorageDirectory() + "/ENPOVE_CROQUIS/"+lista2Enc003.get(i));
                }
                for (int i = 0; i < listaEnc004.size() ; i++) {
                    CopyRawToSDCard(listaEnc004.get(i), Environment.getExternalStorageDirectory() + "/ENPOVE_CROQUIS/"+lista2Enc004.get(i));
                }
                mensaje();
                break;
            case 17:
                for (int i = 0; i < listaEnc006.size() ; i++) {
                    CopyRawToSDCard(listaEnc006.get(i), Environment.getExternalStorageDirectory() + "/ENPOVE_CROQUIS/"+lista2Enc006.get(i));
                }
                for (int i = 0; i < listaEnc007.size() ; i++) {
                    CopyRawToSDCard(listaEnc007.get(i), Environment.getExternalStorageDirectory() + "/ENPOVE_CROQUIS/"+lista2Enc007.get(i));
                }
                mensaje();
                break;
            case 18:
                for (int i = 0; i < listaEnc008.size() ; i++) {
                    CopyRawToSDCard(listaEnc008.get(i), Environment.getExternalStorageDirectory() + "/ENPOVE_CROQUIS/"+lista2Enc008.get(i));
                }
                for (int i = 0; i < listaEnc009.size() ; i++) {
                    CopyRawToSDCard(listaEnc009.get(i), Environment.getExternalStorageDirectory() + "/ENPOVE_CROQUIS/"+lista2Enc009.get(i));
                }
                mensaje();
                break;
            case 20:
                for (int i = 0; i < listaEnc011.size() ; i++) {
                    CopyRawToSDCard(listaEnc011.get(i), Environment.getExternalStorageDirectory() + "/ENPOVE_CROQUIS/"+lista2Enc011.get(i));
                }
                for (int i = 0; i < listaEnc012.size() ; i++) {
                    CopyRawToSDCard(listaEnc012.get(i), Environment.getExternalStorageDirectory() + "/ENPOVE_CROQUIS/"+lista2Enc012.get(i));
                }
                mensaje();
                break;
            default:
                Toast.makeText(this,"HUBO UN ERROR AL DESCARGAR CROQUIS",Toast.LENGTH_LONG).show();
                break;

        }

    }

    public void mensaje(){
        Toast.makeText(this,"SE DESCARGO LOS CROQUIS EN LA CARPETA 'ENPOVE-CROQUIS' ",Toast.LENGTH_LONG).show();
    }



}
