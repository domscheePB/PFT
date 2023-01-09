package com.ws.rest;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.ws.rest.entities.UsuarioRestEntidad;

@Path("/usuario")
public interface ILoginRest
{
	@GET
	@Path("echo")
	@Produces({MediaType.TEXT_PLAIN})
	public String echo();

	@POST
	@Path("login")
	@Produces(MediaType.APPLICATION_JSON)
	public Response loginRest(UsuarioRestEntidad usuario);
}