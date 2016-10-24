package com.pettime.pettimedevelop.Modelo;

/**
 * Created by yz on 21/10/2016.
 */
public class Mascota {

    private int idMascota;
    private String nombreMascota;
    private String especie;
    private String raza;
    private String genero;
    private String fechaNac;
    private int idPropietario;

    public Mascota() {
    }

    public Mascota(int idMascota, String nombreMascota, String especie, String raza, String genero, String fechaNac, int idPropietario) {
        this.especie = especie;
        this.fechaNac = fechaNac;
        this.genero = genero;
        this.idMascota = idMascota;
        this.nombreMascota = nombreMascota;
        this.raza = raza;
        this.idPropietario = idPropietario;
    }

    public String getEspecie() {
        return especie;
    }

    public void setEspecie(String especie) {
        this.especie = especie;
    }

    public String getFechaNac() {
        return fechaNac;
    }

    public void setFechaNac(String fechaNac) {
        this.fechaNac = fechaNac;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public int getIdMascota() {
        return idMascota;
    }

    public void setIdMascota(int idMascota) {
        this.idMascota = idMascota;
    }

    public String getNombreMascota() {
        return nombreMascota;
    }

    public void setNombreMascota(String nombreMascota) {
        this.nombreMascota = nombreMascota;
    }

    public String getRaza() {
        return raza;
    }

    public void setRaza(String raza) {
        this.raza = raza;
    }

    public int getIdPropietario() {
        return idPropietario;
    }

    public void setIdPropietario(int idPropietario) {
        this.idPropietario = idPropietario;
    }
}
