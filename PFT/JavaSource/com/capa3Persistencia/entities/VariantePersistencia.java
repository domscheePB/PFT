package com.capa3Persistencia.entities;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The persistent class for the VARIANTES database table.
 * 
 */
@Entity
@Table(name="VARIANTES")
public class VariantePersistencia implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	@Column(name="ID_VARIANTE", unique=true, nullable=false, precision=19)
	private Long idVariante;

	@Column(nullable=false, length=100)
	private String nombre;

	public VariantePersistencia() {
	}

	public Long getIdVariante() {
		return this.idVariante;
	}

	public void setIdVariante(Long idVariante) {
		this.idVariante = idVariante;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
}