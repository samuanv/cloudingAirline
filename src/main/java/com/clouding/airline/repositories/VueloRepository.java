package com.clouding.airline.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.clouding.airline.entities.Vuelo;

@Repository
public interface VueloRepository extends JpaRepository<Vuelo, Long> {
	Vuelo findByNombre( String nombre );
}