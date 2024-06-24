package com.daos.EstacionAR.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.daos.EstacionAR.Entity.AbonoComercio;
import com.daos.EstacionAR.Repository.IAbonoComercioRepository;

@Service
public class AbonoComercioServiceImpl implements IAbonoComercioService {


	@Autowired
	private IAbonoComercioRepository repo;
	
	@Override
	public List<AbonoComercio> getAll() {
		return repo.findAll();
	}

	@Override
	public Optional<AbonoComercio> getById(Long id) {
		return repo.findById(id);
	}

	 @Override
	    public List<AbonoComercio> findByFechaBetween(LocalDate fechaDesde, LocalDate fechaHasta) {
	        return repo.findByFechaBetween(fechaDesde, fechaHasta);
	    }
	 
	 @Override
	 public void insert(AbonoComercio abonoComercio) {
	        repo.save(abonoComercio);
	    }
}
