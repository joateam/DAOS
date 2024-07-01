package com.daos.EstacionAR.Controller;


import com.daos.EstacionAR.Response.AbonoComercioResponseDTO;
import com.daos.EstacionAR.Service.AbonoComercioServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/abonocomercios")
@Validated
public class AbonoComercioController {

    @Autowired
    private AbonoComercioServiceImpl abonoComercioService;

    @GetMapping(value = "/{id}", produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<AbonoComercioResponseDTO> show(
            @PathVariable Long id,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaDesde,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaHasta) {

        Long recargasPagas = abonoComercioService.obtenerCantidadRecargasPagas(id, fechaDesde, fechaHasta);
        Long recargasImpagas = abonoComercioService.obtenerCantidadRecargasImpagas(id, fechaDesde, fechaHasta);
        Double saldoImpagas = abonoComercioService.obtenerSaldoRecargasImpagas(id, fechaDesde, fechaHasta);
        

        AbonoComercioResponseDTO respuesta = new AbonoComercioResponseDTO(id, recargasPagas, recargasImpagas, saldoImpagas);
        respuesta.add(linkTo(methodOn(AbonoComercioController.class).show(id, fechaDesde, fechaHasta)).withSelfRel());

        return ResponseEntity.ok(respuesta);
    }
    
    @GetMapping(produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<List<AbonoComercioResponseDTO>> index(
    		@RequestParam @NotNull(message = "La fecha de inicio no puede ser nula") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaDesde,
            @RequestParam @NotNull(message = "La fecha de fin no puede ser nula") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaHasta)  throws Exception  {

        List<Long> comercioIds = abonoComercioService.obtenerTodosLosIdsDeComercio();
        List<AbonoComercioResponseDTO> responseList = new ArrayList();

        for (Long comercioId : comercioIds) {
        	Long recargasPagas = abonoComercioService.obtenerCantidadRecargasPagas(comercioId, fechaDesde, fechaHasta);
            Long recargasImpagas = abonoComercioService.obtenerCantidadRecargasImpagas(comercioId, fechaDesde, fechaHasta);
            Double saldoImpagas = abonoComercioService.obtenerSaldoRecargasImpagas(comercioId, fechaDesde, fechaHasta);

            AbonoComercioResponseDTO responseDTO = new AbonoComercioResponseDTO(comercioId, recargasPagas, recargasImpagas, saldoImpagas);
            responseList.add(responseDTO);
        }

        return ResponseEntity.ok(responseList);
    }
}
