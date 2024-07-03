package com.daos.EstacionAR.Service;

import com.daos.EstacionAR.Entity.Comercio;
import java.util.List;

public interface IComercioService {
    Comercio save(Comercio comercio);
    Comercio findById(Long id);
    List<Comercio> findAll();
    Comercio update(Long id, Comercio comercio);
    void delete(Long id);
}
