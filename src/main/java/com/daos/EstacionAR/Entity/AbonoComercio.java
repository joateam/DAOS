package com.daos.EstacionAR.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDate;

@Entity
public class AbonoComercio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long nroComercio;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate fechaDesde;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate fechaHasta;

    private Float importe;

    public AbonoComercio() {
    }

    public AbonoComercio(Long nroComercio, LocalDate fechaDesde, LocalDate fechaHasta, Float importe) {
        this.nroComercio = nroComercio;
        this.fechaDesde = fechaDesde;
        this.fechaHasta = fechaHasta;
        this.importe = importe;
    }

    // Getters y setters
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

    public LocalDate getFechaDesde() {
        return fechaDesde;
    }

    public void setFechaDesde(LocalDate fechaDesde) {
        this.fechaDesde = fechaDesde;
    }

    public LocalDate getFechaHasta() {
        return fechaHasta;
    }

    public void setFechaHasta(LocalDate fechaHasta) {
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
        return "AbonoComercio{" +
                "id=" + id +
                ", nroComercio=" + nroComercio +
                ", fechaDesde=" + fechaDesde +
                ", fechaHasta=" + fechaHasta +
                ", importe=" + importe +
                '}';
    }
}
