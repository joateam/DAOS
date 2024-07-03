package com.daos.EstacionAR.Controller;

import com.daos.EstacionAR.Entity.AbonoComercio;
import com.daos.EstacionAR.Exceptions.ExceptionAr;
import com.daos.EstacionAR.Response.AbonoComercioResponseDTO;
import com.daos.EstacionAR.Service.AbonoComercioServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@RestController
@RequestMapping("/abonocomercios")
@Validated
public class AbonoComercioController {

    @Autowired
    private AbonoComercioServiceImpl abonoComercioService;

    @GetMapping(value = "/{comercioNro}", produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<AbonoComercioResponseDTO> show(
            @PathVariable Long comercioNro,
            @RequestParam @NotNull String fechaDesde,
            @RequestParam @NotNull String fechaHasta) throws ExceptionAr {
    	
    	LocalDate fechaDesdeL = parseAndValidateDate(fechaDesde, "fechaDesde");
    	LocalDate fechaHastaL = parseAndValidateDate(fechaHasta, "fechaHasta");
    	
        Long recargasPagas = Optional.ofNullable(abonoComercioService.obtenerCantidadRecargasPagas(comercioNro, fechaDesdeL, fechaHastaL)).orElse((long) 0);
        Long recargasImpagas = Optional.ofNullable(abonoComercioService.obtenerCantidadRecargasImpagas(comercioNro, fechaDesdeL, fechaHastaL)).orElse((long) 0);
        Float saldoImpagas = Optional.ofNullable(abonoComercioService.obtenerSaldoRecargasImpagas(comercioNro, fechaDesdeL, fechaHastaL)).orElse((float) 0);
        Float saldoPagas = Optional.ofNullable(abonoComercioService.obtenerSaldoRecargasPagas(comercioNro, fechaDesdeL, fechaHastaL)).orElse((float) 0);

        AbonoComercioResponseDTO respuesta = new AbonoComercioResponseDTO(
        									comercioNro,
        									fechaDesdeL,
        									fechaHastaL,
        									recargasPagas,
        									recargasImpagas,
        									saldoImpagas,
        									saldoPagas);
        
        respuesta.add(linkTo(methodOn(AbonoComercioController.class).show(comercioNro, fechaDesde, fechaHasta)).withSelfRel());

        return ResponseEntity.ok(respuesta);
    }

    @GetMapping(produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<List<AbonoComercioResponseDTO>> index(
            @RequestParam @NotNull String fechaDesde,
            @RequestParam @NotNull String fechaHasta) throws ExceptionAr {

    	LocalDate fechaDesdeL = parseAndValidateDate(fechaDesde, "fechaDesde");
    	LocalDate fechaHastaL = parseAndValidateDate(fechaHasta, "fechaHasta");
    	
        List<Long> comercioNrs = abonoComercioService.obtenerTodosLosNrsDeComercio();
        List<AbonoComercioResponseDTO> responseList = new ArrayList<>();

        for (Long comercioNro : comercioNrs) {
        	Long recargasPagas = Optional.ofNullable(abonoComercioService.obtenerCantidadRecargasPagas(comercioNro, fechaDesdeL, fechaHastaL)).orElse((long) 0);
            Long recargasImpagas = Optional.ofNullable(abonoComercioService.obtenerCantidadRecargasImpagas(comercioNro, fechaDesdeL, fechaHastaL)).orElse((long) 0);
            Float saldoImpagas = Optional.ofNullable(abonoComercioService.obtenerSaldoRecargasImpagas(comercioNro, fechaDesdeL, fechaHastaL)).orElse((float) 0);
            Float saldoPagas = Optional.ofNullable(abonoComercioService.obtenerSaldoRecargasPagas(comercioNro, fechaDesdeL, fechaHastaL)).orElse((float) 0);

            AbonoComercioResponseDTO responseDTO = new AbonoComercioResponseDTO(
            		comercioNro,
					fechaDesdeL,
					fechaHastaL,
					recargasPagas,
					recargasImpagas,
					saldoImpagas,
					saldoPagas);
            
            responseDTO.add(linkTo(methodOn(AbonoComercioController.class).show(comercioNro, fechaDesde, fechaHasta)).withSelfRel());
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
    

    
    @PostMapping(consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<Object> create(@Valid @RequestBody AbonoComercioForm abonoComercioForm) throws JsonProcessingException, ExceptionAr {
    	
    		Long comercioNr = abonoComercioForm.getNroComercio();
    		//Falta si comercioNr no existe
    		Float importeAbonar  = abonoComercioForm.getImporteAbonar();
    		LocalDate fechaDesde = abonoComercioForm.getFechaDesde();
    		LocalDate fechaHasta = abonoComercioForm.getFechaHasta();
    		Float saldoImpagas = abonoComercioService.obtenerSaldoRecargasImpagas(comercioNr, fechaDesde, fechaHasta);
    		if(saldoImpagas == null || saldoImpagas == 0) {
    			throw new ExceptionAr("El comercio Nº "+comercioNr + " no tiene saldos a abonar en el período seleccionado", 200);
    		}
    		
    		Float importeAbonarCorrespondiente = Math.round((saldoImpagas * 0.95f) * 100.00f) / 100.00f;
    		if(!importeAbonar.equals(importeAbonarCorrespondiente)) {
    			throw new ExceptionAr("No se pudo realizar el pago. El importeAbonar para el comercio Nº "+comercioNr+" en el período seleccionado es "+importeAbonarCorrespondiente, 400);
    		}
    	
            AbonoComercio abonoComercio = abonoComercioForm.toPojo();
        
            abonoComercioService.actualizarRecargasAAbonado(comercioNr, fechaDesde, fechaHasta);
           
            abonoComercioService.guardarAbonoComercio(abonoComercio);
            
            Long recargasPagas = Optional.ofNullable(abonoComercioService.obtenerCantidadRecargasPagas(comercioNr, fechaDesde, fechaHasta)).orElse((long) 0);
            Float saldoPagas = Optional.ofNullable(abonoComercioService.obtenerSaldoRecargasPagas(comercioNr, fechaDesde, fechaHasta)).orElse((float) 0);
            
        AbonoComercioResponseDTO responseDTO = new AbonoComercioResponseDTO(
                comercioNr,
                fechaDesde,
                fechaHasta,
                recargasPagas,
                saldoPagas
        );

        return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
    }
}
