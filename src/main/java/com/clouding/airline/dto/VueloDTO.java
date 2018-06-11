package com.clouding.airline.dto;

import java.util.Date;

public class VueloDTO {
	Long id;
	String nombre;
	Date fechaCreacionReserva;
	Date fechaSalida;
	Date fechaEmbarque;
	int duracion;
	String tipoVuelo;
	int tarifa;
	int plazasLibres;
	int plazasTotales;

	Long avion_id;
	String aeropuertoOrigen_id;
	String aeropuertoDestino_id;
	String aeropuertoOrigen_nombre;
	String aeropuertoDestino_nombre;
	String aeropuertoOrigen_pais;
	String aeropuertoDestino_pais;

	/**
	 * @return the aeropuertoDestino_pais
	 */
	public String getAeropuertoDestino_pais() {
		return aeropuertoDestino_pais;
	}
	/**
	 * @param aeropuertoDestino_pais the aeropuertoDestino_pais to set
	 */
	public void setAeropuertoDestino_pais(String aeropuertoDestino_pais) {
		this.aeropuertoDestino_pais = aeropuertoDestino_pais;
	}
	/**
	 * @return the aeropuertoOrigen_pais
	 */
	public String getAeropuertoOrigen_pais() {
		return aeropuertoOrigen_pais;
	}
	/**
	 * @param aeropuertoOrigen_pais the aeropuertoOrigen_pais to set
	 */
	public void setAeropuertoOrigen_pais(String aeropuertoOrigen_pais) {
		this.aeropuertoOrigen_pais = aeropuertoOrigen_pais;
	}
	public int getPlazasTotales() {
		return plazasTotales;
	}
	public void setPlazasTotales(int plazasTotales) {
		this.plazasTotales = plazasTotales;
	}
	public String getAeropuertoOrigen_nombre() {
		return aeropuertoOrigen_nombre;
	}
	public void setAeropuertoOrigen_nombre(String aeropuertoOrigen_nombre) {
		this.aeropuertoOrigen_nombre = aeropuertoOrigen_nombre;
	}
	public String getAeropuertoDestino_nombre() {
		return aeropuertoDestino_nombre;
	}
	public void setAeropuertoDestino_nombre(String aeropuertoDestino_nombre) {
		this.aeropuertoDestino_nombre = aeropuertoDestino_nombre;
	}
	public int getPlazasLibres() {
		return plazasLibres;
	}
	public void setPlazasLibres(int plazasLibres) {
		this.plazasLibres = plazasLibres;
	}
	public VueloDTO() {
		
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public Date getFechaCreacionReserva() {
		return fechaCreacionReserva;
	}
	public void setFechaCreacionReserva(Date fechaCreacionReserva) {
		this.fechaCreacionReserva = fechaCreacionReserva;
	}
	public Date getFechaSalida() {
		return fechaSalida;
	}
	public void setFechaSalida(Date fechaSalida) {
		this.fechaSalida = fechaSalida;
	}
	public Date getFechaEmbarque() {
		return fechaEmbarque;
	}
	public void setFechaEmbarque(Date fechaEmbarque) {
		this.fechaEmbarque = fechaEmbarque;
	}
	public int getDuracion() {
		return duracion;
	}
	public void setDuracion(int duracion) {
		this.duracion = duracion;
	}
	public String getTipoVuelo() {
		return tipoVuelo;
	}
	public void setTipoVuelo(String tipoVuelo) {
		this.tipoVuelo = tipoVuelo;
	}
	public int getTarifa() {
		return tarifa;
	}
	public void setTarifa(int tarifa) {
		this.tarifa = tarifa;
	}
	public Long getAvion_id() {
		return avion_id;
	}
	public void setAvion_id(Long avion_id) {
		this.avion_id = avion_id;
	}
	public String getAeropuertoOrigen_id() {
		return aeropuertoOrigen_id;
	}
	public void setAeropuertoOrigen_id(String aeropuertoOrigen_id) {
		this.aeropuertoOrigen_id = aeropuertoOrigen_id;
	}
	public String getAeropuertoDestino_id() {
		return aeropuertoDestino_id;
	}
	public void setAeropuertoDestino_id(String aeropuertoDestino_id) {
		this.aeropuertoDestino_id = aeropuertoDestino_id;
	}

	
}
