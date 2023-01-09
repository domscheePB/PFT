package com.capa2LogicaNegocioEntities;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

public class Usuario
{
	@NotNull
	private Long id;
	@NotNull
	@Length(min=3,	max=50)
	private String nombre;
	@NotNull
	@Length(min=3,	max=50)
	private String apellido;
	@NotNull
	private Integer documento;
	@NotNull
	private String contraseña;
	@NotNull
	private String nombreUsuario;

	public Usuario() {
		super();
	}
	
	public Usuario(Long id, String nombre, String apellido, Integer documento, String contraseña, String nombreUsuario)
	{
		super();
		this.id = id;
		this.nombre = nombre;
		this.apellido = apellido;
		this.documento = documento;
		this.contraseña = contraseña;
		this.nombreUsuario = nombreUsuario;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public Integer getDocumento() {
		return documento;
	}

	public void setDocumento(Integer documento) {
		this.documento = documento;
	}

	public String getContraseña() {
		return contraseña;
	}

	public void setContraseña(String contraseña) {
		this.contraseña = contraseña;
	}

	public String getNombreUsuario() {
		return nombreUsuario;
	}

	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}

	@Override
	public String toString() {
		return "Usuario [id=" + id + ", nombre=" + nombre + ", apellido=" + apellido + ", documento="
				+ documento + ", nombreUsuario=" + nombreUsuario + "]";
	}
}
