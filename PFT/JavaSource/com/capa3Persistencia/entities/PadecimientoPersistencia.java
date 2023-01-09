package com.capa3Persistencia.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the PADECIMIENTOS database table.
 * 
 */
@Entity
@Table(name="PADECIMIENTOS")
public class PadecimientoPersistencia implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	@Column(name="ID_PADECIMIENTO", unique=true, nullable=false, precision=19)
	private Long idPadecimiento;

	@Temporal(TemporalType.DATE)
	@Column(name="FECHA_INICIO", nullable=false)
	private Date fechaInicio;
	
	@Temporal(TemporalType.DATE)
	@Column(name="FECHA_FINAL")
	private Date fechaFinal;

	//bi-directional many-to-one association to Enfermedade
	@ManyToOne
	@JoinColumn(name="ID_ENFERMEDAD", nullable=false)
	private EnfermedadPersistencia enfermedade;

	//bi-directional many-to-one association to Gravedad
	@ManyToOne
	@JoinColumn(name="ID_GRAVEDAD", nullable=false)
	private GravedadPersistencia gravedad;

	//bi-directional many-to-one association to Ternera
	@ManyToOne
	@JoinColumn(name="ID_TERNERA", nullable=false)
	private TerneraPersistencia ternera;

	public PadecimientoPersistencia() {
	}

	public Long getIdPadecimiento() {
		return this.idPadecimiento;
	}

	public void setIdPadecimiento(Long idPadecimiento) {
		this.idPadecimiento = idPadecimiento;
	}

	public Date getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public Date getFechaFinal() {
		return fechaFinal;
	}

	public void setFechaFinal(Date fechaFinal) {
		this.fechaFinal = fechaFinal;
	}

	public EnfermedadPersistencia getEnfermedade() {
		return this.enfermedade;
	}

	public void setEnfermedade(EnfermedadPersistencia enfermedade) {
		this.enfermedade = enfermedade;
	}

	public GravedadPersistencia getGravedad() {
		return this.gravedad;
	}

	public void setGravedad(GravedadPersistencia gravedad) {
		this.gravedad = gravedad;
	}

	public TerneraPersistencia getTernera() {
		return this.ternera;
	}

	public void setTernera(TerneraPersistencia ternera) {
		this.ternera = ternera;
	}

}