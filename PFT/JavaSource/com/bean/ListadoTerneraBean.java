package com.bean;

import javax.ejb.EJB;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.inject.Named;

import com.capa2LogicaNegocio.GestionAlojamientoService;
import com.capa2LogicaNegocio.GestionGuacheraService;
import com.capa2LogicaNegocio.GestionRazaService;
import com.capa2LogicaNegocio.GestionTerneraService;
import com.capa2LogicaNegocioEntities.Ternera;
import com.controlFormato.ControlFormato;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

@Named(value="listadoTernera")		
@SessionScoped				       
public class ListadoTerneraBean implements Serializable
{	
	@EJB
	GestionTerneraService gestionTerneraService;
	
	@EJB
	GestionGuacheraService gestionGuacheraService;
	
	@EJB
	GestionRazaService gestionRazaService;
	
	@EJB
	GestionAlojamientoService gestionAlojamientoService;
	
	private static final long serialVersionUID = 1L;
	
	private String caravanaSnis;
	private String caravanaTambo;
	private String tipoParto;
	private String guachera;
	private String raza;
	private List<SelectItem> guacheras;
	private List<SelectItem> razas;
	private int idMadre;
	private int idPadre;
	private Date nacimientoDesde;
	private Date nacimientoHasta;
	private float pesoDesde;
	private float pesoHasta;
	
	private Ternera terneraSeleccionada;
	private List<Ternera> ternerasSeleccionadas;
	
	public ListadoTerneraBean()
	{
		super();
	}
	
	public void preRenderViewListener()
	{
		List<String> listaGuacheras = gestionGuacheraService.obtenerListaIdentificadores();
		
		if(guacheras != null)
		{
			guacheras.clear();
		}
		else
		{
			guacheras = new ArrayList<SelectItem>();
		}
		
		SelectItem aux = new SelectItem();
		
		for(int i = 0; i<listaGuacheras.size(); i++)
		{
			aux = new SelectItem(listaGuacheras.get(i));
			guacheras.add(aux);
		}
		
		//Carga de razas
		List<String> listaRazas = gestionRazaService.obtenerListaRazas();
		
		if(razas != null)
		{
			razas.clear();
		}
		else
		{
			razas = new ArrayList<SelectItem>();
		}
		
		for(int i = 0; i<listaRazas.size(); i++)
		{
			aux = new SelectItem(listaRazas.get(i));
			razas.add(aux);
		}
		
		seleccionarTerneras();
	}
	
	public String seleccionarTerneras()
	{
		ternerasSeleccionadas = gestionTerneraService.obtenerTerneras(caravanaSnis, caravanaTambo, tipoParto, guachera, raza, idMadre, idPadre, nacimientoDesde, nacimientoHasta, pesoDesde, pesoHasta);
		return "";
	}
	
	public String listar()
	{
		boolean error = false;
		if((caravanaSnis!= null && !caravanaSnis.contentEquals("")) && (!ControlFormato.Alfabetico(caravanaSnis) || caravanaSnis.length()>30))
		{
			FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "'Caravana Snis' debe ser Alfanumérico con máximo de 30 caracteres.", "");
			FacesContext.getCurrentInstance().addMessage(null, facesMsg);
			error = true;
		}
		
