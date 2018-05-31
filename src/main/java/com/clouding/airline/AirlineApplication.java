package com.clouding.airline;

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
			PasajeroRepository pasajeroRepository,
			AgenciaRepository agenciaRepository) {
	    return (args) -> {
	    	// IMPORTAR AEROPUERTOS DESDE EL FICHERO JSON REDUCIDO
	    	/*aeroRepository.deleteAllInBatch();
	    	ObjectMapper mapper = new ObjectMapper();
			Aeropuerto[] aeropuerto = mapper.readValue(new File("./airport-codes_json-min.json"), Aeropuerto[].class);
			aeroRepository.saveAll(Arrays.asList(aeropuerto));*/
			

	    	// Clean db
	    	avionRepository.deleteAll();
	    	vueloRepository.deleteAllInBatch();
	    	reservaRepository.deleteAllInBatch();
	    	pasajeroRepository.deleteAllInBatch();
	    	
	    	//AEROPUERTOS
			Aeropuerto a = aeroRepository.findById("00TA");
			Aeropuerto a1 = aeroRepository.findById("00SC");
			
			//AVIONES
	    	Avion avion = new Avion("Real Madrid Plane", 100, "Boeing 13");
	    	avionRepository.save(avion);
	    	Avion avion1 = new Avion("TWCAM Plane", 10, "Boeing 10");
	    	avionRepository.save(avion);
	    
	    	
	    	
	    	// VUELOS
	    	Calendar fembarque = createNewDate(30,05,2018,22,15); 
	    	Calendar fsalida = createNewDate(30,05,2018,22,30);
	    	Calendar freserva = createNewDate(01,01,2018,11,20);
	    	
	    	Vuelo vuelo = new Vuelo("E557170", freserva.getTime(), fembarque.getTime(), fsalida.getTime(), 4, Vuelo.TipoVuelo.IDA, 50, avion, a, a1);
	    	
	    	fembarque = createNewDate(25,3,2018,19,40); 
	    	fsalida = createNewDate(25,3,2018,19,55);
	    	freserva = createNewDate(25,12,2017,00,01);
	    	
	    	Vuelo vuelo1 = new Vuelo("E557171", freserva.getTime() , fembarque.getTime(), fsalida.getTime(), 4, Vuelo.TipoVuelo.IDA, 80, avion1, a1, a);
	    	
	    	
	    	// mismo dia, aviones dieferentes
	    	
	    	fembarque = createNewDate(2,1,2018,12,00);
	    	fsalida = createNewDate(2,1,2018,12,15);
	    	freserva = createNewDate(8,12,2017,00,01);
	    
	    	
	    	Vuelo vuelo2 = new Vuelo("E557169", freserva.getTime(), fembarque.getTime(), fsalida.getTime(), 4, Vuelo.TipoVuelo.IDA, 50, avion, a, a1);
	    	
	    	fembarque = createNewDate(5,10,2018,12,00);
	    	fsalida = createNewDate(5,10,2018,12,15);
	    	freserva = createNewDate(8,5,2017,00,01);
	    
	    	
	    	Vuelo vuelo3 = new Vuelo("E550011", freserva.getTime(), fembarque.getTime(), fsalida.getTime(), 4, Vuelo.TipoVuelo.IDA_VUELTA, 80, avion1, a, a1);
	    	
	    	
	    	fembarque = createNewDate(5,10,2018,16,00);
	    	fsalida = createNewDate(5,10,2018,16,15);
	    	freserva = createNewDate(8,5,2017,12,00);
	    	
	    	
	    	Vuelo vuelo4 = new Vuelo("E550022", freserva.getTime(), fembarque.getTime(), fsalida.getTime(), 4, Vuelo.TipoVuelo.IDA_VUELTA, 50, avion, a, a1);
	    	
	    	// 3 dias antes/despues
	    	
	    	fembarque = createNewDate(2,6,2018,15,00);
	    	fsalida = createNewDate(2,6,2018,15,15);
	    	freserva = createNewDate(12,3,2018,22,25);
	    	
	    	Vuelo vuelo5 = new Vuelo("E550033", freserva.getTime(), fembarque.getTime(), fsalida.getTime(), 4, Vuelo.TipoVuelo.IDA_VUELTA, 70, avion, a1, a);
	    	
	    	fembarque = createNewDate(3,6,2018,15,00);
	    	fsalida = createNewDate(3,6,2018,15,15);
	    	freserva = createNewDate(12,3,2018,22,25);
	    	
	    	Vuelo vuelo6 = new Vuelo("E550044", freserva.getTime(), fembarque.getTime(), fsalida.getTime(), 4, Vuelo.TipoVuelo.IDA_VUELTA, 40, avion, a1, a);
	    	
	    	fembarque = createNewDate(31,5,2018,15,00);
	    	fsalida = createNewDate(31,5,2018,15,15);
	    	freserva = createNewDate(12,3,2018,22,25);
	    	
	    	Vuelo vuelo7 = new Vuelo("E550055", freserva.getTime(), fembarque.getTime(), fsalida.getTime(), 4, Vuelo.TipoVuelo.IDA_VUELTA, 100, avion, a1, a);

	    	vueloRepository.save(vuelo);
	    	vueloRepository.save(vuelo1);
	    	vueloRepository.save(vuelo2);
	    	vueloRepository.save(vuelo3);
	    	vueloRepository.save(vuelo4);
	    	vueloRepository.save(vuelo5);
	    	vueloRepository.save(vuelo6);
	    	vueloRepository.save(vuelo7);
	    	
	    	
	    	// PASAJEROS
	    	Pasajero pasajero = new Pasajero("11223344","Samuel","Andreo");
	    	pasajeroRepository.save(pasajero);
	    	
			Pasajero pasajero1 = new Pasajero("22441133","Pedro","Andreo");
	    	pasajeroRepository.save(pasajero1);
	    	
	    	Pasajero pasajero2 = new Pasajero("113332244","Mateo","Bernal");
	    	pasajeroRepository.save(pasajero2);
	    	
	    	Pasajero pasajero3 = new Pasajero("44332211","Eduardo","Fonte");
	    	pasajeroRepository.save(pasajero3);
	    	
	    	// AGENCIAS
	    	Agencia agencia = new Agencia("Burjassot vuelos","agencia1", "agencia1");
	    	agenciaRepository.save(agencia);
	    	
	    	Agencia agencia1 = new Agencia("Totana vuelos","agencia2", "agencia2");
	    	agenciaRepository.save(agencia1);
	    	
	    	Agencia agencia2 = new Agencia("Elche airlines internacional","agencia3", "agencia3");
	    	agenciaRepository.save(agencia2);
	 
	    	
	    	// RESERVAS
	    	Calendar fpago = createNewDate(17,5,2018,17,30);
			Reserva reserva = new Reserva(1, false, fpago.getTime(), 0, true, vuelo, pasajero1, agencia1);
			fpago = createNewDate(9,4,2018,17,00);
			Reserva reserva1 = new Reserva(1, true, fpago.getTime(), 25, true, vuelo1, pasajero, agencia1);
			fpago = createNewDate(7,3,2018,16,30);
			Reserva reserva2 = new Reserva(1, false, fpago.getTime(), 0, true, vuelo, pasajero1, agencia1);
			fpago = createNewDate(1,2,2018,7,30);
			Reserva reserva3 = new Reserva(1, true, fpago.getTime(), 2, true, vuelo, pasajero, agencia1);
			
			// Nos vamos de viaje en el TWCAM plane, Samuel en vip, el siempre va en vip.
			fpago = createNewDate(6,2,2018,7,30);
			Reserva reserva4 = new Reserva(1, false, fpago.getTime(), 1, true, vuelo1, pasajero2, agencia);
			fpago = createNewDate(6,2,2018,7,30);
			Reserva reserva5 = new Reserva(1, false, fpago.getTime(), 3, true, vuelo1, pasajero3, agencia);
			fpago = createNewDate(6,2,2018,7,30);
			Reserva reserva6 = new Reserva(1, true, fpago.getTime(), 4, true, vuelo1, pasajero, agencia);

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
	public Calendar createNewDate(int day, int month, int year, int hour, int minute) {
		Calendar cal = Calendar.getInstance();
    	cal.set(Calendar.YEAR, year);
    	cal.set(Calendar.MONTH, month);
    	cal.set(Calendar.DAY_OF_MONTH, day);
    	cal.set(Calendar.HOUR_OF_DAY, hour);
    	cal.set(Calendar.MINUTE, minute);
    	return cal;
	}
}
