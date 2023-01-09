package com.bean;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Named;

import com.capa2LogicaNegocio.GestionHistoricoPesoService;
import com.capa2LogicaNegocio.GestionTerneraService;
import com.capa2LogicaNegocioEntities.HistoricoPeso;
import com.capa2LogicaNegocioEntities.Ternera;
import com.capa2LogicaNegocioEntities.Usuario;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

@Named(value="altaHistoricoPeso")
@SessionScoped
public class GestionHistoricoPesoBean implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	@EJB
	GestionTerneraService gestionTerneraService;
	
	@EJB
	GestionHistoricoPesoService gestionHistoricoService;
	
	private HistoricoPeso historico;
	
	private String usuario;
	private List<SelectItem> terneras;
	
	private boolean error = false;
	
	public GestionHistoricoPesoBean()
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
			historico = new HistoricoPeso();
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
		if(historico.getPeso()<=0 || historico.getPeso()>1500)
		{
			FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "'Peso registrado' debe ser Numérico decimal mayor a 0 y menor o igual a 1500.", "");
			FacesContext.getCurrentInstance().addMessage(null, facesMsg);
			error = true;
		}
		
		if(error==false)
		{
			try
			{
				historico = gestionHistoricoService.crearHistoricoPeso(historico);
				
				FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Se ha agregado un nuevo registro de Peso para la Ternera: " + historico.getTernera().getCaravanatambo(), "");
				FacesContext.getCurrentInstance().addMessage(null, facesMsg);
				
			} catch (Exception e)
			{	
				e.printStackTrace();
			}
		}
		return "";
	}

	public HistoricoPeso getHistorico() {
		return historico;
	}

	public void setHistorico(HistoricoPeso historico) {
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