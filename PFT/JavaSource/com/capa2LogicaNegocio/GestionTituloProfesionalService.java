package com.capa2LogicaNegocio;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.capa2LogicaNegocioEntities.TituloProfesional;
import com.capa3Persistencia.dao.TituloProfesionalPersistenciaDAO;
import com.capa3Persistencia.entities.TitulosProfesionalPersistencia;

@Stateless
@LocalBean
public class GestionTituloProfesionalService implements Serializable
{	
	private static final long serialVersionUID = 1L;
	
	@EJB
	TituloProfesionalPersistenciaDAO tituloPersistenciaDAO;

	public TituloProfesional fromTituloProfesionalPersistencia(TitulosProfesionalPersistencia t)
	{
		TituloProfesional titulo = new TituloProfesional();
		titulo.setId(t.getIdTituloProfesional().longValue());
		titulo.setTitulo(t.getNombre());
		return titulo;
	}
	
	public TitulosProfesionalPersistencia toTitulosProfesionalPersistencia(TituloProfesional t)
	{
		TitulosProfesionalPersistencia tituloPersistencia = new TitulosProfesionalPersistencia();
		tituloPersistencia.setIdTituloProfesional(t.getId()!=null?t.getId().longValue():null);
		tituloPersistencia.setNombre(t.getTitulo());
		
		return tituloPersistencia;
	}
	
	public List<String> obtenerListaTitulos()
	{
		List<String> titulos = new ArrayList<String>();
		List<TitulosProfesionalPersistencia> listaTitulos = tituloPersistenciaDAO.obtenerTodos();
		
		for (int i = 0; i<listaTitulos.size(); i++)
			titulos.add(listaTitulos.get(i).getNombre());
		
		return titulos;
	}
}