package com.capa3Persistencia.dao;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import com.capa3Persistencia.entities.PersonalPersistencia;
import com.capa3Persistencia.entities.UsuarioPersistencia;

/**
 * Session Bean implementation class UsuariosEJBBean
 */
@Stateless
@LocalBean
public class PersonalPersistenciaDAO
{
	@PersistenceContext
	private EntityManager em;
	
	@EJB
	UsuarioPersistenciaDAO usuariosPersistenciaDAO;
	
	public PersonalPersistenciaDAO()
	{
		super();
	}
	
	public PersonalPersistencia Crear(UsuarioPersistencia usuario, float cantHoras) throws Exception
	{
		PersonalPersistencia personal = new PersonalPersistencia();
		try
		{
			UsuarioPersistencia user = usuariosPersistenciaDAO.agregarUsuario(usuario);
			
			personal.setUsuario(usuariosPersistenciaDAO.buscarUsuario(user.getIdUsuario()));
			
			personal.setCantidadDeHoras(cantHoras);
			
			personal = em.merge(personal);
			em.flush();
		} catch (PersistenceException e)
		{
			e.printStackTrace();
		}
		return personal;
	}
	
	public PersonalPersistencia CrearSolo(UsuarioPersistencia usuario, float cantHoras) throws Exception
	{
		PersonalPersistencia personal = new PersonalPersistencia();
		try
		{
			personal.setUsuario(usuariosPersistenciaDAO.buscarUsuario(usuario.getIdUsuario()));
			
			personal.setCantidadDeHoras(cantHoras);
			
			personal = em.merge(personal);
			em.flush();
		} catch (PersistenceException e)
		{
			e.printStackTrace();
		}
		return personal;
	}
	
	public void eliminarPersonal(UsuarioPersistencia usuario)
	{
		try
		{
			UsuarioPersistencia user = usuariosPersistenciaDAO.buscarUsuario(usuario.getIdUsuario());
			
			TypedQuery<PersonalPersistencia> query = em.createQuery("SELECT p FROM PersonalPersistencia p WHERE p.usuario LIKE :usuario",PersonalPersistencia.class)
					.setParameter("usuario", user);
			
			em.remove(query.getResultList().get(0));
			em.flush();
			
			usuariosPersistenciaDAO.eliminarUsuario(usuario);
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public void eliminarPersonalSolo(UsuarioPersistencia usuario)
	{
		try
		{
			UsuarioPersistencia user = usuariosPersistenciaDAO.buscarUsuario(usuario.getIdUsuario());
			
			TypedQuery<PersonalPersistencia> query = em.createQuery("SELECT p FROM PersonalPersistencia p WHERE p.usuario LIKE :usuario",PersonalPersistencia.class)
					.setParameter("usuario", user);
			
			long idPersonal = query.getResultList().get(0).getIdPersonal();

			PersonalPersistencia personal = em.find(PersonalPersistencia.class, idPersonal);
			
			em.remove(personal);
			em.flush();
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public PersonalPersistencia obtenerPersonal(UsuarioPersistencia usuario)
	{
		try
		{
			UsuarioPersistencia user = usuariosPersistenciaDAO.buscarUsuario(usuario.getIdUsuario());
			
			TypedQuery<PersonalPersistencia> query = em.createQuery("SELECT p FROM PersonalPersistencia p WHERE p.usuario LIKE :usuario",PersonalPersistencia.class)
					.setParameter("usuario", user);
			
			long idPersonal = query.getResultList().get(0).getIdPersonal();

			PersonalPersistencia personal = em.find(PersonalPersistencia.class, idPersonal);
			
			return personal;
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
		return null;
	}
	
	public PersonalPersistencia actualizarHoras(UsuarioPersistencia usuario, float cantHoras)
	{
		PersonalPersistencia personal = obtenerPersonal(usuario);
		try
		{
			personal.setCantidadDeHoras(cantHoras);
			
			personal = em.merge(personal);
			em.flush();
		} catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}
		return personal;
	}
}
