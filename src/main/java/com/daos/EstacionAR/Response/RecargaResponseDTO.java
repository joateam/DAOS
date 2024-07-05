package com.daos.EstacionAR.Response;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.hateoas.RepresentationModel;

import com.daos.EstacionAR.Entity.Recarga;
import com.fasterxml.jackson.annotation.JsonFormat;





public class RecargaResponseDTO extends RepresentationModel<RecargaResponseDTO> {
		
		private Long id;

		private Long nroComercio;
			
		private Integer dni;
		
		private String patente;
	   
		private Double importe;
		
		private	LocalDate fecha;

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

		@Override
		public String toString() {
			return "RecargaResponseDTO [id=" + id + ", nroComercio=" + nroComercio + ", dni=" + dni + ", patente="
					+ patente + ", importe=" + importe + ", fecha=" + fecha + ", hasLinks()=" + hasLinks() + "]";
		}
		
		
		
}
