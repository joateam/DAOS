package com.daos.EstacionAR.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class AbonoComercioController {

	@GetMapping(value = "", proceces)
    public ResponseEntity<String> getAbonoComercio(@RequestParam(value = "id", required = false) String id) {
        // Lógica para manejar la solicitud GET
        return ResponseEntity.ok("Solicitud GET recibida con id: " + id);
    }
	
}
