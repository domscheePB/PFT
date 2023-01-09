package com.capa2LogicaNegocioEntities;

import javax.validation.constraints.NotNull;

public class Gravedad
{
	@NotNull
	private Long id;
	@NotNull
	private String tipoGravedad;

	public Gravedad() {
		super();
	}

	public Gravedad(@NotNull Long id, @NotNull String tipoGravedad) {
		super();
		this.id = id;
		this.tipoGravedad = tipoGravedad;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTipoGravedad() {
		return tipoGravedad;
	}

	public void setTipoGravedad(String tipoGravedad) {
		this.tipoGravedad = tipoGravedad;
	}
}
