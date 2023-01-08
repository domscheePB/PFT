package com.capa2LogicaNegocioEntities;

import java.util.Date;

import javax.validation.constraints.NotNull;

public class HistoricoAlimento
{
	@NotNull
	private Long id;
	@NotNull
	private float costoAlim;
	@NotNull
	private Date fechaAlim;
	@NotNull
	private Alimento alimento;
	@NotNull
	private Ternera ternera;
	@NotNull
	private String guachera;

	public HistoricoAlimento()
	{
		super();
	}

	public HistoricoAlimento(@NotNull Long id, @NotNull float costoAlim, @NotNull Date fechaAlim,
			@NotNull Alimento alimento, @NotNull Ternera ternera, @NotNull String guachera) {
		super();
		this.id = id;
		this.costoAlim = costoAlim;
		this.fechaAlim = fechaAlim;
		this.alimento = alimento;
		this.ternera = ternera;
		this.guachera = guachera;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public float getCostoAlim() {
		return costoAlim;
	}

	public void setCostoAlim(float costoAlim) {
		this.costoAlim = costoAlim;
	}

	public Date getFechaAlim() {
		return fechaAlim;
	}

	public void setFechaAlim(Date fechaAlim) {
		this.fechaAlim = fechaAlim;
	}

	public Alimento getAlimento() {
		return alimento;
	}

	public void setAlimento(Alimento alimento) {
		this.alimento = alimento;
	}

	public Ternera getTernera() {
		return ternera;
	}

	public void setTernera(Ternera ternera) {
		this.ternera = ternera;
	}

	public String getGuachera() {
		return guachera;
	}

	public void setGuachera(String guachera) {
		this.guachera = guachera;
	}
}
