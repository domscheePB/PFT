package com.bean;

import javax.ejb.EJB;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.inject.Named;

import com.capa2LogicaNegocio.GestionHistoricoPesoService;
import com.capa2LogicaNegocioEntities.HistoricoPeso;
import com.controlFormato.ControlFormato;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

@Named(value="listadoHistoricoPeso")		
@SessionScoped				       
public class ListadoHistoricoPesoBean implements Serializable{
	
	@EJB
	GestionHistoricoPesoService historicoPesoService;
	
	private static final long serialVersionUID = 1L;
	
	private String identificadorTernera;
	private Date fechaTerneraDesde;
	private Date fechaTerneraHasta;
	
	private List<HistoricoPeso> historicosPeso_T;
	
	private String identificadorGuachera;
	private Date fechaGuacheraDesde;
	private Date fechaGuacheraHasta;
	
	private List<HistoricoPeso> historicosPeso_G;
	
	public ListadoHistoricoPesoBean()
	{
		super();
	}
	
	public String seleccionarHistorico_T()
	{
		boolean error = false;
		if((identificadorTernera != null && !identificadorTernera.contentEquals("")) && (!ControlFormato.AlfaNumerico(identificadorTernera) || identificadorTernera.length()>30))
		{
			FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "'Identificador Ternera' debe ser Alfanumérico y contener menos de 30 caracteres.", "");
			FacesContext.getCurrentInstance().addMessage(null, facesMsg);
			error = true;
		}
		
		if(error == false)
		{
			if(fechaTerneraDesde==null)
			{
				FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Debe ingresar una fecha de inicio para listar", "");
				FacesContext.getCurrentInstance().addMessage(null, facesMsg);
			}
			else if(fechaTerneraHasta!= null && fechaTerneraDesde.compareTo(fechaTerneraHasta) == 1)
			{
				FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Debe ingresar una fecha de final mayor a la fecha de inicio para listar", "");
				FacesContext.getCurrentInstance().addMessage(null, facesMsg);
			}
			else
			{
				historicosPeso_T = historicoPesoService.obtenerVariacionDePeso_T(fechaTerneraDesde, fechaTerneraHasta, identificadorTernera);
			}
		}
		
		return "";
	}
	
	public String seleccionarHistorico_G()
	{
		boolean error = false;
		if((identificadorGuachera != null && !identificadorGuachera.contentEquals("")) && (!ControlFormato.AlfaNumerico(identificadorGuachera) || identificadorGuachera.length()>30))
		{
			FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "'Identificador Guachera' debe ser Alfanumérico y contener menos de 30 caracteres.", "");
			FacesContext.getCurrentInstance().addMessage(null, facesMsg);
			error = true;
		}
		
		if(error == false)
		{
			if(fechaGuacheraDesde==null)
			{
				FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Debe ingresar una fecha de inicio para listar", "");
				FacesContext.getCurrentInstance().addMessage(null, facesMsg);
			}
			else if(fechaGuacheraHasta!= null && fechaGuacheraDesde.compareTo(fechaGuacheraHasta) == 1)
			{
				FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Debe ingresar una fecha de final mayor a la fecha de inicio para listar", "");
				FacesContext.getCurrentInstance().addMessage(null, facesMsg);
			}
			else
			{
				historicosPeso_G = historicoPesoService.obtenerVariacionDePeso_G(fechaGuacheraDesde, fechaGuacheraHasta, identificadorGuachera);
			}
		}
			
		return "";
	}

	public String getIdentificadorTernera() {
		return identificadorTernera;
	}

	public void setIdentificadorTernera(String identificadorTernera) {
		this.identificadorTernera = identificadorTernera;
	}

	public Date getFechaTerneraDesde() {
		return fechaTerneraDesde;
	}

	public void setFechaTerneraDesde(Date fechaTerneraDesde) {
		this.fechaTerneraDesde = fechaTerneraDesde;
	}

	public Date getFechaTerneraHasta() {
		return fechaTerneraHasta;
	}

	public void setFechaTerneraHasta(Date fechaTerneraHasta) {
		this.fechaTerneraHasta = fechaTerneraHasta;
	}

	public List<HistoricoPeso> getHistoricosPeso_T() {
		return historicosPeso_T;
	}

	public void setHistoricosPeso_T(List<HistoricoPeso> historicosPeso_T) {
		this.historicosPeso_T = historicosPeso_T;
	}

	public String getIdentificadorGuachera() {
		return identificadorGuachera;
	}

	public void setIdentificadorGuachera(String identificadorGuachera) {
		this.identificadorGuachera = identificadorGuachera;
	}

	public Date getFechaGuacheraDesde() {
		return fechaGuacheraDesde;
	}

	public void setFechaGuacheraDesde(Date fechaGuacheraDesde) {
		this.fechaGuacheraDesde = fechaGuacheraDesde;
	}

	public Date getFechaGuacheraHasta() {
		return fechaGuacheraHasta;
	}

	public void setFechaGuacheraHasta(Date fechaGuacheraHasta) {
		this.fechaGuacheraHasta = fechaGuacheraHasta;
	}

	public List<HistoricoPeso> getHistoricosPeso_G() {
		return historicosPeso_G;
	}

	public void setHistoricosPeso_G(List<HistoricoPeso> historicosPeso_G) {
		this.historicosPeso_G = historicosPeso_G;
	}
}
