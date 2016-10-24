package com.pettime.pettimedevelop.Vista;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
//import android.location.LocationListener;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;


import com.google.android.gms.location.LocationListener;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.pettime.pettimedevelop.Datos.DataEst;
import com.pettime.pettimedevelop.Modelo.Establecimiento;
import com.pettime.pettimedevelop.R;

import java.util.ArrayList;
import java.util.jar.Manifest;

public class MapaPrincipal extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private Marker actual;
    double lat = 0.0;
    double log = 0.0;
    private DataEst datosEst = new DataEst();
    private ArrayList<Establecimiento> est = datosEst.getEstablecimientos();
    private ArrayList<MarkerOptions> marks = datosEst.getMarcadores();

    public LocationManager handle;
    private String provider;

    //del menu

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mapa_principal);
        Toolbar toolbar = (Toolbar) findViewById(R.id.principalBar);
        setSupportActionBar(toolbar);

        //setContentView(R.layout.esqueleto_mapa_layout);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        int duracion = Toast.LENGTH_LONG;
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        mMap.setMyLocationEnabled(true);
        Toast.makeText(MapaPrincipal.this,"Seleccione un Marcador",duracion).show();
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        //miUbicacion();
        LatLng bogota = new LatLng(4.678899, -74.095813);
        for (int i=0; i<marks.size();i++){
            mMap.addMarker((MarkerOptions)marks.get(i));
        }
        mMap.moveCamera(CameraUpdateFactory.newLatLng(bogota));
        mMap.animateCamera(CameraUpdateFactory.zoomTo(11));
        mMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {


            @Override
            public void onInfoWindowClick(Marker marker) {
                Intent i = new Intent(MapaPrincipal.this,EstablecimientoActivity.class);
                Establecimiento e =buscarEst(marker);
                if(e!=null){
                    i.putExtra("Est",e);
                }
                startActivity(i);
            }
        });


    }


    //SERVICE GPS
    /*public void iniciarServicio(){
        handle = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
        Criteria c = new Criteria();
        c.setAccuracy(Criteria.ACCURACY_FINE);
        provider = handle.getBestProvider(c, true);
        handle.requestLocationUpdates(provider,10000,1, (android.location.LocationListener) this);
    }*/


    private Establecimiento buscarEst(Marker marker){
        Establecimiento estbl=null;
        for(int i=0;i<est.size();i++){
            if(marker.getTitle().equals(est.get(i).getNombreEstablecimiento())){
                estbl=(Establecimiento)est.get(i);
                break;
            }
        }
        return estbl;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_principal, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.buscar:
                Toast.makeText(getApplicationContext(), "Buscar", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.nuevo:
                Intent intent = new Intent(this, RegistrarMascotas.class);
                startActivity(intent);
                Toast.makeText(getApplicationContext(), "Mascota", Toast.LENGTH_LONG).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void agregarMarcador(double lat, double log){
        LatLng coordenadas = new LatLng(lat,log);
        CameraUpdate miUbicacion=CameraUpdateFactory.newLatLngZoom(coordenadas,16);
        if(actual!=null)actual.remove();
        actual=mMap.addMarker(new MarkerOptions().position(coordenadas).title("Mi Posicion Actual")
                .icon(BitmapDescriptorFactory.fromResource(R.mipmap.ic_launcher)));
        mMap.animateCamera(miUbicacion);
    }

    private void actualizarUbicacion(Location location){
        if(location!=null){
            lat=location.getLatitude();
            log=location.getLongitude();
            agregarMarcador(lat, log);
        }
    }

    LocationListener loctionListener = new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {
            actualizarUbicacion(location);
        }
    };

    private void miUbicacion(){
        LocationManager locationManager=(LocationManager)getSystemService(Context.LOCATION_SERVICE);
        Location location=locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        actualizarUbicacion(location);
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,15000,1, (android.location.LocationListener) loctionListener);
    }
}
