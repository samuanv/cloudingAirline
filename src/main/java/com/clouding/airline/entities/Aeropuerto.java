package com.clouding.airline.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "aeropuertos")
public class Aeropuerto {
	@Id
	@Column(name = "id_aeropuerto")
	@JsonProperty("ident")
	private String id;

	@Column(name = "continente")
	@JsonProperty("continent")
	private String continente;

	@Column(name = "coordenadas")
	@JsonProperty("coordinates")
	private String coordenadas;
	
	@Column(name = "elevacion")
	@JsonProperty("elevation_ft")
	private float elevacion;

	@Column(name = "codigo_gps")
	@JsonProperty("gps_code")
	private String codigoGps;

	@Column(name = "codigo_iata")
	@JsonProperty("iata_code")
	private String codigoIata;

	@Column(name = "isoPais")
	@JsonProperty("iso_country")
	private String isoPais;

	@Column(name = "isoRegion")
	@JsonProperty("iso_region")
	private String isoRegion;

	@Column(name = "codigo_local")
	@JsonProperty("local_code")
	private String codigoLocal;

	@Column(name = "municipio")
	@JsonProperty("municipality")
	private String municipio;

	@Column(name = "nombre")
	@JsonProperty("name")
	private String nombre;

	@Column(name = "tipo")
	@JsonProperty("type")
	private String tipo;

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

	/*
	 * mappedBy = nombre de la propiedad en el objeto JAVA*/
  /* 	@OneToMany(mappedBy = "aeropuertoOrigen", cascade= CascadeType.REMOVE)
	private Set<Vuelo> vuelosOrigen = new HashSet<>();
	
	@OneToMany(mappedBy = "aeropuertoDestino", cascade= CascadeType.REMOVE)
	private Set<Vuelo> vuelosDestino = new HashSet<>();
	


	public Aeropuerto() {
		
	}

	public Set<Vuelo> getVuelosOrigen() {
		return vuelosOrigen;
	}

	public void setVuelosOrigen(Set<Vuelo> vuelosOrigen) {
		this.vuelosOrigen = vuelosOrigen;
	}

	public Set<Vuelo> getVuelosDestino() {
		return vuelosDestino;
	}

	public void setVuelosDestino(Set<Vuelo> vuelosDestino) {
		this.vuelosDestino = vuelosDestino;
	}*/
}
