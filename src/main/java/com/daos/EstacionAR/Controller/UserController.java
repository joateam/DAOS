package com.daos.EstacionAR.Controller;

import com.daos.EstacionAR.Entity.User;
import com.daos.EstacionAR.Response.UserResponseDTO;
import com.daos.EstacionAR.Service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
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

    @PostMapping
    public ResponseEntity<User> crearUsuario(@RequestBody User usuario) {
        try {
            User newUser = userServ.saveUser(usuario);
            return new ResponseEntity<>(newUser, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/{dni}")
    public ResponseEntity<EntityModel<UserResponseDTO>> getUserById(@PathVariable Integer dni) {
        try {
            User user = userServ.findUser(dni);
            if (user != null) {
                UserResponseDTO userResponseDTO = new UserResponseDTO(
                        user.getDni(),
                        user.getNombre(),
                        user.getApellido(),
                        user.getDomicilio(),
                        user.getCorreo(),
                        user.getNacimiento(),
                        user.getVehiculo(),
                        user.getSaldo()
                );
                userResponseDTO.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(UserController.class).getUserById(dni)).withSelfRel());

                EntityModel<UserResponseDTO> resource = EntityModel.of(userResponseDTO);
                return ResponseEntity.ok(resource);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{dni}")
    public ResponseEntity<Void> bajaUsuario(@PathVariable Integer dni) {
        try {
            userServ.deleteUser(dni);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
    @PutMapping("/{dni}")
    public ResponseEntity<User> editUsuario(@PathVariable Integer dni, @RequestBody User usuarioNewData) {
        try {
            User updatedUser = userServ.editUser(dni, usuarioNewData);
            return ResponseEntity.ok(updatedUser);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }




}
