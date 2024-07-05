package com.daos.EstacionAR.Service;

import com.daos.EstacionAR.Entity.User;
import com.daos.EstacionAR.Entity.Vehiculo;

import java.util.List;

public interface IUserService {

    public List<User> getUsers();
    public User saveUser(User usuario);
    public void deleteUser(Integer dni);

    public User  editUser(Integer dni, User newUserData);

    public User findUser(Integer dni);
}
