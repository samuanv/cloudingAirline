package com.clouding.airline.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.clouding.airline.entities.Agencia;

@Repository
public interface AgenciaRepository extends JpaRepository<Agencia, Long> {

}
