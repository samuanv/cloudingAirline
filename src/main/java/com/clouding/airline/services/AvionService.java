package com.clouding.airline.services;

import java.util.List;
import java.util.stream.Collectors;

import com.clouding.airline.entities.Avion;
import com.clouding.airline.repositories.AvionRepository;
import com.clouding.airline.repositories.VueloRepository;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AvionService {

    @Autowired
    AvionRepository repository;

    public List<Avion> getAviones() {
        return repository.findAll();
    }

}