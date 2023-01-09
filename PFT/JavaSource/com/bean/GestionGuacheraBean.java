package com.bean;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Named;

import com.capa2LogicaNegocio.GestionGuacheraService;
import com.capa2LogicaNegocio.GestionTipoGuacheraService;
import com.capa2LogicaNegocioEntities.Guachera;
import com.capa2LogicaNegocioEntities.TipoGuachera;
import com.capa2LogicaNegocioEntities.Ubicacion;
import com.controlFormato.ControlFormato;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

@Named(value="gestionGuachera")
@SessionScoped
public class GestionGuacheraBean implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	@EJB
	GestionGuacheraService gestionGuacheraService;
	
	@EJB
	GestionTipoGuacheraService gestionTipoGuacheraService;

	private Long id;
	private String modalidad;
	private boolean modoEdicion=false;
	private boolean error = false;
	
	private Guachera guacheraSeleccionada;
	private List<SelectItem> tipos;
	String identificadorBase;
	
	public GestionGuacheraBean()
	{
		super();
	}
	
	@PostConstruct
	public void init()
	{
		
	}
	
	public void preRenderViewListener()
	{
		cargarListaTipos();
		if (id!=null) {
			guacheraSeleccionada=gestionGuacheraService.obtenerGuachera(id);
			identificadorBase = guacheraSeleccionada.getIdentificadorUnico();
		} else {
			Guachera guacheraAUX = new Guachera();
			identificadorBase = "";
			if(error == true)
			{
				guacheraAUX = guacheraSeleccionada;
				identificadorBase = guacheraSeleccionada.getIdentificadorUnico();
			}
			guacheraSeleccionada= new Guachera();
			guacheraSeleccionada.setUbicacion(new Ubicacion());
			guacheraSeleccionada.setTipoGuachera(new TipoGuachera());
			if(error==true)
			{
				guacheraSeleccionada = guacheraAUX;
			}
		}
		if (modalidad.contentEquals("view")) {
			modoEdicion=false;
		}else if (modalidad.contentEquals("update")) {
			modoEdicion=true;
		}else if (modalidad.contentEquals("insert")) {
			modoEdicion=true;
		}else {
			modoEdicion=false;
			modalidad="view";
		}
	}
	
	public void cargarListaTipos()
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
	}
	
	public String salvarCambios()
	{
		error = false;
		if(guacheraSeleccionada.getIdentificadorUnico() == null || guacheraSeleccionada.getIdentificadorUnico().isEmpty() || guacheraSeleccionada.getIdentificadorUnico().length()<3 || guacheraSeleccionada.getIdentificadorUnico().length()>30 || !ControlFormato.AlfaNumerico(guacheraSeleccionada.getIdentificadorUnico()))
		{
			FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "'Identificador' debe ser Alfanumérico entre 3 y 30 caracteres.", "");
			FacesContext.getCurrentInstance().addMessage(null, facesMsg);
			error = true;
		}
		else if(!guacheraSeleccionada.getIdentificadorUnico().contentEquals(identificadorBase) && gestionGuacheraService.identificadorRepetido(guacheraSeleccionada.getIdentificadorUnico()))
		{
			FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "El 'Identificador' ingresado ya se encuentra registrado.", "");
			FacesContext.getCurrentInstance().addMessage(null, facesMsg);
			error = true;
		}
		if(guacheraSeleccionada.getCapacidad()<=0 || guacheraSeleccionada.getCapacidad()>30)
		{
			FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "'Capacidad' debe ser Numérico entero mayor a 0 y menor a 30.", "");
			FacesContext.getCurrentInstance().addMessage(null, facesMsg);
			error = true;
		}
		if(guacheraSeleccionada.getLargo()<=0 || guacheraSeleccionada.getLargo()>20)
		{
			FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "'Largo' debe ser Numérico decimal mayor a 0 y menor a 20.", "");
			FacesContext.getCurrentInstance().addMessage(null, facesMsg);
			error = true;
		}
		if(guacheraSeleccionada.getAncho()<=0 || guacheraSeleccionada.getAncho()>20)
		{
			FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "'Ancho' debe ser Numérico decimal mayor a 0 y menor a 20.", "");
			FacesContext.getCurrentInstance().addMessage(null, facesMsg);
			error = true;
		}
		if(guacheraSeleccionada.getUbicacion().getLatitud()<-90 || guacheraSeleccionada.getUbicacion().getLatitud()>90)
		{
			FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "'Latitud' debe ser Numérico decimal mayor a -90 y menor a 90.", "");
			FacesContext.getCurrentInstance().addMessage(null, facesMsg);
			error = true;
		}
		if(guacheraSeleccionada.getUbicacion().getLongitud()<-180 || guacheraSeleccionada.getUbicacion().getLatitud()>180)
		{
			FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "'Longitud' debe ser Numérico decimal mayor a -180 y menor a 180.", "");
			FacesContext.getCurrentInstance().addMessage(null, facesMsg);
			error = true;
		}
		if(error == false)
		{
			if(guacheraSeleccionada.getId()==null)
			{
				try {
					Guachera guacheraNueva = new Guachera();
					
					guacheraNueva = gestionGuacheraService.crearGuachera(guacheraSeleccionada);
					
					this.id=guacheraNueva.getId();
					
					FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Se ha agregado una nueva Guachera", "");
					FacesContext.getCurrentInstance().addMessage(null, facesMsg);
					
					this.modalidad="view";
				} catch (Exception e)
				{
					this.modalidad="update";
					
					e.printStackTrace();
				}
			}
			else if (modalidad.equals("update"))
			{	
				try
				{
					guacheraSeleccionada = gestionGuacheraService.actualizarGuachera(guacheraSeleccionada);
					
					FacesContext.getCurrentInstance().addMessage(null, 
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Se ha modificado una Guachera.",""));
							
					this.modalidad="view";
				} catch (Exception e)
				{
					this.modalidad="update";
				}
			}
			else
			{
				this.modalidad="update";
			}
		}
		
		
		return "";
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getModalidad() {
		return modalidad;
	}

	public void setModalidad(String modalidad) {
		this.modalidad = modalidad;
	}

	public boolean isModoEdicion() {
		return modoEdicion;
	}

	public void setModoEdicion(boolean modoEdicion) {
		this.modoEdicion = modoEdicion;
	}

	public Guachera getGuacheraSeleccionada() {
		return guacheraSeleccionada;
	}

	public void setGuacheraSeleccionada(Guachera guacheraSeleccionada) {
		this.guacheraSeleccionada = guacheraSeleccionada;
	}

	public List<SelectItem> getTipos() {
		return tipos;
	}

	public void setTipos(List<SelectItem> tipos) {
		this.tipos = tipos;
	}
}