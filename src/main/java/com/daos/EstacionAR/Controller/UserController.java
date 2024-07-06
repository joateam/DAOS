package com.daos.EstacionAR.Controller;

import com.daos.EstacionAR.Entity.User;
import com.daos.EstacionAR.Service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UserController {

    @Autowired
    private IUserService userServ;

    @GetMapping
    public ResponseEntity<List<User>> getAll() {
        List<User> users = userServ.getUsers();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{dni}")
    public ResponseEntity<User> getUserById(@PathVariable Integer dni) {
        try {
            User user = userServ.findUser(dni);
            if (user != null) {
                return ResponseEntity.ok(user);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }


}
