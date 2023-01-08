package com.bean;

import javax.ejb.EJB;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Named;

import com.capa2LogicaNegocio.GestionGuacheraService;
import com.capa2LogicaNegocio.GestionTipoGuacheraService;
import com.capa2LogicaNegocioEntities.Guachera;
import com.controlFormato.ControlFormato;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

@Named(value="listadoGuachera")		
@SessionScoped				       
public class ListadoGuacheraBean implements Serializable{
	
	@EJB
	GestionGuacheraService gestionGuacheraService;
	
	@EJB
	GestionTipoGuacheraService gestionTipoGuacheraService;
	
	private static final long serialVersionUID = 1L;
	
	private String identificador;
	private String tipo;
	private List<SelectItem> tipos;
	private float anchoDesde;
	private float anchoHasta;
	private float largoDesde;
	private float largoHasta;
	private float latitudDesde;
	private float latitudHasta;
	private float longitudDesde;
	private float longitudHasta;
	private int capacidadDesde;
	private int capacidadHasta;
	
	private Guachera guacheraSeleccionada;
	private List<Guachera> guacherasSeleccionadas;
	
	public ListadoGuacheraBean()
	{
		super();
	}
	
	public void preRenderViewListener()
	{
		List<String> listaTipos = gestionTipoGuacheraService.obtenerTodos();
		
		if(tipos != null)
		{
			tipos.clear();
		}
		else
		{
			tipos = new ArrayList<SelectItem>();
		}
		
		SelectItem aux = new SelectItem();
		
		for(int i = 0; i<listaTipos.size(); i++)
		{
			aux = new SelectItem(listaTipos.get(i));
			tipos.add(aux);
		}
		
		seleccionarGuacheras();
	}
	
	public String seleccionarGuacheras()
	{
		guacherasSeleccionadas=gestionGuacheraService.obtenerGuacheras(identificador, tipo, anchoDesde, anchoHasta, largoDesde, largoHasta, latitudDesde, latitudHasta, longitudDesde, longitudHasta, capacidadDesde, capacidadHasta);
		return "";
	}
	
