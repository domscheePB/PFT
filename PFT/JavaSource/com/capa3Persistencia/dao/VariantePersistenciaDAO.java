package com.capa3Persistencia.dao;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.capa3Persistencia.entities.VariantePersistencia;

/**
 * Session Bean implementation class UsuariosEJBBean
 */
@Stateless
@LocalBean
public class VariantePersistenciaDAO
{
	@PersistenceContext
	private EntityManager em;
	
	public VariantePersistenciaDAO()
	{
		super();
	}
	
	public VariantePersistencia obtener(String nombre)
	{
		VariantePersistencia variante = new VariantePersistencia();
		
		variante.setNombre(nombre);
		
		TypedQuery<VariantePersistencia> query= em.createQuery("SELECT v FROM VariantePersistencia v WHERE v.nombre LIKE :nombre", VariantePersistencia.class)
				.setParameter("nombre", nombre);
		
		if(query.getResultList().isEmpty())
		{			
			em.persist(variante);
			
			em.flush();
			
			variante = obtener(variante.getNombre());
		}
		else
		{
			variante = query.getResultList().get(0);
		}
		
		return variante;
	}
}
