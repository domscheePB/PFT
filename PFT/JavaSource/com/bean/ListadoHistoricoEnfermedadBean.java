package com.bean;

import javax.ejb.EJB;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.inject.Named;

import com.capa2LogicaNegocio.GestionPadecimientoService;
import com.capa2LogicaNegocioEntities.Padecimiento;
import com.controlFormato.ControlFormato;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

@Named(value="listadoHistoricoEnfermedad")		
@SessionScoped				       
public class ListadoHistoricoEnfermedadBean implements Serializable{
	
	@EJB
	GestionPadecimientoService gestionPadecimientoService;
	
	private static final long serialVersionUID = 1L;
	
	private String identificadorTernera;
	private String nombreEnfermedad;
	private String variante;
	private String gravedad;
	private List<SelectItem> gravedades;
	private Date fechaDesde;
	private Date fechaHasta;
	
	private Padecimiento padecimientoSeleccionado;
	private List<Padecimiento> padecimientosSeleccionados;
	
	public ListadoHistoricoEnfermedadBean()
	{
		super();
	}
	
	public void preRenderViewListener()
	{
		List<String> listaGravedades = gestionPadecimientoService.obtenerGravedades();
		
		if(gravedades != null)
		{
			gravedades.clear();
		}
		else
		{
			gravedades = new ArrayList<SelectItem>();
		}
		
		SelectItem aux = new SelectItem();
		
		for(int i = 0; i<listaGravedades.size(); i++)
		{
			aux = new SelectItem(listaGravedades.get(i));
			gravedades.add(aux);
		}
		
		seleccionarHistorico();
	}
	
	public String seleccionarHistorico()
	{
		padecimientosSeleccionados = gestionPadecimientoService.obtenerPadecimientos(identificadorTernera, nombreEnfermedad, variante, gravedad, fechaDesde, fechaHasta);
		return "";
	}
	
	public String listar()
	{
		boolean error = false;
		if((identificadorTernera != null && !identificadorTernera.contentEquals("")) && (!ControlFormato.AlfaNumerico(identificadorTernera) || identificadorTernera.length()>30))
		{
			FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "'Identificador ternera' debe ser Alfanumerico con un máximo de 30 caracteres.", "");
			FacesContext.getCurrentInstance().addMessage(null, facesMsg);
			error = true;
		}
		
		if((nombreEnfermedad != null && !nombreEnfermedad.contentEquals("")) && (!ControlFormato.AlfaNumerico(nombreEnfermedad) || nombreEnfermedad.length()>30))
		{
			FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "'Enfermedad' debe ser Alfanumerico con un máximo de 30 caracteres.", "");
			FacesContext.getCurrentInstance().addMessage(null, facesMsg);
			error = true;
		}
		
		if((variante != null && !variante.contentEquals("")) && (!ControlFormato.AlfaNumerico(variante) || variante.length()>30))
		{
			FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "'Variante' debe ser Alfanumérico con un máximo de 30 caracteres.", "");
			FacesContext.getCurrentInstance().addMessage(null, facesMsg);
			error = true;
		}
		
		if((fechaHasta!= null && fechaDesde != null) && fechaDesde.compareTo(fechaHasta) == 1)
		{
			FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "'Fecha registro hasta' debe ser posterior a 'Fecha registro desde'.", "");
			FacesContext.getCurrentInstance().addMessage(null, facesMsg);
			error = true;
		}
		
		if(error==false)
		{
			seleccionarHistorico();
		}
		
		return "";
	}

	public String getIdentificadorTernera() {
		return identificadorTernera;
	}

	public void setIdentificadorTernera(String identificadorTernera) {
		this.identificadorTernera = identificadorTernera;
	}

	public String getNombreEnfermedad() {
		return nombreEnfermedad;
	}

	public void setNombreEnfermedad(String nombreEnfermedad) {
		this.nombreEnfermedad = nombreEnfermedad;
	}

	public String getVariante() {
		return variante;
	}

	public void setVariante(String variante) {
		this.variante = variante;
	}

	public String getGravedad() {
		return gravedad;
	}

	public void setGravedad(String gravedad) {
		this.gravedad = gravedad;
	}

	public List<SelectItem> getGravedades() {
		return gravedades;
	}

	public void setGravedades(List<SelectItem> gravedades) {
		this.gravedades = gravedades;
	}

	public Date getFechaDesde() {
		return fechaDesde;
	}

	public void setFechaDesde(Date fechaDesde) {
		this.fechaDesde = fechaDesde;
	}

	public Date getFechaHasta() {
		return fechaHasta;
	}

	public void setFechaHasta(Date fechaHasta) {
		this.fechaHasta = fechaHasta;
	}

	public Padecimiento getPadecimientoSeleccionado() {
		return padecimientoSeleccionado;
	}

	public void setPadecimientoSeleccionado(Padecimiento padecimientoSeleccionado) {
		this.padecimientoSeleccionado = padecimientoSeleccionado;
	}

	public List<Padecimiento> getPadecimientosSeleccionados() {
		return padecimientosSeleccionados;
	}

	public void setPadecimientosSeleccionados(List<Padecimiento> padecimientosSeleccionados) {
		this.padecimientosSeleccionados = padecimientosSeleccionados;
	}
}
