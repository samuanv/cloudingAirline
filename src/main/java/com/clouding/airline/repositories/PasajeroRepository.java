package com.clouding.airline.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.clouding.airline.entities.Pasajero;

@Repository
public interface PasajeroRepository  extends JpaRepository<Pasajero, String> {

}
