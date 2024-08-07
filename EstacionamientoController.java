package com.daos.EstacionAR.Controller;

import com.daos.EstacionAR.Entity.Estacionamiento;
import com.daos.EstacionAR.Service.EstacionamientoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(value = "/estacionamientos") // URL base
public class EstacionamientoController {

    @Autowired
    private EstacionamientoService estacionamientoService;

    // CREAR ESTACIONAMIENTO, nuevo registro utilizando el form

    @PostMapping
    @ResponseBody
    public ResponseEntity<?> crearEstacionamiento(@Validated @RequestBody EstacionamientoForm estacionamientoForm) {
        try {
            Estacionamiento nuevoEstacionamiento = estacionamientoService.validarYCrearEstacionamiento(estacionamientoForm);
            return ResponseEntity.ok(nuevoEstacionamiento); // exitoso
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage()); // msj de error
        }
    }

    // ACTUALIZAR ESTACIONAMIENTO

    @PutMapping(value = "/{patente}") // es lo mismo q hacer esto...
    // @RequestMapping (value = "/{patente}"), method = RequestMethod.PutMapping)

    public ResponseEntity<?> actualizarEstacionamiento(@PathVariable String patente,
            @Validated @RequestBody EstacionamientoForm estacionamientoForm) {
        try {
            estacionamientoService.validarEstacionamiento(estacionamientoForm);
            Optional<Estacionamiento> estacionamientoActual = estacionamientoService.actualizarEstacionamiento(patente,
                    estacionamientoForm);
            if (estacionamientoActual.isPresent()) {
                return ResponseEntity.ok(estacionamientoActual.get());
            } else {
                return ResponseEntity.notFound().build(); // no encuentro el estacionamiento
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // CONSULTAR ESTADO DEL ESTACIONAMIENTO, por patente
    @GetMapping(value = "/{patente}")
    public ResponseEntity<?> consultarEstacionamiento(@PathVariable String patente) {
        Optional<Estacionamiento> estacionamiento = estacionamientoService.consultarEstacionamiento(patente);
        if (estacionamiento.isPresent()) {
            return ResponseEntity.ok(estacionamiento.get()); // devuelvo detalles
        } else {
            return ResponseEntity.notFound().build(); // no ahy estacionamiento
        }
    }
}
