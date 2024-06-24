package com.daos.EstacionAR.Repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.daos.EstacionAR.Entity.AbonoComercio;

public interface IAbonoComercioRepository extends JpaRepository<AbonoComercio, Long> {

	List<AbonoComercio> findByFechaBetween(LocalDate fechaDesde, LocalDate fechaHasta);
}
