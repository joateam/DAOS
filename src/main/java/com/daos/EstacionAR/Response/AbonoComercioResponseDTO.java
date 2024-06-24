package com.daos.EstacionAR.Response;

import org.springframework.hateoas.RepresentationModel;
import com.daos.EstacionAR.Entity.AbonoComercio;

public class AbonoComercioResponseDTO extends RepresentationModel<AbonoComercioResponseDTO> {

    private Long id;
    private Long nroComercio;
    private String fechaDesde;
    private String fechaHasta;
    private Float importe;

    public AbonoComercioResponseDTO(AbonoComercio abonoComercio) {
        this.id = abonoComercio.getId();
        this.nroComercio = abonoComercio.getNroComercio();
        this.fechaDesde = abonoComercio.getFechaDesde().toString();
        this.fechaHasta = abonoComercio.getFechaHasta().toString();
        this.importe = abonoComercio.getImporte();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getNroComercio() {
        return nroComercio;
    }

    public void setNroComercio(Long nroComercio) {
        this.nroComercio = nroComercio;
    }

    public String getFechaDesde() {
        return fechaDesde;
    }

    public void setFechaDesde(String fechaDesde) {
        this.fechaDesde = fechaDesde;
    }

    public String getFechaHasta() {
        return fechaHasta;
    }

    public void setFechaHasta(String fechaHasta) {
        this.fechaHasta = fechaHasta;
    }

    public Float getImporte() {
        return importe;
    }

    public void setImporte(Float importe) {
        this.importe = importe;
    }

    @Override
    public String toString() {
        return "AbonoComercioResponseDTO{" +
                "id=" + id +
                ", nroComercio=" + nroComercio +
                ", fechaDesde='" + fechaDesde + '\'' +
                ", fechaHasta='" + fechaHasta + '\'' +
                ", importe=" + importe +
                '}';
    }
}

