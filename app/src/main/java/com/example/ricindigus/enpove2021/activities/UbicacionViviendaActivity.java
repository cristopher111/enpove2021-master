package com.example.ricindigus.enpove2021.activities;

import android.Manifest;
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
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.ricindigus.enpove2021.R;
import com.example.ricindigus.enpove2021.modelo.DAOUtils;
import com.example.ricindigus.enpove2021.modelo.pojos.Marco;
import com.example.ricindigus.enpove2021.modelo.pojos.Ubicacion;
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
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONObject;

import java.lang.reflect.Method;
import java.util.ArrayList;

public class
UbicacionViviendaActivity extends AppCompatActivity implements OnMapReadyCallback, GoogleApiClient.OnConnectionFailedListener,
        GoogleApiClient.ConnectionCallbacks,
        LocationListener, Response.Listener<JSONObject>, Response.ErrorListener {

    private static final String LOGTAG = "android-localizacion";
    private static final int PETICION_PERMISO_LOCALIZACION = 101;
    private static final int PETICION_CONFIG_UBICACION = 201;
    private GoogleApiClient apiClient;
    private LocationRequest locRequest;
    String latitud;
    String longitud;

    private final int TIEMPO = 10000;
    Handler handler = new Handler();
    Runnable myRunnable;

    RequestQueue request;
    JsonObjectRequest jsonObjectRequest;
    ///

    Toolbar toolbar;
    GoogleMap mgoogleMap;
    String idVivienda;
    String idUsuario;
    String vivienda;
    String nroSegmento;
    String vivienda_periodo;
    String conglomerado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_mapa_ubicacion);
        idVivienda = getIntent().getExtras().getString("idVivienda");
        vivienda = getIntent().getExtras().getString("vivienda");
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

        toolbar = (Toolbar) findViewById(R.id.toolbar_mapa);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("UBICACION DE VIVIENDA");
        getSupportActionBar().setSubtitle("VIVIENDA N° "+vivienda);

        MapView mapView = (MapView) findViewById(R.id.map);
        if (mapView != null) {
            mapView.onCreate(null);
            mapView.onResume();
            mapView.getMapAsync(this);
        }

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        sendUbicacion();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_mapa, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_close:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        MapsInitializer.initialize(getApplicationContext());
        mgoogleMap = googleMap;
        mgoogleMap.getUiSettings().setMyLocationButtonEnabled(true);
        Marco marco = DAOUtils.getMarco(idVivienda,UbicacionViviendaActivity.this);
        try {
            final LatLng ubicacion = new LatLng(Double.parseDouble(marco.getLatitud()), Double.parseDouble(marco.getLongitud()));
            showMarkerVivienda(googleMap, marco);
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                return;
            }
            googleMap.setMyLocationEnabled(true);
            googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
            CameraPosition Liberty = CameraPosition.builder().target(ubicacion).zoom(16).bearing(0).tilt(45).build();
            googleMap.moveCamera(CameraUpdateFactory.newCameraPosition(Liberty));
        }catch (Exception e){

            /*Intent intent = new Intent(getApplicationContext(), ViviendaActivity.class);
            intent.putExtra("idVivienda",idVivienda);
            intent.putExtra("nroVivienda", vivienda);
            startActivity(intent);*/
            finish();
            //mostrarMensaje("Coordenadas de ubicación no disponible en el marco");
            Toast.makeText(this, "Coordenadas de ubicación no disponible en el marco", Toast.LENGTH_LONG).show();
        }

    }

    public void showMarkerVivienda(GoogleMap googleMap,Marco marco){
        ArrayList<Marco> lista = DAOUtils.getListaSegmentoViviendas(marco.getNroSegmento(),UbicacionViviendaActivity.this);
        Float colorMarker;

        for (Marco vivienda: lista) {
            try{

            final LatLng ubicacion = new LatLng(Double.parseDouble(vivienda.getLatitud().trim()), Double.parseDouble(vivienda.getLongitud().trim()));
            Log.e("lATITUD",""+Double.parseDouble(vivienda.getLatitud()));
            Log.e("lONGITUD",""+Double.parseDouble(vivienda.getLongitud()));
            if(marco.getNro_selec_vivienda().equals(vivienda.getNro_selec_vivienda())){
               //VIVIENDA ACTUAL
               colorMarker = BitmapDescriptorFactory.HUE_GREEN;
            }else{
                //OTRAS VIVIENDAS
                colorMarker = BitmapDescriptorFactory.HUE_MAGENTA;
            }
            googleMap.addMarker(new MarkerOptions()
                    .position(ubicacion)
                    .title("Vivienda N° "+vivienda.getNro_selec_vivienda())
                    .icon(BitmapDescriptorFactory.defaultMarker(colorMarker))
            );
        }catch(Exception e){
                // Toast.makeText(this, "Coordenadas de ubicación no disponible en el marco", Toast.LENGTH_SHORT).show();
            }

        }

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
                            status.startResolutionForResult(UbicacionViviendaActivity.this, PETICION_CONFIG_UBICACION);
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


                    if (ActivityCompat.checkSelfPermission(UbicacionViviendaActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                        ActivityCompat.requestPermissions(UbicacionViviendaActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, PETICION_PERMISO_LOCALIZACION);
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
                    ubicacion.setUsuario(DAOUtils.getUsuarioId(idUsuario,UbicacionViviendaActivity.this).getNombre());
                    ubicacion.setDni(DAOUtils.getUsuarioId(idUsuario,UbicacionViviendaActivity.this).getDni());
                    ubicacion.setCargo(DAOUtils.getUsuarioId(idUsuario,UbicacionViviendaActivity.this).getUsuario());
                    ubicacion.setIdDispositivo(getIdDispositivo());
                    //ubicacion.setSerial(getSerialNumber());
                    ubicacion.setLongitud(getLatitud(longitud));
                    ubicacion.setLatitud(getLongitud(latitud));
                    //ubicacion.setLongitud(longitud);
                    //ubicacion.setLatitud(latitud);
                    ubicacion.setFechaTablet(UtilsMethods.getFechaNow().getDiaInicio()+"/"+UtilsMethods.getFechaNow().getMesInicio()+"/"+UtilsMethods.getFechaNow().getAnioInicio()+"-"+UtilsMethods.getFechaNow().getHoraInicio()+":"+UtilsMethods.getFechaNow().getMinutoInicio());
                    ubicacion.setVersionApk(UtilsMethods.getVersion(UbicacionViviendaActivity.this));
                    ubicacion.setBateria(""+getBateria());
                    ubicacion.setConexion("Activo");
                    ubicacion.setSegmento(nroSegmento);///
                    ubicacion.setPeriodo(vivienda_periodo);///
                    ubicacion.setConglomerado(conglomerado);///

                   // Toast.makeText(UbicacionViviendaActivity.this, "LONGITUD ES"+ubicacion.getLongitud() +"\n LATITUD ES"+ubicacion.getLatitud(), Toast.LENGTH_LONG).show();


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
/*                  String url =  "http://200.123.3.26/wsenpove/2022/USP_WSENPOVE2022_TRACKING_USUARIO.php?ID_VIVIENDA="+ubicacion.getId()+"&ID_USUARIO="+ubicacion.getIdUsuario()
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
