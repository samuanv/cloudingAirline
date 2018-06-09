package com.clouding.airline.controllers;

import java.util.List;

import com.clouding.airline.dto.AeropuertoDTO;
import com.clouding.airline.services.AvionService;
import com.clouding.airline.entities.Avion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/avion")
public class AvionController {
    @Autowired
    private AvionService service;

    @GetMapping()
    List<Avion> getAviones() {
        return service.getAviones();
    }
}