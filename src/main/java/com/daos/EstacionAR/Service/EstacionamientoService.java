package com.daos.EstacionAR.Service;

import com.daos.EstacionAR.Entity.Estacionamiento;
import com.daos.EstacionAR.Entity.User;
import com.daos.EstacionAR.Controller.EstacionamientoForm;
import com.daos.EstacionAR.Repository.IEstacionamientoRepository;
import com.daos.EstacionAR.Repository.IUserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EstacionamientoService {

    @Autowired
    private IEstacionamientoRepository estacionamientoRepository;

    @Autowired
    private IUserRepository userRepository;

    // Método para validar la contraseña del usuario antes de cualquier operación
    public void validarEstacionamiento(EstacionamientoForm estacionamientoForm) throws Exception {
        // Buscar el usuario por la patente del vehículo
        User user = userRepository.findByPatente(estacionamientoForm.getPatente())
                .orElseThrow(() -> new Exception("El usuario no existe."));

        // Validar la contraseña proporcionada con la almacenada
        if (!esPasswordValido(estacionamientoForm.getPasswordUser(), user.getContraseña())) {
            throw new Exception("La contraseña del usuario no es válida.");
        }
    }

    // Ejemplo para validar la contraseña (comparación de texto)
    private boolean esPasswordValido(String providedPassword, String storedPassword) {
        return providedPassword.equals(storedPassword);
    }

    // Valida y crea un nuevo registro de estacionamiento
    public Estacionamiento validarYCrearEstacionamiento(EstacionamientoForm estacionamientoForm) throws Exception {
        validarEstacionamiento(estacionamientoForm);
        return crearEstacionamiento(estacionamientoForm);
    }

    // Inicia un nuevo registro de estacionamiento
    public Estacionamiento crearEstacionamiento(EstacionamientoForm estacionamientoForm) {
        // Instancia
        Estacionamiento nuevoEstacionamiento = new Estacionamiento();

        // Asignando valores del formulario a la entidad
        nuevoEstacionamiento.setPatente(estacionamientoForm.getPatente());
        nuevoEstacionamiento.setContraseña(estacionamientoForm.getPasswordUser());
        nuevoEstacionamiento.setEstado(
                Estacionamiento.Estado.valueOf(estacionamientoForm.getEstado().name()));

        // guardando en la base de datos mediante el Repository
        return estacionamientoRepository.save(nuevoEstacionamiento);
    }

    // Interactua con el repositorio para actualizar un registro de estacionamiento
    // existente
    public Optional<Estacionamiento> actualizarEstacionamiento(String patente,
            EstacionamientoForm estacionamientoForm) {

        // Validar la contraseña del usuario antes de cualquier operación
        try {
            validarEstacionamiento(estacionamientoForm);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error al validar la contraseña: " + e.getMessage());
        }

        // Busca el estacionamiento por la patente en el repository
        Optional<Estacionamiento> estacionamientoOptional = estacionamientoRepository.findByPatente(patente);

        // Verifica si el estacionamiento existe
        if (estacionamientoOptional.isPresent()) {
            Estacionamiento estacionamiento = estacionamientoOptional.get();

            // Actualizar el estado del estacionamiento según el formulario
            estacionamiento.setEstado(Estacionamiento.Estado.valueOf(estacionamientoForm.getEstado().name()));

            // Guardar el estacionamiento actualizado en la base de datos y devolver la
            // entidad actualizada
            return Optional.of(estacionamientoRepository.save(estacionamiento));
        } else {
            // Si no se encuentra el estacionamiento, devolver Optional.empty()
            return Optional.empty();
        }
    }

    // Consulta un registro de estacionamiento por patente
    public Optional<Estacionamiento> consultarEstacionamiento(String patente) {
        // Buscar el estacionamiento por la patente en el repository
        return estacionamientoRepository.findByPatente(patente);
    }
}
