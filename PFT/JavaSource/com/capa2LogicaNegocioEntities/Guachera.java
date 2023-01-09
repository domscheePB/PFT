package com.capa2LogicaNegocioEntities;

import javax.validation.constraints.NotNull;

public class Guachera
{
	@NotNull
	private Long id;
	@NotNull
	private String identificadorUnico;
	@NotNull
	private float ancho;
	@NotNull
	private float largo;
	@NotNull
	private int capacidad;
	@NotNull
	private TipoGuachera tipoGuachera;
	@NotNull
	private Ubicacion ubicacion;

	public Guachera() {
		super();
	}
	
	public Guachera(Long id, String identificadorUnico, float ancho, float largo, TipoGuachera tipoGuachera, Ubicacion ubicacion, int capacidad)
	{
		super();
		this.id = id;
		this.identificadorUnico = identificadorUnico;
		this.ancho = ancho;
		this.largo = largo;
		this.tipoGuachera = tipoGuachera;
		this.ubicacion = ubicacion;
		this.capacidad = capacidad;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getIdentificadorUnico() {
		return identificadorUnico;
	}

	public void setIdentificadorUnico(String identificadorUnico) {
		this.identificadorUnico = identificadorUnico;
	}

	public float getAncho() {
		return ancho;
	}

	public void setAncho(float ancho) {
		this.ancho = ancho;
	}

	public float getLargo() {
		return largo;
	}

	public void setLargo(float largo) {
		this.largo = largo;
	}

	public TipoGuachera getTipoGuachera() {
		return tipoGuachera;
	}

	public void setTipoGuachera(TipoGuachera tipoGuachera) {
		this.tipoGuachera = tipoGuachera;
	}

	public Ubicacion getUbicacion() {
		return ubicacion;
	}

	public void setUbicacion(Ubicacion ubicacion) {
		this.ubicacion = ubicacion;
	}

	public int getCapacidad() {
		return capacidad;
	}

	public void setCapacidad(int capacidad) {
		this.capacidad = capacidad;
	}
}
