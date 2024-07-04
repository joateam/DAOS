package com.daos.EstacionAR.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.daos.EstacionAR.Entity.Comercio;

public interface IComercioRepository extends JpaRepository<Comercio, Long> {
    
}
