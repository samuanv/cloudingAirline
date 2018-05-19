package com.clouding.airline.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.clouding.airline.entities.Reserva;
import com.clouding.airline.entities.Vuelo;

@Repository
public interface VueloRepository extends JpaRepository<Vuelo, Long> {
	Vuelo findByNombre( String nombre );
	
	/* Q1 */
	@Query("SELECT v FROM Vuelo v "
			+ "WHERE v.aeropuertoOrigen.id = :idOrigen AND v.aeropuertoDestino.id = :idDestino"
			+ " AND v.reservas.size + :plazas < v.avion.plazas AND v.fechaSalida = :fecha")
	List<Vuelo> retrieveByFiltersAndFreePax(@Param("idOrigen") String idOrigen,
			@Param("idDestino") String idDestino,
			@Param("plazas") int plazas,
			@Param("fecha") Date fecha);
	
	/* Q2: Necesaria query nativa, hql no acepta opoeraciones */
	@Query(value="SELECT * FROM vuelos v "
			+ "WHERE v.fecha_salida BETWEEN DATE_SUB(:fecha, INTERVAL 3 DAY) AND DATE_ADD(:fecha, INTERVAL 3 DAY) "
			+ "ORDER BY v.tarifa DESC", nativeQuery = true)
	List<Vuelo> retrieveByDateDiff(@Param("fecha") Date fecha);
	
	/* Q3.1 */
	List<Vuelo> findByFechaSalidaGreaterThan(Date fecha);
	
	/* Q3.2 */
	List<Vuelo> findByFechaSalidaLessThan(Date fecha);
	

}