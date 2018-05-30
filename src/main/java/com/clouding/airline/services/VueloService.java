package com.clouding.airline.services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clouding.airline.entities.Vuelo;
import com.clouding.airline.repositories.VueloRepository;

@Service
public class VueloService {

	@Autowired
	VueloRepository repository;
	/* Q1 */
	public List<Vuelo> retrieveByFiltersAndFreePax(String idOrigen, String idDestino, Integer plazas, Date fecha) {
		return repository.retrieveByFiltersAndFreePax(idOrigen, idDestino, plazas, fecha);
	}
	
	public List<Vuelo> findAll() {
		return repository.findAll();
	}
	
	public Vuelo save(Vuelo vuelo) {
		return repository.save(vuelo);
	}
	
	public List<Vuelo> retrieveByDateDiff(Date fecha) {
		return repository.retrieveByDateDiff(fecha);
	}
}
