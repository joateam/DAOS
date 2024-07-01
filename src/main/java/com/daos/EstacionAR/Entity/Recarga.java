package com.daos.EstacionAR.Entity;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;



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

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate fecha;
	
	private Integer abonado;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getNroComercio() {
		return this.nroComercio;
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
