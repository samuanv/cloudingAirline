package com.clouding.airline.services;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clouding.airline.dto.VueloDTO;
import com.clouding.airline.entities.Vuelo;
import com.clouding.airline.repositories.AeropuertoRepository;
import com.clouding.airline.repositories.AvionRepository;
import com.clouding.airline.repositories.VueloRepository;

@Service
public class VueloService {

	@Autowired
	VueloRepository repository;
	/* Q1 */
	public List<Vuelo> retrieveByFiltersAndFreePax(String idOrigen, String idDestino, Integer plazas, Date fecha) {
		System.out.println(idOrigen + idDestino + plazas + fecha);
		Calendar c = Calendar.getInstance(); 
		c.setTime(fecha); 
		c.add(Calendar.DATE, 1);
		System.out.println(fecha);
		System.out.println(c.getTime());
		List<Vuelo> vuelos = repository.retrieveByFiltersAndFreePax(idOrigen, idDestino, plazas, fecha, c.getTime());
		return vuelos;
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
	
	
	ModelMapper modelMapper = new ModelMapper();
	@Autowired
	AeropuertoRepository aeropuertoRepository;
	@Autowired
	AvionRepository avionRepository;
	public VueloDTO convertToDto(Vuelo vuelo) {
		VueloDTO vueloDTO = modelMapper.map(vuelo, VueloDTO.class);
		vueloDTO.setAeropuertoOrigen_id(vuelo.getAeropuertoOrigen().getId());
		vueloDTO.setAeropuertoDestino_id(vuelo.getAeropuertoDestino().getId());
		vueloDTO.setAvion_id(vuelo.getAvion().getId());
		vueloDTO.setPlazasLibres(vuelo.getAvion().getPlazas() - vuelo.getReservas().size());
		vueloDTO.setPlazasTotales(vuelo.getAvion().getPlazas());
		vueloDTO.setAeropuertoDestino_nombre(vuelo.getAeropuertoDestino().getNombre());
		vueloDTO.setAeropuertoOrigen_nombre(vuelo.getAeropuertoOrigen().getNombre());
		vueloDTO.setAeropuertoDestino_pais(vuelo.getAeropuertoDestino().getIsoPais());
		vueloDTO.setAeropuertoOrigen_pais(vuelo.getAeropuertoOrigen().getIsoPais());
		return vueloDTO;
	}

	public List<VueloDTO> convertToDto(List<Vuelo> vuelos) {
		return vuelos.stream().map(e -> convertToDto(e)).collect(Collectors.toList());
	}

	public List<Vuelo> convertToVuelo(List<VueloDTO> vuelos) {
		return vuelos.stream().map(e -> convertToVuelo(e)).collect(Collectors.toList());
	}

	public Vuelo convertToVuelo(VueloDTO vueloDTO) {
		Vuelo vuelo = modelMapper.map(vueloDTO, Vuelo.class);

		System.out.println(vueloDTO.getAeropuertoDestino_id());
		System.out.println(vueloDTO.getAeropuertoOrigen_id());
		System.out.println(vueloDTO.getAvion_id());
		System.out.println(vueloDTO.getNombre());
		vuelo.setAeropuertoDestino(aeropuertoRepository.findById(vueloDTO.getAeropuertoDestino_id()));
		vuelo.setAeropuertoOrigen(aeropuertoRepository.findById(vueloDTO.getAeropuertoOrigen_id()));
		vuelo.setAvion(avionRepository.findById(vueloDTO.getAvion_id()).get());
		return vuelo;
	}
}
