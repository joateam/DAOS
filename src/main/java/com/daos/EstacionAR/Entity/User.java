package com.daos.EstacionAR.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class User {
    @Id
    private Integer dni;
    private String nombre;
    private String apellido;
    private String contraseña;
    private String domicilio;
    private String correo;
    private LocalDate nacimiento;
    @OneToOne
    private Vehiculo vehiculo;
}
