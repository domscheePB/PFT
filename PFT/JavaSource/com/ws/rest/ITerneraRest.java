package com.ws.rest;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.ws.rest.entities.TerneraRestEntidad;

@Path("/ternera")
public interface ITerneraRest
{
	@GET
	@Path("echo")
	@Produces({MediaType.TEXT_PLAIN})
	public String echo();

	@GET
	@Path("obtener")
	@Produces(MediaType.APPLICATION_JSON)
	public Response obtenerTerneraRest();
	
	@POST
	@Path("registrar")
	@Produces(MediaType.APPLICATION_JSON)
	public Response registrarTerneraRest(TerneraRestEntidad ternera);
	
	@GET
	@Path("obtenerRazas")
	@Produces(MediaType.APPLICATION_JSON)
	public Response obtenerRazasRest();
	
	@GET
	@Path("obtenerGuacheras")
	@Produces(MediaType.APPLICATION_JSON)
	public Response obtenerGuacherasRest();
}