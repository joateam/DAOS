package com.daos.EstacionAR.Controller;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

//import org.hibernate.mapping.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.daos.EstacionAR.Entity.Comercio;
import com.daos.EstacionAR.Entity.Recarga;
import com.daos.EstacionAR.Entity.User;
import com.daos.EstacionAR.Exceptions.ExceptionAr;
import com.daos.EstacionAR.Response.RecargaResponseDTO;
import com.daos.EstacionAR.Service.ComercioServiceImpl;
import com.daos.EstacionAR.Service.RecargaService;
import com.daos.EstacionAR.Service.UserService;

@RestController
@RequestMapping("/recarga")
public class RecargaController {
	
	@Autowired
	private RecargaService recargaService;
		
	
	@Autowired
	private ComercioServiceImpl comercioService;
	
	//ESTE METODO TRAE TODAS LAS RECARGAS
	@GetMapping(produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<List<RecargaResponseDTO>> obtenerTodas(){
		List<Recarga> recargas = recargaService.getALL();
		List<RecargaResponseDTO> dtos = new ArrayList<RecargaResponseDTO>();
		for (Recarga pojo : recargas) {
			dtos.add(construirRespuesta(pojo));
		}
		return ResponseEntity.ok(dtos);
	}
	
	@GetMapping(value="/{id}",produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<RecargaResponseDTO> obtenerRecarga(@PathVariable Long id){
		 Recarga recarga = recargaService.getById(id);
		 
		 
		 if (recarga != null) {
			 	RecargaResponseDTO dto = construirRespuesta(recarga);
	            return new ResponseEntity<>(dto, HttpStatus.OK);
	        } else {
	            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	        }
	}
	
	
	@GetMapping(value="/q",produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<List<RecargaResponseDTO>> filtrarTodo(
			@RequestParam (required=false) Integer dni,
			@RequestParam (required=false) String patente,
			@RequestParam (required=false) Long nroComercio) {
		
		List<Recarga> recargas = null;
		
		// Busca primero por dni si existe los busca
		if(dni != null) {
			 recargas = recargaService.getByDni(dni);
			
			// si existe filtra por patente en caso de que exista
			if ( patente != null) {
				recargas = recargas.stream()
						.filter(recarga -> recarga.getPatente().equals(patente))
						.toList();
				}
			// si existe filtra por nroComercio en caso de que exista
			if (nroComercio != null) {
				recargas = recargas.stream()
						.filter(recarga -> recarga.getNroComercio().equals(nroComercio))
						.toList();			
			}
		}
		// SI DNI NO EXISTE BUSCA POR PATENTE
		else if(patente != null){
			recargas = recargaService.getByPatente(patente);
			// FILTRA POR COMERCIO EN CASO DE QUE EXISTA
			if (nroComercio != null) {
				recargas = recargas.stream()
						.filter(recarga -> recarga.getNroComercio().equals(nroComercio))
						.toList();
			}
		}
		// SI PATENTE NO EXISTE BUSCA POR NRO COMERCIO
		else if(nroComercio != null){
			
			recargas = recargaService.getByNroComercio(nroComercio);
		
		}
		
		if (recargas.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}else {
			
			List<RecargaResponseDTO> dtos = new ArrayList<RecargaResponseDTO>();
			for (Recarga pojo : recargas) {
				dtos.add(construirRespuesta(pojo));
			}
			return ResponseEntity.ok(dtos);
		}
		
		
	}
	
	
	@PostMapping(consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public  ResponseEntity<Recarga> recargar(@RequestBody  Recarga recarga) throws ExceptionAr {
		
		Comercio comercio = comercioService.findByNro(recarga.getNroComercio());
		
		
		
		if (comercio.getEstado().equals("autorizado")) {
				recargaService.Recargar(recarga);
				recargaService.actualizarSaldo(recarga);
				return new ResponseEntity<Recarga>(recarga, HttpStatus.CREATED);
		}else{
			throw new ExceptionAr("El comercio" +  comercio.getComercioNr() +   "no esta autorizado para realizar la reacarga", 403);
			//return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
		}
		
		
	}
	
	
	
	
	private RecargaResponseDTO construirRespuesta(Recarga pojo) /**throws ExceptionAr*/ {
		try {
			RecargaResponseDTO dto = new RecargaResponseDTO(pojo);
			 //Self link
			Link selfLink = WebMvcLinkBuilder.linkTo(RecargaController.class)
										.slash(pojo.getId())                
										.withSelfRel();
			
			
			//Method link: Link al servicio que permitirá navegar hacia el usuario relacionado a la recarga
			Link userLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(UserController.class)
			       													.getUserById(pojo.getDni()))
			 														.withRel("usuario");
			//Method link: Link al servicio que permitirá navegar hacia el comercio relacionado a la recarga
			Link comercioLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(ComercioController.class)
			       													.getById(pojo.getId()))
			       													.withRel("comercio");
		
			dto.add(selfLink);
			dto.add(userLink);
			dto.add(comercioLink);
			return dto;
		
		} catch (Exception e) {
			return null;
			//throw new ExceptionAr("Error", 500);
		}
	}

}
