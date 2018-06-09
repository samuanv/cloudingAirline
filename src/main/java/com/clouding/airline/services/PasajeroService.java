package com.clouding.airline.services;

import java.util.*;
import java.util.stream.Collectors;

import com.clouding.airline.dto.PasajeroDTO;
import com.clouding.airline.entities.Pasajero;
import com.clouding.airline.entities.Reserva;
import com.clouding.airline.repositories.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class PasajeroService {

    @Autowired
    PasajeroRepository repository;

    public List<Pasajero> vipCount() {
        return repository.findByCountEmbarque();
    }

    ModelMapper modelMapper = new ModelMapper();
    @Autowired
    ReservaRepository reservaRepository;

    public PasajeroDTO convertToDto(Pasajero pasajero) {
        PasajeroDTO pasajeroDTO = modelMapper.map(pasajero, PasajeroDTO.class);
        /*Set<Long> reservasIds = new HashSet<>();
        pasajero.getReservas().forEach( reserva -> reservasIds.add(reserva.getId()));
        pasajeroDTO.setReservas(reservasIds);*/
        return pasajeroDTO;
    }

    public Pasajero convertToPasajero(PasajeroDTO pasajeroDTO) {
        Pasajero pasajero = modelMapper.map(pasajeroDTO, Pasajero.class);
        /*Set<Reserva> reservaSet = new HashSet<Reserva>();
        pasajeroDTO.getReservas().forEach( reservaId -> reservaSet.add(reservaRepository.findById(reservaId).get()));
        pasajero.setReservas(reservaSet);*/
        return pasajero;
    }

    public List<PasajeroDTO> convertToDto(List<Pasajero> pasajeros) {
        return pasajeros.stream().map(e -> convertToDto(e)).collect(Collectors.toList());
    }

    public List<Pasajero> convertToPasajero(List<PasajeroDTO> pasajeros) {
        return pasajeros.stream().map(e -> convertToPasajero(e)).collect(Collectors.toList());
    }

}
