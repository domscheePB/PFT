package com.capa2LogicaNegocioEntities;

import javax.validation.constraints.NotNull;

public class TipoGuachera
{
	@NotNull
	private Long id;
	@NotNull
	private String tipo;

	public TipoGuachera() {
		super();
	}

	public TipoGuachera(Long id, String tipo)
	{
		super();
		this.id = id;
		this.tipo = tipo;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
}