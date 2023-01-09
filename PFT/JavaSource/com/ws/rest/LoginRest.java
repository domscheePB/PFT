package com.ws.rest;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ws.rs.core.Response;

import com.ws.rest.bean.LoginBean;
import com.ws.rest.entities.UsuarioRestEntidad;

@Stateless
@LocalBean
public class LoginRest implements ILoginRest
{	
	@EJB
	LoginBean loginBean;
	
	public LoginRest() {
	}

	@Override
	public String echo() {
		return "Servicio usuarios disponible";
	}

	@Override
	public Response loginRest(UsuarioRestEntidad usuario)
	{
		System.out.println("Usuario " + usuario);
		usuario = loginBean.login(usuario);
		if(usuario.getRol() == null)
		{
			return Response.status(404).build();
		}
		else
		{
			return Response.ok(usuario).build();
		}
	}
}
