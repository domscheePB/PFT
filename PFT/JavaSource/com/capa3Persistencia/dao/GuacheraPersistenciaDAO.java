package com.capa3Persistencia.dao;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import com.capa3Persistencia.entities.GuacheraPersistencia;
import com.capa3Persistencia.entities.TipoGuacheraPersistencia;
import com.capa3Persistencia.entities.UbicacionPersistencia;

/**
 * Session Bean implementation class UsuariosEJBBean
 */
@Stateless
@LocalBean
public class GuacheraPersistenciaDAO
{
	@PersistenceContext
	private EntityManager em;
	
	@EJB
	TipoGuacheraPersistenciaDAO tipoGuacheraPersistenciaDAO;
	
	@EJB
	UbicacionPersistenciaDAO ubicacionPersistenciaDAO;
	
	public GuacheraPersistenciaDAO()
	{
		super();
	}
	
	public boolean idRepetido(String identificadorUnico)
	{
		TypedQuery<GuacheraPersistencia> query= em.createQuery("SELECT g FROM GuacheraPersistencia g WHERE g.identificadorUnico LIKE :identificadorUnico",GuacheraPersistencia.class)
				.setParameter("identificadorUnico", identificadorUnico);
		
		if(query.getResultList().isEmpty())
		{
			return false;
		}
		
		return true;
	}

	public List<GuacheraPersistencia> obtenerTodos()
	{
		TypedQuery<GuacheraPersistencia> query = em.createQuery("SELECT g FROM GuacheraPersistencia g",GuacheraPersistencia.class);
		
		List<GuacheraPersistencia> Guacheras = query.getResultList();
		
		return Guacheras;
	}
	
	public List<GuacheraPersistencia> obtenerGuacheras(String identificador,
			String tipo,
			float anchoDesde, float anchoHasta,
			float largoDesde, float largoHasta,
			float latitudDesde, float latitudHasta,
			float longitudDesde, float longitudHasta,
			int capacidadDesde, int capacidadHasta)
	{
		String query= 	"Select g from GuacheraPersistencia g  ";
		String queryCriterio="";
		if (identificador!=null && ! identificador.contentEquals(""))
		{
			queryCriterio+=(!queryCriterio.isEmpty()?" and ":"")+ " lower(g.identificadorUnico) like '%"+identificador.toLowerCase() +"%' ";
		}
		if (!queryCriterio.contentEquals(""))
		{
			query+=" where "+queryCriterio;
		}
		
		List<GuacheraPersistencia> resultList = (List<GuacheraPersistencia>) em.createQuery(query,GuacheraPersistencia.class)
				 .getResultList();
		
		int i = 0;
		
		while(i<resultList.size())
		{
			if(anchoDesde != 0 && !(anchoDesde <= resultList.get(i).getAncho()))
			{
				resultList.remove(i);
			}
			else if(anchoHasta != 0 && !(anchoHasta >= resultList.get(i).getAncho()))
			{
				resultList.remove(i);
			}
			else if(largoDesde != 0 && !(largoDesde <= resultList.get(i).getAncho()))
			{
				resultList.remove(i);
			}
			else if(largoHasta != 0 && !(largoHasta >= resultList.get(i).getAncho()))
			{
				resultList.remove(i);
			}
			else if(latitudDesde != 0 && !(latitudDesde <= resultList.get(i).getUbicacione().getLatitud()))
			{
				resultList.remove(i);
			}
			else if(latitudHasta != 0 && !(latitudHasta >= resultList.get(i).getUbicacione().getLatitud()))
			{
				resultList.remove(i);
			}
			else if(longitudDesde != 0 && !(longitudDesde <=resultList.get(i).getUbicacione().getLongitud()))
			{
				resultList.remove(i);
			}
			else if(longitudHasta != 0 && !(longitudHasta >= resultList.get(i).getUbicacione().getLongitud()))
			{
				resultList.remove(i);
			}
			else if(capacidadDesde != 0 && !(capacidadDesde <= resultList.get(i).getCapacidad()))
			{
				resultList.remove(i);
			}
			else if(capacidadHasta != 0 && !(capacidadHasta >= resultList.get(i).getCapacidad()))
			{
				resultList.remove(i);
			}
			else if((tipo!=null && ! tipo.contentEquals("")) && !(resultList.get(i).getTipoGuachera().getTipo().contentEquals(tipo)))
			{
				resultList.remove(i);
			}
			else
			{
				i++;
			}
		}
		
		return  resultList;
	}

