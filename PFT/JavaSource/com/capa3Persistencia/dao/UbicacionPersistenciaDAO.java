package com.capa3Persistencia.dao;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.capa3Persistencia.entities.UbicacionPersistencia;

/**
 * Session Bean implementation class UsuariosEJBBean
 */
@Stateless
@LocalBean
public class UbicacionPersistenciaDAO
{
	@PersistenceContext
	private EntityManager em;
	
	@EJB
	UsuarioPersistenciaDAO usuariosPersistenciaDAO;
	
	public UbicacionPersistenciaDAO()
	{
		super();
	}
	
	public void Crear(UbicacionPersistencia ubicacion) throws Exception
	{
		em.persist(ubicacion);
		em.flush();
	}

	public List<UbicacionPersistencia> obtenerTodos() {
		
		TypedQuery<UbicacionPersistencia> query = em.createQuery("SELECT u FROM UbicacionPersistencia u", UbicacionPersistencia.class);
		
		List<UbicacionPersistencia> ubi = query.getResultList();
		
		return ubi;
	}

	public UbicacionPersistencia obtenerUbicacion (float latitud, float longitud)
	{	
		TypedQuery<UbicacionPersistencia> query = em.createQuery("SELECT u FROM UbicacionPersistencia u WHERE u.latitud LIKE :latitud AND u.longitud LIKE :longitud",UbicacionPersistencia.class)
				.setParameter("latitud", latitud)
				.setParameter("longitud", longitud);
		
		UbicacionPersistencia ubicacion = new UbicacionPersistencia();
		
		try
		{
			ubicacion = query.getResultList().get(0);
		} catch (Exception e)
		{
			ubicacion = null;
		}
		return ubicacion;
	}
}
