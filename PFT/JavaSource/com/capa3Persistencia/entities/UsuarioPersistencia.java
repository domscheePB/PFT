package com.capa3Persistencia.entities;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The persistent class for the USUARIOS database table.
 * 
 */
@Entity
@Table(name="USUARIOS")
public class UsuarioPersistencia implements Serializable
{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	@Column(name="ID_USUARIO", unique=true, nullable=false, precision=19)
	private Long idUsuario;

	@Column(nullable=false, length=50)
	private String apellido;

	@Column(nullable=false, length=50)
	private String contraseña;

	@Column(nullable=false, precision=10)
	private Integer documento;

	@Column(nullable=false, length=50)
	private String nombre;

	@Column(name="NOMBRE_USUARIO", nullable=false, length=50)
	private String nombreUsuario;

	public UsuarioPersistencia() {
	}

	public Long getIdUsuario() {
		return this.idUsuario;
	}

	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getApellido() {
		return this.apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getContraseña() {
		return this.contraseña;
	}

	public void setContraseña(String contraseña) {
		this.contraseña = contraseña;
	}

	public Integer getDocumento() {
		return this.documento;
	}

	public void setDocumento(Integer documento) {
		this.documento = documento;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getNombreUsuario() {
		return this.nombreUsuario;
	}

	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}
}