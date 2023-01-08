package com.capa3Persistencia.entities;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The persistent class for the ENFERMEDADES database table.
 * 
 */
@Entity
@Table(name="ENFERMEDADES")
public class EnfermedadPersistencia implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	@Column(name="ID_ENFERMEDAD", unique=true, nullable=false, precision=19)
	private Long idEnfermedad;

	@Column(length=50)
	private String tratamiento;

	//bi-directional many-to-one association to NombresEnfermedade
	@ManyToOne
	@JoinColumn(name="ID_NOMENFERMEDAD", nullable=false)
	private NombresEnfermedadPersistencia nombresEnfermedade;

	//bi-directional many-to-one association to Variante
	@ManyToOne
	@JoinColumn(name="ID_VARIANTE", nullable=false)
	private VariantePersistencia variante;

	public EnfermedadPersistencia() {
	}

	public Long getIdEnfermedad() {
		return this.idEnfermedad;
	}

	public void setIdEnfermedad(Long idEnfermedad) {
		this.idEnfermedad = idEnfermedad;
	}

	public String getTratamiento() {
		return this.tratamiento;
	}

	public void setTratamiento(String tratamiento) {
		this.tratamiento = tratamiento;
	}

	public NombresEnfermedadPersistencia getNombresEnfermedade() {
		return this.nombresEnfermedade;
	}

	public void setNombresEnfermedade(NombresEnfermedadPersistencia nombresEnfermedade) {
		this.nombresEnfermedade = nombresEnfermedade;
	}

	public VariantePersistencia getVariante() {
		return this.variante;
	}

	public void setVariante(VariantePersistencia variante) {
		this.variante = variante;
	}
}