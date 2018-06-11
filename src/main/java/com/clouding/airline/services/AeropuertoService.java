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

    public List<Aeropuerto> getAeropuertos() {
        return repository.findAll();
    }

    ModelMapper modelMapper = new ModelMapper();
    @Autowired
    VueloRepository vueloRepository;

    public AeropuertoDTO convertToDto(Aeropuerto aeropuerto) {
        AeropuertoDTO aeropuertoDTO = modelMapper.map(aeropuerto, AeropuertoDTO.class);
        return aeropuertoDTO;
    }

    public Aeropuerto convertToAeropuerto(AeropuertoDTO aeropuertoDTO) {
        Aeropuerto aeropuerto = modelMapper.map(aeropuertoDTO, Aeropuerto.class);

        return aeropuerto;
    }

    public List<AeropuertoDTO> convertToDto(List<Aeropuerto> aeropuertos) {
        return aeropuertos.stream().map(e -> convertToDto(e)).collect(Collectors.toList());
    }

    public List<Aeropuerto> convertToAeropuerto(List<AeropuertoDTO> aeropuertos) {
        return aeropuertos.stream().map(e -> convertToAeropuerto(e)).collect(Collectors.toList());
    }

}
