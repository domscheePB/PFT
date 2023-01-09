package com.capa3Persistencia.dao;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import com.capa3Persistencia.entities.EncargadoPersistencia;
import com.capa3Persistencia.entities.UsuarioPersistencia;

/**
 * Session Bean implementation class UsuariosEJBBean
 */
@Stateless
@LocalBean
public class EncargadoPersistenciaDAO
{
	@PersistenceContext
	private EntityManager em;
	
	@EJB
	UsuarioPersistenciaDAO usuariosPersistenciaDAO;
	
	@EJB
	TituloProfesionalPersistenciaDAO titulosPersistenciaDAO;
	
	public EncargadoPersistenciaDAO()
	{
		super();
	}
	
	public EncargadoPersistencia Crear(UsuarioPersistencia usuario, String titulo) throws Exception
	{
		EncargadoPersistencia encargado = new EncargadoPersistencia();
		try
		{
			UsuarioPersistencia user = usuariosPersistenciaDAO.agregarUsuario(usuario);
			
			encargado.setUsuario(usuariosPersistenciaDAO.buscarUsuario(user.getIdUsuario()));
			
			encargado.setTitulosProfesionale(titulosPersistenciaDAO.obtenerTitulo(titulo));
			
			em.persist(encargado);
			em.flush();
		} catch (PersistenceException e)
		{
			e.printStackTrace();
		}
		
		return encargado;
	}
	
	public EncargadoPersistencia CrearSolo(UsuarioPersistencia usuario, String titulo) throws Exception
	{
		EncargadoPersistencia encargado = new EncargadoPersistencia();
		try
		{
			encargado.setUsuario(usuariosPersistenciaDAO.buscarUsuario(usuario.getIdUsuario()));
			
			encargado.setTitulosProfesionale(titulosPersistenciaDAO.obtenerTitulo(titulo));
			
			em.persist(encargado);
			em.flush();
		} catch (PersistenceException e)
		{
			e.printStackTrace();
		}
		
		return encargado;
	}
	
	public void eliminarEncargado(UsuarioPersistencia usuario)
	{
		try
		{
			UsuarioPersistencia user = usuariosPersistenciaDAO.buscarUsuario(usuario.getIdUsuario());
			
			TypedQuery<EncargadoPersistencia> query = em.createQuery("SELECT e FROM EncargadoPersistencia e WHERE e.usuario LIKE :usuario",EncargadoPersistencia.class)
					.setParameter("usuario", user);
			
			em.remove(query.getResultList().get(0));
			em.flush();
			
			usuariosPersistenciaDAO.eliminarUsuario(usuario);
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public void eliminarEncargadoSolo(UsuarioPersistencia usuario)
	{
		try
		{
			UsuarioPersistencia user = usuariosPersistenciaDAO.buscarUsuario(usuario.getIdUsuario());
			
			TypedQuery<EncargadoPersistencia> query = em.createQuery("SELECT e FROM EncargadoPersistencia e WHERE e.usuario LIKE :usuario",EncargadoPersistencia.class)
					.setParameter("usuario", user);
			
			em.remove(query.getResultList().get(0));
			em.flush();
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public EncargadoPersistencia obtenerEncargado(UsuarioPersistencia usuario)
	{
		try
		{
			UsuarioPersistencia user = usuariosPersistenciaDAO.buscarUsuario(usuario.getIdUsuario());
			
			TypedQuery<EncargadoPersistencia> query = em.createQuery("SELECT p FROM EncargadoPersistencia p WHERE p.usuario LIKE :usuario",EncargadoPersistencia.class)
					.setParameter("usuario", user);
			
			long idEncargado = query.getResultList().get(0).getIdEncargado();

			EncargadoPersistencia encargado = em.find(EncargadoPersistencia.class, idEncargado);
			
			return encargado;
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
		return null;
	}
	
	public EncargadoPersistencia actualizarTitulo(UsuarioPersistencia usuario, String titulo)
	{
		EncargadoPersistencia encargado = obtenerEncargado(usuario);
		try
		{
			encargado.setTitulosProfesionale(titulosPersistenciaDAO.obtenerTitulo(titulo));
			
			encargado = em.merge(encargado);
			em.flush();
		} catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}
		return encargado;
	}
}