		if((caravanaTambo!= null && !caravanaTambo.contentEquals("")) && (!ControlFormato.Alfabetico(caravanaTambo) || caravanaTambo.length()>30))
		{
			FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "'Caravana Tambo' debe ser Alfanumérico con máximo de 30 caracteres.", "");
			FacesContext.getCurrentInstance().addMessage(null, facesMsg);
			error = true;
		}
		
		if(idMadre != 0 && idMadre<0)
		{
			FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "'Identificador-Madre' debe ser Numérico entero mayor a 0.", "");
			FacesContext.getCurrentInstance().addMessage(null, facesMsg);
			error = true;
		}
		
		if(idPadre != 0 && idPadre<0)
		{
			FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "'Identificador-Padre' debe ser Numérico entero mayor a 0.", "");
			FacesContext.getCurrentInstance().addMessage(null, facesMsg);
			error = true;
		}
		
		if((nacimientoHasta!= null && nacimientoDesde != null) && nacimientoDesde.compareTo(nacimientoHasta) == 1)
		{
			FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "'Fecha de Nacimiento hasta' debe ser posterior a 'Fecha de nacimiento desde'.", "");
			FacesContext.getCurrentInstance().addMessage(null, facesMsg);
			error = true;
		}
		
		boolean errorPeso = false;
		if(pesoDesde != 0 && pesoDesde<15)
		{
			FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "'Peso al nacer desde' debe ser Numérico decimal mayor a 15.", "");
			FacesContext.getCurrentInstance().addMessage(null, facesMsg);
			errorPeso = true;
			error = true;
		}
		
		if(pesoHasta != 0 && pesoHasta<15)
		{
			FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "'Peso al nacer hasta' debe ser Numérico decimal mayor a 15.", "");
			FacesContext.getCurrentInstance().addMessage(null, facesMsg);
			errorPeso = true;
			error = true;
		}
		
		if((pesoHasta!= 0 && pesoDesde != 0) && !errorPeso && (pesoHasta < pesoDesde))
		{
			FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "'Peso al nacer desde' debe ser menor a 'Peso al nacer hasta'.", "");
			FacesContext.getCurrentInstance().addMessage(null, facesMsg);
			error = true;
		}
		
		if(error==false)
		{
			seleccionarTerneras();
		}
		
		return "";
	}
	
	public String obtenerGuacheraActual(Ternera ternera)
	{
		return gestionAlojamientoService.obtenerUltimaGuachera(ternera).getIdentificadorUnico();
	}
	
	public void eliminarTernera(Ternera ternera) throws Exception
	{
		try
		{
			gestionTerneraService.eliminarTernera(ternera);
			seleccionarTerneras();
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	public String getCaravanaSnis() {
		return caravanaSnis;
	}

	public void setCaravanaSnis(String caravanaSnis) {
		this.caravanaSnis = caravanaSnis;
	}

	public String getCaravanaTambo() {
		return caravanaTambo;
	}

	public void setCaravanaTambo(String caravanaTambo) {
		this.caravanaTambo = caravanaTambo;
	}

	public String getTipoParto() {
		return tipoParto;
	}

	public void setTipoParto(String tipoParto) {
		this.tipoParto = tipoParto;
	}

	public String getGuachera() {
		return guachera;
	}

	public void setGuachera(String guachera) {
		this.guachera = guachera;
	}

	public String getRaza() {
		return raza;
	}

	public void setRaza(String raza) {
		this.raza = raza;
	}

	public List<SelectItem> getGuacheras() {
		return guacheras;
	}

	public void setGuacheras(List<SelectItem> guacheras) {
		this.guacheras = guacheras;
	}

	public List<SelectItem> getRazas() {
		return razas;
	}

	public void setRazas(List<SelectItem> razas) {
		this.razas = razas;
	}

	public int getIdMadre() {
		return idMadre;
	}

	public void setIdMadre(int idMadre) {
		this.idMadre = idMadre;
	}

	public int getIdPadre() {
		return idPadre;
	}

	public void setIdPadre(int idPadre) {
		this.idPadre = idPadre;
	}

	public Date getNacimientoDesde() {
		return nacimientoDesde;
	}

	public void setNacimientoDesde(Date nacimientoDesde) {
		this.nacimientoDesde = nacimientoDesde;
	}

	public Date getNacimientoHasta() {
		return nacimientoHasta;
	}

	public void setNacimientoHasta(Date nacimientoHasta) {
		this.nacimientoHasta = nacimientoHasta;
	}

	public float getPesoDesde() {
		return pesoDesde;
	}

	public void setPesoDesde(float pesoDesde) {
		this.pesoDesde = pesoDesde;
	}

	public float getPesoHasta() {
		return pesoHasta;
	}

	public void setPesoHasta(float pesoHasta) {
		this.pesoHasta = pesoHasta;
	}

	public Ternera getTerneraSeleccionada() {
		return terneraSeleccionada;
	}

	public void setTerneraSeleccionada(Ternera terneraSeleccionada) {
		this.terneraSeleccionada = terneraSeleccionada;
	}

	public List<Ternera> getTernerasSeleccionadas() {
		return ternerasSeleccionadas;
	}

	public void setTernerasSeleccionadas(List<Ternera> ternerasSeleccionadas) {
		this.ternerasSeleccionadas = ternerasSeleccionadas;
	}
}
