package com.capa2LogicaNegocioEntities;

import javax.validation.constraints.NotNull;

public class Alimento
{
	@NotNull
	private Long id;
	@NotNull
	private float cantidad;
	@NotNull
	private float costo;

	public Alimento()
	{
		super();
	}

	public Alimento(@NotNull Long id, @NotNull float cantidad, @NotNull float costo) {
		super();
		this.id = id;
		this.cantidad = cantidad;
		this.costo = costo;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public float getCantidad() {
		return cantidad;
	}

	public void setCantidad(float cantidad) {
		this.cantidad = cantidad;
	}

	public float getCosto() {
		return costo;
	}

	public void setCosto(float costo) {
		this.costo = costo;
	}
}
