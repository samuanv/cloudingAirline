package com.clouding.airline.dto;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import com.clouding.airline.entities.Agencia;
import com.clouding.airline.entities.Pasajero;
import com.clouding.airline.entities.Vuelo;

public class ReservaDTO {

	private Long id;

	private int numBultos;

	private boolean embarquePrioritario;

	private Date fechaPago;

	/*Asiento. Si es 0, no se ha emitido la tarjeta de embarque*/
	private int asiento;
	
	private boolean activa;

	private String pasajero_id;

	private Long agencia_id;
	
	private Long vuelo_id;
	
	
	public ReservaDTO() {
		
	}
	public int getNumBultos() {
		return numBultos;
	}

	public void setNumBultos(int numBultos) {
		this.numBultos = numBultos;
	}

	public boolean isEmbarquePrioritario() {
		return embarquePrioritario;
	}

	public void setEmbarquePrioritario(boolean embarquePrioritario) {
		this.embarquePrioritario = embarquePrioritario;
	}

	public Date getFechaPago() {
		return fechaPago;
	}

	public void setFechaPago(Date fechaPago) {
		this.fechaPago = fechaPago;
	}

	public int getAsiento() {
		return asiento;
	}

	public void setAsiento(int asiento) {
		this.asiento = asiento;
	}

	public boolean isActiva() {
		return activa;
	}

	public void setActiva(boolean activa) {
		this.activa = activa;
	}

	public Long getVuelo_id() {
		return vuelo_id;
	}

	public void setVuelo_id(Long vuelo_id) {
		this.vuelo_id = vuelo_id;
	}

	public String getPasajero_id() {
		return pasajero_id;
	}

	public void setPasajero_id(String pasajero_id) {
		this.pasajero_id = pasajero_id;
	}

	public Long getAgencia_id() {
		return agencia_id;
	}

	public void setAgencia_id(Long agencia_id) {
		this.agencia_id = agencia_id;
	}

}
