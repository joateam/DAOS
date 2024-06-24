package com.daos.EstacionAR.Controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.daos.EstacionAR.Entity.AbonoComercio;
import com.daos.EstacionAR.Response.AbonoComercioResponseDTO;
import com.daos.EstacionAR.Service.AbonoComercioServiceImpl;

@RestController
@RequestMapping("/abonocomercios")

public class AbonoComercioController {
	
	@Autowired
	private AbonoComercioServiceImpl service;

	    @GetMapping(value = "/{id}", produces = { MediaType.APPLICATION_JSON_VALUE })
	    public ResponseEntity<AbonoComercioResponseDTO> getById(@PathVariable Long id)
	    {
	        Optional<AbonoComercio> ac = service.getById(id);
	        if (ac.isPresent()) {
	            AbonoComercio pojo = ac.get();
	            return new ResponseEntity<AbonoComercioResponseDTO>(buildResponse(pojo), HttpStatus.OK);
	        } else {
	            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	        }
	    }
	
	    @GetMapping(produces = { MediaType.APPLICATION_JSON_VALUE })
	    public ResponseEntity<List<AbonoComercio>> getAll(
	            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaDesde,
	            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaHasta,
	            @RequestParam(required = false) Long nroComercio) {

	        List<AbonoComercio> abonos = service.findByFechaBetween(fechaDesde, fechaHasta);
	        
	        if (nroComercio != null) {
	            abonos = abonos.stream()
	                .filter(abono -> abono.getNroComercio().equals(nroComercio))
	                .toList();
	        }
	        

	        if (abonos.isEmpty()) {
	            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	        }
	        return ResponseEntity.ok(abonos);
	    }
	
	    /*@PostMapping(consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	    public ResponseEntity<String> createAbonoComercio(@RequestBody AbonoComercioForm abonoComercioForm) {
	        AbonoComercio abonoComercio = abonoComercioForm.toPojo();
	        
	        Float porcentajeEsperado = 0.95f;
	        Float importeCobrado = abonoComercio.getImporte() / porcentajeEsperado;
	        Float importeCorrecto = importeCobrado * porcentajeEsperado;

	        if (!abonoComercio.getImporte().equals(importeCorrecto)) {
	            return ResponseEntity.badRequest().body("El importe abonado no es el correcto.");
	        }

	        service.save(abonoComercio);
	        return ResponseEntity.status(HttpStatus.CREATED).body("Abono registrado correctamente.");
	    }*/
	    
	    private AbonoComercioResponseDTO buildResponse(AbonoComercio abonoComercio) {
	        AbonoComercioResponseDTO dto = new AbonoComercioResponseDTO(abonoComercio);

	        Link selfLink = WebMvcLinkBuilder.linkTo(AbonoComercioController.class)
	            .slash(abonoComercio.getId())
	            .withSelfRel();

	        dto.add(selfLink);
	        return dto;
	    }
}
