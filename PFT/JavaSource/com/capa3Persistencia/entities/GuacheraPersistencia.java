package com.capa3Persistencia.entities;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The persistent class for the GUACHERAS database table.
 * 
 */
@Entity
@Table(name="GUACHERAS")
public class GuacheraPersistencia implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	@Column(name="ID_GUACHERA", unique=true, nullable=false, precision=19)
	private Long idGuachera;

	@Column(nullable=false, precision=126)
	private float ancho;

	@Column(nullable=false, precision=10)
	private int capacidad;

	@Column(name="IDENTIFICADOR_UNICO", nullable=false, length=50)
	private String identificadorUnico;

	@Column(nullable=false, precision=126)
	private float largo;

	//bi-directional many-to-one association to TipoGuachera
	@ManyToOne
	@JoinColumn(name="ID_TIPO_GUACHERA", nullable=false)
	private TipoGuacheraPersistencia tipoGuachera;

	//bi-directional many-to-one association to Ubicacione
	@ManyToOne
	@JoinColumn(name="ID_UBICACION", nullable=false)
	private UbicacionPersistencia ubicacione;

	public GuacheraPersistencia() {
	}

	public Long getIdGuachera() {
		return this.idGuachera;
	}

	public void setIdGuachera(Long idGuachera) {
		this.idGuachera = idGuachera;
	}

	public float getAncho() {
		return this.ancho;
	}

	public void setAncho(float ancho) {
		this.ancho = ancho;
	}

	public int getCapacidad() {
		return this.capacidad;
	}

	public void setCapacidad(int capacidad) {
		this.capacidad = capacidad;
	}

	public String getIdentificadorUnico() {
		return this.identificadorUnico;
	}

	public void setIdentificadorUnico(String identificadorUnico) {
		this.identificadorUnico = identificadorUnico;
	}

	public float getLargo() {
		return this.largo;
	}

	public void setLargo(float largo) {
		this.largo = largo;
	}

	public TipoGuacheraPersistencia getTipoGuachera() {
		return this.tipoGuachera;
	}

	public void setTipoGuachera(TipoGuacheraPersistencia tipoGuachera) {
		this.tipoGuachera = tipoGuachera;
	}

	public UbicacionPersistencia getUbicacione() {
		return this.ubicacione;
	}

	public void setUbicacione(UbicacionPersistencia ubicacione) {
		this.ubicacione = ubicacione;
	}

}