package com.capa2LogicaNegocioEntities;

import javax.validation.constraints.NotNull;

public class Ubicacion
{
	@NotNull
	private Long id;
	@NotNull
	private float latitud;
	@NotNull
	private float longitud;

	public Ubicacion()
	{
		super();
	}

	public Ubicacion(Long id, float latitud, float longitud)
	{
		super();
		this.id = id;
		this.latitud = latitud;
		this.longitud = longitud;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public float getLatitud() {
		return latitud;
	}

	public void setLatitud(float latitud) {
		this.latitud = latitud;
	}

	public float getLongitud() {
		return longitud;
	}

	public void setLongitud(float longitud) {
		this.longitud = longitud;
	}
}
