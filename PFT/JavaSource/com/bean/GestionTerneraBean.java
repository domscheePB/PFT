package com.bean;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Named;

import com.capa2LogicaNegocio.GestionGuacheraService;
import com.capa2LogicaNegocio.GestionRazaService;
import com.capa2LogicaNegocio.GestionTerneraService;
import com.capa2LogicaNegocioEntities.Raza;
import com.capa2LogicaNegocioEntities.Ternera;
import com.controlFormato.ControlFormato;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

@Named(value="gestionTernera")
@SessionScoped
public class GestionTerneraBean implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	@EJB
	GestionTerneraService gestionTerneraService;
	
	@EJB
	GestionGuacheraService gestionGuacheraService;
	
	@EJB
	GestionRazaService gestionRazaService;

	private Long id;
	private String modalidad;
	private boolean modoEdicion=false;
	private boolean error = false;
	
	private Ternera terneraSeleccionada;
	private String identificadorGuacheraActual;
	private List<SelectItem> guacheras;
	private List<SelectItem> razas;
	String caravanaTamboBase;
	
	public GestionTerneraBean()
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
		if (id!=null) {
			terneraSeleccionada=gestionTerneraService.obtenerTerneraID(id);
			caravanaTamboBase = terneraSeleccionada.getCaravanatambo();
		} else {
			Ternera terneraAUX = new Ternera();
			caravanaTamboBase = "";
			if(error == true)
			{
				terneraAUX = terneraSeleccionada;
				caravanaTamboBase = terneraSeleccionada.getCaravanatambo();
			}			
			terneraSeleccionada = new Ternera();
			terneraSeleccionada.setRaza(new Raza());
			if(error==true)
			{
				terneraSeleccionada = terneraAUX;
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
	
	public void cargarListas()
	{
		//Carga de Guacheras
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
	}
	
	public String salvarCambios()
	{
		error = false;
		//Caravana tambo
		if(terneraSeleccionada.getCaravanatambo() == null || terneraSeleccionada.getCaravanatambo().isEmpty() || terneraSeleccionada.getCaravanatambo().length()<3 || terneraSeleccionada.getCaravanatambo().length()>30 || !ControlFormato.AlfaNumerico(terneraSeleccionada.getCaravanatambo()))
		{
			FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "'Caravana Tambo' debe ser Alfanumérica entre 3 y 30 caracteres.", "");
			FacesContext.getCurrentInstance().addMessage(null, facesMsg);
			error = true;
		}
		else if(!terneraSeleccionada.getCaravanatambo().contentEquals(caravanaTamboBase) && gestionTerneraService.caravanaTamboRepetida(terneraSeleccionada.getCaravanatambo()))
		{
			FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "La 'Caravana Tambo' ingresada ya se encuentra registrada.", "");
			FacesContext.getCurrentInstance().addMessage(null, facesMsg);
			error = true;
		}
		//Caravana Snis
		if((terneraSeleccionada.getCaravanasnis() != null && !terneraSeleccionada.getCaravanasnis().contentEquals("")) && (terneraSeleccionada.getCaravanasnis().length()<3 || terneraSeleccionada.getCaravanasnis().length()>30 || !ControlFormato.AlfaNumerico(terneraSeleccionada.getCaravanasnis())))
		{
			FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "'Caravana Snis' debe ser Alfanumérica entre 3 y 30 caracteres", "");
			FacesContext.getCurrentInstance().addMessage(null, facesMsg);
			error = true;
		}
		//ID Madre
		if(terneraSeleccionada.getIdMadre() <= 0)
		{
			FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "'Identificador-Madre' debe ser Numérico entero mayor a 0", "");
			FacesContext.getCurrentInstance().addMessage(null, facesMsg);
			error = true;
		}
		//ID Padre
		if(terneraSeleccionada.getIdPadre() <= 0)
		{
			FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "'Identificador-Padre' debe ser Numérico entero mayor a 0", "");
			FacesContext.getCurrentInstance().addMessage(null, facesMsg);
			error = true;
		}
		//Peso al nacer
		if(terneraSeleccionada.getPesoAlNacer() != 0 && (terneraSeleccionada.getPesoAlNacer() < 15 || terneraSeleccionada.getPesoAlNacer() > 60))
		{
			FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "'Peso al nacer' debe ser Numérico entero entre 15 y 60 o 0 en caso de no registrar peso de nacimiento.", "");
			FacesContext.getCurrentInstance().addMessage(null, facesMsg);
			error = true;
		}
		
		if(error == false)
		{
			if(terneraSeleccionada.getId()==null)
			{
				try {
					Ternera terneraNueva = new Ternera();
					
					terneraNueva = gestionTerneraService.crearTernera(terneraSeleccionada, terneraSeleccionada.getRaza().getNombre(), identificadorGuacheraActual);
					
					this.id=terneraNueva.getId();
					
					FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Se ha agregado una nueva Ternera", "");
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
					terneraSeleccionada = gestionTerneraService.actualizarTernera(terneraSeleccionada, terneraSeleccionada.getRaza().getNombre(), identificadorGuacheraActual);
					
					FacesContext.getCurrentInstance().addMessage(null, 
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Se ha modificado una Ternera.",""));
							
					this.modalidad="view";
				} catch (Exception e)
				{
					e.printStackTrace();
					
					this.modalidad="update";
				}
			}
		}
		else
		{
			this.modalidad="update";
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

	public Ternera getTerneraSeleccionada() {
		return terneraSeleccionada;
	}

	public void setTerneraSeleccionada(Ternera terneraSeleccionada) {
		this.terneraSeleccionada = terneraSeleccionada;
	}

	public String getIdentificadorGuacheraActual() {
		return identificadorGuacheraActual;
	}

	public void setIdentificadorGuacheraActual(String identificadorGuacheraActual) {
		this.identificadorGuacheraActual = identificadorGuacheraActual;
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
}