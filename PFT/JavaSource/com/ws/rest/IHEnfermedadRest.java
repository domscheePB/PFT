package com.ws.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/enfermedad")
public interface IHEnfermedadRest
{
	@GET
	@Path("echo")
	@Produces({MediaType.TEXT_PLAIN})
	public String echo();

	@GET
	@Path("obtener")
	@Produces(MediaType.APPLICATION_JSON)
	public Response obtenerHEnfermedadRest();
}