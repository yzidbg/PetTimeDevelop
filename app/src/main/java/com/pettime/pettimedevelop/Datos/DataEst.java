package com.pettime.pettimedevelop.Datos;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.pettime.pettimedevelop.Modelo.Establecimiento;

import java.util.ArrayList;

/**
 * Created by yz on 4/05/2016.
 */
public class DataEst {
    private ArrayList<Establecimiento> establecimientos = new ArrayList<>();
    private ArrayList<MarkerOptions> marcadores = new ArrayList<>();

    public DataEst() {
        cargarEstablecimientos();
        cargarMarcadores();
    }

    private void cargarEstablecimientos(){
        establecimientos.add(new Establecimiento(7,"Cl. 126 #7c-2 a 7c-98",
                "http://www.gespet.com/es/estilo/img/programa-guarderia-perros-residencia-gatos-aplicacion-app-mascotas-gratis.png",
                1,4.701977,-74.032488,"Guardería Calle 126"));
        establecimientos.add(new Establecimiento(15,"Cra. 4 #185-15",
                "http://comps.canstockphoto.es/can-stock-photo_csp29730348.jpg",
                2,4.759459,-74.023905,"Guardería Carrera 4"));
        establecimientos.add(new Establecimiento(1,"Cl. 200",
                "http://fachadasycasas.com/wp-content/uploads/2015/11/imagenes-de-dibujos-de-casas-bonitas-6.jpg",
                3,4.778448,-74.040384,"Guardería Calle 200"));
        establecimientos.add(new Establecimiento(10,"Cra. 76 #179-26 a 179-36",
                "http://png.clipart.me/previews/196/house-sketch-vector-1-4287.jpg",
                4,4.766815,-74.0627,"Guardería Carrera 76"));
        establecimientos.add(new Establecimiento(3,"Cl. 146f Bis #76-2 a 76-98",
                "http://thumbs.dreamstime.com/x/house-sketch-17465875.jpg",
                5,4.739444,-74.074202,"Guardería Calle 146f"));
        establecimientos.add(new Establecimiento(8,"Cra. 103 #130c-17 a 130c-77,",
                "http://thumbs.dreamstime.com/t/l%C3%ADnea-del-bosquejo-en-casa-contenga-el-bosquejo-ilustraci%C3%B3n-del-vector-58114331.jpg",
                6,4.732601,-74.099092,"Guardería Carrera 103"));
        establecimientos.add(new Establecimiento(12,"Cl. 77a #70-2 a 70-60",
                "http://thumbs.dreamstime.com/z/concepto-moderno-de-la-construcci%C3%B3n-de-nueva-viviendas-del-proyecto-del-arquitecto-56149850.jpg",
                7,4.690001,-74.087763,"Guardería Calle 77a"));
        establecimientos.add(new Establecimiento(9,"Cra. 98 #22h-1 a 22h-51",
                "http://previews.123rf.com/images/tashat/tashat1305/tashat130500026/20011410-Vector-el-bosquejo-de-la-casa-moderna-Foto-de-archivo.jpg",
                8,4.676828,-74.138231,"Guardería Carrera 98"));
        establecimientos.add(new Establecimiento(3,"Cra. 15 #31B-19",
                "http://comps.canstockphoto.es/can-stock-photo_csp29730348.jpg",
                9,4.619852,-74.071455,"Guardería Carrera 15"));
        establecimientos.add(new Establecimiento(2,"Cl. 12 #79a-9 a 79a-37",
                "http://diarquitecturaydiseno.com/img/diseno_arquitectonico/diseno_arquitectonico4.jpg",
                10,4.648939,-74.136515,"Guardería Calle 126"));
    }

    private void cargarMarcadores(){
        for(int i=0; i<establecimientos.size();i++){
            marcadores.add(new MarkerOptions().position(new LatLng(establecimientos.get(i).getLat(),
                    establecimientos.get(i).getLon())).title(establecimientos.get(i).getNombreEstablecimiento()).snippet("Cupos: " +
                    +establecimientos.get(i).getCupos()+", Dirección: "+establecimientos.get(i).getDirecEstablecimiento()));
        }

    }

    public ArrayList<Establecimiento> getEstablecimientos() {
        return establecimientos;
    }

    public ArrayList<MarkerOptions> getMarcadores() {
        return marcadores;
    }
}
