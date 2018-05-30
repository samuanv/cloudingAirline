package com.clouding.airline.controllers;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.clouding.airline.entities.Vuelo;
import com.clouding.airline.services.VueloService;

@RestController
@RequestMapping("/vuelo")
public class VueloController {

	@Autowired
	private VueloService service;

	@GetMapping
	List<Vuelo> getVuelos(@RequestParam("id_origen") String idOrigen,
			@RequestParam("id_destino") String idDestino, @RequestParam("plazas") Integer plazas,
			@RequestParam("fecha") String fecha) {
		if (idDestino!=null && idOrigen!=null && plazas!=null && fecha!=null) {
			return service.retrieveByFiltersAndFreePax(idOrigen, idDestino, plazas, new Date(fecha));
		} else {
			return service.findAll();
		}
	}

	@PostMapping
	Vuelo addVuelo(Vuelo vuelo) {
		return service.save(vuelo);
	}
	
	@GetMapping("/proximo")
	List<Vuelo> getVuelosProximos(@RequestParam("fecha") String fecha) {
		return service.retrieveByDateDiff(new Date(fecha));
	}
	

}
