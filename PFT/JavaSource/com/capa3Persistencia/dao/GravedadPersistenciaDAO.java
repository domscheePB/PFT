package com.capa3Persistencia.dao;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.capa3Persistencia.entities.GravedadPersistencia;
/**
 * Session Bean implementation class UsuariosEJBBean
 */
@Stateless
@LocalBean
public class GravedadPersistenciaDAO
{
	@PersistenceContext
	private EntityManager em;
	
	public GravedadPersistenciaDAO()
	{
		super();
	}
	
	public GravedadPersistencia obtener(String tipoGravedad)
	{
		GravedadPersistencia gravedad = new GravedadPersistencia();
		
		TypedQuery<GravedadPersistencia> query= em.createQuery("SELECT g FROM GravedadPersistencia g WHERE g.tipoGravedad LIKE :tipoGravedad", GravedadPersistencia.class)
				.setParameter("tipoGravedad", tipoGravedad);
		
		gravedad = query.getResultList().get(0);
		
		return gravedad;
	}
	
	public List<GravedadPersistencia> obtenerTodos()
	{
		TypedQuery<GravedadPersistencia> query = em.createQuery("SELECT g FROM GravedadPersistencia g", GravedadPersistencia.class);
		List<GravedadPersistencia> nombres = query.getResultList();
		
		return nombres;
	}
}
