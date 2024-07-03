package com.daos.EstacionAR.Repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.daos.EstacionAR.Entity.Recarga;

import jakarta.transaction.Transactional;

public interface IRecargaParaAbonoComercioRepository extends JpaRepository<Recarga, Long> {

	 @Query("SELECT COUNT(r) FROM Recarga r WHERE r.nroComercio = :comercioId AND r.fecha BETWEEN :fechaDesde AND :fechaHasta AND r.abonado = 1")
	    Long countRecargasPagas(@Param("comercioId") Long comercioId, @Param("fechaDesde") LocalDate fechaDesde, @Param("fechaHasta") LocalDate fechaHasta);

	    @Query("SELECT COUNT(r) FROM Recarga r WHERE r.nroComercio = :comercioId AND r.fecha BETWEEN :fechaDesde AND :fechaHasta AND r.abonado = 0")
	    Long countRecargasImpagas(@Param("comercioId") Long comercioId, @Param("fechaDesde") LocalDate fechaDesde, @Param("fechaHasta") LocalDate fechaHasta);

	    @Query("SELECT SUM(r.importe) FROM Recarga r WHERE r.nroComercio = :comercioId AND r.fecha BETWEEN :fechaDesde AND :fechaHasta AND r.abonado = 0")
	    Float sumSaldoRecargasImpagas(@Param("comercioId") Long comercioId, @Param("fechaDesde") LocalDate fechaDesde, @Param("fechaHasta") LocalDate fechaHasta);
	    
	    @Query("SELECT SUM(r.importe) FROM Recarga r WHERE r.nroComercio = :comercioId AND r.fecha BETWEEN :fechaDesde AND :fechaHasta AND r.abonado = 1")
	    Float sumSaldoRecargasPagas(@Param("comercioId") Long comercioId, @Param("fechaDesde") LocalDate fechaDesde, @Param("fechaHasta") LocalDate fechaHasta);
	    
	    @Query("SELECT DISTINCT r.nroComercio FROM Recarga r ORDER BY r.nroComercio")
	    List<Long> findAllComercioNrs();
	    
	    @Modifying
	    @Transactional
	    @Query("UPDATE Recarga r SET r.abonado = 1 WHERE r.nroComercio = :comercioId AND r.fecha BETWEEN :fechaDesde AND :fechaHasta AND r.abonado = 0")
	    void updateRecargasToAbonado(@Param("comercioId") Long comercioId, @Param("fechaDesde") LocalDate fechaDesde, @Param("fechaHasta") LocalDate fechaHasta);
}
