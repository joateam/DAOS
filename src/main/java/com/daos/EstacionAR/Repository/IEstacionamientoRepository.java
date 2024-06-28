package com.daos.EstacionAR.Repository;

import com.daos.EstacionAR.Entity.Estacionamiento;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface IEstacionamientoRepository extends JpaRepository<Estacionamiento, Long> {
    Optional<Estacionamiento> findByPatente(String patente);
}

