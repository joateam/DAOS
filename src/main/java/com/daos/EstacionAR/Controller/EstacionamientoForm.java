package com.daos.EstacionAR.Controller;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class EstacionamientoForm {

    @NotBlank(message = "La patente no puede estar en blanco")
    private String patente;

    @NotBlank(message = "La contraseña de usuario no puede estar en blanco")
    private String passwordUser;

    @NotNull(message = "El estado no puede ser nulo")
    private EstadoEstacionamiento estado;

    // Getters y setters
    public String getPatente() {
        return patente;
    }

    public void setPatente(String patente) {
        this.patente = patente;
    }

    public String getPasswordUser() {
        return passwordUser;
    }

    public void setPasswordUser(String passwordUser) {
        this.passwordUser = passwordUser;
    }

    public EstadoEstacionamiento getEstado() {
        return estado;
    }

    public void setEstado(EstadoEstacionamiento estado) {
        this.estado = estado;
    }

    // Enum para el estado del estacionamiento (Estacionado / Libre)
    public enum EstadoEstacionamiento {
        ESTACIONADO,
        LIBRE
    }
}

