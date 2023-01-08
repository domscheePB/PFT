package com.capa3Persistencia.dao;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.capa3Persistencia.entities.RazaPersistencia;

/**
 * Session Bean implementation class UsuariosEJBBean
 */
@Stateless
@LocalBean
public class RazaPersistenciaDAO
{
	@PersistenceContext
	private EntityManager em;
	
	public RazaPersistenciaDAO()
	{
		super();
	}
	
	public RazaPersistencia obtenerRaza(String nomRaza)
	{	
		TypedQuery<RazaPersistencia> query = em.createQuery("SELECT r FROM RazaPersistencia r WHERE r.raza LIKE :raza", RazaPersistencia.class)
				.setParameter("raza", nomRaza);
		
		RazaPersistencia raza = new RazaPersistencia();
		
		try
		{
			raza = query.getResultList().get(0);
		} catch (Exception e)
		{
			raza = null;
		}
		return raza;
	}
	
	public List<RazaPersistencia> obtenerTodos()
	{
		try
		{
			TypedQuery<RazaPersistencia> query = em.createQuery("SELECT r FROM RazaPersistencia r",RazaPersistencia.class);
			List<RazaPersistencia> raza = query.getResultList();

			return raza;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
