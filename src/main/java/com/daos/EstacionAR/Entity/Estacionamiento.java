package com.daos.EstacionAR.Entity;

import jakarta.persistence.Entity;

@Entity
public class Estacionamiento {

    private String Patente;

    private User passwordUser;

    public Boolean Estado;

    public User dniUser;


    // Costructor sin parámetros
    public Estacionamiento() {

    }

    // Constructor con parámetros
    public Estacionamiento(String Patente, User passwordUser,Boolean Estado) {
        this.Patente = Patente;
        this.passwordUser= passwordUser;
        this.Estado = Estado;
    }

    // Getters and Setters
    public String getPatente() {
        return Patente;
    }

    public void setPatente(String patente) {
        Patente = patente;
    }

    public User getPasswordUser() {
        return passwordUser;
    }

    public void setPasswordUser(User passwordUser) {
        this.passwordUser = passwordUser;
    }

    public Boolean getEstado() {
        return Estado;
    }

    public void setEstado(Boolean estado) {
        Estado = estado;
    }

    public User getDniUser() {
        return dniUser;
    }

    public void setDniUser(User dniUser) {
        this.dniUser = dniUser;
    }

    @Override
    public String toString() {
        return "Estacionamiento{" +
                "Patente='" + Patente + '\'' +
                ", Estado=" + Estado +
                ", dniUser=" + dniUser +
                '}';
    }    

}
