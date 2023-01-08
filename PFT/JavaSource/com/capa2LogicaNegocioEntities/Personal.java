package com.capa2LogicaNegocioEntities;

import javax.validation.constraints.NotNull;

public class Personal
{
	@NotNull
	private Long id;
	@NotNull
	private Usuario usuario;
	@NotNull
	private float cantidadDeHoras;

	public Personal()
	{
		super();
	}
	
	public Personal(Long id, Usuario usuario, float cantidadDeHoras)
	{
		super();
		this.id = id;
		this.usuario = usuario;
		this.cantidadDeHoras = cantidadDeHoras;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public float getCantidadDeHoras() {
		return cantidadDeHoras;
	}

	public void setCantidadDeHoras(float cantidadDeHoras) {
		this.cantidadDeHoras = cantidadDeHoras;
	}
}
