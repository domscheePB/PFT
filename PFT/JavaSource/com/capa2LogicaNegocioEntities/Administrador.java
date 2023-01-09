package com.capa2LogicaNegocioEntities;

import javax.validation.constraints.NotNull;

public class Administrador
{
	@NotNull
	private Long id;
	@NotNull
	private Usuario usuario;

	public Administrador() {
		super();
	}
	
	public Administrador(Long id, Usuario usuario)
	{
		super();
		this.id = id;
		this.usuario = usuario;
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
}
