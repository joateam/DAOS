package com.daos.EstacionAR.Service;

import com.daos.EstacionAR.Controller.EstacionamientoForm;
import com.daos.EstacionAR.Entity.Estacionamiento;
import com.daos.EstacionAR.Repository.IEstacionamientoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EstacionamientoService {

     @Autowired
    private IEstacionamientoRepository estacionamientoRepository;

    // Interactua con el repositorio para validar la contraseña del usuario y asegurarse de que no haya operaciones duplicadas
    public void validarEstacionamiento(EstacionamientoForm estacionamientoForm) throws Exception {
        // validacion
    }

    // Inicia un nuevo registro de estacionamiento
    public Estacionamiento crearEstacionamiento(EstacionamientoForm estacionamientoForm) {

        // Instancia
        Estacionamiento nuevoEstacionamiento = new Estacionamiento();

        // Asignando valores del formulario a la entidad 
        nuevoEstacionamiento.setPatente(estacionamientoForm.getPatente());
        nuevoEstacionamiento.setPasswordUser(estacionamientoForm.getPasswordUser());
        nuevoEstacionamiento.setEstado(estacionamientoForm.getEstado() == EstacionamientoForm.EstadoEstacionamiento.ESTACIONADO);

        // guardando en la base de datos mediante el Repository
        return estacionamientoRepository.save(nuevoEstacionamiento);
    }

    // Interactura con el repositorio para actualizar un registro de estacionamiento existente
    public Optional<Estacionamiento> actualizarEstacionamiento(String patente, EstacionamientoForm estacionamientoForm) {
        // lógica
        return Optional.empty();
    }

    // Cnsultar un registro de estacionamiento por patente
    public Optional<Estacionamiento> consultarEstacionamiento(String patente) {
        // lógica
        return Optional.empty();
    }
}
