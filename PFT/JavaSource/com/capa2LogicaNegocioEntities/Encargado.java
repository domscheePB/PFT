package com.capa2LogicaNegocioEntities;

import javax.validation.constraints.NotNull;

public class Encargado
{
	@NotNull
	private Long id;
	@NotNull
	private Usuario usuario;
	@NotNull
	private TituloProfesional titulo;

	public Encargado() {
		super();
	}
	
	public Encargado(Long id, Usuario usuario, TituloProfesional titulo)
	{
		super();
		this.id = id;
		this.usuario = usuario;
		this.titulo = titulo;
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

	public TituloProfesional getTitulo() {
		return titulo;
	}

	public void setTitulo(TituloProfesional titulo) {
		this.titulo = titulo;
	}
}
