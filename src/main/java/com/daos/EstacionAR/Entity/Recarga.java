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
		
	private Long dni;
	
	private String patente;
   
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "#.00")
	@Column(nullable=false,scale=2)
	private double importe;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd@HH:mm:ss")
	public LocalDateTime fecha = LocalDateTime.now();
	
	public Recarga(){}
	
	public Recarga(Long nroComercio, Long dni, String patente, double importe , LocalDateTime fecha) {
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

	public Long getDni() {
		return dni;
	}

	public void setDni(Long dni) {
		this.dni = dni;
	}

	public String getPatente() {
		return patente;
	}

	public void setPatente(String patente) {
		this.patente = patente;
	}

	public double getImporte() {
		return importe;
	}

	public void setImporte(double importe) {
		this.importe = importe;
	}

	public LocalDateTime getFecha() {
		return fecha;
	}

	public void setFecha(LocalDateTime fecha) {
		this.fecha = fecha;
	}

	
	
	
	
}
