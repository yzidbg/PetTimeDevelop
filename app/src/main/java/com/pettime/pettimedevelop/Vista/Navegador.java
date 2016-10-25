package com.pettime.pettimedevelop.Vista;

import android.app.AlertDialog;
import android.app.FragmentManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.pettime.pettimedevelop.Datos.DataEst;
import com.pettime.pettimedevelop.Fragmentos.FragmentoInicial;
import com.pettime.pettimedevelop.Fragmentos.FragmentoVacio;
import com.pettime.pettimedevelop.Modelo.Establecimiento;
import com.pettime.pettimedevelop.Modelo.Usuario;
import com.pettime.pettimedevelop.R;

import java.util.ArrayList;

public class Navegador extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, OnMapReadyCallback {

    private GoogleMap mMap;
    private Marker actual;
    double lat = 0.0;
    double log = 0.0;
    private DataEst datosEst = new DataEst();
    private ArrayList<Establecimiento> est = datosEst.getEstablecimientos();
    private ArrayList<MarkerOptions> marks = datosEst.getMarcadores();
    public Usuario usr;

    SupportMapFragment sMapFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        sMapFragment = SupportMapFragment.newInstance();

        Intent entrada = getIntent();
        usr = (Usuario)entrada.getSerializableExtra("User");

        setContentView(R.layout.activity_navegador);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        FragmentManager fm = getFragmentManager();

        //FragmentoInicial hf = new FragmentoInicial();
        /*Bundle b = new Bundle();
        b.putString("1",usr.getNombre());
        b.putString("2",usr.getEmail());
        hf.setArguments(b);*/

        fm.beginTransaction().replace(R.id.content_frame_nav, new FragmentoVacio()).commit();

        sMapFragment.getMapAsync(this);

        View header = LayoutInflater.from(this).inflate(R.layout.nav_header_navegador, null);
        navigationView.addHeaderView(header);

        TextView lblTituloNav= (TextView)header.findViewById(R.id.lblTituloNav);

        lblTituloNav.setText("Bienvenid@ "+usr.getNombre());

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {

            AlertDialog.Builder builder =
                    new AlertDialog.Builder(this, R.style.AppCompatAlertDialogStyle);
            builder.setTitle("Salir");
            builder.setMessage("Esta seguro que desea salir?");

            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                    finish();
                }
            });

            builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });

            builder.show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.navegador, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        FragmentManager fm = getFragmentManager();
        android.support.v4.app.FragmentManager sFm = getSupportFragmentManager();

        int id = item.getItemId();

        if (sMapFragment.isAdded())
            sFm.beginTransaction().hide(sMapFragment).commit();

        if (id == R.id.nav_search) {
            if (!sMapFragment.isAdded())
                sFm.beginTransaction().add(R.id.map, sMapFragment).commit();
            else
                sFm.beginTransaction().show(sMapFragment).commit();
        } else if (id == R.id.nav_profile) {

        } else if (id == R.id.nav_pets) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

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
                Intent i = new Intent(Navegador.this,EstablecimientoActivity.class);
                Establecimiento e =buscarEst(marker);
                if(e!=null){
                    i.putExtra("Est",e);
                }
                startActivity(i);
            }
        });
    }

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

}
