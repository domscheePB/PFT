package com.capa3Persistencia.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the ENCARGADOS database table.
 * 
 */
@Entity
@Table(name="ENCARGADOS")
public class EncargadoPersistencia implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	@Column(name="ID_ENCARGADO", unique=true, nullable=false, precision=19)
	private Long idEncargado;

	//bi-directional many-to-one association to TitulosProfesionale
	@ManyToOne
	@JoinColumn(name="ID_TITULO_PROFESIONAL", nullable=false)
	private TitulosProfesionalPersistencia titulosProfesionale;

	//bi-directional many-to-one association to Usuario
	@ManyToOne
	@JoinColumn(name="ID_USUARIO", nullable=false)
	private UsuarioPersistencia usuario;

	public EncargadoPersistencia() {
	}

	public Long getIdEncargado() {
		return this.idEncargado;
	}

	public void setIdEncargado(Long idEncargado) {
		this.idEncargado = idEncargado;
	}

	public TitulosProfesionalPersistencia getTitulosProfesionale() {
		return this.titulosProfesionale;
	}

	public void setTitulosProfesionale(TitulosProfesionalPersistencia titulosProfesionale) {
		this.titulosProfesionale = titulosProfesionale;
	}

	public UsuarioPersistencia getUsuario() {
		return this.usuario;
	}

	public void setUsuario(UsuarioPersistencia usuario) {
		this.usuario = usuario;
	}

}