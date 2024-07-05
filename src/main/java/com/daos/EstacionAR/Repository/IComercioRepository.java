package com.daos.EstacionAR.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.daos.EstacionAR.Entity.Comercio;

public interface IComercioRepository extends JpaRepository<Comercio, Long> {
    	Optional<Comercio> findByComercioNr(Long nroComercio);
}
