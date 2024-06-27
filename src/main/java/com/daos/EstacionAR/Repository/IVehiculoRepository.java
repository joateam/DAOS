package com.daos.EstacionAR.Repository;

import com.daos.EstacionAR.Entity.Vehiculo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IVehiculoRepository extends JpaRepository<Vehiculo, String> {
