package com.clouding.airline.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.clouding.airline.entities.Aeropuerto;

@Repository
public interface AeropuertoRepository extends JpaRepository<Aeropuerto, Long> {
	Aeropuerto findById(String id);
	
	@Query(value="SELECT aeropuertos.*FROM\r\n" + 
			"aeropuertos, vuelos \r\n" + 
			"WHERE aeropuertos.id_aeropuerto = vuelos.aeropuerto_id_destino\r\n" + 
			"AND vuelos.fecha_salida BETWEEN DATE_SUB(NOW(), INTERVAL 31 DAY) AND NOW()\r\n" + 
			"group by tarifa, aeropuertos.id_aeropuerto\r\n" + 
			"order by sum(tarifa) DESC", nativeQuery = true)
	List<Aeropuerto> findMostProfitable();
}