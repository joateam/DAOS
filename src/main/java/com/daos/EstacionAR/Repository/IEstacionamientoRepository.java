package com.daos.EstacionAR.Repository;

import com.daos.EstacionAR.Entity.Estacionamiento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IEstacionamientoRepository extends JpaRepository<Estacionamiento, Long> {
    
    @Query("SELECT e FROM Estacionamiento e WHERE e.vehiculo.patente = ?1 AND e.estado = 'ESTACIONADO'")
    Optional<Estacionamiento> findEstacionadoByPatente(String patente);

    Optional<Estacionamiento> findByVehiculoPatenteAndEstado(String patente, String estado);

    Optional<Estacionamiento> findByVehiculoPatente(String patente);

}

