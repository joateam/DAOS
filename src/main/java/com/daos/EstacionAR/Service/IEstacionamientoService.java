package com.daos.EstacionAR.Service;

import java.util.Optional;

import com.daos.EstacionAR.Controller.EstacionamientoForm;
import com.daos.EstacionAR.Entity.Estacionamiento;

public interface IEstacionamientoService {

    void validarEstacionamiento(EstacionamientoForm estacionamientoForm) throws Exception;

    Estacionamiento validarYCrearEstacionamiento(EstacionamientoForm estacionamientoForm) throws Exception;

    Estacionamiento crearEstacionamiento(EstacionamientoForm estacionamientoForm);

    Optional<Estacionamiento> actualizarEstacionamiento(String patente, EstacionamientoForm estacionamientoForm);

    Optional<Estacionamiento> consultarEstacionamiento(String patente);
}