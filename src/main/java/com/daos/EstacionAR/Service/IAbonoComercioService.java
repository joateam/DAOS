package com.daos.EstacionAR.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import com.daos.EstacionAR.Entity.AbonoComercio;

public interface IAbonoComercioService {

	public List<AbonoComercio> getAll();

	public Optional<AbonoComercio> getById(Long id);
	
	public void insert(AbonoComercio ac) throws Exception;
	
	public List<AbonoComercio> findByFechaBetween(LocalDate fechaDesde, LocalDate fechaHasta);
}
