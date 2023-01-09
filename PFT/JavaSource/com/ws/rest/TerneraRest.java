package com.ws.rest;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ws.rs.core.Response;

import com.ws.rest.bean.TerneraBean;
import com.ws.rest.entities.TerneraRestEntidad;

@Stateless
@LocalBean
public class TerneraRest implements ITerneraRest
{
	@EJB
	TerneraBean terneraBean;
	
	public TerneraRest() {
	}

	@Override
	public String echo()
	{
		return "Servicio usuarios disponible";
	}

	@Override
	public Response obtenerTerneraRest()
	{
		List<TerneraRestEntidad> listaTerneras = terneraBean.obtenerTerneras();
		if(listaTerneras.size() != 0)
		{
			return Response.ok().entity(listaTerneras).build();
		}
		else
		{
			return Response.serverError().build();
		}
	}

	@Override
	public Response registrarTerneraRest(TerneraRestEntidad ternera)
	{
		ternera = terneraBean.crearTernera(ternera);
		
		if(ternera.getId() != null)
		{
			return Response.ok().entity(ternera).build();
		}
		else
		{
			return Response.serverError().build();
		}
	}

	@Override
	public Response obtenerRazasRest()
	{
		List<String> listaRazas = terneraBean.listaRazas();
		if(listaRazas.size() != 0)
		{
			return Response.ok().entity(listaRazas).build();
		}
		else
		{
			return Response.serverError().build();
		}
	}

	@Override
	public Response obtenerGuacherasRest()
	{
		List<String> listaGuacheras = terneraBean.listaGuacheras();
		if(listaGuacheras.size() != 0)
		{
			return Response.ok().entity(listaGuacheras).build();
		}
		else
		{
			return Response.serverError().build();
		}
	}
}
