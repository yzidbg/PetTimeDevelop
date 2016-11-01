package com.pettime.pettimedevelop.Datos;

import com.pettime.pettimedevelop.Modelo.Usuario;

import java.util.ArrayList;

/**
 * Created by yz on 13/05/2016.
 */
public class DataUsr {
    private ArrayList<Usuario> usuarios = new ArrayList<>();

    public DataUsr() {
        cargaUsuarios();
    }
    private void cargaUsuarios(){
        usuarios.add(new Usuario(1000,1,1,2,"Calle con carrera","mail@mail.com",14327391,"Yesid Bermúdez","123456","123456","123456",null));
    }

    public void setUsuarios(ArrayList<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    public ArrayList<Usuario> getUsuarios() {
        return usuarios;
    }
}
