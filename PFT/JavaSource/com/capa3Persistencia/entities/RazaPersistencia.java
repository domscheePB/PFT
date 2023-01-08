package com.capa3Persistencia.entities;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The persistent class for the RAZAS database table.
 * 
 */
@Entity
@Table(name="RAZAS")
public class RazaPersistencia implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	@Column(name="ID_RAZA", unique=true, nullable=false, precision=19)
	private Long idRaza;

	@Column(nullable=false, length=50)
	private String raza;

	public RazaPersistencia() {
	}

	public Long getIdRaza() {
		return this.idRaza;
	}

	public void setIdRaza(Long idRaza) {
		this.idRaza = idRaza;
	}

	public String getRaza() {
		return raza;
	}

	public void setRaza(String raza) {
		this.raza = raza;
	}
}