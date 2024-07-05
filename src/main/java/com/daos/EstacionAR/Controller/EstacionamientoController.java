package com.daos.EstacionAR.Controller;

import com.daos.EstacionAR.Entity.Estacionamiento;
import com.daos.EstacionAR.Response.EstacionamientoResponseDTO;
import com.daos.EstacionAR.Service.IEstacionamientoService;

import jakarta.validation.Valid;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/estacionamientos") // URL base
public class EstacionamientoController {

    @Autowired
    private IEstacionamientoService estacionamientoService;

    @PostMapping("/insertar")
    public ResponseEntity<EstacionamientoResponseDTO> insertarEstacionamiento(
            @Valid @RequestBody EstacionamientoForm form) {
        Estacionamiento estacionamiento = new Estacionamiento();
        estacionamiento.setPatente(form.getPatente());
        estacionamiento.setEstado(Estacionamiento.Estado.valueOf(form.getEstado().name()));

        Estacionamiento nuevoEstacionamiento = estacionamientoService.insertarEstacionamiento(estacionamiento);
        EstacionamientoResponseDTO responseDTO = new EstacionamientoResponseDTO(nuevoEstacionamiento);
        return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
    }

    /**
     * @PostMapping("/insertar")
     * public void crearEstacionamiento(@RequestBody Estacionamiento
     * estacionamiento){
     * estacionamientoService.insertarEstacionamiento(estacionamiento);
     * } NO DEVUELVE RESPUESTA
     **/

     @GetMapping("/consultar/{patente}")
    public ResponseEntity<EntityModel<EstacionamientoResponseDTO>> consultarEstado(@PathVariable String patente) {
        Estacionamiento estacionamiento = estacionamientoService.consultarEstado(patente);

        EstacionamientoResponseDTO responseDTO = new EstacionamientoResponseDTO(estacionamiento);
        EntityModel<EstacionamientoResponseDTO> resource = EntityModel.of(responseDTO);
        resource.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(EstacionamientoController.class)
                .consultarEstado(patente)).withSelfRel());
        resource.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(UserController.class)
                .getUserData(estacionamiento.getDniUser())).withRel("user-data"));
        return new ResponseEntity<>(resource, HttpStatus.OK);
    } 

    @PutMapping("/finalizar")
    public ResponseEntity<EstacionamientoResponseDTO> finalizarEstacionamiento(
            @Valid @RequestBody EstacionamientoForm form) {
        Estacionamiento estacionamiento = estacionamientoService.finalizarEstacionamiento(form.getPatente(),
                form.getPasswordUser());
        EstacionamientoResponseDTO responseDTO = new EstacionamientoResponseDTO(estacionamiento);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

}
