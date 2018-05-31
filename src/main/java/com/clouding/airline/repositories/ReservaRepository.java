package com.clouding.airline.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.clouding.airline.entities.Agencia;
import com.clouding.airline.entities.Reserva;

@Repository
public interface ReservaRepository extends JpaRepository<Reserva, Long> {
	@Transactional
	Reserva save(Reserva r);
	
<<<<<<< HEAD
	/*Q3 */
//	@Query("select r " + 
//			"from Reserva r \r\n" + 
//			"WHERE r.vuelo.fechaSalida > p.dni AND r.embarquePrioritario = 1 \r\n")
//	List<Reserva> findReservasRealizadas(@Param("agencia") Long id, @Param("today") Date date);
=======
	/*Q3.1 */
	@Query("select r " + 
			"from Reserva r \r\n" + 
			"WHERE r.vuelo.fechaSalida > :today AND r.agencia = :agencia \r\n")
	List<Reserva> findReservasRealizadasPendientes(@Param("agencia") Agencia agencia, @Param("today") Date today);
	/*Q3.2 */
	@Query("select r " + 
			"from Reserva r \r\n" + 
			"WHERE r.vuelo.fechaSalida < :today AND r.agencia = :agencia \r\n")
	List<Reserva> findReservasRealizadasFinalizadas(@Param("agencia") Agencia agencia, @Param("today") Date today);
	
>>>>>>> 9cc84312ddfce382f52725c8397c7ff4f6cbdf7f
	/*Q8 */
	@Query (value = "SELECT MONTH(reservas.fecha_pago) as mes, sum(tarifa) as importe \r\n" + 
			"FROM reservas, vuelos\r\n" + 
			"WHERE reservas.vuelo_id_vuelo = vuelos.id_vuelo\r\n" + 
			"AND reservas.fecha_pago BETWEEN DATE_SUB(NOW(), INTERVAL 6 MONTH) AND NOW()\r\n" + 
			"group by MONTH(reservas.fecha_pago)", nativeQuery = true)
	List<TotalByMonth> findTotalByMonth();
	static interface TotalByMonth {
	    int getImporte();
	    int getMes();
	}
	
	/*Q4 */
	@Query("SELECT r FROM Reserva r WHERE r.asiento = 0 AND r.vuelo.id = :vueloId AND r.agencia.id = :agenciaId")
	List<Reserva> retrieveByAsientoAndFecha(@Param("agenciaId") Long agenciaId, @Param("vueloId") Long vueloId);
	
	/*Q4.1*/
	@Transactional
	@Modifying
	@Query (value = "UPDATE reservas SET reservas.asiento = (SELECT FLOOR(RAND() * :plazas) \r\n" + 
			"AS random_num FROM (SELECT * FROM reservas) as re \r\n" + 
			"WHERE 'random_num' NOT IN (SELECT asiento FROM (SELECT * FROM reservas) as r WHERE vuelo_id_vuelo = :vueloId) LIMIT 1)\r\n" + 
			" WHERE reservas.asiento = 0", nativeQuery = true)
	int embarcar(@Param("vueloId") Long vueloId, @Param("plazas") int plazas );
	
	/*Q4.2*/
	@Query(value = "select (case when tt.min_asiento > 1 then 1\r\n" + 
			"             else min(reservas.asiento) + 1\r\n" + 
			"        end)\r\n" + 
			"from reservas cross join\r\n" + 
			"     (select min(asiento) as min_asiento from reservas where reservas.vuelo_id_vuelo = :vueloId) tt\r\n" + 
			"where not exists (select 1 from reservas t2 where t2.asiento = reservas.asiento + 1)", nativeQuery = true)
	int getFreeSeats(@Param("vueloId") Long vueloId);
}