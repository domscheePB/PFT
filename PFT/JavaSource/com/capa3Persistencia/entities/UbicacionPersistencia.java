package com.capa3Persistencia.entities;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The persistent class for the UBICACIONES database table.
 * 
 */
@Entity
@Table(name="UBICACIONES")
public class UbicacionPersistencia implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	@Column(name="ID_UBICACION", unique=true, nullable=false, precision=19)
	private Long idUbicacion;

	@Column(nullable=false, precision=126)
	private float latitud;

	@Column(nullable=false, precision=126)
	private float longitud;

	public UbicacionPersistencia() {
	}

	public Long getIdUbicacion() {
		return this.idUbicacion;
	}

	public void setIdUbicacion(Long idUbicacion) {
		this.idUbicacion = idUbicacion;
	}

	public float getLatitud() {
		return this.latitud;
	}

	public void setLatitud(float latitud) {
		this.latitud = latitud;
	}

	public float getLongitud() {
		return this.longitud;
	}

	public void setLongitud(float longitud) {
		this.longitud = longitud;
	}
}