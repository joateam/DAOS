package com.daos.EstacionAR.Service;


import java.util.List;


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
		
		return repo.findByPatente(patente);
	}

	@Override
	public List<Recarga> getByNroComercio(Long nroComercio) {
		return repo.findByNroComercio(nroComercio);
	}

	@Override
	public List<Recarga> getALL() {
			
		return repo.findAll();
	}

	@Override
	public void Recargar(Recarga recarga) {
		repo.save(recarga);
		
	}

	
	
	
	
	
}
