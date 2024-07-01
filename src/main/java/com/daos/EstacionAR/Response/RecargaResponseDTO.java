package com.daos.EstacionAR.Response;

import java.time.LocalDateTime;

import org.springframework.hateoas.RepresentationModel;

import com.daos.EstacionAR.Entity.Recarga;
import com.fasterxml.jackson.annotation.JsonFormat;





public class RecargaResponseDTO extends RepresentationModel<RecargaResponseDTO> {
		
		private Long id;

		private Long nroComercio;
			
		private Long dni;
		
		private String patente;
	   
		private double importe;
		
		private	LocalDateTime fecha;

		public RecargaResponseDTO(Recarga pojo) {
			super();
			this.id = pojo.getId();
			this.nroComercio = pojo.getNroComercio();
			this.dni = pojo.getDni();
			this.patente = pojo.getPatente();
			this.importe = pojo.getImporte();
			this.fecha = pojo.getFecha();
		}
		
		public RecargaResponseDTO() {
			super();
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

		@Override
		public String toString() {
			return "RecargaResponseDTO [id=" + id + ", nroComercio=" + nroComercio + ", dni=" + dni + ", patente="
					+ patente + ", importe=" + importe + ", fecha=" + fecha + ", hasLinks()=" + hasLinks() + "]";
		}
		
		
		
}
