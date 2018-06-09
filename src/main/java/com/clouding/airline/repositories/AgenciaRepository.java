package com.clouding.airline.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.clouding.airline.entities.Agencia;
import sun.security.util.Password;

@Repository
public interface AgenciaRepository extends JpaRepository<Agencia, Long> {
     Agencia findByUsernameAndPassword(String username, String password);
}
