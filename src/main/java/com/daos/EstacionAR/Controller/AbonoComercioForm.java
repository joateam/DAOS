package com.daos.EstacionAR.Controller;

import com.daos.EstacionAR.Entity.AbonoComercio;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.time.LocalDate;

public class AbonoComercioForm {

    @NotNull(message = "El número de comercio no puede ser nulo")
    @Min(value = 1, message = "El número de comercio debe ser un entero positivo")
    private Long nroComercio;

    @NotNull(message = "La fecha de inicio no puede ser nula")
    private LocalDate fechaDesde;

    @NotNull(message = "La fecha de fin no puede ser nula")
    private LocalDate fechaHasta;

    @NotNull(message = "El importe no puede ser nulo")
    @Positive(message = "El importe debe ser un número positivo")
    private Float importe;

    // Getters y setters
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

    public AbonoComercio toPojo() {
        AbonoComercio abonoComercio = new AbonoComercio();
        abonoComercio.setNroComercio(this.getNroComercio());
        abonoComercio.setFechaDesde(this.getFechaDesde());
        abonoComercio.setFechaHasta(this.getFechaHasta());
        abonoComercio.setImporte(this.getImporte());
        return abonoComercio;
    }
}

