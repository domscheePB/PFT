package com.capa3Persistencia.dao;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.capa3Persistencia.entities.TitulosProfesionalPersistencia;

/**
 * Session Bean implementation class UsuariosEJBBean
 */
@Stateless
@LocalBean
public class TituloProfesionalPersistenciaDAO
{
	@PersistenceContext
	private EntityManager em;
	
	@EJB
	UsuarioPersistenciaDAO usuariosPersistenciaDAO;
	
	public TituloProfesionalPersistenciaDAO()
	{
		super();
	}
	
	public void Crear(TitulosProfesionalPersistencia titulo) throws Exception
	{
		try
		{
			em.persist(titulo);
			em.flush();
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public TitulosProfesionalPersistencia obtenerTitulo(String nombre)
	{		
		TypedQuery<TitulosProfesionalPersistencia> query = em.createQuery("SELECT t FROM TitulosProfesionalPersistencia t WHERE t.nombre LIKE :nombre", TitulosProfesionalPersistencia.class)
				.setParameter("nombre", nombre);
		
		TitulosProfesionalPersistencia Titulo = new TitulosProfesionalPersistencia();
		
		try
		{
			Titulo = query.getResultList().get(0);
		} catch (Exception e)
		{
			Titulo = null;
		}
		
		return Titulo;
	}
	
	public List<TitulosProfesionalPersistencia> obtenerTodos()
	{		
		TypedQuery<TitulosProfesionalPersistencia> query = em.createQuery("SELECT t FROM TitulosProfesionalPersistencia t",TitulosProfesionalPersistencia.class);
		List<TitulosProfesionalPersistencia> Titulos = query.getResultList();
		
		return Titulos;
	}
}
