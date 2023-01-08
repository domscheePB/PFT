package com.capa3Persistencia.dao;

import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import com.capa3Persistencia.entities.AlojamientoPersistencia;
import com.capa3Persistencia.entities.GuacheraPersistencia;
import com.capa3Persistencia.entities.RazaPersistencia;
import com.capa3Persistencia.entities.TerneraPersistencia;

/**
 * Session Bean implementation class UsuariosEJBBean
 */
@Stateless
@LocalBean
public class TerneraPersistenciaDAO
{
	@PersistenceContext
	private EntityManager em;
	
	@EJB
	RazaPersistenciaDAO razaPersistenciaDAO;
	
	@EJB
	GuacheraPersistenciaDAO guacheraPersistenciaDAO;
	
	@EJB
	AlojamientoPersistenciaDAO alojamientoPersistenciaDAO;
	
	public TerneraPersistenciaDAO()
	{
		super();
	}
	
	public TerneraPersistencia Crear(TerneraPersistencia ternera, String nomRaza, String identificadorGuachera) throws Exception
	{
		try
		{
			GuacheraPersistencia guachera = guacheraPersistenciaDAO.obtenerGuachera(identificadorGuachera);
			
			RazaPersistencia raza = razaPersistenciaDAO.obtenerRaza(nomRaza);
			
			ternera.setRaza(raza);
			
			em.persist(ternera);
			em.flush();
			
			ternera = obtenerTernera(ternera.getCaravanatambo());
			
			alojamientoPersistenciaDAO.Crear(guachera, ternera);
			
		} catch (PersistenceException e)
		{
			e.printStackTrace();
		}
		
		return ternera;
	}
	
	public List<TerneraPersistencia> obtenerTerneras(String caravanaSnis,
			String caravanaTambo,
			String tipoParto,
			String guachera,
			String raza,
			int idMadre,
			int idPadre,
			Date nacimientoDesde,
			Date nacimientoHasta,
			float pesoDesde,
			float pesoHasta)
	{
		String query= 	"Select t from TerneraPersistencia t  ";
		String queryCriterio="";
		if (caravanaSnis!=null && ! caravanaSnis.contentEquals(""))
		{
			queryCriterio+=(!queryCriterio.isEmpty()?" and ":"")+ " lower(t.caravanasnis) like '%"+caravanaSnis.toLowerCase() +"%' ";
		}
		if (caravanaTambo!=null && ! caravanaTambo.contentEquals(""))
		{
			queryCriterio+=(!queryCriterio.isEmpty()?" and ":"")+ " lower(t.caravanatambo) like '%"+caravanaTambo.toLowerCase() +"%' ";
		}
		if (tipoParto!=null && ! tipoParto.contentEquals(""))
		{
			queryCriterio+=(!queryCriterio.isEmpty()?" and ":"")+ " lower(t.tipodeparto) like '%"+tipoParto.toLowerCase() +"%' ";
		}
		if (idMadre!=0)
		{
			queryCriterio+=(!queryCriterio.isEmpty()?" and ":"")+ " lower(t.idMadre) like '%"+ idMadre +"%' ";
		}
		if (idPadre!=0)
		{
			queryCriterio+=(!queryCriterio.isEmpty()?" and ":"")+ " lower(t.idPadre) like '%"+ idPadre +"%' ";
		}
		if (!queryCriterio.contentEquals(""))
		{
			query+=" where "+queryCriterio;
		}
		
		List<TerneraPersistencia> resultList = (List<TerneraPersistencia>) em.createQuery(query,TerneraPersistencia.class)
				 .getResultList();
		
		int i = 0;
		
		while(i<resultList.size())
		{
			Date fechaAUX = resultList.get(i).getFechanacimiento();
			GuacheraPersistencia guacheraActual = alojamientoPersistenciaDAO.obtenerUltimaGuachera(resultList.get(i));
			if(nacimientoDesde != null && fechaAUX.before(nacimientoDesde))
			{
				resultList.remove(i);
			}
			else if(nacimientoHasta != null && fechaAUX.after(nacimientoHasta))
			{
				resultList.remove(i);
			}
			else if(pesoDesde != 0 && !(pesoDesde <= resultList.get(i).getPesoalnacer()))
			{
				resultList.remove(i);
			}
			else if(pesoHasta != 0 && !(pesoHasta >= resultList.get(i).getPesoalnacer()))
			{
				resultList.remove(i);
			}
			else if((raza!=null && ! raza.contentEquals("")) && !raza.equals(resultList.get(i).getRaza().getRaza()))
			{
				resultList.remove(i);
			}
			else if((guachera != null && ! guachera.contentEquals("")) && !guacheraActual.getIdentificadorUnico().equals(guachera))
			{
				resultList.remove(i);
			}
			else
			{
				i++;
			}
		}
		
		return resultList;
	}
	
