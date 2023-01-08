package com.capa3Persistencia.entities;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The persistent class for the NOMBRES_ENFERMEDADES database table.
 * 
 */
@Entity
@Table(name="NOMBRES_ENFERMEDADES")
public class NombresEnfermedadPersistencia implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	@Column(name="ID_NOMENFERMEDAD", unique=true, nullable=false, precision=19)
	private Long idNomenfermedad;

	@Column(name="NOMBRE_ENFER", nullable=false, length=50)
	private String nombreEnfer;

	public NombresEnfermedadPersistencia() {
	}

	public Long getIdNomenfermedad() {
		return this.idNomenfermedad;
	}

	public void setIdNomenfermedad(Long idNomenfermedad) {
		this.idNomenfermedad = idNomenfermedad;
	}

	public String getNombreEnfer() {
		return this.nombreEnfer;
	}

	public void setNombreEnfer(String nombreEnfer) {
		this.nombreEnfer = nombreEnfer;
	}
}