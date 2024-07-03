package com.daos.EstacionAR.Service;

import com.daos.EstacionAR.Entity.Comercio;
import com.daos.EstacionAR.Repository.IComercioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ComercioServiceImpl implements IComercioService {

    @Autowired
    private IComercioRepository comercioRepository;

    @Override
    public Comercio save(Comercio comercio) {
        return comercioRepository.save(comercio);
    }

    @Override
    public Comercio findById(Long id) {
        Optional<Comercio> comercio = comercioRepository.findById(id);
        return comercio.orElse(null);
    }

    @Override
    public List<Comercio> findAll() {
        return comercioRepository.findAll();
    }

    @Override
    public Comercio update(Long id, Comercio comercio) {
        Optional<Comercio> existingComercio = comercioRepository.findById(id);
        if (existingComercio.isPresent()) {
            Comercio updatedComercio = existingComercio.get();
            updatedComercio.setComercioNr(comercio.getComercioNr());
            updatedComercio.setCuit(comercio.getCuit());
            updatedComercio.setDireccion(comercio.getDireccion());
            updatedComercio.setEstado(comercio.getEstado());
            return comercioRepository.save(updatedComercio);
        } else {
            return null;
        }
    }

    @Override
    public void delete(Long id) {
        comercioRepository.deleteById(id);
    }
}
