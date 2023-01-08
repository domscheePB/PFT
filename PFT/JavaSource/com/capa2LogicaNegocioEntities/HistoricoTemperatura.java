package com.capa2LogicaNegocioEntities;

import java.util.Date;

import javax.validation.constraints.NotNull;

public class HistoricoTemperatura
{
	@NotNull
	private Long id;
	@NotNull
	private Date fecha;
	@NotNull
	private float temperatura;
	@NotNull
	private Ternera ternera;
	@NotNull
	private Usuario usuario;

	public HistoricoTemperatura() {
		super();
	}

	public HistoricoTemperatura(@NotNull Long id, @NotNull Date fecha, @NotNull float temperatura, @NotNull Ternera ternera,
			@NotNull Usuario usuario) {
		super();
		this.id = id;
		this.fecha = fecha;
		this.temperatura = temperatura;
		this.ternera = ternera;
		this.usuario = usuario;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public float getTemperatura() {
		return temperatura;
	}

	public void setTemperatura(float temperatura) {
		this.temperatura = temperatura;
	}

	public Ternera getTernera() {
		return ternera;
	}

	public void setTernera(Ternera ternera) {
		this.ternera = ternera;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
}
