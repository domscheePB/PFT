package com.capa3Persistencia.entities;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The persistent class for the TIPO_GUACHERAS database table.
 * 
 */
@Entity
@Table(name="TIPO_GUACHERAS")
public class TipoGuacheraPersistencia implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	@Column(name="ID_TIPO_GUACHERA", unique=true, nullable=false, precision=19)
	private Long idTipoGuachera;

	@Column(nullable=false, length=50)
	private String tipo;

	public TipoGuacheraPersistencia() {
	}

	public Long getIdTipoGuachera() {
		return this.idTipoGuachera;
	}

	public void setIdTipoGuachera(Long idTipoGuachera) {
		this.idTipoGuachera = idTipoGuachera;
	}

	public String getTipo() {
		return this.tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
}