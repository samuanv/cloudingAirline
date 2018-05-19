package com.clouding.airline.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonManagedReference;
@Entity
@Table(name = "vuelos")
public class Vuelo {
	
	public enum TipoVuelo {
		IDA, IDA_VUELTA
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_vuelo")
	private Long id;

	@NotNull
	@Size(max = 100)
	@Column(unique = true)
	private String nombre;
	
	@NotNull
	@Column(name = "fecha_reserva_creacion")
	private Date fechaCreacionReserva;

	@NotNull
	@Column(name = "fecha_salida")
	private Date fechaSalida;
	
	@NotNull
	@Column(name = "fecha_embarque")
	private Date fechaEmbarque;

	@NotNull
	@Column(name = "duracion")
	private int duracion;

	@NotNull
	@Enumerated(EnumType.STRING)
	@Column(name = "tipo_vuelo")
	private TipoVuelo tipoVuelo;

	@NotNull
	@Column(name = "tarifa")
	private float tarifa;

	/* foreignKey = nombre de la fk
	 * name = nombre de la columna en BD*/
	@ManyToOne
	@JoinColumn(name = "avion_id", foreignKey=@ForeignKey(name="fk_aviones_id"))
	@JsonManagedReference
	private Avion avion;

	@ManyToOne
	@JoinColumn(name = "aeropuerto_id_origen", foreignKey=@ForeignKey(name="fk_aeropuerto_origen"))
	@JsonManagedReference
	private Aeropuerto aeropuerto_origen;

	@ManyToOne
	@JoinColumn(name = "aeropuerto_id_destino", foreignKey=@ForeignKey(name="fk_aeropuerto_destino"))
	@JsonManagedReference
	private Aeropuerto aeropuerto_destino;

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

	public TipoVuelo getTipoVuelo() {
		return tipoVuelo;
	}

	public void setTipoVuelo(TipoVuelo tipoVuelo) {
		this.tipoVuelo = tipoVuelo;
	}

	public float getTarifa() {
		return tarifa;
	}

	public void setTarifa(float tarifa) {
		this.tarifa = tarifa;
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

	public Avion getAvion() {
		return avion;
	}

	public void setAvion(Avion avion) {
		this.avion = avion;
	}



	public Vuelo( @NotNull @Size(max = 100) String nombre, @NotNull Date fechaCreacionReserva,
			@NotNull Date fechaSalida, @NotNull Date fechaEmbarque, @NotNull int duracion, @NotNull TipoVuelo tipoVuelo,
			@NotNull float tarifa, Avion avion, Aeropuerto aeropuerto_origen, Aeropuerto aeropuerto_destino) {
		super();
		this.nombre = nombre;
		this.fechaCreacionReserva = fechaCreacionReserva;
		this.fechaSalida = fechaSalida;
		this.fechaEmbarque = fechaEmbarque;
		this.duracion = duracion;
		this.tipoVuelo = tipoVuelo;
		this.tarifa = tarifa;
		this.avion = avion;
		this.aeropuerto_origen = aeropuerto_origen;
		this.aeropuerto_destino = aeropuerto_destino;
	}

	public Aeropuerto getAeropuerto_origen() {
		return aeropuerto_origen;
	}

	public void setAeropuerto_origen(Aeropuerto aeropuerto_origen) {
		this.aeropuerto_origen = aeropuerto_origen;
	}

	public Aeropuerto getAeropuerto_destino() {
		return aeropuerto_destino;
	}

	public void setAeropuerto_destino(Aeropuerto aeropuerto_destino) {
		this.aeropuerto_destino = aeropuerto_destino;
	}

	public Vuelo(@NotNull @Size(max = 100) String nombre, @NotNull Date fechaCreacionReserva, @NotNull Date fechaSalida,
			@NotNull Date fechaEmbarque, @NotNull int duracion, @NotNull TipoVuelo tipoVuelo, @NotNull float tarifa,
			Avion avion) {
		super();
		this.nombre = nombre;
		this.fechaCreacionReserva = fechaCreacionReserva;
		this.fechaSalida = fechaSalida;
		this.fechaEmbarque = fechaEmbarque;
		this.duracion = duracion;
		this.tipoVuelo = tipoVuelo;
		this.tarifa = tarifa;
		this.avion = avion;
	}

	public Vuelo() {
	}
	
}
