package com.clouding.airline.dto;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PasajeroDTO {

    private String dni;
    private String nombre;
    private String apellidos;
   // private Set<Long> reservas_id = new HashSet<>();


    public PasajeroDTO() {
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

   /* public Set<Long> getReservas() {
        return reservas_id;
    }

    public void setReservas(Set<Long> reservas) {
        this.reservas_id = reservas;
    }*/
}
