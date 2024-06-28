package com.daos.EstacionAR.Controller;


import java.util.List;


//import org.hibernate.mapping.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.daos.EstacionAR.Entity.Recarga;
import com.daos.EstacionAR.Service.RecargaService;
import com.daos.EstacionAR.Service.UserService;

@Controller
@RequestMapping("/recarga")
public class RecargaController {
	
	@Autowired
	private RecargaService recargaService;
	/**
	@Autowired
	private UserService userService;
	
	@Autowired
	private ComercioService comercioService;
	*/
	@GetMapping(value ="/filtrar_recargas",produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<List<Recarga>> getAll(
			@RequestParam Long dni,
			@RequestParam String patente,
			@RequestParam Long nroComercio){
		
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
		}
		
		return ResponseEntity.ok(recargas);
	}
	
	/**
	@PostMapping(value="/recargar")
	public void recargar() {
		
	}
	*/
	

}
