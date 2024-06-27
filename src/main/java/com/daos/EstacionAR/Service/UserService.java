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
    public void saveUser(User usuario, Vehiculo coche) {

    }

    @Override
    public void deleteUser(Integer dni) {
        userRepo.deleteById(dni);
    }

    @Override
    public void editUser(Integer dni, User newUserData) {
        User usuarioEncontrado = this.findUser(dni);

    }

    @Override
    public User findUser(Integer dni) {
        return userRepo.findById(dni).orElse(null);
    }
}