	public void Actualizar(TerneraPersistencia ternera, String nomRaza, String identificadorGuachera) throws Exception
	{
		try
		{
			GuacheraPersistencia guachera = guacheraPersistenciaDAO.obtenerGuachera(identificadorGuachera);
			
			RazaPersistencia raza = razaPersistenciaDAO.obtenerRaza(nomRaza);
			
			raza = em.find(RazaPersistencia.class, raza.getIdRaza());
			
			TerneraPersistencia terneraAUX = em.find(TerneraPersistencia.class, ternera.getIdTernera());
			
			terneraAUX.setCaravanasnis(ternera.getCaravanasnis());
			terneraAUX.setCaravanatambo(ternera.getCaravanatambo());
			terneraAUX.setFechanacimiento(ternera.getFechanacimiento());
			terneraAUX.setIdMadre(ternera.getIdMadre());
			terneraAUX.setIdPadre(ternera.getIdPadre());
			if(!(ternera.getPesoalnacer() == 0))
			{
				terneraAUX.setPesoalnacer(ternera.getPesoalnacer());
			}
			terneraAUX.setRaza(raza);
			terneraAUX.setTipodeparto(ternera.getTipodeparto());
			
			em.merge(terneraAUX);
			em.flush();
			
			terneraAUX = obtenerTernera(terneraAUX.getCaravanatambo());
			
			if(alojamientoPersistenciaDAO.obtenerUltimaGuachera(terneraAUX).getIdentificadorUnico() != guachera.getIdentificadorUnico())
			{
				alojamientoPersistenciaDAO.actualizarFecha(terneraAUX, alojamientoPersistenciaDAO.obtenerUltimaGuachera(terneraAUX));
				alojamientoPersistenciaDAO.Crear(guachera, ternera);
			}
		} catch (PersistenceException e)
		{
			e.printStackTrace();
		}
	}
	
	public boolean idRepetido(String identificador)
	{
		TypedQuery<TerneraPersistencia> query= em.createQuery("SELECT t FROM TerneraPersistencia t WHERE t.caravanatambo LIKE :caravanatambo",TerneraPersistencia.class)
				.setParameter("caravanatambo", identificador);
		
		if(query.getResultList().isEmpty())
		{
			return false;
		}
		
		return true;
	}
	
	public void eliminarTernera(TerneraPersistencia ternera)
	{
		try
		{
			AlojamientoPersistencia alojamiento = new AlojamientoPersistencia();
			
			ternera = em.find(TerneraPersistencia.class, ternera.getIdTernera());
			
			TypedQuery<AlojamientoPersistencia> query = em.createQuery("SELECT a FROM AlojamientoPersistencia a WHERE a.ternera LIKE :ternera",AlojamientoPersistencia.class)
					.setParameter("ternera", ternera);
			
			while(query.getResultList().size() != 0)
			{
				long idAlojamiento = query.getResultList().get(0).getIdAlojamiento();
				
				alojamiento = em.find(AlojamientoPersistencia.class, idAlojamiento);
				
				em.remove(alojamiento);
				em.flush();
			}
			
			em.remove(ternera);
			em.flush();
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public TerneraPersistencia obtenerTernera(String caravanaTambo)
	{
		TypedQuery<TerneraPersistencia> query= em.createQuery("SELECT t FROM TerneraPersistencia t WHERE t.caravanatambo LIKE :caravanatambo",TerneraPersistencia.class)
				.setParameter("caravanatambo", caravanaTambo);
		
		if(query.getResultList().size() != 0)
		{
			long idTernera = query.getResultList().get(0).getIdTernera();
			
			TerneraPersistencia ternera = em.find(TerneraPersistencia.class, idTernera);
			
			return ternera;
		}
		else
		{
			return null;
		}
	}
	
	public TerneraPersistencia obtenerTerneraID(Long id) throws PersistenceException
	{
		TerneraPersistencia ternera =  em.find(TerneraPersistencia.class, id);
		
		return ternera;
	}
	
	public List<TerneraPersistencia> obtenerTodos()
	{		
		TypedQuery<TerneraPersistencia> query = em.createQuery("SELECT t FROM TerneraPersistencia t",TerneraPersistencia.class);
		List<TerneraPersistencia> Usuarios = query.getResultList();
		
		return Usuarios;
	}
}
