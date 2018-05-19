package com.clouding.airline;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.clouding.airline.entities.Avion;
import com.clouding.airline.entities.Vuelo;
import com.clouding.airline.repositories.AvionRepository;
import com.clouding.airline.repositories.VueloRepository;

@SpringBootApplication
public class AirlineApplication implements CommandLineRunner {

	@Autowired
	private AvionRepository avionRepository;
	
	@Autowired
	private VueloRepository vueloRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(AirlineApplication.class, args);
	}
	
    @Override
    public void run(String... args) throws Exception {
        // Cleanup the tables
    	avionRepository.deleteAllInBatch();
    	vueloRepository.deleteAllInBatch();
    	Avion avion = new Avion("Avion1", 100, "Modelo");
    	Vuelo vuelo = new Vuelo("Vuelo1");

    	avionRepository.save(avion);
    	vuelo.setAvion(avion);
    	vueloRepository.save(vuelo);

    	Vuelo v = vueloRepository.findByNombre("Vuelo1");
    	System.out.println(v.getNombre());
    	
    }
}
