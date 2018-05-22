package com.clouding.airline.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.clouding.airline.entities.Pasajero;

@Repository
public interface PasajeroRepository  extends JpaRepository<Pasajero, String> {

	/* Q6 */
	@Query("select p " + 
			"from Pasajero p, Reserva r \r\n" + 
			"WHERE r.pasajero.id = p.dni AND r.embarquePrioritario = 1 \r\n" + 
			"GROUP BY r.embarquePrioritario, r.pasajero.id\r\n" + 
			"HAVING COUNT(r.embarquePrioritario)>1")
	List<Pasajero> findByCountEmbarque();
}
