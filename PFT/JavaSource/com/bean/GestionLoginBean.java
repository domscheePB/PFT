package com.bean;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import java.io.Serializable;
import javax.inject.Named;

import com.capa3Encriptado.Encriptado;
import com.capa2LogicaNegocio.GestionUsuarioService;
import com.utils.Session;

import javax.enterprise.context.SessionScoped;

@Named(value="gestionLogin")
@SessionScoped
public class GestionLoginBean implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	@EJB
	GestionUsuarioService gestionUsuarioService;
	
	private boolean funcionesAdministrador = false;
	private boolean funcionesEncargado = false;
	private String usuario;
	private String password;
	private boolean loginExitoso=false;
	private boolean mensaje=false;
	
	public GestionLoginBean()
	{
		super();
	}
	
	@PostConstruct
	public void init()
	{
		
	}
	
	public String login()
	{
		loginExitoso = gestionUsuarioService.login(usuario, Encriptado.ecnode(password));
		password = "";
		
		if(loginExitoso)
		{
			
			Session.getCurrentInstance().setLoggedUser(usuario);
			
			System.out.println("Entro");
			
			String rol = "";
			try
			{
				rol = gestionUsuarioService.obtenerRol(usuario);
			} catch (Exception e) 
			{
				e.printStackTrace();
			}
			
			if(rol.equals("Administrador"))
			{
				funcionesAdministrador = true;
				funcionesEncargado = true;
			}
			else if(rol.equals("Encargado"))
			{
				funcionesAdministrador = false;
				funcionesEncargado = true;
			}
			else
			{
				funcionesAdministrador = false;
				funcionesEncargado = false;
			}
			mensaje = false;
			return "index";
		}
		else
		{
			mensaje = true;
			return "Login";
		}
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public boolean isLoginExitoso() {
		return loginExitoso;
	}

	public void setLoginExitoso(boolean loginExitoso) {
		this.loginExitoso = loginExitoso;
	}

	public boolean isMensaje() {
		return mensaje;
	}

	public void setMensaje(boolean mensaje) {
		this.mensaje = mensaje;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isFuncionesAdministrador() {
		return funcionesAdministrador;
	}

	public void setFuncionesAdministrador(boolean funcionesAdministrador) {
		this.funcionesAdministrador = funcionesAdministrador;
	}

	public boolean isFuncionesEncargado() {
		return funcionesEncargado;
	}

	public void setFuncionesEncargado(boolean funcionesEncargado) {
		this.funcionesEncargado = funcionesEncargado;
	}
	
	
	
}