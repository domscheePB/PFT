package com.capa3Persistencia.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the ADMINISTRADORES database table.
 * 
 */
@Entity
@Table(name="ADMINISTRADORES")
public class AdministradorPersistencia implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	@Column(name="ID_ADMINISTRADOR", unique=true, nullable=false, precision=19)
	private Long idAdministrador;

	@ManyToOne
	@JoinColumn(name="ID_USUARIO", nullable=false)
	private UsuarioPersistencia usuario;

	public AdministradorPersistencia() {
	}

	public Long getIdAdministrador() {
		return this.idAdministrador;
	}

	public void setIdAdministrador(Long idAdministrador) {
		this.idAdministrador = idAdministrador;
	}

	public UsuarioPersistencia getUsuario() {
		return this.usuario;
	}

	public void setUsuario(UsuarioPersistencia usuario) {
		this.usuario = usuario;
	}
}