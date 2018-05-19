package com.clouding.airline.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.clouding.airline.entities.Reserva;

@Repository
public interface ReservaRepository extends JpaRepository<Reserva, Long> {
	@Modifying
	@Query("update Reserva as r SET r.asiento = 15 WHERE r.asiento = 0 AND r.vuelo.id = :id")
	int embarcar(@Param("id") Long id);
}