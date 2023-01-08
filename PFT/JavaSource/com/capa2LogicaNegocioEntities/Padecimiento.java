package com.capa2LogicaNegocioEntities;

import java.util.Date;

import javax.validation.constraints.NotNull;

public class Padecimiento
{
	@NotNull
	private Long id;
	@NotNull
	private Date fechaInicio;
	@NotNull
	private Date fechaFinal;
	@NotNull
	private Enfermedad enfermedad;
	@NotNull
	private Gravedad gravedad;
	@NotNull
	private Ternera ternera;

	public Padecimiento() {
		super();
	}
	
	public Padecimiento(@NotNull Long id, @NotNull Date fechaInicio, @NotNull Date fechaFinal,
			@NotNull Enfermedad enfermedad, @NotNull Gravedad gravedad, @NotNull Ternera ternera) {
		super();
		this.id = id;
		this.fechaInicio = fechaInicio;
		this.fechaFinal = fechaFinal;
		this.enfermedad = enfermedad;
		this.gravedad = gravedad;
		this.ternera = ternera;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public Date getFechaFinal() {
		return fechaFinal;
	}

	public void setFechaFinal(Date fechaFinal) {
		this.fechaFinal = fechaFinal;
	}

	public Enfermedad getEnfermedad() {
		return enfermedad;
	}

	public void setEnfermedad(Enfermedad enfermedad) {
		this.enfermedad = enfermedad;
	}
	
	public Gravedad getGravedad() {
		return gravedad;
	}

	public void setGravedad(Gravedad gravedad) {
		this.gravedad = gravedad;
	}

	public Ternera getTernera() {
		return ternera;
	}

	public void setTernera(Ternera ternera) {
		this.ternera = ternera;
	}
}
