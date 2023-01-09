package com.capa2LogicaNegocioEntities;

import javax.validation.constraints.NotNull;

public class Enfermedad
{
	@NotNull
	private Long id;
	@NotNull
	private String tratamiento;
	@NotNull
	private NomEnfermedad nombre;
	@NotNull
	private Variante variante;

	public Enfermedad() {
		super();
	}
	
	public Enfermedad(@NotNull Long id, @NotNull String tratamiento, @NotNull NomEnfermedad nombre,
			@NotNull Variante variante) {
		super();
		this.id = id;
		this.tratamiento = tratamiento;
		this.nombre = nombre;
		this.variante = variante;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTratamiento() {
		return tratamiento;
	}

	public void setTratamiento(String tratamiento) {
		this.tratamiento = tratamiento;
	}

	public NomEnfermedad getNombre() {
		return nombre;
	}

	public void setNombre(NomEnfermedad nombre) {
		this.nombre = nombre;
	}

	public Variante getVariante() {
		return variante;
	}

	public void setVariante(Variante variante) {
		this.variante = variante;
	}
}
