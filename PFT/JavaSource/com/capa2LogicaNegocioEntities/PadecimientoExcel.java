package com.capa2LogicaNegocioEntities;

import java.util.Date;

public class PadecimientoExcel
{
	private int nroRegistro;
	private String identificadorTernera;
	private String nomEnfermedad;
	private String variante;
	private String gravedad;
	private Date fechaRegistro;
	private boolean error;

	public PadecimientoExcel() {
		super();
	}

	public PadecimientoExcel(int nroRegistro, String identificadorTernera, String nomEnfermedad, String variante,
			String gravedad, Date fechaRegistro, boolean error) {
		super();
		this.nroRegistro = nroRegistro;
		this.identificadorTernera = identificadorTernera;
		this.nomEnfermedad = nomEnfermedad;
		this.variante = variante;
		this.gravedad = gravedad;
		this.fechaRegistro = fechaRegistro;
		this.error = error;
	}

	public String getIdentificadorTernera() {
		return identificadorTernera;
	}

	public void setIdentificadorTernera(String identificadorTernera) {
		this.identificadorTernera = identificadorTernera;
	}

	public String getNomEnfermedad() {
		return nomEnfermedad;
	}

	public void setNomEnfermedad(String nomEnfermedad) {
		this.nomEnfermedad = nomEnfermedad;
	}

	public String getVariante() {
		return variante;
	}

	public void setVariante(String variante) {
		this.variante = variante;
	}

	public String getGravedad() {
		return gravedad;
	}

	public void setGravedad(String gravedad) {
		this.gravedad = gravedad;
	}

	public Date getFechaRegistro() {
		return fechaRegistro;
	}

	public void setFechaRegistro(Date fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}

	public boolean isError() {
		return error;
	}

	public void setError(boolean error) {
		this.error = error;
	}

	public int getNroRegistro() {
		return nroRegistro;
	}

	public void setNroRegistro(int nroRegistro) {
		this.nroRegistro = nroRegistro;
	}

	@Override
	public String toString() {
		return "PadecimientoExcel [nroRegistro=" + nroRegistro + ", identificadorTernera=" + identificadorTernera
				+ ", nomEnfermedad=" + nomEnfermedad + ", variante=" + variante + ", gravedad=" + gravedad
				+ ", fechaRegistro=" + fechaRegistro + ", error=" + error + "]";
	}
}
