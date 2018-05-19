package com.clouding.airline;

import java.util.Date;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.clouding.airline.entities.Aeropuerto;
import com.clouding.airline.entities.Avion;
import com.clouding.airline.entities.Vuelo;
import com.clouding.airline.repositories.AeropuertoRepository;
import com.clouding.airline.repositories.AvionRepository;
import com.clouding.airline.repositories.VueloRepository;

@SpringBootApplication
public class AirlineApplication {

	public static void main(String[] args) { 
		SpringApplication.run(AirlineApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner loadData(AeropuertoRepository aeroRepository, VueloRepository vueloRepository, AvionRepository avionRepository) {
	    return (args) -> {
	    	/*// IMPORTAR AEROPUERTOS DESDE EL FICHERO JSON REDUCIDO
	    	aeroRepository.deleteAllInBatch();
			ObjectMapper mapper = new ObjectMapper();
			Aeropuerto[] aeropuerto = mapper.readValue(new File("./airport-codes_json-min.json"), Aeropuerto[].class);
			aeroRepository.saveAll(Arrays.asList(aeropuerto));*/
	    	avionRepository.deleteAllInBatch();
	    	vueloRepository.deleteAllInBatch();
	    	
			Aeropuerto a = aeroRepository.findById("00A");
			Aeropuerto a1 = aeroRepository.findById("00AK");
	    	Avion avion = new Avion("Avion1", 100, "Modelo1");
	    	avionRepository.save(avion);
	    	Vuelo vuelo = new Vuelo("Vuelo1", new Date(), new Date(), new Date(), 4, Vuelo.TipoVuelo.IDA, 50, avion, a, a1);
	    	vueloRepository.save(vuelo);
	    	
	    	/*avionRepository.deleteAllInBatch();
	        // save a couple of customers
	    	avionRepository.save(avion);*/
	    };
	}
}
