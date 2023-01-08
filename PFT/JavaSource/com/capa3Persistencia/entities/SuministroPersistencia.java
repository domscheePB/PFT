package com.capa3Persistencia.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;

/**
 * The persistent class for the SUMINISTROS database table.
 * 
 */
@Entity
@Table(name="SUMINISTROS")
public class SuministroPersistencia implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	@Column(name="ID_SUMINISTRO", unique=true, nullable=false, precision=19)
	private Long idSuministro;

	@Column(name="COSTO_SUMINISTRO", nullable=false, precision=126)
	private float costoSuministro;

	@Temporal(TemporalType.DATE)
	@Column(name="FECHA_APLICACION", nullable=false)
	private Date fechaAplicacion;

	//bi-directional many-to-one association to Medicamento
	@ManyToOne
	@JoinColumn(name="ID_MEDICAMENTO", nullable=false)
	private MedicamentoPersistencia medicamento;

	//bi-directional many-to-one association to Ternera
	@ManyToOne
	@JoinColumn(name="ID_TERNERA", nullable=false)
	private TerneraPersistencia ternera;

	public SuministroPersistencia() {
	}

	public Long getIdSuministro() {
		return this.idSuministro;
	}

	public void setIdSuministro(Long idSuministro) {
		this.idSuministro = idSuministro;
	}

	public float getCostoSuministro() {
		return this.costoSuministro;
	}

	public void setCostoSuministro(float costoSuministro) {
		this.costoSuministro = costoSuministro;
	}

	public Date getFechaAplicacion() {
		return this.fechaAplicacion;
	}

	public void setFechaAplicacion(Date fechaAplicacion) {
		this.fechaAplicacion = fechaAplicacion;
	}

	public MedicamentoPersistencia getMedicamento() {
		return this.medicamento;
	}

	public void setMedicamento(MedicamentoPersistencia medicamento) {
		this.medicamento = medicamento;
	}

	public TerneraPersistencia getTernera() {
		return this.ternera;
	}

	public void setTernera(TerneraPersistencia ternera) {
		this.ternera = ternera;
	}

}