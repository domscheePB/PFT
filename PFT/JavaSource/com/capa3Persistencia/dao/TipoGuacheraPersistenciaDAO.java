package com.capa3Persistencia.dao;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.capa3Persistencia.entities.TipoGuacheraPersistencia;

/**
 * Session Bean implementation class UsuariosEJBBean
 */
@Stateless
@LocalBean
public class TipoGuacheraPersistenciaDAO
{
	@PersistenceContext
	private EntityManager em;
	
	public TipoGuacheraPersistenciaDAO()
	{
		super();
	}
	
	public List<TipoGuacheraPersistencia> obtenerTodos()
	{		
		TypedQuery<TipoGuacheraPersistencia> query = em.createQuery("SELECT p FROM TipoGuacheraPersistencia p", TipoGuacheraPersistencia.class);
		List<TipoGuacheraPersistencia> TiposGuachera = query.getResultList();
		
		return TiposGuachera;
	}
	
	public TipoGuacheraPersistencia obtenerTipoGuachera(String tipo)
	{		
		TypedQuery<TipoGuacheraPersistencia> query = em.createQuery("SELECT t FROM TipoGuacheraPersistencia t WHERE t.tipo LIKE :tipo", TipoGuacheraPersistencia.class)
				.setParameter("tipo", tipo);
		
		TipoGuacheraPersistencia TipoGuachera = new TipoGuacheraPersistencia();
		
		try
		{
			TipoGuachera = query.getResultList().get(0);
		} catch (Exception e)
		{
			TipoGuachera = null;
		}
		
		return TipoGuachera;
	}
}
