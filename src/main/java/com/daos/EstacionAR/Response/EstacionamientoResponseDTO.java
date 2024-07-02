package com.daos.EstacionAR.Response;

import com.daos.EstacionAR.Entity.Estacionamiento;

public class EstacionamientoResponseDTO {

    private Long id;
    private String patente;
    private String estado;
    private String horaInicio;
    private String horaFin;

    public EstacionamientoResponseDTO(Estacionamiento estacionamiento) {
        this.id = estacionamiento.getId();
        this.patente = estacionamiento.getPatente();
        this.estado = estacionamiento.getEstado().toString();
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

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
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
                ", horaInicio='" + horaInicio + '\'' +
                ", horaFin='" + horaFin + '\'' +
                '}';
    }
}
