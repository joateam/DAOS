package com.daos.EstacionAR.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;


@Entity
public class Recarga {
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	/**
	 Nro Comercio (entero positivo)
	o Dni (entero positivo)
	o Patente (alfanumérico)
	o Importe (numero con dos decimales)
	 */
	
	private Long NroComercio;
	
	
	private Long dni;
	
	@Column(length=10)
	private String patente;
   
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "#.00")
	@Column(nullable=false,scale=2)
	private double importe;
	
	
	
}
