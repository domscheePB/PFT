package com.capa3Persistencia.entities;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The persistent class for the TITULOS_PROFESIONALES database table.
 * 
 */
@Entity
@Table(name="TITULOS_PROFESIONALES")
public class TitulosProfesionalPersistencia implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	@Column(name="ID_TITULO_PROFESIONAL", unique=true, nullable=false, precision=19)
	private Long idTituloProfesional;

	@Column(nullable=false, length=100)
	private String nombre;

	public TitulosProfesionalPersistencia() {
	}

	public Long getIdTituloProfesional() {
		return this.idTituloProfesional;
	}

	public void setIdTituloProfesional(Long idTituloProfesional) {
		this.idTituloProfesional = idTituloProfesional;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
}