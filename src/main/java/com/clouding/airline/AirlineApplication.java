package com.clouding.airline;

import java.util.Calendar;
import java.util.Date;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.clouding.airline.entities.Aeropuerto;
import com.clouding.airline.entities.Avion;
import com.clouding.airline.entities.Pasajero;
import com.clouding.airline.entities.Reserva;
import com.clouding.airline.entities.Vuelo;
import com.clouding.airline.repositories.AeropuertoRepository;
import com.clouding.airline.repositories.AvionRepository;
import com.clouding.airline.repositories.PasajeroRepository;
import com.clouding.airline.repositories.ReservaRepository;
import com.clouding.airline.repositories.VueloRepository;

@SpringBootApplication
public class AirlineApplication {

	public static void main(String[] args) { 
		SpringApplication.run(AirlineApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner loadData(AeropuertoRepository aeroRepository,
			VueloRepository vueloRepository,
			AvionRepository avionRepository,
			ReservaRepository reservaRepository,
			PasajeroRepository pasajeroRepository) {
	    return (args) -> {
	    	/*// IMPORTAR AEROPUERTOS DESDE EL FICHERO JSON REDUCIDO
	    	aeroRepository.deleteAllInBatch();
			ObjectMapper mapper = new ObjectMapper();
			Aeropuerto[] aeropuerto = mapper.readValue(new File("./airport-codes_json-min.json"), Aeropuerto[].class);
			aeroRepository.saveAll(Arrays.asList(aeropuerto));*/
	    	
	    	// Clean db
	    	avionRepository.deleteAllInBatch();
	    	vueloRepository.deleteAllInBatch();
	    	reservaRepository.deleteAllInBatch();
	    	pasajeroRepository.deleteAllInBatch();
	    	
	    	
			Aeropuerto a = aeroRepository.findById("00A");
			Aeropuerto a1 = aeroRepository.findById("00AK");
			
	    	Avion avion = new Avion("Avion1", 2, "Modelo1");
	    	avionRepository.save(avion);
	    	Calendar cal = Calendar.getInstance();
	    	cal.set(Calendar.YEAR, 2018);
	    	cal.set(Calendar.MONTH, Calendar.MAY);
	    	cal.set(Calendar.DAY_OF_MONTH, 17);
	    	Vuelo vuelo = new Vuelo("Vuelo1", new Date(), cal.getTime(), new Date(), 4, Vuelo.TipoVuelo.IDA, 50, avion, a, a1);
	    	cal.set(Calendar.YEAR, 2018);
	    	cal.set(Calendar.MONTH, Calendar.MAY);
	    	cal.set(Calendar.DAY_OF_MONTH, 22);
	    	Vuelo vuelo1 = new Vuelo("Vuelo2", new Date(), cal.getTime(), new Date(), 4, Vuelo.TipoVuelo.IDA, 80, avion, a, a1);
	    	vueloRepository.save(vuelo);
	    	vueloRepository.save(vuelo1);

	    	Pasajero pasajero = new Pasajero("11223344","Samuel","Andreo");
	    	pasajeroRepository.save(pasajero);
	    	

			Reserva reserva = new Reserva(1, false, new Date(), 0, true, vuelo, pasajero);
			reservaRepository.save(reserva);
			
			
			System.out.println(reservaRepository.embarcar(vueloRepository.findByNombre("Vuelo1").getId()));
			// Puedo hacer esto por el EAGLE en la relacion
			/*for (Reserva re : vueloRepository.findByNombre("Vuelo1").getReservas()) {
				System.out.println("Reservas de vuelo1--------------"+re.getPasajero().getNombre());
			}
			for (Vuelo v : vueloRepository.retrieveByFiltersAndFreePax("00A", "00AK",2,new Date())) {
				System.out.println("Q1. Vuelos con ida 00A y vuelta 00AK, 2 plazas disponibles y fecha de hoy ------------:" + v.getNombre());
			};
			for (Vuelo v : vueloRepository.retrieveByDateDiff(new Date())) {
				System.out.println("Q2. Vuelos 3 dias antes o despues de hoy ------------:" + v.getNombre());
			};
			for (Vuelo v: vueloRepository.findByFechaSalidaGreaterThan(new Date())) {
				System.out.println("Q3.1. Vuelos que aun no han ocurrido--------------"+v.getNombre());
			}
			for (Vuelo v: vueloRepository.findByFechaSalidaLessThan(new Date())) {
				System.out.println("Q3.2. Vuelos que ya han ocurrido--------------"+v.getNombre());
			}*/
	    };
	}
}
