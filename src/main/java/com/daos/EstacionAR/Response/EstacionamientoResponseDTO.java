package com.daos.EstacionAR.Response;

import org.springframework.hateoas.RepresentationModel;

import com.daos.EstacionAR.Entity.Estacionamiento;

public class EstacionamientoResponseDTO extends RepresentationModel<EstacionamientoResponseDTO> {

    private Long id;
    private String patente;
    private String estado;
    private Integer dniUser;

    public EstacionamientoResponseDTO(Estacionamiento pojo) {

        this.id = pojo.getId();
        this.patente = pojo.getPatente();
        this.estado = pojo.getEstado().toString();
        this.dniUser = pojo.getDniUser();
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

    public Integer getDniUser() {
        return dniUser;
    }

    public void setDniUser(Integer dniUser) {
        this.dniUser = dniUser;
    }

    @Override
    public String toString() {
        return "EstacionamientoResponseDTO{" +
                ", patente='" + patente + '\'' +
                ", estado='" + estado + '\'' +
                ", dniUser='" + dniUser + '\'' +
                '}';
    }
}