	public String listar()
	{
		boolean error = false;
		boolean errorCapacidad = false;
		boolean errorLargo = false;
		boolean errorAncho = false;
		boolean errorLatitud = false;
		boolean errorLongitud = false;
		if((identificador!= null && !identificador.contentEquals("")) && !ControlFormato.AlfaNumerico(identificador) || identificador.length()>30)
		{
			FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "'Identificador' debe ser Alfanumérico con máximo de 30 caracteres.", "");
			FacesContext.getCurrentInstance().addMessage(null, facesMsg);
			error = true;
		}
		//Capacidad
		if(capacidadDesde != 0 && (capacidadDesde<0 || capacidadDesde > 30))
		{
			FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "'Capacidad desde' debe ser Numérico entero entre 0 y 30", "");
			FacesContext.getCurrentInstance().addMessage(null, facesMsg);
			errorCapacidad = true;
			error = true;
		}
		if(capacidadHasta != 0 && (capacidadHasta<0 || capacidadHasta > 30))
		{
			FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "'Capacidad hasta' debe ser Numérico entero entre 0 y 30", "");
			FacesContext.getCurrentInstance().addMessage(null, facesMsg);
			errorCapacidad = true;
			error = true;
		}
		if((capacidadDesde != 0 && capacidadHasta != 0) && (!errorCapacidad) && capacidadDesde>capacidadHasta)
		{
			FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "'Capacidad desde' debe ser menor a 'Capacidad hasta'", "");
			FacesContext.getCurrentInstance().addMessage(null, facesMsg);
			error = true;
		}
		//Ancho
		if(anchoDesde != 0 && (anchoDesde<0 || anchoDesde > 20))
		{
			FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "'Ancho desde' debe ser Numérico entero entre 0 y 20", "");
			FacesContext.getCurrentInstance().addMessage(null, facesMsg);
			errorAncho = true;
			error = true;
		}
		if(anchoHasta != 0 && (anchoHasta<0 || anchoHasta > 20))
		{
			FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "'Ancho hasta' debe ser Numérico entero entre 0 y 20", "");
			FacesContext.getCurrentInstance().addMessage(null, facesMsg);
			errorAncho = true;
			error = true;
		}
		if((anchoDesde != 0 && anchoHasta != 0) && (!errorAncho) && anchoDesde>anchoHasta)
		{
			FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "'Ancho desde' debe ser menor a 'Ancho hasta'", "");
			FacesContext.getCurrentInstance().addMessage(null, facesMsg);
			error = true;
		}
		//Largo
		if(largoDesde != 0 && (largoDesde<0 || largoDesde > 20))
		{
			FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "'Largo desde' debe ser Numérico entero entre 0 y 20", "");
			FacesContext.getCurrentInstance().addMessage(null, facesMsg);
			errorLargo = true;
			error = true;
		}
		if(largoHasta != 0 && (largoHasta<0 || largoHasta > 20))
		{
			FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "'Largo hasta' debe ser Numérico entero entre 0 y 20", "");
			FacesContext.getCurrentInstance().addMessage(null, facesMsg);
			errorLargo = true;
			error = true;
		}
		if((largoDesde != 0 && largoHasta != 0) && (!errorLargo) && largoDesde>largoHasta)
		{
			FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "'Largo desde' debe ser menor a 'Largo hasta'", "");
			FacesContext.getCurrentInstance().addMessage(null, facesMsg);
			error = true;
		}
		//Latitud
		if(latitudDesde != 0 && (latitudDesde<-90 || latitudDesde > 90))
		{
			FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "'Latitud desde' debe ser Numérico decimal entre -90 y 90", "");
			FacesContext.getCurrentInstance().addMessage(null, facesMsg);
			errorLatitud = true;
			error = true;
		}
		if(latitudHasta != 0 && (latitudHasta<-90 || latitudHasta > 90))
		{
			FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "'Latitud hasta' debe ser Numérico entero entre -90 y 90", "");
			FacesContext.getCurrentInstance().addMessage(null, facesMsg);
			errorLatitud = true;
			error = true;
		}
		if((latitudDesde != 0 && latitudHasta != 0) && (!errorLatitud) && latitudDesde>latitudHasta)
		{
			FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "'Latitud desde' debe ser menor a 'Latitud hasta'", "");
			FacesContext.getCurrentInstance().addMessage(null, facesMsg);
			error = true;
		}
		//Longitud
		if(longitudDesde != 0 && (longitudDesde<-180 || longitudDesde > 180))
		{
			FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "'Longitud desde' debe ser Numérico decimal entre -180 y 180", "");
			FacesContext.getCurrentInstance().addMessage(null, facesMsg);
			errorLongitud = true;
			error = true;
		}
		if(longitudHasta != 0 && (longitudHasta<-180 || longitudHasta > 180))
		{
			FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "'Longitud hasta' debe ser Numérico entero entre -180 y 180", "");
			FacesContext.getCurrentInstance().addMessage(null, facesMsg);
			errorLongitud = true;
			error = true;
		}
		if((longitudDesde != 0 && longitudHasta != 0) && (!errorLongitud) && longitudDesde>longitudHasta)
		{
			FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "'Longitud desde' debe ser menor a 'Longitud hasta'", "");
			FacesContext.getCurrentInstance().addMessage(null, facesMsg);
			error = true;
		}
		//Ejecución si no hay error:
		if(error == false)
		{
			seleccionarGuacheras();
		}
		return "";
	}
	
	public void eliminarGuachera(Guachera guachera) throws Exception
	{
		try
		{
			gestionGuacheraService.eliminarGuachera(guachera);
			seleccionarGuacheras();
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	public String getIdentificador() {
		return identificador;
	}

	public void setIdentificador(String identificador) {
		this.identificador = identificador;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public List<SelectItem> getTipos() {
		return tipos;
	}

	public void setTipos(List<SelectItem> tipos) {
		this.tipos = tipos;
	}

	public float getAnchoDesde() {
		return anchoDesde;
	}

	public void setAnchoDesde(float anchoDesde) {
		this.anchoDesde = anchoDesde;
	}

	public float getAnchoHasta() {
		return anchoHasta;
	}

	public void setAnchoHasta(float anchoHasta) {
		this.anchoHasta = anchoHasta;
	}

	public float getLargoDesde() {
		return largoDesde;
	}

	public void setLargoDesde(float largoDesde) {
		this.largoDesde = largoDesde;
	}

	public float getLargoHasta() {
		return largoHasta;
	}

	public void setLargoHasta(float largoHasta) {
		this.largoHasta = largoHasta;
	}

	public float getLatitudDesde() {
		return latitudDesde;
	}

	public void setLatitudDesde(float latitudDesde) {
		this.latitudDesde = latitudDesde;
	}

	public float getLatitudHasta() {
		return latitudHasta;
	}

	public void setLatitudHasta(float latitudHasta) {
		this.latitudHasta = latitudHasta;
	}

	public float getLongitudDesde() {
		return longitudDesde;
	}

	public void setLongitudDesde(float longitudDesde) {
		this.longitudDesde = longitudDesde;
	}

	public float getLongitudHasta() {
		return longitudHasta;
	}

	public void setLongitudHasta(float longitudHasta) {
		this.longitudHasta = longitudHasta;
	}

	public int getCapacidadDesde() {
		return capacidadDesde;
	}

	public void setCapacidadDesde(int capacidadDesde) {
		this.capacidadDesde = capacidadDesde;
	}

	public int getCapacidadHasta() {
		return capacidadHasta;
	}

	public void setCapacidadHasta(int capacidadHasta) {
		this.capacidadHasta = capacidadHasta;
	}

	public Guachera getGuacheraSeleccionada() {
		return guacheraSeleccionada;
	}

	public void setGuacheraSeleccionada(Guachera guacheraSeleccionada) {
		this.guacheraSeleccionada = guacheraSeleccionada;
	}

	public List<Guachera> getGuacherasSeleccionadas() {
		return guacherasSeleccionadas;
	}

	public void setGuacherasSeleccionadas(List<Guachera> guacherasSeleccionadas) {
		this.guacherasSeleccionadas = guacherasSeleccionadas;
	}
}
