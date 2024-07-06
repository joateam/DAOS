package com.daos.EstacionAR.Service;

import java.time.LocalDate;
import java.util.List;

import com.daos.EstacionAR.Entity.AbonoComercio;
import com.daos.EstacionAR.Entity.Comercio;

public interface IAbonoComercioService {

	 Long obtenerCantidadRecargasPagas(Long comercioId, LocalDate fechaDesde, LocalDate fechaHasta);

	 Long obtenerCantidadRecargasImpagas(Long comercioId, LocalDate fechaDesde, LocalDate fechaHasta);

	 Float obtenerSaldoRecargasImpagas(Long comercioId, LocalDate fechaDesde, LocalDate fechaHasta);
	 
	 Float obtenerSaldoRecargasPagas(Long comercioId, LocalDate fechaDesde, LocalDate fechaHasta);
	 
	 List<Long> obtenerTodosLosNrsDeComercio();
	
	 void guardarAbonoComercio(AbonoComercio ac);
	 
	 void actualizarRecargasAAbonado(Long comercioNro, LocalDate fechaDesde, LocalDate fechaHasta);
<<<<<<< HEAD
=======
	 
	 
>>>>>>> mat-branch
}
