package com.clouding.airline.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.clouding.airline.entities.Avion;

@Repository
public interface AvionRepository extends JpaRepository<Avion, Long> {

}