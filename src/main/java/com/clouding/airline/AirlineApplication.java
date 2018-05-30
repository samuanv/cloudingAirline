package com.clouding.airline;

import java.io.File;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.clouding.airline.entities.Aeropuerto;
import com.clouding.airline.entities.Agencia;
import com.clouding.airline.entities.Avion;
import com.clouding.airline.entities.Pasajero;
import com.clouding.airline.entities.Reserva;
import com.clouding.airline.entities.Vuelo;
import com.clouding.airline.repositories.AeropuertoRepository;
import com.clouding.airline.repositories.AgenciaRepository;
import com.clouding.airline.repositories.AvionRepository;
import com.clouding.airline.repositories.PasajeroRepository;
import com.clouding.airline.repositories.ReservaRepository;
import com.clouding.airline.repositories.VueloRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootApplication
public class AirlineApplication {

	public static void main(String[] args) { 
		SpringApplication.run(AirlineApplication.class, args);
	}
	
	// @Bean
	public CommandLineRunner loadData(AeropuertoRepository aeroRepository,
			VueloRepository vueloRepository,
			AvionRepository avionRepository,
			ReservaRepository reservaRepository,
			PasajeroRepository pasajeroRepository,
			AgenciaRepository agenciaRepository) {
	    return (args) -> {
	    	// IMPORTAR AEROPUERTOS DESDE EL FICHERO JSON REDUCIDO
	    	aeroRepository.deleteAllInBatch();
			ObjectMapper mapper = new ObjectMapper();
			Aeropuerto[] aeropuerto = mapper.readValue(new File("./airport-codes_json-min.json"), Aeropuerto[].class);
			aeroRepository.saveAll(Arrays.asList(aeropuerto));
	    	
	    	// Clean db
	    	avionRepository.deleteAll();
	    	vueloRepository.deleteAllInBatch();
	    	reservaRepository.deleteAllInBatch();
	    	pasajeroRepository.deleteAllInBatch();
	    
	    	
			Aeropuerto a = aeroRepository.findById("00TA");
			Aeropuerto a1 = aeroRepository.findById("00SC");
			
	    	Avion avion = new Avion("Avion1", 20, "Modelo1");
	    	avionRepository.save(avion);
	    	Calendar cal = Calendar.getInstance();
	    	cal.set(Calendar.YEAR, 2018);
	    	cal.set(Calendar.MONTH, Calendar.MAY);
	    	cal.set(Calendar.DAY_OF_MONTH, 23);
	    	Vuelo vuelo = new Vuelo("Vuelo1", new Date(), cal.getTime(), new Date(), 4, Vuelo.TipoVuelo.IDA, 50, avion, a, a1);
	    	cal.set(Calendar.YEAR, 2018);
	    	cal.set(Calendar.MONTH, Calendar.MAY);
	    	cal.set(Calendar.DAY_OF_MONTH, 23);
	    	cal.set(Calendar.HOUR, 0);
	    	Vuelo vuelo1 = new Vuelo("Vuelo2", new Date(), cal.getTime(), new Date(), 4, Vuelo.TipoVuelo.IDA, 80, avion, a1, a);
	    	cal.set(Calendar.YEAR, 2018);
	    	cal.set(Calendar.MONTH, Calendar.MAY);
	    	cal.set(Calendar.DAY_OF_MONTH, 17);
	    	Vuelo vuelo2 = new Vuelo("Vuelo3", new Date(), cal.getTime(), new Date(), 4, Vuelo.TipoVuelo.IDA, 50, avion, a, a1);

	    	vueloRepository.save(vuelo);
	    	vueloRepository.save(vuelo1);
	    	vueloRepository.save(vuelo2);

	    	Pasajero pasajero = new Pasajero("11223344","Samuel","Andreo");
	    	pasajeroRepository.save(pasajero);
	    	
			Pasajero pasajero2 = new Pasajero("11244","Pedro","Andreo");
	    	pasajeroRepository.save(pasajero2);
	    	
	    	Agencia agencia = new Agencia("agencia1","agencia1");
	    	agenciaRepository.save(agencia);
	    	cal.set(Calendar.MONTH, Calendar.MAY);
	    	cal.set(Calendar.DAY_OF_MONTH, 17);
			Reserva reserva = new Reserva(1, false, cal.getTime(), 0, true, vuelo, pasajero, agencia);
	    	cal.set(Calendar.MONTH, Calendar.APRIL);
			Reserva reserva1 = new Reserva(1, true, cal.getTime(), 25, true, vuelo1, pasajero2, agencia);
	    	cal.set(Calendar.MONTH, Calendar.MARCH);
			Reserva reserva2 = new Reserva(1, false, cal.getTime(), 0, true, vuelo, pasajero, agencia);
	    	cal.set(Calendar.MONTH, Calendar.FEBRUARY);
			Reserva reserva3 = new Reserva(1, true, cal.getTime(), 2, true, vuelo, pasajero2, agencia);

			reservaRepository.saveAll(Arrays.asList(reserva,reserva1,reserva2,reserva3));
			
			/*Vuelo vEmbarque = vueloRepository.findByNombre("Vuelo2");
			List<Reserva> sinEmbarcar = reservaRepository.findByAsiento(0);
			// Si tiene 24 horas de antelación
			boolean moreThanDay = Math.abs(vEmbarque.getFechaSalida().getTime() - new Date().getTime()) > 24 * 60 * 60 * 1000L;
			if (!moreThanDay) {
				// Asigno asiento aleatorio a aquellos que tienen 0 
				reservaRepository.embarcar(vEmbarque.getId(), vEmbarque.getAvion().getPlazas());
				
				
				for (Reserva res1: sinEmbarcar) {
					Reserva res = reservaRepository.findById(res1.getId()).get();
					System.out.println("Q4. Embarcar y devolver pasajeros:" + res.getId() + "------------" + res.getAsiento());
				}				
			}*/
			
			/*for (TotalByMonth m: reservaRepository.findTotalByMonth()) {
				System.out.println("Q8. TotalByMonth:" + m.getMes() + "-" + m.getImporte());
				
			}*/
			
			
			/*for(Aeropuerto ae: aeroRepository.findMostProfitable()) {
				System.out.println("Q7. Destinos que más facturan------"+ ae.getMunicipio()+ "-" + ae.getId());
			}*/
			
			/* Q6
			for(Pasajero pas : pasajeroRepository.findByCountEmbarque()) {
				System.out.println("Q6. Ha utilizado más de dos veces el embarque prioritario" + pas.getNombre());
			};*/
			
	    	/* Q5	
			Reserva re1 = reservaRepository.findAll().get(0);
	    	boolean moreThanWeek = Math.abs(vEmbarque.getFechaSalida().getTime() - new Date().getTime()) > 168 * 60 * 60 * 1000L;
			if(!moreThanWeek) {
				// Cambiar pasajero
				re1.setPasajero(pasajero2);
				// Cancelar reserva
				re1.setActiva(false);
			}*/
			

			// reservaRepository.getFreeSeats(vueloRepository.findByNombre("Vuelo2").getId());*/
			
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
