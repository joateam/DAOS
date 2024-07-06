package com.daos.EstacionAR.Response;

import com.daos.EstacionAR.Controller.UserController;
import com.daos.EstacionAR.Entity.Vehiculo;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;

import java.math.BigDecimal;
import java.time.LocalDate;

public class UserResponseDTO extends RepresentationModel<UserResponseDTO> {
    private Integer dni;
    private String nombre;
    private String apellido;
    private String domicilio;
    private String correo;
    private LocalDate nacimiento;
    private Vehiculo vehiculo;
    private BigDecimal saldo;

    public UserResponseDTO() {
    }

    public UserResponseDTO(Integer dni, String nombre, String apellido, String domicilio, String correo, LocalDate nacimiento, Vehiculo vehiculo, BigDecimal saldo) {
        this.dni = dni;
        this.nombre = nombre;
        this.apellido = apellido;
        this.domicilio = domicilio;
        this.correo = correo;
        this.nacimiento = nacimiento;
        this.vehiculo = vehiculo;
        this.saldo = saldo;
    }

    public Integer getDni() {
        return dni;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getDomicilio() {
        return domicilio;
    }

    public String getCorreo() {
        return correo;
    }

    public LocalDate getNacimiento() {
        return nacimiento;
    }

    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    public BigDecimal getSaldo() {
        return saldo;
    }

    @Override
    public String toString() {
        return "UserResponseDTO{" +
                "dni=" + dni +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", domicilio='" + domicilio + '\'' +
                ", correo='" + correo + '\'' +
                ", nacimiento=" + nacimiento +
                ", vehiculo=" + vehiculo +
                ", saldo=" + saldo +
                '}';
    }


}