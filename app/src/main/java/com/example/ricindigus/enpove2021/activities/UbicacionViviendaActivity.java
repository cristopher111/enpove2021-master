package com.example.ricindigus.enpove2021.activities;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
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

import com.example.ricindigus.enpove2021.R;
import com.example.ricindigus.enpove2021.modelo.DAOUtils;
import com.example.ricindigus.enpove2021.modelo.pojos.Marco;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

public class
UbicacionViviendaActivity extends AppCompatActivity implements OnMapReadyCallback {
    Toolbar toolbar;
    GoogleMap mgoogleMap;
    String idVivienda;
    String vivienda;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_mapa_ubicacion);
        idVivienda = getIntent().getExtras().getString("idVivienda");
        vivienda = getIntent().getExtras().getString("vivienda");
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
}
