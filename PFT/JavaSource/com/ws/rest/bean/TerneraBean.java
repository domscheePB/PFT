package com.ws.rest.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.capa2LogicaNegocio.GestionAlojamientoService;
import com.capa2LogicaNegocio.GestionGuacheraService;
import com.capa2LogicaNegocio.GestionRazaService;
import com.capa2LogicaNegocio.GestionTerneraService;
import com.capa2LogicaNegocioEntities.Guachera;
import com.capa2LogicaNegocioEntities.Raza;
import com.capa2LogicaNegocioEntities.Ternera;
import com.ws.rest.entities.TerneraRestEntidad;

@Stateless
@LocalBean
public class TerneraBean implements Serializable
{	
	private static final long serialVersionUID = 1L;
	
	@EJB
	GestionTerneraService gestionTerneraService;
	
	@EJB
	GestionAlojamientoService gestionAlojamientoService;
	
	@EJB
	GestionRazaService gestionRazaService;
	
	@EJB
	GestionGuacheraService gestionGuacheraService;
	
	public TerneraRestEntidad crearTernera(TerneraRestEntidad ternera)
	{
		ternera = fromTernera(gestionTerneraService.crearTernera(toTernera(ternera), ternera.getRaza(), ternera.getIdentificadorGuachera()));
		
		return ternera;
	}
	
	public List<String> listaRazas()
	{
		List<String> listaRazas = new ArrayList<String>();
		List<Raza> listaAux = gestionRazaService.obtenerTodas();
		for(int i = 0; i<listaAux.size(); i++)
			listaRazas.add(listaAux.get(i).getNombre());
		
		return listaRazas;
	}
	
	public List<String> listaGuacheras()
	{
		List<String> listaGuacheras = new ArrayList<String>();
		List<Guachera> listaAux = gestionGuacheraService.obtenerTodas();
		for(int i = 0; i<listaAux.size(); i++)
			listaGuacheras.add(listaAux.get(i).getIdentificadorUnico());
		
		return listaGuacheras;
	}
	
	public List<TerneraRestEntidad> obtenerTerneras()
	{
		List<Ternera> listaAux = gestionTerneraService.obtenerTodas();
		List<TerneraRestEntidad> listaTerneras = new ArrayList<TerneraRestEntidad>();
		for(int i = 0; i<listaAux.size(); i++)
			listaTerneras.add(fromTernera(listaAux.get(i)));
		
		return listaTerneras;
	}
	
	//Conversión de entidades:
	public TerneraRestEntidad fromTernera(Ternera t)
	{
		TerneraRestEntidad ternera = new TerneraRestEntidad();
		ternera.setId(t.getId().longValue());
		ternera.setCaravanasnis(t.getCaravanasnis());
		ternera.setCaravanatambo(t.getCaravanatambo());
		ternera.setFechaNacimiento(t.getFechaNacimiento());
		ternera.setIdentificadorGuachera(gestionAlojamientoService.obtenerUltimaGuachera(t).getIdentificadorUnico());
		ternera.setIdMadre(t.getIdMadre());
		ternera.setIdPadre(t.getIdPadre());
		ternera.setPesoAlNacer(t.getPesoAlNacer());
		ternera.setRaza(t.getRaza().getNombre());
		ternera.setTipoDeParto(t.getTipoDeParto());
		
		return ternera;
	}
	
	public Ternera toTernera(TerneraRestEntidad t)
	{
		Ternera ternera = new Ternera();
		ternera.setId(t.getId()!=null?t.getId().longValue():null);
		ternera.setCaravanasnis(t.getCaravanasnis());
		ternera.setCaravanatambo(t.getCaravanatambo());
		ternera.setFechaNacimiento(t.getFechaNacimiento());
		ternera.setIdMadre(t.getIdMadre());
		ternera.setIdPadre(t.getIdPadre());
		ternera.setPesoAlNacer(t.getPesoAlNacer());
		ternera.setRaza(gestionRazaService.obtenerRaza(t.getRaza()));
		ternera.setTipoDeParto(t.getTipoDeParto());
		
		return ternera;
	}
}