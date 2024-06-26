package com.daos.EstacionAR.Controller;

import com.daos.EstacionAR.Entity.Estacionamiento;
import com.daos.EstacionAR.Service.EstacionamientoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/estacionamientos") //URL base
public class EstacionamientoController {

    @Autowired
    private EstacionamientoService estacionamientoService;

    //INICIAR ESTACIONAMIENTO
    @PostMapping
    public ResponseEntity<?> crearEstacionamiento(@Validated @RequestBody EstacionamientoForm estacionamientoForm) {
        try {
            estacionamientoService.validarEstacionamiento(estacionamientoForm);
            Estacionamiento nuevoEstacionamiento = estacionamientoService.crearEstacionamiento(estacionamientoForm);
            return ResponseEntity.ok(nuevoEstacionamiento);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // ACTUALIZAR ESTACIONAMIENTO
    @PutMapping("/{patente}")
    public ResponseEntity<?> actualizarEstacionamiento(@PathVariable String patente, @Validated @RequestBody EstacionamientoForm estacionamientoForm) {
        try {
            estacionamientoService.validarEstacionamiento(estacionamientoForm);
            Optional<Estacionamiento> estacionamientoActual = estacionamientoService.actualizarEstacionamiento(patente, estacionamientoForm);
            if (estacionamientoActual.isPresent()) {
                return ResponseEntity.ok(estacionamientoActual.get());
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    //CONSULTAR ESTADO DEL ESTACIONAMIENTO
    @GetMapping("/{patente}")
    public ResponseEntity<?> consultarEstacionamiento(@PathVariable String patente) {
        Optional<Estacionamiento> estacionamiento = estacionamientoService.consultarEstacionamiento(patente);
        if (estacionamiento.isPresent()) {
            return ResponseEntity.ok(estacionamiento.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
