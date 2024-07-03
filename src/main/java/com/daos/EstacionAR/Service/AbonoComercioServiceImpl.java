package com.daos.EstacionAR.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.daos.EstacionAR.Entity.AbonoComercio;
import com.daos.EstacionAR.Entity.Recarga;
import com.daos.EstacionAR.Repository.IAbonoComercioRepository;
import com.daos.EstacionAR.Repository.IRecargaParaAbonoComercioRepository;

import jakarta.transaction.Transactional;

@Service
public class AbonoComercioServiceImpl implements IAbonoComercioService {

	 @Autowired
	    private IRecargaParaAbonoComercioRepository recargaAbonoComercioRepository;
	    
	   @Autowired
	    private IAbonoComercioRepository abonoComercioRepository;

	    @Override
	    public Long obtenerCantidadRecargasPagas(Long comercioId, LocalDate fechaDesde, LocalDate fechaHasta) {
	        return recargaAbonoComercioRepository.countRecargasPagas(comercioId, fechaDesde, fechaHasta);
	    }

	    @Override
	    public Long obtenerCantidadRecargasImpagas(Long comercioNr, LocalDate fechaDesde, LocalDate fechaHasta) {
	        return recargaAbonoComercioRepository.countRecargasImpagas(comercioNr, fechaDesde, fechaHasta);
	    }

	    @Override
	    public Float obtenerSaldoRecargasImpagas(Long comercioId, LocalDate fechaDesde, LocalDate fechaHasta) {
	        return recargaAbonoComercioRepository.sumSaldoRecargasImpagas(comercioId, fechaDesde, fechaHasta);
	    }
	    
	    @Override
	    public Float obtenerSaldoRecargasPagas(Long comercioId, LocalDate fechaDesde, LocalDate fechaHasta) {
	        return recargaAbonoComercioRepository.sumSaldoRecargasPagas(comercioId, fechaDesde, fechaHasta);
	    }

	    @Override
	    public List<Long> obtenerTodosLosNrsDeComercio() {
	        return recargaAbonoComercioRepository.findAllComercioNrs();
	    }

		@Override
		public void guardarAbonoComercio(AbonoComercio ac) {
			abonoComercioRepository.save(ac);
		}
	    
		@Override
	    @Transactional
	    public void actualizarRecargasAAbonado(Long comercioNro, LocalDate fechaDesde, LocalDate fechaHasta) {
	        recargaAbonoComercioRepository.updateRecargasToAbonado(comercioNro, fechaDesde, fechaHasta);
	    }
}
