package com.clouding.airline.controllers;

import com.clouding.airline.dto.AeropuertoDTO;
import com.clouding.airline.services.AeropuertoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/aeropuerto")
public class AeropuertoController {
    @Autowired
    private AeropuertoService service;

    @GetMapping("/top")
    List<AeropuertoDTO> getTopDestinations() {
        return service.convertToDto(service.getTopDestinations());
    }
    @GetMapping()
    List<AeropuertoDTO> getAeropuertos() {
        return service.convertToDto(service.getAeropuertos());
    }
}