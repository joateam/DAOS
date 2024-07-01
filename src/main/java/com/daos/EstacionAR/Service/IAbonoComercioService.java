package com.daos.EstacionAR.Service;

import java.time.LocalDate;
import java.util.List;

public interface IAbonoComercioService {

	 Long obtenerCantidadRecargasPagas(Long comercioId, LocalDate fechaDesde, LocalDate fechaHasta);

	 Long obtenerCantidadRecargasImpagas(Long comercioId, LocalDate fechaDesde, LocalDate fechaHasta);

	 Double obtenerSaldoRecargasImpagas(Long comercioId, LocalDate fechaDesde, LocalDate fechaHasta);
	 
	 List<Long> obtenerTodosLosIdsDeComercio();
	
}
