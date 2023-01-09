package com.capa3Persistencia.entities;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The persistent class for the GRAVEDAD database table.
 * 
 */
@Entity
@Table(name="GRAVEDAD")
public class GravedadPersistencia implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	@Column(name="ID_GRAVEDAD", unique=true, nullable=false, precision=19)
	private Long idGravedad;

	@Column(name="TIPO_GRAVEDAD", nullable=false, length=50)
	private String tipoGravedad;

	public GravedadPersistencia() {
	}

	public Long getIdGravedad() {
		return this.idGravedad;
	}

	public void setIdGravedad(Long idGravedad) {
		this.idGravedad = idGravedad;
	}

	public String getTipoGravedad() {
		return this.tipoGravedad;
	}

	public void setTipoGravedad(String tipoGravedad) {
		this.tipoGravedad = tipoGravedad;
	}
}