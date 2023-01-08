package com.capa3Persistencia.dao;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.capa3Persistencia.entities.EnfermedadPersistencia;
import com.capa3Persistencia.entities.NombresEnfermedadPersistencia;
import com.capa3Persistencia.entities.VariantePersistencia;
/**
 * Session Bean implementation class UsuariosEJBBean
 */
@Stateless
@LocalBean
public class EnfermedadPersistenciaDAO
{
	@PersistenceContext
	private EntityManager em;
	
	@EJB
	NombreEnfermedadPersistenciaDAO nombreEnfermedadPersistenciaDAO;
	
	@EJB
	VariantePersistenciaDAO variantePersistenciaDAO;
	
	public EnfermedadPersistenciaDAO()
	{
		super();
	}
	
	public EnfermedadPersistencia obtener(String nomEnfermedad, String nombreVariante)
	{
		EnfermedadPersistencia enfermedad = new EnfermedadPersistencia();
		
		try
		{
			VariantePersistencia variante = variantePersistenciaDAO.obtener(nombreVariante);
			
			NombresEnfermedadPersistencia nombreEnfermedad = nombreEnfermedadPersistenciaDAO.obtener(nomEnfermedad);
			
			enfermedad.setNombresEnfermedade(nombreEnfermedad);
			
			enfermedad.setVariante(variante);
			
			TypedQuery<EnfermedadPersistencia> query= em.createQuery("SELECT e FROM EnfermedadPersistencia e WHERE e.nombresEnfermedade LIKE :nombresEnfermedade AND e.variante LIKE :variante", EnfermedadPersistencia.class)
					.setParameter("nombresEnfermedade", enfermedad.getNombresEnfermedade())
					.setParameter("variante", enfermedad.getVariante());
			
			if(query.getResultList().isEmpty())
			{
				em.persist(enfermedad);
				
				em.flush();
				
				enfermedad = obtener(enfermedad.getNombresEnfermedade().getNombreEnfer(), enfermedad.getVariante().getNombre());
			}
			else
			{
				enfermedad = query.getResultList().get(0);
			}
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		
		return enfermedad;
	}
}
