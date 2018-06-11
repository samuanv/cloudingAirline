package com.clouding.airline.controllers;

import com.clouding.airline.dto.PasajeroDTO;
import com.clouding.airline.services.PasajeroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/pasajero")
public class PasajeroController {
    @Autowired
    private PasajeroService service;

    @GetMapping("/vipCount")
    List<PasajeroDTO> getVipCount() {
        return service.convertToDto(service.vipCount());
    }
    @PostMapping
    List<PasajeroDTO> addPasajero(@RequestBody List<PasajeroDTO> pasajeros) {
        return service.convertToDto(service.save(service.convertToPasajero(pasajeros)));
    }
    @PutMapping("/{id}")
    PasajeroDTO changeName(@PathVariable String id, @RequestBody String nombre) {
        return service.convertToDto(service.changeName(id, nombre));
    }
}