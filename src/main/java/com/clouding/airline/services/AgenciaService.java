package com.clouding.airline.services;

import java.util.*;
import java.util.stream.Collectors;

import com.clouding.airline.dto.AgenciaDTO;
import com.clouding.airline.entities.Agencia;
import com.clouding.airline.repositories.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class AgenciaService {

    @Autowired
    AgenciaRepository repository;

    public Agencia login(String username, String password) {
        return repository.findByUsernameAndPassword(username, password);
    }

    public List<Agencia> getAgencias() {
        return repository.findAll();
    }
    ModelMapper modelMapper = new ModelMapper();


    public AgenciaDTO convertToDto(Agencia agencia) {
        AgenciaDTO agenciaDTO = modelMapper.map(agencia, AgenciaDTO.class);

        agenciaDTO.setNum_reservas(agencia.getReservas().size());
        return agenciaDTO;
    }

    public Agencia convertToAgencia(AgenciaDTO agenciaDTO) {
        Agencia agencia = modelMapper.map(agenciaDTO, Agencia.class);
        return agencia;
    }

    public List<AgenciaDTO> convertToDto(List<Agencia> agencias) {
        return agencias.stream().map(e -> convertToDto(e)).collect(Collectors.toList());
    }

    public List<Agencia> convertToAgencia(List<AgenciaDTO> agencias) {
        return agencias.stream().map(e -> convertToAgencia(e)).collect(Collectors.toList());
    }

}
