package com.capa2LogicaNegocio;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.capa2LogicaNegocioEntities.Guachera;
import com.capa3Persistencia.dao.GuacheraPersistenciaDAO;
import com.capa3Persistencia.entities.GuacheraPersistencia;

@Stateless
@LocalBean
public class GestionGuacheraService implements Serializable
{	
	private static final long serialVersionUID = 1L;
	
	@EJB
	GestionUbicacionService gestionUbicacionService;
	
	@EJB
	GestionTipoGuacheraService gestionTipoGuacheraService;
	
	@EJB
	GuacheraPersistenciaDAO gestionGuacheraDAO;

	public Guachera fromGuacheraPersistencia(GuacheraPersistencia g)
	{
		Guachera guachera = new Guachera();
		guachera.setId(g.getIdGuachera());
		guachera.setIdentificadorUnico(g.getIdentificadorUnico());
		guachera.setAncho(g.getAncho());
		guachera.setLargo(g.getLargo());
		guachera.setCapacidad(g.getCapacidad());
		guachera.setUbicacion(gestionUbicacionService.fromUbicacionPersistencia(g.getUbicacione()));
		guachera.setTipoGuachera(gestionTipoGuacheraService.fromTipoGuacheraPersistencia(g.getTipoGuachera()));
		return guachera;
	}
	
	public GuacheraPersistencia toGuacheraPersistencia(Guachera g)
	{
		GuacheraPersistencia guachera = new GuacheraPersistencia();
		guachera.setIdGuachera(g.getId()!=null?g.getId().longValue():null);
		guachera.setIdentificadorUnico(g.getIdentificadorUnico());
		guachera.setAncho(g.getAncho());
		guachera.setLargo(g.getLargo());
		guachera.setCapacidad(g.getCapacidad());
		guachera.setUbicacione(gestionUbicacionService.toUbicacionPersistencia(g.getUbicacion()));
		guachera.setTipoGuachera(gestionTipoGuacheraService.toTipoGuacheraPersistencia(g.getTipoGuachera()));
		
		return guachera;
	}

	public Guachera obtenerGuachera(Long id)
	{
		GuacheraPersistencia guachera = gestionGuacheraDAO.obtenerGuacheraID(id);
		
		return fromGuacheraPersistencia(guachera);
	}
	
	public Guachera crearGuachera(Guachera guachera) throws Exception
	{
		GuacheraPersistencia g = gestionGuacheraDAO.Crear(toGuacheraPersistencia(guachera));
		
		return fromGuacheraPersistencia(g);
	}
	
	public Guachera actualizarGuachera(Guachera guachera) throws Exception
	{
		GuacheraPersistencia g = gestionGuacheraDAO.Actualizar(toGuacheraPersistencia(guachera));
		
		return fromGuacheraPersistencia(g);
	}
	
	public List<Guachera> obtenerGuacheras(String identificador,
			String tipo,
			float anchoDesde, float anchoHasta,
			float largoDesde, float largoHasta,
			float latitudDesde, float latitudHasta,
			float longitudDesde, float longitudHasta,
			int capacidadDesde, int capacidadHasta)
	{
		List<GuacheraPersistencia> gPersistencia = gestionGuacheraDAO.obtenerGuacheras(identificador, tipo, anchoDesde, anchoHasta, largoDesde, largoHasta, latitudDesde, latitudHasta, longitudDesde, longitudHasta, capacidadDesde, capacidadHasta);
		
		List<Guachera> listaGuacheras = new ArrayList<Guachera>();
		for(int i = 0; i<gPersistencia.size(); i++)
			listaGuacheras.add(fromGuacheraPersistencia(gPersistencia.get(i)));
		
		return listaGuacheras;
	}
	
	public void eliminarGuachera(Guachera guachera) throws Exception
	{
		gestionGuacheraDAO.eliminarGuachera(toGuacheraPersistencia(guachera));
	}
	
	public List<Guachera> obtenerTodas()
	{
		List<GuacheraPersistencia> listaAux = gestionGuacheraDAO.obtenerTodos();
		List<Guachera> listaGuacheras = new ArrayList<Guachera>();
		
		for(int i = 0; i<listaAux.size(); i++)
			listaGuacheras.add(fromGuacheraPersistencia(listaAux.get(i)));
		
		return listaGuacheras;
	}
	
	public List<String> obtenerListaIdentificadores()
	{
		List<Guachera> listaGuacheras = obtenerTodas();
		List<String> listaIdentificadores = new ArrayList<String>();
		
		for(int i = 0; i<listaGuacheras.size(); i++)
			listaIdentificadores.add(listaGuacheras.get(i).getIdentificadorUnico());
		
		return listaIdentificadores;
	}
	
	public boolean identificadorRepetido(String identificador)
	{
		return gestionGuacheraDAO.idRepetido(identificador);
	}
}