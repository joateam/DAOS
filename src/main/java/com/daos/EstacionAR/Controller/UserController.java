package com.daos.EstacionAR.Controller;

import com.daos.EstacionAR.Entity.User;
import com.daos.EstacionAR.Service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UserController {

    @Autowired
    private IUserService userServ;

    @GetMapping("")
    public List<User> getAll(){
        return userServ.getUsers();
    }

    @PostMapping("")
    public void crearUsuario(@RequestBody User usuario){
        userServ.saveUser(usuario);
    }

    @DeleteMapping("/{dni}")
    public void bajaUsuario(@PathVariable Integer dni){
        userServ.deleteUser(dni);
    }

    @PutMapping("/{dni}")
    public void editUsuario(@PathVariable Integer dni, @RequestBody User usuarioNewData){
        userServ.editUser(dni,usuarioNewData);
    }
}
