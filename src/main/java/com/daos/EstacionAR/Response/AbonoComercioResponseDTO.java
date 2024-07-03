package com.daos.EstacionAR.Response;

import java.time.LocalDate;

import org.springframework.hateoas.RepresentationModel;

public class AbonoComercioResponseDTO extends RepresentationModel<AbonoComercioResponseDTO> {

	private Long comercioNr;
    private Long cantRecargasPagas;
    private Long cantRecargasImpagas;
    private Float saldoImpagas;
    private Float saldoPagas;
    private Float importeAbonar;
    private Float importeAbonado;
    private LocalDate fechaDesde;
    private LocalDate fechaHasta;

    public AbonoComercioResponseDTO(Long comercioNr, LocalDate fechaDesde, LocalDate fechaHasta, Long cantRecargasPagas, Long cantRecargasImpagas, Float saldoImpagas, Float saldoPagas) {
        this.comercioNr = comercioNr;
        this.fechaDesde = fechaDesde;
        this.fechaHasta = fechaHasta;
        this.cantRecargasPagas = cantRecargasPagas;
        this.cantRecargasImpagas = cantRecargasImpagas;
        this.saldoImpagas = saldoImpagas;
        this.saldoPagas = saldoPagas;
        this.importeAbonar = calcularImporte(saldoImpagas);
        this.importeAbonado = calcularImporte(saldoPagas);
    }
    
    public Float getSaldoPagas() {
		return saldoPagas;
	}

	public void setSaldoPagas(Float saldoPagas) {
		this.saldoPagas = saldoPagas;
	}

	 public AbonoComercioResponseDTO(Long comercioNr, LocalDate fechaDesde, LocalDate fechaHasta, Long cantRecargasPagas, Float saldoPagas) {
	        this.comercioNr = comercioNr;
	        this.fechaDesde = fechaDesde;
	        this.fechaHasta = fechaHasta;
	        this.cantRecargasPagas = cantRecargasPagas;
	        this.cantRecargasImpagas = 0l;
	        this.saldoImpagas = 0f;
	        this.saldoPagas = saldoPagas;
	        this.importeAbonar = 0f;
	        this.importeAbonado = calcularImporte(saldoPagas);
	    }

	public Float getImporteAbonado() {
		return importeAbonado;
	}

	public void setImporteAbonado(Float importeAbonado) {
		this.importeAbonado = importeAbonado;
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

	

    public Long getComercioNr() {
		return comercioNr;
	}

	public void setComercioNr(Long comercioNr) {
		this.comercioNr = comercioNr;
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

    public Float getSaldoImpagas() {
        return saldoImpagas;
    }

    public void setSaldoImpagas(Float saldoImpagas) {
        this.saldoImpagas = saldoImpagas;
    }

    @Override
    public String toString() {
        return "{AbonoComercioResponseDTO{" +
                "comercioNr=" + comercioNr +
                ", cantRecargasPagas=" + cantRecargasPagas +
                ", cantRecargasImpagas=" + cantRecargasImpagas +
                ", saldoImpagas=" + saldoImpagas +
                '}';
    }

	public Float getImporteAbonar() {
		return importeAbonar;
	}

	public void setImporteAbonar(Float importeAbonar) {
		this.importeAbonar = importeAbonar;
	}

	private float calcularImporte(Float importe) {
	        try {
	            return Math.round((importe * 0.95f) * 100.00f) / 100.00f;
	        } catch (Exception e) {
	            return 0.0f;
	        }
	    }
	
}
