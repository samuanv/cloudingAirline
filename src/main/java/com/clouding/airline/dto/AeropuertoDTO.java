package com.clouding.airline.dto;

import java.util.List;

public class AeropuertoDTO {
    private String id;
    private String continente;
    private String coordenadas;
    private float elevacion;
    private String codigoGps;
    private String codigoIata;
    private String isoPais;
    private String isoRegion;
    private String codigoLocal;
    private String municipio;
    private String nombre;
    private String tipo;

    public AeropuertoDTO() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContinente() {
        return continente;
    }

    public void setContinente(String continente) {
        this.continente = continente;
    }

    public String getCoordenadas() {
        return coordenadas;
    }

    public void setCoordenadas(String coordenadas) {
        this.coordenadas = coordenadas;
    }

    public float getElevacion() {
        return elevacion;
    }

    public void setElevacion(float elevacion) {
        this.elevacion = elevacion;
    }

    public String getCodigoGps() {
        return codigoGps;
    }

    public void setCodigoGps(String codigoGps) {
        this.codigoGps = codigoGps;
    }

    public String getCodigoIata() {
        return codigoIata;
    }

    public void setCodigoIata(String codigoIata) {
        this.codigoIata = codigoIata;
    }

    public String getIsoPais() {
        return isoPais;
    }

    public void setIsoPais(String isoPais) {
        this.isoPais = isoPais;
    }

    public String getIsoRegion() {
        return isoRegion;
    }

    public void setIsoRegion(String isoRegion) {
        this.isoRegion = isoRegion;
    }

    public String getCodigoLocal() {
        return codigoLocal;
    }

    public void setCodigoLocal(String codigoLocal) {
        this.codigoLocal = codigoLocal;
    }

    public String getMunicipio() {
        return municipio;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
