package com.clouding.airline.services;

import java.util.*;
import java.util.stream.Collectors;

import com.clouding.airline.dto.AeropuertoDTO;
import com.clouding.airline.entities.Aeropuerto;
import com.clouding.airline.entities.Vuelo;
import com.clouding.airline.repositories.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class AeropuertoService {

    @Autowired
    AeropuertoRepository repository;

    public List<Aeropuerto> getTopDestinations() {
        return repository.findMostProfitable();
    }

    ModelMapper modelMapper = new ModelMapper();
    @Autowired
    VueloRepository vueloRepository;

    public AeropuertoDTO convertToDto(Aeropuerto aeropuerto) {
        AeropuertoDTO aeropuertoDTO = modelMapper.map(aeropuerto, AeropuertoDTO.class);
       /* List<Long> vuelosDestinoIds = new ArrayList<>();
        List<Long> vuelosOrigenIds = new ArrayList<>();
        aeropuerto.getVuelosDestino().forEach( vuelosDestino -> vuelosDestinoIds.add(vuelosDestino.getId()));
        aeropuerto.getVuelosOrigen().forEach( vuelosOrigen -> vuelosOrigenIds.add(vuelosOrigen.getId()));

        aeropuertoDTO.setVuelosDestino(vuelosDestinoIds);
        aeropuertoDTO.setVuelosOrigen(vuelosOrigenIds);*/
        return aeropuertoDTO;
    }

    public Aeropuerto convertToAeropuerto(AeropuertoDTO aeropuertoDTO) {
        Aeropuerto aeropuerto = modelMapper.map(aeropuertoDTO, Aeropuerto.class);
        /*Set<Vuelo> vuelosDestinoSet = new HashSet<Vuelo>();
        Set<Vuelo> vuelosOrigenSet = new HashSet<Vuelo>();

        aeropuertoDTO.getVuelosDestino().forEach( vueloDestinoId -> vuelosDestinoSet.add(vueloRepository.findById(vueloDestinoId).get()));
        aeropuertoDTO.getVuelosOrigen().forEach( reservaId -> vuelosOrigenSet.add(vueloRepository.findById(reservaId).get()));

        aeropuerto.setVuelosDestino(vuelosDestinoSet);
        aeropuerto.setVuelosOrigen(vuelosOrigenSet);*/

        return aeropuerto;
    }

    public List<AeropuertoDTO> convertToDto(List<Aeropuerto> aeropuertos) {
        return aeropuertos.stream().map(e -> convertToDto(e)).collect(Collectors.toList());
    }

    public List<Aeropuerto> convertToAeropuerto(List<AeropuertoDTO> aeropuertos) {
        return aeropuertos.stream().map(e -> convertToAeropuerto(e)).collect(Collectors.toList());
    }

}
