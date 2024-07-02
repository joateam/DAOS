package com.daos.EstacionAR.Response;

import org.springframework.hateoas.RepresentationModel;

public class AbonoComercioResponseDTO extends RepresentationModel<AbonoComercioResponseDTO> {

    private Long idComercio;
    private Long cantRecargasPagas;
    private Long cantRecargasImpagas;
    private Double saldoImpagas;

    public AbonoComercioResponseDTO(Long idComercio, Long cantRecargasPagas, Long cantRecargasImpagas, Double saldoImpagas) {
        this.idComercio = idComercio;
        this.cantRecargasPagas = cantRecargasPagas;
        this.cantRecargasImpagas = cantRecargasImpagas;
        this.saldoImpagas = saldoImpagas;
    }

    // Getters y setters
    public Long getIdComercio() {
        return idComercio;
    }

    public void setIdComercio(Long idComercio) {
        this.idComercio = idComercio;
    }

    public Long getCantRecargasPagas() {
        return cantRecargasPagas;
    }

    public void setCantRecargasPagas(Long cantRecargasPagas) {
        this.cantRecargasPagas = cantRecargasPagas;
    }

    public Long getCantRecargasImpagas() {
        return cantRecargasImpagas;
    }

    public void setCantRecargasImpagas(Long cantRecargasImpagas) {
        this.cantRecargasImpagas = cantRecargasImpagas;
    }

    public Double getSaldoImpagas() {
        return saldoImpagas;
    }

    public void setSaldoImpagas(Double saldoImpagas) {
        this.saldoImpagas = saldoImpagas;
    }

    @Override
    public String toString() {
        return "AbonoComercioResponseDTO{" +
                "idComercio=" + idComercio +
                ", cantRecargasPagas=" + cantRecargasPagas +
                ", cantRecargasImpagas=" + cantRecargasImpagas +
                ", saldoImpagas=" + saldoImpagas +
                '}';
    }
}
