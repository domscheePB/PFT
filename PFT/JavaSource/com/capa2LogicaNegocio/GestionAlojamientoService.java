package com.capa2LogicaNegocio;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.capa2LogicaNegocioEntities.Guachera;
import com.capa2LogicaNegocioEntities.Ternera;
import com.capa3Persistencia.dao.AlojamientoPersistenciaDAO;

@Stateless
@LocalBean
public class GestionAlojamientoService implements Serializable
{	
	private static final long serialVersionUID = 1L;
	
	@EJB
	GestionTerneraService gestionTerneraService;
	
	@EJB
	GestionGuacheraService gestionGuacheraService;
	
	@EJB
	AlojamientoPersistenciaDAO gestionAlojamientoDAO;

	public Guachera obtenerUltimaGuachera(Ternera ternera)
	{
		return gestionGuacheraService.fromGuacheraPersistencia(gestionAlojamientoDAO.obtenerUltimaGuachera(gestionTerneraService.toTerneraPersistencia(ternera)));
	}
	
	public String obtenerIdentificadorGuachera(String identificador)
	{
		return gestionAlojamientoDAO.obtenerIdentificadorGuachera(identificador);
	}
}