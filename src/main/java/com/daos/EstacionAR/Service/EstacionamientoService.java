package com.daos.EstacionAR.Service;

import com.daos.EstacionAR.Entity.Estacionamiento;
import com.daos.EstacionAR.Entity.User;
import com.daos.EstacionAR.Entity.Estacionamiento.Estado;
import com.daos.EstacionAR.Controller.EstacionamientoForm;
import com.daos.EstacionAR.Repository.IEstacionamientoRepository;
import com.daos.EstacionAR.Repository.IUserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EstacionamientoService implements IEstacionamientoService{

    @Autowired
    private IEstacionamientoRepository estacionamientoRepository;

    @Autowired
    private IUserRepository userRepository;

    @Override
    public Estacionamiento insertarEstacionamiento(Estacionamiento estacionamiento) {
        // Validar si el vehículo ya está estacionado
        Optional<Estacionamiento> estacionado = estacionamientoRepository.findEstacionadoByPatente(estacionamiento.getPatente());
        if (estacionado.isPresent()) {
            throw new IllegalStateException("El vehículo ya está estacionado.");
        }
        
        // Insertar nuevo registro de estacionamiento
        return estacionamientoRepository.save(estacionamiento);
    }

    @Override
    public Estacionamiento finalizarEstacionamiento(String patente, String password) {
        // Buscar el registro de estacionamiento
        Optional<Estacionamiento> optionalEstacionamiento = estacionamientoRepository.findEstacionadoByPatente(patente);
        if (optionalEstacionamiento.isPresent()) {
            Estacionamiento estacionamiento = optionalEstacionamiento.get();
            
             Long userId = estacionamiento.getUserId();
             Optional<User> userOptional = userRepository.findById(userId);
             if (userOptional.isPresent()) {
                 User user = userOptional.get();
                 if (!user.getContraseña().equals(password)) {
                     throw new IllegalStateException("Contraseña incorrecta.");
                 }
             } else {
                 throw new IllegalStateException("Usuario no encontrado.");
             }
            
            // Finalizar estacionamiento
            estacionamiento.setEstado(Estado.LIBRE);
            return estacionamientoRepository.save(estacionamiento);
        } else {
            throw new IllegalStateException("No hay registro de estacionamiento para el vehículo.");
        }
    }

    @Override
    public Estacionamiento consultarEstado(String patente) {
        Optional<Estacionamiento> optionalEstacionamiento = estacionamientoRepository.findByVehiculoPatente(patente);
        if (optionalEstacionamiento.isPresent()) {
            return optionalEstacionamiento.get();
        } else {
            throw new IllegalStateException("No hay registro de estacionamiento para el vehículo.");
        }
    }
}