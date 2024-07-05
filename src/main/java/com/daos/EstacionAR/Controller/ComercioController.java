package com.daos.EstacionAR.Controller;

import com.daos.EstacionAR.Entity.Comercio;
import com.daos.EstacionAR.Service.ComercioServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/comercios")
public class ComercioController {

    @Autowired
    private ComercioServiceImpl comercioService;

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Comercio> getById(@PathVariable Long id) {
        Comercio comercio = comercioService.findById(id);
        if (comercio != null) {
            return new ResponseEntity<>(comercio, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    
    
}
