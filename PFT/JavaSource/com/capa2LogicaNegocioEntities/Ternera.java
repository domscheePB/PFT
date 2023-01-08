package com.capa2LogicaNegocioEntities;

import java.util.Date;

import javax.validation.constraints.NotNull;

public class Ternera
{
	@NotNull
	private Long id;
	private String caravanasnis;
	@NotNull
	private String caravanatambo;
	@NotNull
	private Date fechaNacimiento;
	@NotNull
	private int idMadre;
	@NotNull
	private int idPadre;
	private float pesoAlNacer;
	@NotNull
	private String tipoDeParto;
	@NotNull
	private Raza raza;
	
	public Ternera() {
		super();
	}

	public Ternera(Long id, String caravanasnis, String caravanatambo,
			Date fechaNacimiento, int idMadre, int idPadre, float pesoAlNacer,
			String tipoDeParto, Raza raza) {
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

	public Raza getRaza() {
		return raza;
	}

	public void setRaza(Raza raza) {
		this.raza = raza;
	}
}
