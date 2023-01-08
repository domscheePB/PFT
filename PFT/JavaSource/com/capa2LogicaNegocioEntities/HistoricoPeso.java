package com.capa2LogicaNegocioEntities;

import java.util.Date;

import javax.validation.constraints.NotNull;

public class HistoricoPeso
{
	@NotNull
	private Long id;
	@NotNull
	private Date fecha;
	@NotNull
	private float peso;
	@NotNull
	private Ternera ternera;
	private Usuario usuario;
	private String identificadorGuachera;

	public HistoricoPeso() {
		super();
	}

	public HistoricoPeso(@NotNull Long id, @NotNull Date fecha, @NotNull float peso, @NotNull Ternera ternera,
			Usuario usuario, String identificadorGuachera) {
		super();
		this.id = id;
		this.fecha = fecha;
		this.peso = peso;
		this.ternera = ternera;
		this.usuario = usuario;
		this.identificadorGuachera = identificadorGuachera;
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

	public float getPeso() {
		return peso;
	}

	public void setPeso(float peso) {
		this.peso = peso;
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

	public String getIdentificadorGuachera() {
		return identificadorGuachera;
	}

	public void setIdentificadorGuachera(String identificadorGuachera) {
		this.identificadorGuachera = identificadorGuachera;
	}
}
