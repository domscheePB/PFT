package com.bean;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Named;

import com.capa2LogicaNegocio.GestionHistoricoTemperaturaService;
import com.capa2LogicaNegocio.GestionTerneraService;
import com.capa2LogicaNegocioEntities.HistoricoTemperatura;
import com.capa2LogicaNegocioEntities.Ternera;
import com.capa2LogicaNegocioEntities.Usuario;
//import com.utils.ExceptionsTools;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

@Named(value="altaHistoricoTemperatura")
@SessionScoped
public class GestionHistoricoTemperaturaBean implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	@EJB
	GestionTerneraService gestionTerneraService;
	
	@EJB
	GestionHistoricoTemperaturaService gestionHistoricoService;
	
	private HistoricoTemperatura historico;
	
	private String usuario;
	private List<SelectItem> terneras;
	
	private boolean error = false;
	
	public GestionHistoricoTemperaturaBean()
	{
		super();
	}
	
	@PostConstruct
	public void init()
	{
		
	}
	
	public void preRenderViewListener()
	{
		cargarListas();
		if(error == false)
		{
			historico = new HistoricoTemperatura();
			Usuario usuarioAUX = new Usuario();
			usuarioAUX.setNombreUsuario(usuario);
			historico.setUsuario(usuarioAUX);
			Ternera terneraAUX = new Ternera();
			historico.setTernera(terneraAUX);
		}
	}
	
	public void cargarListas()
	{
		List<String> listaTerneras = gestionTerneraService.obtenerIdentificadoresTerneras();
		
		if(terneras != null)
		{
			terneras.clear();
		}
		else
		{
			terneras = new ArrayList<SelectItem>();
		}
		
		SelectItem aux = new SelectItem();
		
		for(int i = 0; i<listaTerneras.size(); i++)
		{
			aux = new SelectItem(listaTerneras.get(i));
			terneras.add(aux);
		}
	}
	
	public String salvarCambios()
	{
		error = false;
		if(historico.getTemperatura()<=30 || historico.getTemperatura()>45)
		{
			FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "'Peso registrado' debe ser Numérico decimal mayor o igual a 30 y menor o igual a 45.", "");
			FacesContext.getCurrentInstance().addMessage(null, facesMsg);
			error = true;
		}
		
		if(error==false)
		{
			try
			{
				historico = gestionHistoricoService.crearHistoricoTemperatura(historico);
				
				FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Se ha agregado un nuevo registro de Temperatura para la Ternera: " + historico.getTernera().getCaravanatambo(), "");
				FacesContext.getCurrentInstance().addMessage(null, facesMsg);
				
			} catch (Exception e)
			{
				e.printStackTrace();
			}
		}
		
		return "";
	}

	public HistoricoTemperatura getHistorico() {
		return historico;
	}

	public void setHistorico(HistoricoTemperatura historico) {
		this.historico = historico;
	}

	public List<SelectItem> getTerneras() {
		return terneras;
	}

	public void setTerneras(List<SelectItem> terneras) {
		this.terneras = terneras;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
}