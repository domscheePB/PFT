package com.capa2LogicaNegocio;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.capa2LogicaNegocioEntities.Raza;
import com.capa3Persistencia.dao.RazaPersistenciaDAO;
import com.capa3Persistencia.entities.RazaPersistencia;

@Stateless
@LocalBean
public class GestionRazaService implements Serializable
{	
	private static final long serialVersionUID = 1L;
	
	@EJB
	RazaPersistenciaDAO razaPersistenciaDAO;

	public Raza fromRazaPersistencia(RazaPersistencia r)
	{
		Raza raza = new Raza();
		raza.setId(r.getIdRaza().longValue());
		raza.setNombre(r.getRaza());
		
		return raza;
	}
	
	public RazaPersistencia toRazaPersistencia(Raza r)
	{
		RazaPersistencia razaPersistencia = new RazaPersistencia();
		razaPersistencia.setIdRaza(r.getId()!=null?r.getId().longValue():null);
		razaPersistencia.setRaza(r.getNombre());
		
		return razaPersistencia;
	}
	
	public List<Raza> obtenerTodas()
	{
		List<Raza> listaRazas = new ArrayList<Raza>();
		List<RazaPersistencia> listaAux = razaPersistenciaDAO.obtenerTodos();
		for(int i = 0; i<listaAux.size(); i++)
			listaRazas.add(fromRazaPersistencia(listaAux.get(i)));
		
		return listaRazas;
	}
	
	public List<String> obtenerListaRazas()
	{
		List<Raza> razas = obtenerTodas();
		List<String> listaRaza = new ArrayList<String>();
		
		for(int i = 0; i<razas.size(); i++)
			listaRaza.add(razas.get(i).getNombre());
		
		return listaRaza;
	}
	
	public Raza obtenerRaza(String nombre)
	{
		return fromRazaPersistencia(razaPersistenciaDAO.obtenerRaza(nombre));
	}
}