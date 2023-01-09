package com.ws.rest.entities;

public class UsuarioRestEntidad
{
	private String usuario;
	private String clave;
	private String rol;
	
	public UsuarioRestEntidad() {
		super();
	}

	public UsuarioRestEntidad(String usuario, String clave, String rol) {
		super();
		this.usuario = usuario;
		this.clave = clave;
		this.rol = rol;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public String getRol() {
		return rol;
	}

	public void setRol(String rol) {
		this.rol = rol;
	}

	@Override
	public String toString() {
		return "UsuarioRestEntidad [usuario=" + usuario + ", clave=" + clave + ", rol=" + rol + "]";
	}
}
