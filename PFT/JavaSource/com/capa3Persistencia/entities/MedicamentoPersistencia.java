package com.capa3Persistencia.entities;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The persistent class for the MEDICAMENTOS database table.
 * 
 */
@Entity
@Table(name="MEDICAMENTOS")
public class MedicamentoPersistencia implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	@Column(name="ID_MEDICAMENTO", unique=true, nullable=false, precision=19)
	private Long idMedicamento;

	@Column(nullable=false, precision=126)
	private float dosis;

	@Column(nullable=false, length=50)
	private String producto;

	@Column(nullable=false, length=50)
	private String tipo;

	public MedicamentoPersistencia() {
	}

	public Long getIdMedicamento() {
		return this.idMedicamento;
	}

	public void setIdMedicamento(Long idMedicamento) {
		this.idMedicamento = idMedicamento;
	}

	public float getDosis() {
		return this.dosis;
	}

	public void setDosis(float dosis) {
		this.dosis = dosis;
	}

	public String getProducto() {
		return this.producto;
	}

	public void setProducto(String producto) {
		this.producto = producto;
	}

	public String getTipo() {
		return this.tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
}