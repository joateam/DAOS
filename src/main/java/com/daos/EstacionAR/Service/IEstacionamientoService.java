package com.daos.EstacionAR.Service;

import com.daos.EstacionAR.Entity.Estacionamiento;

public interface IEstacionamientoService {

    Estacionamiento insertarEstacionamiento(Estacionamiento estacionamiento);
    Estacionamiento finalizarEstacionamiento(String patente, String password);
    Estacionamiento consultarEstado(String patente);
}