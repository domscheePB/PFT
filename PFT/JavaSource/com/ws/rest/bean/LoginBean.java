package com.ws.rest.bean;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.capa2LogicaNegocio.GestionUsuarioService;
import com.capa3Encriptado.Encriptado;
import com.ws.rest.entities.UsuarioRestEntidad;

@Stateless
@LocalBean
public class LoginBean implements Serializable
{	
	private static final long serialVersionUID = 1L;
	
	@EJB
	GestionUsuarioService gestionUsuarioService;

	public UsuarioRestEntidad login(UsuarioRestEntidad usuario)
	{
		boolean loginExitoso = false;
		loginExitoso = gestionUsuarioService.login(usuario.getUsuario(), Encriptado.ecnode(usuario.getClave()));
		
		if(loginExitoso)
		{
			usuario.setRol(gestionUsuarioService.obtenerRol(usuario.getUsuario()));
		}
		else
		{
			usuario.setRol(null);
			
		}
		return usuario;
	}
}