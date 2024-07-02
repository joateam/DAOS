package com.daos.EstacionAR.Controller;

import com.daos.EstacionAR.Exceptions.ExceptionAr;
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
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
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
            @RequestParam @NotNull(message = "La fecha de inicio no puede ser nula") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE, pattern = "yyyy-MM-dd") LocalDate fechaDesde,
            @RequestParam @NotNull(message = "La fecha de fin no puede ser nula") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE, pattern = "yyyy-MM-dd") LocalDate fechaHasta) throws ExceptionAr {

        Long recargasPagas = abonoComercioService.obtenerCantidadRecargasPagas(id, fechaDesde, fechaHasta);
        Long recargasImpagas = abonoComercioService.obtenerCantidadRecargasImpagas(id, fechaDesde, fechaHasta);
        Double saldoImpagas = abonoComercioService.obtenerSaldoRecargasImpagas(id, fechaDesde, fechaHasta);

        AbonoComercioResponseDTO respuesta = new AbonoComercioResponseDTO(id, recargasPagas, recargasImpagas, saldoImpagas);
        respuesta.add(linkTo(methodOn(AbonoComercioController.class).show(id, fechaDesde, fechaHasta)).withSelfRel());

        return ResponseEntity.ok(respuesta);
    }

    @GetMapping(produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<List<AbonoComercioResponseDTO>> index(
            @RequestParam @NotNull String fechaDesde,
            @RequestParam @NotNull String fechaHasta) throws ExceptionAr {

    	LocalDate fechaDesdeL = parseAndValidateDate(fechaDesde, "fechaDesde");
    	LocalDate fechaHastaL = parseAndValidateDate(fechaHasta, "fechaHasta");
    	
        List<Long> comercioIds = abonoComercioService.obtenerTodosLosIdsDeComercio();
        List<AbonoComercioResponseDTO> responseList = new ArrayList<>();

        for (Long comercioId : comercioIds) {
            Long recargasPagas = abonoComercioService.obtenerCantidadRecargasPagas(comercioId, fechaDesdeL, fechaHastaL);
            Long recargasImpagas = abonoComercioService.obtenerCantidadRecargasImpagas(comercioId, fechaDesdeL, fechaHastaL);
            Double saldoImpagas = abonoComercioService.obtenerSaldoRecargasImpagas(comercioId, fechaDesdeL, fechaHastaL);

            AbonoComercioResponseDTO responseDTO = new AbonoComercioResponseDTO(comercioId, recargasPagas, recargasImpagas, saldoImpagas);
            responseDTO.add(linkTo(methodOn(AbonoComercioController.class).show(comercioId, fechaDesdeL, fechaHastaL)).withSelfRel());
            responseList.add(responseDTO);
        }

        return ResponseEntity.ok(responseList);
    }
    
    private final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    
    private LocalDate parseAndValidateDate(String dateStr, String nombreVar) throws ExceptionAr {
        try {
            return LocalDate.parse(dateStr, dateFormatter);
        } catch (DateTimeParseException e) {
            throw new ExceptionAr("Formato de " + nombreVar + " inválido, se espera el formato 'yyyy-MM-dd'", 400);
        }
    }
}
