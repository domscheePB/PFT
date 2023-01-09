package com.ws.rest.entities;

import java.util.Date;

import javax.validation.constraints.NotNull;

public class TerneraRestEntidad
{
	@NotNull
	private Long id;
	@NotNull
	private String caravanasnis;
	@NotNull
	private String caravanatambo;
	@NotNull
	private Date fechaNacimiento;
	@NotNull
	private int idMadre;
	@NotNull
	private int idPadre;
	@NotNull
	private float pesoAlNacer;
	@NotNull
	private String tipoDeParto;
	@NotNull
	private String raza;
	@NotNull
	private String identificadorGuachera;
	
	public TerneraRestEntidad() {
		super();
	}

	public TerneraRestEntidad(@NotNull Long id, @NotNull String caravanasnis, @NotNull String caravanatambo,
			@NotNull Date fechaNacimiento, @NotNull int idMadre, @NotNull int idPadre, @NotNull float pesoAlNacer,
			@NotNull String tipoDeParto, @NotNull String raza, @NotNull String identificadorGuachera) {
		super();
		this.id = id;
		this.caravanasnis = caravanasnis;
		this.caravanatambo = caravanatambo;
		this.fechaNacimiento = fechaNacimiento;
		this.idMadre = idMadre;
		this.idPadre = idPadre;
		this.pesoAlNacer = pesoAlNacer;
		this.tipoDeParto = tipoDeParto;
		this.raza = raza;
		this.identificadorGuachera = identificadorGuachera;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCaravanasnis() {
		return caravanasnis;
	}

	public void setCaravanasnis(String caravanasnis) {
		this.caravanasnis = caravanasnis;
	}

	public String getCaravanatambo() {
		return caravanatambo;
	}

	public void setCaravanatambo(String caravanatambo) {
		this.caravanatambo = caravanatambo;
	}

	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public int getIdMadre() {
		return idMadre;
	}

	public void setIdMadre(int idMadre) {
		this.idMadre = idMadre;
	}

	public int getIdPadre() {
		return idPadre;
	}

	public void setIdPadre(int idPadre) {
		this.idPadre = idPadre;
	}

	public float getPesoAlNacer() {
		return pesoAlNacer;
	}

	public void setPesoAlNacer(float pesoAlNacer) {
		this.pesoAlNacer = pesoAlNacer;
	}

	public String getTipoDeParto() {
		return tipoDeParto;
	}

	public void setTipoDeParto(String tipoDeParto) {
		this.tipoDeParto = tipoDeParto;
	}

	public String getRaza() {
		return raza;
	}

	public void setRaza(String raza) {
		this.raza = raza;
	}

	public String getIdentificadorGuachera() {
		return identificadorGuachera;
	}

	public void setIdentificadorGuachera(String identificadorGuachera) {
		this.identificadorGuachera = identificadorGuachera;
	}
}
