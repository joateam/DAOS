package com.daos.EstacionAR.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.daos.EstacionAR.Entity.AbonoComercio;
import com.daos.EstacionAR.Entity.Recarga;
import com.daos.EstacionAR.Repository.IAbonoComercioRepository;

@Service
public class AbonoComercioServiceImpl implements IAbonoComercioService {

	 @Autowired
	    private IAbonoComercioRepository abonoComercioRepository;

	    @Override
	    public Long obtenerCantidadRecargasPagas(Long comercioId, LocalDate fechaDesde, LocalDate fechaHasta) {
	        return abonoComercioRepository.countRecargasPagas(comercioId, fechaDesde, fechaHasta);
	    }

	    @Override
	    public Long obtenerCantidadRecargasImpagas(Long comercioId, LocalDate fechaDesde, LocalDate fechaHasta) {
	        return abonoComercioRepository.countRecargasImpagas(comercioId, fechaDesde, fechaHasta);
	    }

	    @Override
	    public Double obtenerSaldoRecargasImpagas(Long comercioId, LocalDate fechaDesde, LocalDate fechaHasta) {
	        return abonoComercioRepository.sumSaldoRecargasImpagas(comercioId, fechaDesde, fechaHasta);
	    }

	    @Override
	    public List<Long> obtenerTodosLosIdsDeComercio() {
	        return abonoComercioRepository.findAllComercioIds();
	    }
}
