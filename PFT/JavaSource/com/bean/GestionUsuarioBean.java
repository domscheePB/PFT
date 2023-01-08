package com.bean;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Named;

import com.capa2LogicaNegocio.GestionAdministradorService;
import com.capa2LogicaNegocio.GestionEncargadoService;
import com.capa2LogicaNegocio.GestionPersonalService;
import com.capa2LogicaNegocio.GestionTituloProfesionalService;
import com.capa2LogicaNegocio.GestionUsuarioService;
import com.capa2LogicaNegocioEntities.Administrador;
import com.capa2LogicaNegocioEntities.Encargado;
import com.capa2LogicaNegocioEntities.Personal;
import com.capa2LogicaNegocioEntities.Usuario;
//import com.capa3Persistencia.exception.PersistenciaException;
import com.controlFormato.ControlFormato;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

@Named(value="gestionUsuario")
@SessionScoped
public class GestionUsuarioBean implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	@EJB
	GestionUsuarioService gestionUsuarioService;
	
	@EJB
	GestionAdministradorService gestionAdministradorService;
	
	@EJB
	GestionEncargadoService gestionEncargadoService;
	
	@EJB
	GestionPersonalService gestionPersonalService;
	
	@EJB
	GestionTituloProfesionalService gestionTitulosService;

	private Long id;
	private String modalidad;
	private boolean error = false;
	
	private Usuario usuarioSeleccionado;
	private String rol;
	private String titulo;
	private float cantHoras;
	private boolean modoEdicion=false;
	private String claveSeleccionada;
	private boolean encargado = false;
	private boolean personal = false;
	private List<SelectItem> titulos;
	private int documentoBase;
	
	public GestionUsuarioBean()
	{
		super();
	}
	
	@PostConstruct
	public void init()
	{
		
	}
	
	public void preRenderViewListener()
	{
		cargarListaTitulos();
		if (id!=null) {
			usuarioSeleccionado=gestionUsuarioService.buscarUsuario(id);
			documentoBase = usuarioSeleccionado.getDocumento();
		} else {
			String nombre = "";
			String apellido = "";
			int documento = 0;
			documentoBase = 0;
			if(error == true)
			{
				nombre = usuarioSeleccionado.getNombre();
				apellido = usuarioSeleccionado.getApellido();
				documento = usuarioSeleccionado.getDocumento();
				documentoBase = usuarioSeleccionado.getDocumento();
			}
			usuarioSeleccionado=new Usuario();
			if(error == true)
			{
				usuarioSeleccionado.setNombre(nombre);
				usuarioSeleccionado.setApellido(apellido);
				usuarioSeleccionado.setDocumento(documento);
			}
		}
		if (modalidad.contentEquals("view")) {
			claveSeleccionada = gestionUsuarioService.desencriptado(usuarioSeleccionado.getContraseña());
			rol = gestionUsuarioService.obtenerRolUsuario(usuarioSeleccionado);
			if(rol.equals("Encargado"))
			{
				titulo = gestionEncargadoService.obtenerEncargado(usuarioSeleccionado).getTitulo().getTitulo();
			}
			else if(rol.equals("Personal"))
			{
				cantHoras = gestionPersonalService.obtenerPersonal(usuarioSeleccionado).getCantidadDeHoras();
			}
			modoEdicion=false;
		}else if (modalidad.contentEquals("update")) {
			modoEdicion=true;
		}else if (modalidad.contentEquals("insert")) {
			modoEdicion=true;
		}else {
			modoEdicion=false;
			cantHoras = 0;
			rol = "Administrador";
			modalidad="view";
		}
		viewDatosExtras();
	}
	
	public void cargarListaTitulos()
	{
		List<String> listaTitulos = gestionTitulosService.obtenerListaTitulos();
		
		if(titulos != null)
		{
			titulos.clear();
		}
		else
		{
			titulos = new ArrayList<SelectItem>();
		}
		
		SelectItem aux = new SelectItem();
		
		for(int i = 0; i<listaTitulos.size(); i++)
		{
			aux = new SelectItem(listaTitulos.get(i));
			titulos.add(aux);
		}
	}
	
	public String cambiarModalidadUpdate() throws CloneNotSupportedException
	{
		return "DatosEmpleado?faces-redirect=true&includeViewParams=true";
	}
	
	public String salvarCambios()
	{	
		error = false;
		if(usuarioSeleccionado.getNombre().length()<3 || usuarioSeleccionado.getNombre().length()>30 || !ControlFormato.Alfabetico(usuarioSeleccionado.getNombre()))
		{
			FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "'Nombre' debe ser Alfabético entre 3 y 30 caracteres.", "");
			FacesContext.getCurrentInstance().addMessage(null, facesMsg);
			error = true;
		}
		if(usuarioSeleccionado.getApellido().length()<3 || usuarioSeleccionado.getApellido().length()>30 || !ControlFormato.Alfabetico(usuarioSeleccionado.getApellido()))
		{
			FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "'Apellido' debe ser Alfabético entre 3 y 30 caracteres.", "");
			FacesContext.getCurrentInstance().addMessage(null, facesMsg);
			error = true;
		}
		if(!ControlFormato.esCIValida(String.valueOf(usuarioSeleccionado.getDocumento())))
		{
			FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "El 'Documento' ingresado no cumple con las normas nacionales uruguayas, por favor verifique el valor ingresado.", "");
			FacesContext.getCurrentInstance().addMessage(null, facesMsg);
			error = true;
		}
		else if(usuarioSeleccionado.getDocumento() != documentoBase && gestionUsuarioService.documentoRepetido(usuarioSeleccionado.getDocumento()))
		{
			FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "El 'Documento' ingresado ya se encuentra registrado en el sistema.", "");
			FacesContext.getCurrentInstance().addMessage(null, facesMsg);
			error = true;
		}
		if(rol.equals("Personal") && (!(Float.valueOf(cantHoras) > 0) || !(Float.valueOf(cantHoras) <= 48)))
		{
			FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "'Cantidad de Horas' debe ser Numérico Decimal mayor a 0", "");
			FacesContext.getCurrentInstance().addMessage(null, facesMsg);
			error = true;
		}
		if(error == false)
		{
			if (usuarioSeleccionado.getId()==null)
			{
				Usuario usuarioNuevo = new Usuario();
				try
				{
					if(rol.equals("Administrador"))
					{
						Administrador admin = new Administrador();
						try
						{
							admin = gestionAdministradorService.agregarAdministrador(usuarioSeleccionado);
							usuarioNuevo = admin.getUsuario();
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
					else if(rol.equals("Encargado"))
					{
						Encargado encargado = new Encargado();
						try
						{
							encargado = gestionEncargadoService.agregarEncargado(usuarioSeleccionado, titulo);
							usuarioNuevo = encargado.getUsuario();
						} catch (Exception e)
						{
							e.printStackTrace();
						}
					}
					else if(rol.equals("Personal"))
					{
						Personal personal = new Personal();
						try
						{
							personal = gestionPersonalService.agregarPersonal(usuarioSeleccionado, cantHoras);
							usuarioNuevo = personal.getUsuario();
						} catch (Exception e)
						{
							e.printStackTrace();
						}
					}
					
					this.id=usuarioNuevo.getId();
					
					FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Se ha agregado un nuevo Usuario", "");
					FacesContext.getCurrentInstance().addMessage(null, facesMsg);
					
					this.modalidad="view";
				} catch (Exception e)
				{					
					this.modalidad="update";
					
					e.printStackTrace();
				}
			} else if (modalidad.equals("update"))
			{
				String rolAUX = "";
				try
				{
					rolAUX = gestionUsuarioService.obtenerRolUsuario(usuarioSeleccionado);
				} catch (Exception e1)
				{
					e1.printStackTrace();
				}
				try
				{
					if(rol.equals(rolAUX))
					{
						if(rol.equals("Encargado"))
						{
							gestionEncargadoService.actualizarEncargado(usuarioSeleccionado, titulo);
						}
						else if(rol.equals("Personal"))
						{
							gestionPersonalService.actualizarPersonal(usuarioSeleccionado, cantHoras);
						}	
					}
					else
					{
						if(rolAUX.equals("Administrador"))
						{
							try
							{
								gestionAdministradorService.eliminarSoloAdministrador(usuarioSeleccionado);
								
								if(rol.equals("Encargado"))
								{
									gestionEncargadoService.cambiarRolEncargado(usuarioSeleccionado, titulo);
								}
								else if(rol.equals("Personal"))
								{
									gestionPersonalService.cambiarRolPersonal(usuarioSeleccionado, cantHoras);
								}
							} catch (Exception e)
							{
								e.printStackTrace();
							}	
						}
						else if(rolAUX.equals("Encargado"))
						{
							gestionEncargadoService.eliminarSoloEncargado(usuarioSeleccionado);
							
							if(rol.equals("Administrador"))
							{
								gestionAdministradorService.cambiarRolAdministrador(usuarioSeleccionado);
							}
							else if(rol.equals("Personal"))
							{
								gestionPersonalService.cambiarRolPersonal(usuarioSeleccionado, cantHoras);
							}
						}
						else if(rolAUX.equals("Personal"))
						{
							gestionPersonalService.eliminarSoloPersonal(usuarioSeleccionado);
							
							if(rol.equals("Administrador"))
							{
								gestionAdministradorService.cambiarRolAdministrador(usuarioSeleccionado);
							}
							else if(rol.equals("Encargado"))
							{
								gestionEncargadoService.cambiarRolEncargado(usuarioSeleccionado, titulo);
							}
						}
					}
					
					gestionUsuarioService.actualizarUsuario(usuarioSeleccionado);
					
					FacesContext.getCurrentInstance().addMessage(null, 
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Se ha modificado Usuario.",""));
					
					this.modalidad="view";
					
				} catch (Exception e)
				{
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

	public void viewDatosExtras()
	{
		if(rol != null)
		{
			if(rol.equals("Encargado"))
			{
				encargado = true;
				personal = false;
			}
			else if(rol.equals("Personal"))
			{
				encargado = false;
				personal = true;
			}
			else
			{
				encargado = false;
				personal = false;
			}
		}
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
	public boolean getModoEdicion() {
		return modoEdicion;
	}
	public boolean isModoEdicion() {
		return modoEdicion;
	}
	public void setModoEdicion(boolean modoEdicion) {
		this.modoEdicion = modoEdicion;
	}
	public Usuario getUsuarioSeleccionado() {
		return usuarioSeleccionado;
	}
	public void setUsuarioSeleccionado(Usuario usuarioSeleccionado) {
		this.usuarioSeleccionado = usuarioSeleccionado;
	}
	public String getClaveSeleccionada() {
		return claveSeleccionada;
	}
	public void setClaveSeleccionada(String claveSeleccionada) {
		this.claveSeleccionada = claveSeleccionada;
	}
	public String getRol() {
		return rol;
	}
	public void setRol(String rol) {
		this.rol = rol;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public float getCantHoras() {
		return cantHoras;
	}

	public void setCantHoras(float cantHoras) {
		this.cantHoras = cantHoras;
	}

	public boolean isEncargado() {
		return encargado;
	}

	public void setEncargado(boolean encargado) {
		this.encargado = encargado;
	}

	public boolean isPersonal() {
		return personal;
	}

	public void setPersonal(boolean personal) {
		this.personal = personal;
	}

	public List<SelectItem> getTitulos() {
		return titulos;
	}

	public void setTitulos(List<SelectItem> titulos) {
		this.titulos = titulos;
	}
}