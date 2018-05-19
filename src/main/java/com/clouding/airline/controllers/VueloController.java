package com.clouding.airline.controllers;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.clouding.airline.entities.Vuelo;
import com.clouding.airline.repositories.VueloRepository;

@RestController
public class VueloController {
	
	@Autowired
	private VueloRepository vueloRepository;
	
	@RequestMapping("/vuelo")
	List<Vuelo> getVuelos(@RequestParam("nombre") String nombre) {
		System.out.println("/vuelo");
		return null;
	}
	
	public VueloController() {
		System.out.println("VueloControlle");
	}

}
