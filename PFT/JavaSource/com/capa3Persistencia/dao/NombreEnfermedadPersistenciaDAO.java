package com.capa3Persistencia.dao;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.capa3Persistencia.entities.NombresEnfermedadPersistencia;
/**
 * Session Bean implementation class UsuariosEJBBean
 */
@Stateless
@LocalBean
public class NombreEnfermedadPersistenciaDAO
{
	@PersistenceContext
	private EntityManager em;
	
	public NombreEnfermedadPersistenciaDAO()
	{
		super();
	}
	
	public NombresEnfermedadPersistencia obtener(String nombre)
	{
		NombresEnfermedadPersistencia nombreEnfermedad = new NombresEnfermedadPersistencia();
		
		nombreEnfermedad.setNombreEnfer(nombre);
		
		TypedQuery<NombresEnfermedadPersistencia> query= em.createQuery("SELECT n FROM NombresEnfermedadPersistencia n WHERE n.nombreEnfer LIKE :nombreEnfer", NombresEnfermedadPersistencia.class)
				.setParameter("nombreEnfer", nombreEnfermedad.getNombreEnfer());
		
		if(query.getResultList().isEmpty())
		{
			em.persist(nombreEnfermedad);
			
			em.flush();
			
			nombreEnfermedad = obtener(nombreEnfermedad.getNombreEnfer());
		}
		else
		{
			nombreEnfermedad = query.getResultList().get(0);
		}
		
		return nombreEnfermedad;
	}
	
	public List<NombresEnfermedadPersistencia> obtenerTodos()
	{
		TypedQuery<NombresEnfermedadPersistencia> query = em.createQuery("SELECT n FROM NombresEnfermedadPersistencia n", NombresEnfermedadPersistencia.class);
		List<NombresEnfermedadPersistencia> nombres = query.getResultList();
		
		return nombres;
	}
}
