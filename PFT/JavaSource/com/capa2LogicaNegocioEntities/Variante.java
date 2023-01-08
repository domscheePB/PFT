package com.capa2LogicaNegocioEntities;

import javax.validation.constraints.NotNull;

public class Variante
{
	@NotNull
	private Long id;
	@NotNull
	private String nombre;

	public Variante() {
		super();
	}

	public Variante(@NotNull Long id, @NotNull String nombre) {
		super();
		this.id = id;
		this.nombre = nombre;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}	
}
