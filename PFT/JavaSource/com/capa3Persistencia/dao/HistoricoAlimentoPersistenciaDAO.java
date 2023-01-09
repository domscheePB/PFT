package com.capa3Persistencia.dao;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.capa3Persistencia.entities.HistoricoAlimentoPersistencia;
/**
 * Session Bean implementation class UsuariosEJBBean
 */
@Stateless
@LocalBean
public class HistoricoAlimentoPersistenciaDAO
{
	@PersistenceContext
	private EntityManager em;
	
	public HistoricoAlimentoPersistenciaDAO()
	{
		super();
	}
	
	public List<HistoricoAlimentoPersistencia> obtenerTodos()
	{
		TypedQuery<HistoricoAlimentoPersistencia> query = em.createQuery("SELECT h FROM HistoricoAlimentoPersistencia h", HistoricoAlimentoPersistencia.class);
		List<HistoricoAlimentoPersistencia> historico = query.getResultList();
		
		return historico;
	}
}