	public void eliminarGuachera(GuacheraPersistencia guachera)
	{
		guachera = em.find(GuacheraPersistencia.class, guachera.getIdGuachera());
		
		em.remove(guachera);
		em.flush();
	}
	
	public GuacheraPersistencia obtenerGuachera(String identificador)
	{
		TypedQuery<GuacheraPersistencia> query= em.createQuery("SELECT g FROM GuacheraPersistencia g WHERE g.identificadorUnico LIKE :identificadorUnico",GuacheraPersistencia.class)
				.setParameter("identificadorUnico", identificador);
		
		long idGuachera = query.getResultList().get(0).getIdGuachera();
		
		GuacheraPersistencia guachera = em.find(GuacheraPersistencia.class, idGuachera);
		
		return guachera;
	}
	
	public GuacheraPersistencia obtenerGuacheraID(Long id) throws PersistenceException
	{
		GuacheraPersistencia guachera = em.find(GuacheraPersistencia.class, id);
		
		return guachera;
	}
	
	public GuacheraPersistencia Actualizar(GuacheraPersistencia guachera) throws Exception
	{
		try
		{
			UbicacionPersistencia ubic = ubicacionPersistenciaDAO.obtenerUbicacion(guachera.getUbicacione().getLatitud(), guachera.getUbicacione().getLongitud());
			
			TipoGuacheraPersistencia tipo = tipoGuacheraPersistenciaDAO.obtenerTipoGuachera(guachera.getTipoGuachera().getTipo());
			
			if(ubic==null)
			{
				ubic = guachera.getUbicacione();
				
				ubic = new UbicacionPersistencia();
				ubic.setLatitud(guachera.getUbicacione().getLatitud());
				ubic.setLongitud(guachera.getUbicacione().getLongitud());
				
				ubicacionPersistenciaDAO.Crear(ubic);
				
				ubic = ubicacionPersistenciaDAO.obtenerUbicacion(ubic.getLatitud(), ubic.getLongitud());
			}
			
			guachera.setUbicacione(ubic);
			guachera.setTipoGuachera(tipo);
			
			guachera = em.merge(guachera);
			em.flush();
			
			return guachera;
		} catch (PersistenceException e)
		{
			e.printStackTrace();
			return null;
		}
	}

	public GuacheraPersistencia Crear(GuacheraPersistencia guachera) throws Exception
	{
		try
		{
			UbicacionPersistencia ubic = ubicacionPersistenciaDAO.obtenerUbicacion(guachera.getUbicacione().getLatitud(), guachera.getUbicacione().getLongitud());
			
			TipoGuacheraPersistencia tipo = tipoGuacheraPersistenciaDAO.obtenerTipoGuachera(guachera.getTipoGuachera().getTipo());
			
			if(ubic==null)
			{
				ubic = guachera.getUbicacione();
				
				ubicacionPersistenciaDAO.Crear(ubic);
				
				ubic = ubicacionPersistenciaDAO.obtenerUbicacion(ubic.getLatitud(), ubic.getLongitud());
			}
			
			guachera.setUbicacione(ubic);
			guachera.setTipoGuachera(tipo);
			
			guachera = em.merge(guachera);
			em.flush();
			
			return guachera;
		} catch (PersistenceException e)
		{
			e.printStackTrace();
			return null;
		}
	}
}
