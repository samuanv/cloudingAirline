package com.clouding.airline.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.clouding.airline.dto.ReservaDTO;
import com.clouding.airline.entities.Reserva;
import com.clouding.airline.services.ReservaService;

@RestController
@RequestMapping("/reserva")
public class ReservaController {
	@Autowired
	private ReservaService service;
	
	@PostMapping
	ReservaDTO addReservas(@RequestBody ReservaDTO reservaDTO) {
		Reserva reserva = service.save(service.convertToReserva(reservaDTO));
		return service.convertToDto(reserva);
		//return reservaDTO;
	}
}
