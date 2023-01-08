package com.ws.rest.entities;

import java.util.Date;

public class HEnfermedadRestEntidad
{
	private Long id;
	private String identificadorTernera;
	private String enfermedad;
	private String variante;
	private String gravedad;
	private Date fechaRegistro;
	
	public HEnfermedadRestEntidad() {
		super();
	}

	public HEnfermedadRestEntidad(Long id, String identificadorTernera, String enfermedad, String variante,
			String gravedad, Date fechaRegistro) {
		super();
		this.id = id;
		this.identificadorTernera = identificadorTernera;
		this.enfermedad = enfermedad;
		this.variante = variante;
		this.gravedad = gravedad;
		this.fechaRegistro = fechaRegistro;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getIdentificadorTernera() {
		return identificadorTernera;
	}

	public void setIdentificadorTernera(String identificadorTernera) {
		this.identificadorTernera = identificadorTernera;
	}

	public String getEnfermedad() {
		return enfermedad;
	}

	public void setEnfermedad(String enfermedad) {
		this.enfermedad = enfermedad;
	}

	public String getVariante() {
		return variante;
	}

	public void setVariante(String variante) {
		this.variante = variante;
	}

	public String getGravedad() {
		return gravedad;
	}

	public void setGravedad(String gravedad) {
		this.gravedad = gravedad;
	}

	public Date getFechaRegistro() {
		return fechaRegistro;
	}

	public void setFechaRegistro(Date fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}
}
