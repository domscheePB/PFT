package com.capa2LogicaNegocioEntities;

import javax.validation.constraints.NotNull;

public class TituloProfesional
{
	@NotNull
	private Long id;
	@NotNull
	private String titulo;

	public TituloProfesional()
	{
		super();
	}
	
	public TituloProfesional(Long id, String titulo)
	{
		super();
		this.id = id;
		this.titulo = titulo;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
}
