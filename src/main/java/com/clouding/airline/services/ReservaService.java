package com.clouding.airline.services;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clouding.airline.dto.ReservaDTO;
import com.clouding.airline.entities.Reserva;
import com.clouding.airline.repositories.AgenciaRepository;
import com.clouding.airline.repositories.PasajeroRepository;
import com.clouding.airline.repositories.ReservaRepository;
import com.clouding.airline.repositories.VueloRepository;

@Service
public class ReservaService {
	@Autowired
	ReservaRepository repository;

	/* Q1 */
	public List<Reserva> saveAll(List<Reserva> reservas) {
		return repository.saveAll(reservas);
	}
	public Reserva save(Reserva reservas) {
		return repository.save(reservas);
	}
	
	ModelMapper modelMapper = new ModelMapper();

	@Autowired
	AgenciaRepository agenciaRepository;
	@Autowired
	PasajeroRepository pasajeroRepository;
	@Autowired
	VueloRepository vueloRepository;
	public ReservaDTO convertToDto(Reserva reserva) {
		ReservaDTO reservaDTO = modelMapper.map(reserva, ReservaDTO.class);
		reservaDTO.setAgencia_id(reserva.getAgencia().getId());
		reservaDTO.setPasajero_id(reserva.getPasajero().getDni());
		reservaDTO.setVuelo_id(reserva.getVuelo().getId());
	    return reservaDTO;
	}
	public Reserva convertToReserva(ReservaDTO reservaDTO) {
		Reserva reserva = modelMapper.map(reservaDTO, Reserva.class);
		reserva.setAgencia(agenciaRepository.findById(reservaDTO.getAgencia_id()).get());
		reserva.setPasajero(pasajeroRepository.findById(reservaDTO.getPasajero_id()).get());
		reserva.setVuelo(vueloRepository.findById(reservaDTO.getVuelo_id()).get());
	    return reserva;
	} 
}
