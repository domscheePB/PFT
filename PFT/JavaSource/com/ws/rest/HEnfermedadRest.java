package com.ws.rest;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ws.rs.core.Response;

import com.ws.rest.bean.HEnfermedadBean;
import com.ws.rest.entities.HEnfermedadRestEntidad;

@Stateless
@LocalBean
public class HEnfermedadRest implements IHEnfermedadRest
{	
	@EJB
	HEnfermedadBean hEnfermedadBean;
	
	public HEnfermedadRest() {
		
	}

	@Override
	public String echo() {
		return "Servicio usuarios disponible";
	}

	@Override
	public Response obtenerHEnfermedadRest()
	{
		List<HEnfermedadRestEntidad> listaHistorico = hEnfermedadBean.obtenerHistorico();
		if(listaHistorico.size() != 0)
		{
			return Response.ok().entity(listaHistorico).build();
		}
		else
		{
			return Response.serverError().build();
		}
	}
}
