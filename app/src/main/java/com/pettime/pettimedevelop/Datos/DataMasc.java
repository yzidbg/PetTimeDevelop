package com.pettime.pettimedevelop.Datos;

import com.pettime.pettimedevelop.Modelo.Mascota;

import java.util.ArrayList;

/**
 * Created by yz on 21/10/2016.
 */
public class DataMasc {
    private ArrayList<Mascota> mascotas = new ArrayList<>();

    public DataMasc() {
        cargaMascotas();
    }

    private void cargaMascotas(){
        mascotas.add(new Mascota(1,"Monita","Canino","Beagle","Hembra","01/10/2013",14327391));
        mascotas.add(new Mascota(2,"Mia","Canino","Criolla","Hembra","01/01/2016",14327391));
    }

    public ArrayList<Mascota> getMascotas() {
        return mascotas;
    }

    public void setMascotas(ArrayList<Mascota> mascotas) {
        this.mascotas = mascotas;
    }
}
