package com.daos.EstacionAR.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.daos.EstacionAR.Entity.Recarga;

@Repository
public interface IRecargaRepository extends JpaRepository<Recarga, Long>{
	List<Recarga> findByDni(Integer dni);
	List<Recarga> findByPatente(String patente);
	List<Recarga> findByNroComercio(Long nroComercio);
	Optional<Recarga> findById(Long id);
	
	
}
