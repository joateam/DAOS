package com.daos.EstacionAR.Entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;


@Getter @Setter
@Entity
public class Recarga {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

	private Long nroComercio;
		
	private Integer dni;
	
	private String patente;
	
	private Integer abonado;
   
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "#.00")
	@Column(nullable=false,scale=2)
	private Double importe;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	public LocalDate fecha = LocalDate.now();
	
	public Recarga(){}
	
	public Recarga(Long nroComercio, Integer dni, String patente, Double importe , LocalDate fecha) {
		super();
		this.nroComercio = nroComercio; 
		this.dni = dni;
		this.patente = patente;
		this.importe = importe;
		this.fecha= fecha;
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

	public Integer getDni() {
		return dni;
	}

	public void setDni(Integer dni) {
		this.dni = dni;
	}

	public String getPatente() {
		return patente;
	}

	public void setPatente(String patente) {
		this.patente = patente;
	}

	public Double getImporte() {
		return importe;
	}

	public void setImporte(Double importe) {
		this.importe = importe;
	}

	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

	
	
	public Integer getAbonado() {
		return abonado;
	}
	
	public void setAbonado(Integer abonado) {
		this.abonado = abonado;
	}
}
