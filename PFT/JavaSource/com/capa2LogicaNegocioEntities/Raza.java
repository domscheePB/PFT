package com.capa2LogicaNegocioEntities;

import javax.validation.constraints.NotNull;

public class Raza
{
	@NotNull
	private Long id;
	@NotNull
	private String nombre;

	public Raza()
	{
		super();
	}

	public Raza(Long id, String nombre)
	{
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
