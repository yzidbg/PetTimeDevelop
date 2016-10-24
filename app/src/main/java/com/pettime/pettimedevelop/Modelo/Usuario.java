package com.pettime.pettimedevelop.Modelo;

import android.os.Parcelable;

import java.io.Serializable;

/**
 * Created by yz on 13/05/2016.
 */
public class Usuario implements Serializable{
    private int idUsaurio;
    private int codTipoId;
    private String nombre;
    private String email;
    private String password;
    private int codCuidad;
    private String dir;
    private String tel1;
    private String tel2;
    private int codTipoPerfil;
    private int codEstadoCuenta;

    public Usuario() {
    }

    public Usuario(int codCuidad, int codEstadoCuenta, int codTipoId, int codTipoPerfil, String dir, String email, int idUsaurio, String nombre, String password, String tel1, String tel2) {
        this.codCuidad = codCuidad;
        this.codEstadoCuenta = codEstadoCuenta;
        this.codTipoId = codTipoId;
        this.codTipoPerfil = codTipoPerfil;
        this.dir = dir;
        this.email = email;
        this.idUsaurio = idUsaurio;
        this.nombre = nombre;
        this.password = password;
        this.tel1 = tel1;
        this.tel2 = tel2;
    }

    public int getCodCuidad() {
        return codCuidad;
    }

    public void setCodCuidad(int codCuidad) {
        this.codCuidad = codCuidad;
    }

    public int getCodEstadoCuenta() {
        return codEstadoCuenta;
    }

    public void setCodEstadoCuenta(int codEstadoCuenta) {
        this.codEstadoCuenta = codEstadoCuenta;
    }

    public int getCodTipoId() {
        return codTipoId;
    }

    public void setCodTipoId(int codTipoId) {
        this.codTipoId = codTipoId;
    }

    public int getCodTipoPerfil() {
        return codTipoPerfil;
    }

    public void setCodTipoPerfil(int codTipoPerfil) {
        this.codTipoPerfil = codTipoPerfil;
    }

    public String getDir() {
        return dir;
    }

    public void setDir(String dir) {
        this.dir = dir;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getIdUsaurio() {
        return idUsaurio;
    }

    public void setIdUsaurio(int idUsaurio) {
        this.idUsaurio = idUsaurio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTel1() {
        return tel1;
    }

    public void setTel1(String tel1) {
        this.tel1 = tel1;
    }

    public String getTel2() {
        return tel2;
    }

    public void setTel2(String tel2) {
        this.tel2 = tel2;
    }
}
