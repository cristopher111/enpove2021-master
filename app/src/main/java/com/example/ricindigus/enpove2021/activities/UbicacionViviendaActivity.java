package com.example.ricindigus.enpove2021.activities;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

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
        final LatLng ubicacion = new LatLng(Double.parseDouble(marco.getLatitud()), Double.parseDouble(marco.getLongitud()));
        googleMap.addMarker(new MarkerOptions()
                .position(ubicacion)
                .title("Vivienda N° "+vivienda)
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_MAGENTA))
        );
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        googleMap.setMyLocationEnabled(true);
        googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        CameraPosition Liberty = CameraPosition.builder().target(ubicacion).zoom(16).bearing(0).tilt(45).build();
        googleMap.moveCamera(CameraUpdateFactory.newCameraPosition(Liberty));

    }
}
