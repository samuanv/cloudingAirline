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
}