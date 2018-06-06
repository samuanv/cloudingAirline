package com.clouding.airline.controllers;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.clouding.airline.dto.VueloDTO;
import com.clouding.airline.services.VueloService;

@RestController
@RequestMapping("/vuelo")
public class VueloController {

	@Autowired
	private VueloService service;


	@GetMapping
	List<VueloDTO> getVuelos(@RequestParam(value = "id_origen", required=false) String idOrigen,
			@RequestParam(value = "id_destino", required=false) String idDestino,
			@RequestParam(value = "plazas", required=false) Integer plazas,
			@RequestParam(value = "fecha", required=false) String fecha) {
		if (idDestino!=null && idOrigen!=null && plazas!=null && fecha!=null) {
			return service.convertToDto(service.retrieveByFiltersAndFreePax(idOrigen, idDestino, plazas, new Date(fecha)));
		} else {
			return service.convertToDto(service.findAll());
		}
	}

	@PostMapping
	VueloDTO addVuelo(VueloDTO vuelo) {
		return service.convertToDto(service.save(service.convertToVuelo(vuelo)));
	}
	
	@GetMapping("/proximo")
	List<VueloDTO> getVuelosProximos(@RequestParam("fecha") String fecha) {
		return service.convertToDto(service.retrieveByDateDiff(new Date(fecha)));
	}
	

}
