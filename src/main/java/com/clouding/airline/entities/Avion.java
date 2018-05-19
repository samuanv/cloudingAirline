package com.clouding.airline.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "aviones")
public class Avion {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_avion")
	private Long id;

	@NotNull
	@Size(max = 100)
	@Column(unique = true)
	private String nombre;

	@NotNull
	private Integer plazas;

	@NotNull
	@Size(max = 350)
	private String modelo;

	/*
	 * mappedBy = nombre de la propiedad en el objeto JAVA*/
	@OneToMany(mappedBy = "avion")
	@JsonBackReference
	private Set<Vuelo> vuelos = new HashSet<>();

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public Set<Vuelo> getVuelos() {
		return vuelos;
	}

	public void setVuelos(Set<Vuelo> vuelos) {
		this.vuelos = vuelos;
	}

	public Avion() {

	}

	public Avion(String nombre, Integer plazas, String modelo) {
		this.plazas = plazas;
		this.nombre = nombre;
		this.modelo = modelo;
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

	public Integer getPlazas() {
		return plazas;
	}

	public void setPlazas(Integer plazas) {
		this.plazas = plazas;
	}

}