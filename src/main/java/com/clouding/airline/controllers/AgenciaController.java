package com.clouding.airline.controllers;


import java.util.List;

import com.clouding.airline.dto.AgenciaDTO;
import com.clouding.airline.dto.LoginDTO;
import com.clouding.airline.services.AgenciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/agencia")
public class AgenciaController {
    @Autowired
    private AgenciaService service;

    @PostMapping("/login")
    AgenciaDTO login(@RequestBody LoginDTO login) {
        return service.convertToDto(service.login( login.getUsername(), login.getPassword() ));
    }

    @GetMapping()
    List<AgenciaDTO> getAgencias() {
        return service.convertToDto(service.getAgencias());
    }
}