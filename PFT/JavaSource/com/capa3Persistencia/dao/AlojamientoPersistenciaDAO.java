package com.capa3Persistencia.dao;

import java.time.LocalDateTime;
import java.util.Date;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import com.capa3Persistencia.entities.AlojamientoPersistencia;
import com.capa3Persistencia.entities.GuacheraPersistencia;
import com.capa3Persistencia.entities.TerneraPersistencia;
/**
 * Session Bean implementation class UsuariosEJBBean
 */
@Stateless
@LocalBean
public class AlojamientoPersistenciaDAO
{
	@PersistenceContext
	private EntityManager em;
	
	@EJB
	TerneraPersistenciaDAO terneraPersistenciaDAO;
	
	public AlojamientoPersistenciaDAO()
	{
		super();
	}
	
	public void Crear(GuacheraPersistencia guachera, TerneraPersistencia ternera) throws Exception
	{
		try
		{
			AlojamientoPersistencia alojamiento = new AlojamientoPersistencia();
			
			alojamiento.setGuachera(guachera);
			
			alojamiento.setTernera(ternera);
			
			Date fechaActual = convertToDateViaSqlTimestamp(LocalDateTime.now());
			
			alojamiento.setFechaEntrada(fechaActual);
			
			em.persist(alojamiento);
			em.flush();
		} catch (PersistenceException e)
		{
			e.printStackTrace();
		}
	}
	
	public boolean actualizarFecha(TerneraPersistencia ternera, GuacheraPersistencia guachera)
	{
		AlojamientoPersistencia alojamiento = new AlojamientoPersistencia();
		
		boolean actualizable = false;
		
		try
		{
			TypedQuery<AlojamientoPersistencia> query= em.createQuery("SELECT a FROM AlojamientoPersistencia a WHERE a.ternera LIKE :ternera AND a.guachera LIKE :guachera",AlojamientoPersistencia.class)
					.setParameter("ternera", ternera)
					.setParameter("guachera", guachera);
			
			
			int i = 0;
			while(i<query.getResultList().size() && !actualizable)
			{
				alojamiento = query.getResultList().get(0);
				if(alojamiento.getFechaSalida() == null)
				{
					actualizable = true;
				}
				else
				{
					i++;
				}
			}
			
			if(actualizable == true)
			{
				alojamiento = em.find(AlojamientoPersistencia.class, alojamiento.getIdAlojamiento());
				
				alojamiento.setFechaSalida(convertToDateViaSqlTimestamp(LocalDateTime.now()));
				
				em.merge(alojamiento);
				em.flush();
				
				return true;
			}
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		return false;
	}
	
	public GuacheraPersistencia obtenerUltimaGuachera(TerneraPersistencia ternera)
	{
		AlojamientoPersistencia alojamiento = new AlojamientoPersistencia();
		
		GuacheraPersistencia guachera = new GuacheraPersistencia();
		
		try
		{
			TypedQuery<AlojamientoPersistencia> query= em.createQuery("SELECT a FROM AlojamientoPersistencia a WHERE a.ternera LIKE :ternera",AlojamientoPersistencia.class)
					.setParameter("ternera", ternera);
			
			int i = 0;
			while(i< query.getResultList().size())
			{
				alojamiento = query.getResultList().get(i);
				if(alojamiento.getFechaSalida() == null)
				{
					guachera = alojamiento.getGuachera();
				}
				i++;
			}
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		
		return guachera;
	}
	
	public String obtenerIdentificadorGuachera(String identificadorT)
	{
		TerneraPersistencia ternera = terneraPersistenciaDAO.obtenerTernera(identificadorT);
		
		GuacheraPersistencia guachera = obtenerUltimaGuachera(ternera);
		
		return guachera.getIdentificadorUnico();
	}
	
	public Date convertToDateViaSqlTimestamp(LocalDateTime dateToConvert)
    {
        return java.sql.Timestamp.valueOf(dateToConvert);
    }
}
