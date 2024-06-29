package com.daos.EstacionAR.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

import java.time.LocalDateTime;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.GenerationType;
import jakarta.persistence.GeneratedValue;

@Entity
public class Estacionamiento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String patente;

    private String contraseña;

    // DEFINE LOS POSIBLES ESTADOS
    @Enumerated(EnumType.STRING)
    private Estado estado; 

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    private LocalDateTime horaInicio;

    private LocalDateTime horaFin;
//-------------------------------------------------------------------------

    public enum Estado {
        LIBRE,
        ESTACIONADO
    }

    // CONSTRCUTOR SIN PARAMETROS
    public Estacionamiento() {
       
    }

    // Getters y setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPatente() {
        return patente;
    }

    public void setPatente(String patente) {
        this.patente = patente;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocalDateTime getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(LocalDateTime horaInicio) {
        this.horaInicio = horaInicio;
    }

    public LocalDateTime getHoraFin() {
        return horaFin;
    }

    public void setHoraFin(LocalDateTime horaFin) {
        this.horaFin = horaFin;
    }

    public Integer getDniUser() {
        return user != null ? user.getDni() : null;
    }

    @Override
    public String toString() {
        return "Estacionamiento{" +
                "id=" + id +
                ", patente='" + patente + '\'' +
                ", estado=" + estado +
                ", dni user=" + (user != null ? user.getDni() : "null") +
                '}';
    }
}
