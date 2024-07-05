package com.daos.EstacionAR.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity

public class User {
    @Id
    private Integer dni;
	@NotNull
	private String nombre;
	@NotNull
    private String apellido;
    private String contraseña;
    private String domicilio;
    private String correo;
    private LocalDate nacimiento;
    @OneToOne(cascade = CascadeType.ALL)
    private Vehiculo vehiculo;
	@Column(updatable = false)
	private BigDecimal saldo;

	public User() {
	}

	public User(Integer dni, String nombre, String apellido, String contraseña, String domicilio, String correo, LocalDate nacimiento, Vehiculo vehiculo, BigDecimal saldo) {
		this.dni = dni;
		this.nombre = nombre;
		this.apellido = apellido;
		this.contraseña = contraseña;
		this.domicilio = domicilio;
		this.correo = correo;
		this.nacimiento = nacimiento;
		this.vehiculo = vehiculo;
		this.saldo= saldo;
	}

	public Integer getDni() {
		return dni;
	}

	public void setDni(Integer dni) {
		this.dni = dni;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getContraseña() {
		return contraseña;
	}

	public void setContraseña(String contraseña) {
		this.contraseña = contraseña;
	}

	public String getDomicilio() {
		return domicilio;
	}

	public void setDomicilio(String domicilio) {
		this.domicilio = domicilio;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public LocalDate getNacimiento() {
		return nacimiento;
	}

	public void setNacimiento(LocalDate nacimiento) {
		this.nacimiento = nacimiento;
	}

	public Vehiculo getVehiculo() {
		return vehiculo;
	}

	public void setVehiculo(Vehiculo vehiculo) {
		this.vehiculo = vehiculo;
	}

	public BigDecimal getSaldo() {
		return saldo;
	}



	@Override
	public String toString() {
		return "User{" +
				"dni=" + dni +
				", nombre='" + nombre + '\'' +
				", apellido='" + apellido + '\'' +
				", contraseña='" + contraseña + '\'' +
				", domicilio='" + domicilio + '\'' +
				", correo='" + correo + '\'' +
				", nacimiento=" + nacimiento +
				", vehiculo=" + vehiculo +
				", saldo=" + saldo +
				'}';
	}
}
