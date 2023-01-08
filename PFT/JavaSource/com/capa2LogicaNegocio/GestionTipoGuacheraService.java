package com.capa2LogicaNegocio;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.capa2LogicaNegocioEntities.TipoGuachera;
import com.capa3Persistencia.dao.TipoGuacheraPersistenciaDAO;
import com.capa3Persistencia.entities.TipoGuacheraPersistencia;

@Stateless
@LocalBean
public class GestionTipoGuacheraService implements Serializable
{	
	private static final long serialVersionUID = 1L;
	
	@EJB
	TipoGuacheraPersistenciaDAO tipoGuacheraPersistenciaDAO;

	public TipoGuachera fromTipoGuacheraPersistencia(TipoGuacheraPersistencia t)
	{
		TipoGuachera tipoGuachera = new TipoGuachera();
		tipoGuachera.setId(t.getIdTipoGuachera());
		tipoGuachera.setTipo(t.getTipo());
		return tipoGuachera;
	}
	
	public TipoGuacheraPersistencia toTipoGuacheraPersistencia(TipoGuachera t)
	{
		TipoGuacheraPersistencia tipoGuacheraPersistencia = new TipoGuacheraPersistencia();
		tipoGuacheraPersistencia.setIdTipoGuachera(t.getId()!=null?t.getId().longValue():null);
		tipoGuacheraPersistencia.setTipo(t.getTipo());
		
		return tipoGuacheraPersistencia;
	}
	
	public List<String> obtenerTodos()
	{
		List<TipoGuacheraPersistencia> tipos = tipoGuacheraPersistenciaDAO.obtenerTodos();
		List<String> lista = new ArrayList<String>();
		
		for(int i = 0; i<tipos.size(); i++)
			 lista.add(tipos.get(i).getTipo());
		
		return lista;
	}
}