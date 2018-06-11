package com.clouding.airline.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.clouding.airline.dto.EmbarqueDTO;
import com.clouding.airline.dto.ReservaDTO;
import com.clouding.airline.repositories.ReservaRepository.TotalByMonth;
import com.clouding.airline.services.ReservaService;

@RestController
@RequestMapping("/reserva")
public class ReservaController {
	@Autowired
	private ReservaService service;

	@PostMapping
	List<ReservaDTO> addReservas(@RequestBody List<ReservaDTO> reservasDTO) {
		return service.convertToDto(service.saveAll(service.convertToReserva(reservasDTO)));
	}

	@GetMapping("/{idAgencia}")
	List<ReservaDTO> getReservasDeAgencia(@PathVariable Long idAgencia, @RequestParam("realizado") Boolean realizado) {
		if (realizado) {
			return service.convertToDto(service.findByAgenciaRealizados(idAgencia));
		} else {
			return service.convertToDto(service.findByAgenciaNoRealizados(idAgencia));

		}
	}

	@GetMapping("/total")
	List<TotalByMonth> getTotalByMonth() {
		return service.totalByMonth();
	}
	@PostMapping("/embarcar")
	List<ReservaDTO> embarcar(@RequestBody EmbarqueDTO embarqueDTO) {
		return service.convertToDto(service.embarcar(embarqueDTO.getVuelo_id(), embarqueDTO.getAgencia_id()));
	}
	@PostMapping("/cancelar")
	ReservaDTO cancelar(@RequestBody ReservaDTO reservaDTO) {
		return service.convertToDto(service.cancelar( reservaDTO.getId(), reservaDTO.getVuelo_id()));
	}
	@PostMapping("/cambiar")
	ReservaDTO cambiarNombre(@RequestBody ReservaDTO reservaDTO) {
		return service.convertToDto(service.cambiarNombre( reservaDTO.getId(), reservaDTO.getVuelo_id(), reservaDTO.getPasajero_id()));
	}
	@PatchMapping("/{id}")
	ReservaDTO editReserva(@PathVariable Long id, HttpServletRequest request) {

		return service.convertToDto(service.edit(id, request));
	}
}
