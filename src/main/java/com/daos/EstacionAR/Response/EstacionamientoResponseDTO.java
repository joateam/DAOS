package com.daos.EstacionAR.Response;

import com.daos.EstacionAR.Entity.Estacionamiento;

public class EstacionamientoResponseDTO {

    private Long id;
    private String patente;
    private String contraseña;
    private String estado;
    private Integer dniUser;
    private String horaInicio;
    private String horaFin;

    public EstacionamientoResponseDTO(Estacionamiento estacionamiento) {
        this.id = estacionamiento.getId();
        this.patente = estacionamiento.getPatente();
        this.contraseña = estacionamiento.getContraseña();
        this.estado = estacionamiento.getEstado().toString();
        this.dniUser = estacionamiento.getDniUser();
        this.horaInicio = estacionamiento.getHoraInicio() != null ? estacionamiento.getHoraInicio().toString() : null;
        this.horaFin = estacionamiento.getHoraFin() != null ? estacionamiento.getHoraFin().toString() : null;
    }


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

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Integer getDniUser() {
        return dniUser;
    }

    public void setDniUser(Integer dniUser) {
        this.dniUser = dniUser;
    }

    public String getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(String horaInicio) {
        this.horaInicio = horaInicio;
    }

    public String getHoraFin() {
        return horaFin;
    }

    public void setHoraFin(String horaFin) {
        this.horaFin = horaFin;
    }

    @Override
    public String toString() {
        return "EstacionamientoDTO{" +
                "id=" + id +
                ", patente='" + patente + '\'' +
                ", estado='" + estado + '\'' +
                ", dniUser=" + dniUser +
                ", horaInicio='" + horaInicio + '\'' +
                ", horaFin='" + horaFin + '\'' +
                '}';
    }
}
