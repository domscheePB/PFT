package com.capa3Persistencia.dao;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import com.capa3Persistencia.entities.AdministradorPersistencia;
import com.capa3Persistencia.entities.UsuarioPersistencia;

/**
 * Session Bean implementation class UsuariosEJBBean
 */
@Stateless
@LocalBean
public class AdministradorPersistenciaDAO
{
	@PersistenceContext
	private EntityManager em;
	
	@EJB
	UsuarioPersistenciaDAO usuariosPersistenciaDAO;
	
	public AdministradorPersistenciaDAO()
	{
		super();
	}
	
	public void eliminarAdminSolo(UsuarioPersistencia usuario)
	{
		try
		{
			UsuarioPersistencia user = usuariosPersistenciaDAO.buscarUsuario(usuario.getIdUsuario());
			
			TypedQuery<AdministradorPersistencia> query = em.createQuery("SELECT a FROM AdministradorPersistencia a WHERE a.usuario LIKE :usuario",AdministradorPersistencia.class)
					.setParameter("usuario", user);
			
			long idAdmin = query.getResultList().get(0).getIdAdministrador();

			AdministradorPersistencia admin = em.find(AdministradorPersistencia.class, idAdmin);
			
			em.remove(admin);
			em.flush();
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public void eliminarAdmin(UsuarioPersistencia usuario)
	{
		try
		{			
			UsuarioPersistencia user = usuariosPersistenciaDAO.buscarUsuario(usuario.getIdUsuario());
			
			TypedQuery<AdministradorPersistencia> query = em.createQuery("SELECT a FROM AdministradorPersistencia a WHERE a.usuario LIKE :usuario",AdministradorPersistencia.class)
					.setParameter("usuario", user);
			
			long idAdmin = query.getResultList().get(0).getIdAdministrador();

			AdministradorPersistencia admin = em.find(AdministradorPersistencia.class, idAdmin);
			
			em.remove(admin);
			em.flush();
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public AdministradorPersistencia Crear(UsuarioPersistencia usuario) throws Exception
	{
		AdministradorPersistencia admin = new AdministradorPersistencia();
		
		try
		{
			UsuarioPersistencia user = usuariosPersistenciaDAO.agregarUsuario(usuario);
			
			admin.setUsuario(usuariosPersistenciaDAO.buscarUsuario(user.getIdUsuario()));
			
			admin = em.merge(admin);
			em.flush();
			
		} catch (PersistenceException e)
		{
			e.printStackTrace();
		}
		
		return admin;
	}
	
	public AdministradorPersistencia CrearSolo(UsuarioPersistencia usuario) throws Exception
	{
		AdministradorPersistencia admin = new AdministradorPersistencia();
		try
		{
			admin.setUsuario(usuariosPersistenciaDAO.buscarUsuario(usuario.getIdUsuario()));
			
			admin = em.merge(admin);
			em.flush();
			
		} catch (PersistenceException e)
		{
			e.printStackTrace();
		}
		return admin;
	}
}
