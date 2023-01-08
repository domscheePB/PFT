package com.capa2LogicaNegocioEntities;

import javax.validation.constraints.NotNull;

public class NomEnfermedad
{
	@NotNull
	private Long id;
	@NotNull
	private String nombreEnfer;

	public NomEnfermedad() {
		super();
	}

	public NomEnfermedad(@NotNull Long id, @NotNull String nombreEnfer) {
		super();
		this.id = id;
		this.nombreEnfer = nombreEnfer;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombreEnfer() {
		return nombreEnfer;
	}

	public void setNombreEnfer(String nombreEnfer) {
		this.nombreEnfer = nombreEnfer;
	}
	
	
}
