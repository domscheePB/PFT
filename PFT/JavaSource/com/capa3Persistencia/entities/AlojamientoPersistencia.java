package com.capa3Persistencia.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the ALOJAMIENTOS database table.
 * 
 */
@Entity
@Table(name="ALOJAMIENTOS")
public class AlojamientoPersistencia implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	@Column(name="ID_ALOJAMIENTO", unique=true, nullable=false, precision=19)
	private Long idAlojamiento;

	@Temporal(TemporalType.DATE)
	@Column(name="FECHA_ENTRADA", nullable=false)
	private Date fechaEntrada;

	@Temporal(TemporalType.DATE)
	@Column(name="FECHA_SALIDA")
	private Date fechaSalida;

	//bi-directional many-to-one association to Guachera
	@ManyToOne
	@JoinColumn(name="ID_GUACHERA", nullable=false)
	private GuacheraPersistencia guachera;

	//bi-directional many-to-one association to Ternera
	@ManyToOne
	@JoinColumn(name="ID_TERNERA", nullable=false)
	private TerneraPersistencia ternera;

	public AlojamientoPersistencia() {
	}

	public Long getIdAlojamiento() {
		return this.idAlojamiento;
	}

	public void setIdAlojamiento(Long idAlojamiento) {
		this.idAlojamiento = idAlojamiento;
	}

	public Date getFechaEntrada() {
		return this.fechaEntrada;
	}

	public void setFechaEntrada(Date fechaEntrada) {
		this.fechaEntrada = fechaEntrada;
	}

	public Date getFechaSalida() {
		return this.fechaSalida;
	}

	public void setFechaSalida(Date fechaSalida) {
		this.fechaSalida = fechaSalida;
	}

	public GuacheraPersistencia getGuachera() {
		return this.guachera;
	}

	public void setGuachera(GuacheraPersistencia guachera) {
		this.guachera = guachera;
	}

	public TerneraPersistencia getTernera() {
		return this.ternera;
	}

	public void setTernera(TerneraPersistencia ternera) {
		this.ternera = ternera;
	}

}