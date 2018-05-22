
package com.clouding.airline.entities;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "reservas")
public class Reserva {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_reserva")
	private Long id;

	@Column(name = "num_bultos")
	private int numBultos;

	@Column(name = "embarque_prioritario")
	private boolean embarquePrioritario;

	@NotNull
	@Column(name = "fecha_pago")
	private Date fechaPago;

	/*Asiento. Si es 0, no se ha emitido la tarjeta de embarque*/
	@Column(name = "asiento")
	private int asiento;
	
	@NotNull
	@Column(name = "activa")
	private boolean activa;


	@ManyToOne(cascade= CascadeType.REMOVE)
	private Vuelo vuelo;
	

	@ManyToOne(cascade= CascadeType.REMOVE)
	@JoinColumn(name = "pasajero_id", foreignKey=@ForeignKey(name="fk_pasajeros_id"))
	private Pasajero pasajero;
	

	public Reserva(int numBultos, boolean embarquePrioritario, @NotNull Date fechaPago, int asiento,
			@NotNull boolean activa, @NotNull Vuelo vuelo, @NotNull Pasajero pasajero) {
		super();
		this.numBultos = numBultos;
		this.embarquePrioritario = embarquePrioritario;
		this.fechaPago = fechaPago;
		this.asiento = asiento;
		this.activa = activa;
		this.vuelo = vuelo;
		this.pasajero = pasajero;
	}

	public Pasajero getPasajero() {
		return pasajero;
	}

	public void setPasajero(Pasajero pasajero) {
		this.pasajero = pasajero;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public Vuelo getVuelo() {
		return vuelo;
	}

	public void setVuelo(Vuelo vuelo) {
		this.vuelo = vuelo;
	}
	public Reserva () {
		
	}

}