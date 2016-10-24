package com.pettime.pettimedevelop.Vista;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.pettime.pettimedevelop.Modelo.Establecimiento;
import com.pettime.pettimedevelop.R;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class EstablecimientoActivity extends AppCompatActivity {

    public static String URLa;
    private ImageView imgImagen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_establecimientos);
        Intent i = getIntent();
        Establecimiento est=(Establecimiento)i.getSerializableExtra("Est");
        URLa=est.getFoto();
        TextView lblNomEst = (TextView) findViewById(R.id.lblNomEst);
        lblNomEst.setText(est.getNombreEstablecimiento());
        TextView lblDirEst = (TextView) findViewById(R.id.lblDirec);
        lblDirEst.setText(est.getDirecEstablecimiento());
        TextView lblCuposEst = (TextView) findViewById(R.id.lblCupo);
        lblCuposEst.setText(String.valueOf(est.getCupos()));


        imgImagen = (ImageView)findViewById(R.id.imagen);
        CargaImagenes nuevaTarea = new CargaImagenes();
        nuevaTarea.execute(URLa);
    }

    private class CargaImagenes extends AsyncTask<String, Void, Bitmap> {

        ProgressDialog pDialog;

        @Override
        protected void onPreExecute() {
            // TODO Auto-generated method stub
            super.onPreExecute();

            pDialog = new ProgressDialog(EstablecimientoActivity.this);
            pDialog.setMessage("Cargando Imagen");
            pDialog.setCancelable(true);
            pDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            pDialog.show();

        }

        @Override
        protected Bitmap doInBackground(String... params) {
            // TODO Auto-generated method stub
            //Log.i("doInBackground" , "Entra en doInBackground");
            String url = params[0];
            Bitmap imagen = descargarImagen(url);
            return imagen;
        }

        @Override
        protected void onPostExecute(Bitmap result) {
            // TODO Auto-generated method stub
            super.onPostExecute(result);

            imgImagen.setImageBitmap(result);
            pDialog.dismiss();
        }

        private Bitmap descargarImagen (String imageHttpAddress){
            URL imageUrl = null;
            Bitmap imagen = null;
            try{
                imageUrl = new URL(imageHttpAddress);
                HttpURLConnection conn = (HttpURLConnection) imageUrl.openConnection();
                conn.connect();
                imagen = BitmapFactory.decodeStream(conn.getInputStream());
            }catch(IOException ex){
                ex.printStackTrace();
            }

            return imagen;
        }
    }

}
