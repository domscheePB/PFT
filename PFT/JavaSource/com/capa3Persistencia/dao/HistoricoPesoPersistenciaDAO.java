package com.capa3Persistencia.dao;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import com.capa3Persistencia.entities.HistoricoTerneraPesoPersistencia;
/**
 * Session Bean implementation class UsuariosEJBBean
 */
@Stateless
@LocalBean
public class HistoricoPesoPersistenciaDAO
{
	@PersistenceContext
	private EntityManager em;
	
	@EJB
	TerneraPersistenciaDAO terneraPersistenciaDAO;
	
	@EJB
	UsuarioPersistenciaDAO usuarioPersistenciaDAO;
	
	public HistoricoPesoPersistenciaDAO()
	{
		super();
	}
	
	public HistoricoTerneraPesoPersistencia Crear(HistoricoTerneraPesoPersistencia historico) throws Exception
	{
		try
		{
			historico.setTernera(terneraPersistenciaDAO.obtenerTernera(historico.getTernera().getCaravanatambo()));
			
			historico.setUsuario(usuarioPersistenciaDAO.obtenerNombreUsuario(historico.getUsuario().getNombreUsuario()));
			
			HistoricoTerneraPesoPersistencia historicoP = em.merge(historico);
			em.flush();
			return historicoP;
		} catch (PersistenceException e)
		{
			e.printStackTrace();
			return null;
		}
	}
	
	public List<HistoricoTerneraPesoPersistencia> obtenerTodos()
	{
		TypedQuery<HistoricoTerneraPesoPersistencia> query = em.createQuery("SELECT h FROM HistoricoTerneraPesoPersistencia h", HistoricoTerneraPesoPersistencia.class);
		List<HistoricoTerneraPesoPersistencia> historico = query.getResultList();
		
		return historico;
	}
}