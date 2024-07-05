package com.daos.EstacionAR.Service;

import com.daos.EstacionAR.Entity.User;
import com.daos.EstacionAR.Entity.Vehiculo;
import com.daos.EstacionAR.Repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements IUserService {

    @Autowired
    private IUserRepository userRepo;


    @Override
    public List<User> getUsers() {
        return userRepo.findAll();
    }

    @Override
    public User saveUser(User usuario) {
        if (userRepo.existsById(usuario.getDni())) {
            throw new RuntimeException("Ya existe un usuario con este DNI");
        }
        if (userRepo.existsByVehiculoPatente(usuario.getVehiculo().getPatente())) {
            throw new RuntimeException("Ya existe un usuario asociado a esta patente");
        }
        // Eliminamos la inicialización del saldo aquí
        return userRepo.save(usuario);
    }

    @Override
    public void deleteUser(Integer dni) {
        userRepo.deleteById(dni);
    }

    @Override
    public User  editUser(Integer dni, User newUserData) {
        User usuarioEncontrado = this.findUser(dni);
        if(usuarioEncontrado != null){
            usuarioEncontrado.setNombre(newUserData.getNombre());
            usuarioEncontrado.setApellido(newUserData.getApellido());
            usuarioEncontrado.setContraseña(newUserData.getContraseña());
            usuarioEncontrado.setCorreo(newUserData.getCorreo());
            usuarioEncontrado.setDomicilio(newUserData.getDomicilio());
            usuarioEncontrado.setNacimiento(newUserData.getNacimiento());

            // Verificar si se está cambiando la patente
            if (!usuarioEncontrado.getVehiculo().getPatente().equals(newUserData.getVehiculo().getPatente())) {
                if (userRepo.existsByVehiculoPatente(newUserData.getVehiculo().getPatente())) {
                    throw new RuntimeException("Ya existe un usuario asociado a esta patente");
                }
                usuarioEncontrado.getVehiculo().setPatente(newUserData.getVehiculo().getPatente());
            }

            return userRepo.save(usuarioEncontrado);
        } else {
            throw new RuntimeException("Usuario no encontrado");
        }
    }

    @Override
    public User findUser(Integer dni) {
        return userRepo.findById(dni).orElse(null);
    }
}
