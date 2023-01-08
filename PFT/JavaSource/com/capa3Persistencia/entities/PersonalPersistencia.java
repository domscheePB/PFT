package com.capa3Persistencia.entities;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The persistent class for the PERSONAL database table.
 * 
 */
@Entity
@Table(name="PERSONAL")
public class PersonalPersistencia implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	@Column(name="ID_PERSONAL", unique=true, nullable=false, precision=19)
	private Long idPersonal;

	@Column(name="CANTIDAD_DE_HORAS", precision=126)
	private float cantidadDeHoras;

	//bi-directional many-to-one association to Usuario
	@ManyToOne
	@JoinColumn(name="ID_USUARIO", nullable=false)
	private UsuarioPersistencia usuario;

	public PersonalPersistencia() {
	}

	public Long getIdPersonal() {
		return this.idPersonal;
	}

	public void setIdPersonal(Long idPersonal) {
		this.idPersonal = idPersonal;
	}

	public float getCantidadDeHoras() {
		return this.cantidadDeHoras;
	}

	public void setCantidadDeHoras(float cantidadDeHoras) {
		this.cantidadDeHoras = cantidadDeHoras;
	}

	public UsuarioPersistencia getUsuario() {
		return this.usuario;
	}

	public void setUsuario(UsuarioPersistencia usuario) {
		this.usuario = usuario;
	}
}