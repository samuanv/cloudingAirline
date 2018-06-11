package com.clouding.airline.services;

import java.util.*;
import java.util.stream.Collectors;

import com.clouding.airline.dto.PasajeroDTO;
import com.clouding.airline.entities.Pasajero;
import com.clouding.airline.entities.Vuelo;
import com.clouding.airline.repositories.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class PasajeroService {

    @Autowired
    PasajeroRepository repository;
    VueloRepository vueloRepository;

    public List<Pasajero> vipCount() {
        return repository.findByCountEmbarque();
    }
    public List<Pasajero> save(Iterable<Pasajero> pasajeros) {
        return repository.saveAll(pasajeros);
    }

    public Pasajero changeName(Long idVuelo, String id, String nombre) {
        Vuelo vuelo = vueloRepository.findById(idVuelo).get();
        if ((Math.abs(vuelo.getFechaSalida().getTime() - new Date().getTime()) > 7 * 24 * 60 * 60 * 1000L)){
            Pasajero p = repository.findByDni(id);
            p.setNombre(nombre);
            return repository.save(p);
        }else {
            Pasajero p = new Pasajero();
            return p;
        }
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
