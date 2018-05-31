package com.clouding.airline.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.codehaus.jackson.map.ObjectMapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clouding.airline.dto.ReservaDTO;
import com.clouding.airline.entities.Reserva;
import com.clouding.airline.entities.Vuelo;
import com.clouding.airline.repositories.AgenciaRepository;
import com.clouding.airline.repositories.PasajeroRepository;
import com.clouding.airline.repositories.ReservaRepository;
import com.clouding.airline.repositories.ReservaRepository.TotalByMonth;
import com.clouding.airline.repositories.VueloRepository;

@Service
public class ReservaService {
	@Autowired
	ReservaRepository repository;
	ObjectMapper objectMapper = new ObjectMapper();

	/* Q1 */
	public List<Reserva> saveAll(List<Reserva> reservas) {
		List<Reserva> reservaResponse = new ArrayList<Reserva>();
		for (Reserva reserva : reservas) {
			/* Comprobar que hay plazas libres */
			if (reserva.getVuelo().getReservas().size() <= reserva.getVuelo().getAvion().getPlazas()) {
				reservaResponse.add(repository.save(reserva));
			}
		}
		return reservaResponse;
	}

	public Reserva save(Reserva reservas) {
		return repository.save(reservas);
	}

	public Reserva edit(Long id, HttpServletRequest request) {

		Reserva reserva = repository.findById(id).get();
		Reserva reservaFinal = null;
		try {

			reservaFinal = objectMapper.readerForUpdating(reserva).readValue(request.getReader());
			repository.save(reservaFinal);
		} catch (Exception e) {
			e.getStackTrace();

		}
		return reservaFinal;

	}

	public List<Reserva> findByAgenciaRealizados(Long id) {
		return repository.findReservasRealizadasFinalizadas(agenciaRepository.findById(id).get(), new Date());
	}

	public List<Reserva> findByAgenciaNoRealizados(Long id) {
		return repository.findReservasRealizadasPendientes(agenciaRepository.findById(id).get(), new Date());
	}

	public List<Reserva> embarcar(Long idVuelo, Long idAgencia) {
		// Comprobación de las 24 horas en Java para no tener que hacer una Query Nativa
		// y usar HQL
		Vuelo vuelo = vueloRepository.findById(idVuelo).get();
		if (!(Math.abs(vuelo.getFechaSalida().getTime() - new Date().getTime()) > 24 * 60 * 60 * 1000L)) {
			List<Reserva> sinEmbarcar = repository.retrieveByAsientoAndFecha(idAgencia, idVuelo);
			// repository.embarcar(idVuelo, vuelo.getAvion().getPlazas());
			// Re flush the objects with new seats
			List<Reserva> embarcados = new ArrayList<Reserva>();
			for (Reserva reserva : sinEmbarcar) {
				reserva.setAsiento(repository.getFreeSeats(idVuelo));
				embarcados.add(repository.save(reserva));
			}
			return embarcados;
		} else {
			return new ArrayList<Reserva>();
		}

	}
	public List<TotalByMonth> totalByMonth() {
		return repository.findTotalByMonth();
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

	public List<ReservaDTO> convertToDto(List<Reserva> reservas) {
		return reservas.stream().map(e -> convertToDto(e)).collect(Collectors.toList());
	}

	public List<Reserva> convertToReserva(List<ReservaDTO> reservas) {
		return reservas.stream().map(e -> convertToReserva(e)).collect(Collectors.toList());
	}

	public Reserva convertToReserva(ReservaDTO reservaDTO) {
		Reserva reserva = modelMapper.map(reservaDTO, Reserva.class);
		reserva.setAgencia(agenciaRepository.findById(reservaDTO.getAgencia_id()).get());
		reserva.setPasajero(pasajeroRepository.findById(reservaDTO.getPasajero_id()).get());
		reserva.setVuelo(vueloRepository.findById(reservaDTO.getVuelo_id()).get());
		return reserva;
	}
}
