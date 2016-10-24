package com.pettime.pettimedevelop.Modelo;

import java.io.Serializable;

/**
 * Created by yz on 4/05/2016.
 */
public class Establecimiento implements Serializable {
    private int idEstablecimiento;
    private int cupos;
    private String nombreEstablecimiento;
    private String direcEstablecimiento;
    private double lat;
    private double lon;
    private String foto;

    public Establecimiento() {
    }

    public Establecimiento(int cupos, String direcEstablecimiento, String foto, int idEstablecimiento, double lat, double lon, String nombreEstablecimiento) {
        this.cupos = cupos;
        this.direcEstablecimiento = direcEstablecimiento;
        this.foto = foto;
        this.idEstablecimiento = idEstablecimiento;
        this.lat = lat;
        this.lon = lon;
        this.nombreEstablecimiento = nombreEstablecimiento;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public int getCupos() {
        return cupos;
    }

    public void setCupos(int cupos) {
        this.cupos = cupos;
    }

    public String getDirecEstablecimiento() {
        return direcEstablecimiento;
    }

    public void setDirecEstablecimiento(String direcEstablecimiento) {
        this.direcEstablecimiento = direcEstablecimiento;
    }

    public int getIdEstablecimiento() {
        return idEstablecimiento;
    }

    public void setIdEstablecimiento(int idEstablecimiento) {
        this.idEstablecimiento = idEstablecimiento;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    public String getNombreEstablecimiento() {
        return nombreEstablecimiento;
    }

    public void setNombreEstablecimiento(String nombreEstablecimiento) {
        this.nombreEstablecimiento = nombreEstablecimiento;
    }
}
