package com.daos.EstacionAR.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.daos.EstacionAR.Entity.Recarga;
import com.daos.EstacionAR.Repository.IRecargaRepository;

@Service
public class RecargaService implements IRecargaService {
	
	@Autowired
	private IRecargaRepository repo;
	
	@Override
	public List<Recarga> getByDni(Long dni) {
		// TODO Auto-generated method stub
		
		return repo.findByDni(dni);
	}

	@Override
	public List<Recarga> getByPatente(String patente) {
		// TODO Auto-generated method stub
		List<Recarga> recargas = new ArrayList<Recarga>();
		return recargas;
	}

	@Override
	public List<Recarga> getByNroComercio(Long nroComercio) {
		// TODO Auto-generated method stub
		List<Recarga> recargas = new ArrayList<Recarga>();
		return recargas;
	}
	
	
	
}
