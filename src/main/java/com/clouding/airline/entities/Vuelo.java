package com.clouding.airline.entities;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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

	@Enumerated(EnumType.STRING)
	@Column(name = "tipo_vuelo")
	private TipoVuelo tipoVuelo;

	@NotNull
	@Column(name = "tarifa")
	private float tarifa;

	/* foreignKey = nombre de la fk
	 * name = nombre de la columna en BD*/
	@ManyToOne(cascade= CascadeType.REMOVE)
	@JoinColumn(name = "avion_id", foreignKey=@ForeignKey(name="fk_aviones_id"))
	@JsonManagedReference
	private Avion avion;
	
	@NotNull
	@ManyToOne
	@JoinColumn(name = "aeropuerto_id_origen", foreignKey=@ForeignKey(name="fk_aeropuerto_origen"))
	private Aeropuerto aeropuertoOrigen;

	@NotNull
	@ManyToOne
	@JoinColumn(name = "aeropuerto_id_destino", foreignKey=@ForeignKey(name="fk_aeropuerto_destino"))
	private Aeropuerto aeropuertoDestino;
	
	// Eager para poder consultar todas las reservas de un vuelo
	@OneToMany(mappedBy = "vuelo", cascade=CascadeType.REMOVE, fetch=FetchType.EAGER)
	private Set<Reserva> reservas = new HashSet<>();
	
	public Set<Reserva> getReservas() {
		return reservas;
	}

	public void setReservas(Set<Reserva> reservas) {
		this.reservas = reservas;
	}

	public void setAeropuertoOrigen(Aeropuerto aeropuertoOrigen) {
		this.aeropuertoOrigen = aeropuertoOrigen;
	}

	public Vuelo(@NotNull @Size(max = 100) String nombre, @NotNull Date fechaCreacionReserva,
			@NotNull Date fechaSalida, @NotNull Date fechaEmbarque, @NotNull int duracion, TipoVuelo tipoVuelo,
			@NotNull float tarifa, Avion avion, Aeropuerto aeropuertoOrigen, Aeropuerto aeropuertoDestino) {
		super();
		this.nombre = nombre;
		this.fechaCreacionReserva = fechaCreacionReserva;
		this.fechaSalida = fechaSalida;
		this.fechaEmbarque = fechaEmbarque;
		this.duracion = duracion;
		this.tipoVuelo = tipoVuelo;
		this.tarifa = tarifa;
		this.avion = avion;
		this.aeropuertoOrigen = aeropuertoOrigen;
		this.aeropuertoDestino = aeropuertoDestino;
	}


	public Aeropuerto getAeropuertoOrigen() {
		return aeropuertoOrigen;
	}

	public void setAeropuertoOrigin(Aeropuerto aeropuertoOrigen) {
		this.aeropuertoOrigen = aeropuertoOrigen;
	}

	public Aeropuerto getAeropuertoDestino() {
		return aeropuertoDestino;
	}

	public void setAeropuertoDestino(Aeropuerto aeropuertoDestino) {
		this.aeropuertoDestino = aeropuertoDestino;
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

	public Vuelo() {
	}
	
}
