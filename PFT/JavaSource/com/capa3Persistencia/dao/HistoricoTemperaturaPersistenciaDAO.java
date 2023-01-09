package com.capa3Persistencia.dao;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;

import com.capa3Persistencia.entities.HistoricoTerneraTemperaturaPersistencia;
/**
 * Session Bean implementation class UsuariosEJBBean
 */
@Stateless
@LocalBean
public class HistoricoTemperaturaPersistenciaDAO
{
	@PersistenceContext
	private EntityManager em;
	
	@EJB
	TerneraPersistenciaDAO terneraPersistenciaDAO;
	
	@EJB
	UsuarioPersistenciaDAO usuarioPersistenciaDAO;
	
	public HistoricoTemperaturaPersistenciaDAO()
	{
		super();
	}
	
	public HistoricoTerneraTemperaturaPersistencia Crear(HistoricoTerneraTemperaturaPersistencia historico) throws Exception
	{
		try
		{
			historico.setTernera(terneraPersistenciaDAO.obtenerTernera(historico.getTernera().getCaravanatambo()));
			
			historico.setUsuario(usuarioPersistenciaDAO.obtenerNombreUsuario(historico.getUsuario().getNombreUsuario()));
			
			HistoricoTerneraTemperaturaPersistencia historicoT = em.merge(historico);
			em.flush();
			return historicoT;
		} catch (PersistenceException e)
		{
			e.printStackTrace();
			return null;
		}
	}
}
