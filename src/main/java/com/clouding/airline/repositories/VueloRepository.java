package com.clouding.airline.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.clouding.airline.entities.Vuelo;

@Repository
public interface VueloRepository extends JpaRepository<Vuelo, Long> {
	List<Vuelo> findByNombre( String nombre );
	
	@Query("SELECT v FROM Vuelo v WHERE LOWER(v.nombre) = LOWER(:nombre)")
	Vuelo retrieveByNombre(@Param("nombre") String nombre);
}