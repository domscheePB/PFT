package com.bean;

import javax.ejb.EJB;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.inject.Named;

import com.capa2LogicaNegocio.GestionAlojamientoService;
import com.capa2LogicaNegocio.GestionHistoricoAlimentoService;
import com.capa2LogicaNegocioEntities.HistoricoAlimento;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

@Named(value="listadoHistoricoAlimento")		
@SessionScoped				       
public class ListadoHistoricoAlimentoBean implements Serializable{
	
	@EJB
	GestionHistoricoAlimentoService gestionHistoricoAlimentoService;
	
	@EJB
	GestionAlojamientoService gestionAlojamientoService;
	
	private static final long serialVersionUID = 1L;
	
	private String identificadorTernera;
	private Date fechaTerneraDesde;
	private Date fechaTerneraHasta;
	
	private List<HistoricoAlimento> historicosAlimento_T;
	private List<String> ternerasDistintas_T;
	
	private String identificadorGuachera;
	private Date fechaGuacheraDesde;
	private Date fechaGuacheraHasta;
	
	private List<HistoricoAlimento> historicosAlimento_G;
	private List<String> guacherasDistintas;
	
	public ListadoHistoricoAlimentoBean()
	{
		super();
	}
	
	public String seleccionarHistorico_T()
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
			historicosAlimento_T = gestionHistoricoAlimentoService.obtenerCostosAlimentoTernera(fechaTerneraDesde, fechaTerneraHasta);
			
			ternerasDistintas_T = obtenerListaTerneras(historicosAlimento_T, identificadorTernera);
		}
		
		return "";
	}
	
	public String seleccionarHistorico_G()
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
			historicosAlimento_G = gestionHistoricoAlimentoService.obtenerCostosAlimentoTernera(fechaGuacheraDesde, fechaGuacheraHasta);
			
			guacherasDistintas = obtenerListaGuacheras(historicosAlimento_G, identificadorGuachera);
		}
		
		return "";
	}
	
	private List<String> obtenerListaTerneras(List<HistoricoAlimento> historico, String identificador)
	{
		List<String> ternerasDistintas = new ArrayList<String>();
		for(int i = 0; i<historico.size(); i++)
		{
			if(!ternerasDistintas.contains(historico.get(i).getTernera().getCaravanatambo()) && historico.get(i).getTernera().getCaravanatambo().toLowerCase().contains(identificador.toLowerCase()))
			{
				ternerasDistintas.add(historico.get(i).getTernera().getCaravanatambo());
			}
		}
		
		return ternerasDistintas;
	}
	
	private List<String> obtenerListaGuacheras(List<HistoricoAlimento> historico, String identificador)
	{
		List<String> guacherasDistintas = new ArrayList<String>();
		for(int i = 0; i<historico.size(); i++)
		{
			String guachera = historico.get(i).getGuachera();
			if(!guacherasDistintas.contains(guachera) && guachera.toLowerCase().contains(identificador))
				guacherasDistintas.add(guachera);
		}
		
		return guacherasDistintas;
	}
	
	public float obtenerCostoDeAlimentar_T(String ternera)
	{
		float costo = 0;
		for(int i = 0; i<historicosAlimento_T.size(); i++)
		{
			if(historicosAlimento_T.get(i).getTernera().getCaravanatambo().contentEquals(ternera))
				costo += historicosAlimento_T.get(i).getCostoAlim();
		}
		
		return costo;
	}
	
	public float obtenerCostoDeAlimentar_G(String guachera)
	{
		float costo = 0;
		for(int i = 0; i<historicosAlimento_G.size(); i++)
		{
			if(historicosAlimento_G.get(i).getGuachera().contentEquals(guachera))
				costo += historicosAlimento_G.get(i).getCostoAlim();
		}
		
		return costo;
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

	public List<HistoricoAlimento> getHistoricosAlimento_T() {
		return historicosAlimento_T;
	}

	public void setHistoricosAlimento_T(List<HistoricoAlimento> historicosAlimento_T) {
		this.historicosAlimento_T = historicosAlimento_T;
	}

	public List<String> getTernerasDistintas_T() {
		return ternerasDistintas_T;
	}

	public void setTernerasDistintas_T(List<String> ternerasDistintas_T) {
		this.ternerasDistintas_T = ternerasDistintas_T;
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

	public List<HistoricoAlimento> getHistoricosAlimento_G() {
		return historicosAlimento_G;
	}

	public void setHistoricosAlimento_G(List<HistoricoAlimento> historicosAlimento_G) {
		this.historicosAlimento_G = historicosAlimento_G;
	}

	public List<String> getGuacherasDistintas() {
		return guacherasDistintas;
	}

	public void setGuacherasDistintas(List<String> guacherasDistintas) {
		this.guacherasDistintas = guacherasDistintas;
	}
}
