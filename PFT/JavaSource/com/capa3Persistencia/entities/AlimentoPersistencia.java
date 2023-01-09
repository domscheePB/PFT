package com.capa3Persistencia.entities;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The persistent class for the ALIMENTOS database table.
 * 
 */
@Entity
@Table(name="ALIMENTOS")
public class AlimentoPersistencia implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	@Column(name="ID_ALIMENTO", unique=true, nullable=false, precision=19)
	private Long idAlimento;

	@Column(nullable=false, precision=126)
	private float cantidad;

	@Column(nullable=false, precision=126)
	private float costo;
	
	@ManyToOne
	@JoinColumn(name="ID_NOM_ALI", nullable=false)
	private TiposAlimentoPersistencia tipoAlimento;

	public AlimentoPersistencia() {
	}

	public Long getIdAlimento() {
		return this.idAlimento;
	}

	public void setIdAlimento(Long idAlimento) {
		this.idAlimento = idAlimento;
	}

	public float getCantidad() {
		return this.cantidad;
	}

	public void setCantidad(float cantidad) {
		this.cantidad = cantidad;
	}

	public float getCosto() {
		return this.costo;
	}

	public void setCosto(float costo) {
		this.costo = costo;
	}

	public TiposAlimentoPersistencia getTipoAlimento() {
		return tipoAlimento;
	}

	public void setTipoAlimento(TiposAlimentoPersistencia tipoAlimento) {
		this.tipoAlimento = tipoAlimento;
	}
}