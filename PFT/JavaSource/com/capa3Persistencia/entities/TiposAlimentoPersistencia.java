package com.capa3Persistencia.entities;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The persistent class for the TIPOS_ALIMENTOS database table.
 * 
 */
@Entity
@Table(name="TIPOS_ALIMENTOS")
public class TiposAlimentoPersistencia implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	@Column(name="ID_NOM_ALI", unique=true, nullable=false, precision=19)
	private Long idNomAli;

	@Column(name="NOM_ALI", nullable=false, length=50)
	private String nomAli;

	public TiposAlimentoPersistencia() {
	}

	public Long getIdNomAli() {
		return this.idNomAli;
	}

	public void setIdNomAli(Long idNomAli) {
		this.idNomAli = idNomAli;
	}

	public String getNomAli() {
		return this.nomAli;
	}

	public void setNomAli(String nomAli) {
		this.nomAli = nomAli;
	}
}