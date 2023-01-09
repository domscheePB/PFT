package com.capa2LogicaNegocio;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.capa2LogicaNegocioEntities.Ubicacion;
import com.capa3Persistencia.dao.UbicacionPersistenciaDAO;
import com.capa3Persistencia.entities.UbicacionPersistencia;

@Stateless
@LocalBean
public class GestionUbicacionService implements Serializable
{	
	private static final long serialVersionUID = 1L;
	
	@EJB
	UbicacionPersistenciaDAO ubicacionPersistenciaDAO;

	public Ubicacion fromUbicacionPersistencia(UbicacionPersistencia u)
	{
		Ubicacion ubicacion = new Ubicacion();
		ubicacion.setId(u.getIdUbicacion().longValue());
		ubicacion.setLatitud(u.getLatitud());
		ubicacion.setLongitud(u.getLongitud());
		
		return ubicacion;
	}
	
	public UbicacionPersistencia toUbicacionPersistencia(Ubicacion u)
	{
		UbicacionPersistencia ubicacionPersistencia = new UbicacionPersistencia();
		ubicacionPersistencia.setIdUbicacion(u.getId()!=null?u.getId().longValue():null);
		ubicacionPersistencia.setLatitud(u.getLatitud());
		ubicacionPersistencia.setLongitud(u.getLongitud());
		
		return ubicacionPersistencia;
	}
}