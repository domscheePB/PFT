package com.bean;

import javax.ejb.EJB;
import java.io.Serializable;
import java.util.List;

import javax.inject.Named;

import com.capa2LogicaNegocio.GestionUsuarioService;
import com.capa2LogicaNegocioEntities.Usuario;
import com.controlFormato.ControlFormato;

import javax.enterprise.context.SessionScoped;	//JEE8
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

@Named(value="gestionUsuarios")		//JEE8
@SessionScoped				        //JEE8
public class ListadoUsuariosBean implements Serializable{
	
	@EJB
	GestionUsuarioService gestionUsuarioService;
	
	private static final long serialVersionUID = 1L;
	
	private String criterioNombre;
	private String criterioApellido;
	private String criterioDocumento;
	private String criterioRol;
	
	private List<Usuario> usuariosSeleccionados;
	private Usuario usuarioSeleccionado;
	
	public ListadoUsuariosBean()
	{
		super();
	}
	
	public void preRenderViewListener()
	{
		seleccionarUsuarios();
	}
	
	public String seleccionarUsuarios()
	{
		usuariosSeleccionados=gestionUsuarioService.seleccionarUsuarios(criterioNombre, criterioApellido, criterioDocumento, criterioRol);
		return "";
	}
	
	public String listar()
	{
		boolean error = false;
		if((criterioNombre!=null && !criterioNombre.contentEquals(""))  && (!ControlFormato.Alfabetico(criterioNombre) || criterioNombre.length()>30))
		{
			FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "'Nombre' debe ser Alfabético con máximo de 30 caracteres.", "");
			FacesContext.getCurrentInstance().addMessage(null, facesMsg);
			error = true;
		}
		if((criterioApellido!=null && !criterioApellido.contentEquals("")) && (!ControlFormato.Alfabetico(criterioApellido) || criterioApellido.length()>30))
		{
			FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "'Apellido' debe ser Alfabético con máximo de 30 caracteres.", "");
			FacesContext.getCurrentInstance().addMessage(null, facesMsg);
			error = true;
		}
		if((criterioDocumento!=null && !criterioDocumento.contentEquals("")) && (!ControlFormato.Numerico(criterioDocumento) || Integer.valueOf(criterioDocumento)>100000000 || Integer.valueOf(criterioDocumento)<=0))
		{
			FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "'Documento' debe ser Numérico entero con máximo de 8 cifras.", "");
			FacesContext.getCurrentInstance().addMessage(null, facesMsg);
			error = true;
		}
		
		if(error == false)
		{
			seleccionarUsuarios();
		}
		
		return "";
	}
	
	public String obtenerRol(Usuario usuario)
	{
		return gestionUsuarioService.obtenerRolUsuario(usuario);
	}
	
	public String eliminarUsuario(Usuario usuario)
	{
		try
		{
			gestionUsuarioService.borrarUsuario(usuario);
			seleccionarUsuarios();
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		return "Empleados";
	}
	
	public String getCriterioNombre() {
		return criterioNombre;
	}
	public void setCriterioNombre(String criterioNombre) {
		this.criterioNombre = criterioNombre;
	}

	public List<Usuario> getUsuariosSeleccionados() {
		return usuariosSeleccionados;
	}

	public void setUsuariosSeleccionados(List<Usuario> usuariosSeleccionados) {
		this.usuariosSeleccionados = usuariosSeleccionados;
	}

	public Usuario getUsuarioSeleccionado() {
		return usuarioSeleccionado;
	}

	public void setUsuarioSeleccionado(Usuario usuarioSeleccionado) {
		this.usuarioSeleccionado = usuarioSeleccionado;
	}

	public String getCriterioApellido() {
		return criterioApellido;
	}

	public void setCriterioApellido(String criterioApellido) {
		this.criterioApellido = criterioApellido;
	}

	public String getCriterioDocumento() {
		return criterioDocumento;
	}

	public void setCriterioDocumento(String criterioDocumento) {
		this.criterioDocumento = criterioDocumento;
	}

	public String getCriterioRol() {
		return criterioRol;
	}

	public void setCriterioRol(String criterioRol) {
		this.criterioRol = criterioRol;
	}
}
