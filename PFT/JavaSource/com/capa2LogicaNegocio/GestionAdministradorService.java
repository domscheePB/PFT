package com.capa2LogicaNegocio;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.capa2LogicaNegocioEntities.Administrador;
import com.capa2LogicaNegocioEntities.Usuario;
import com.capa3Persistencia.dao.AdministradorPersistenciaDAO;
import com.capa3Persistencia.entities.AdministradorPersistencia;

@Stateless
@LocalBean
public class GestionAdministradorService implements Serializable
{	
	private static final long serialVersionUID = 1L;
	
	@EJB
	AdministradorPersistenciaDAO administradorPersistenciaDAO;
	
	@EJB
	GestionUsuarioService gestionUsuarioService;

	public Administrador fromAdministradorPersistencia(AdministradorPersistencia a)
	{
		Administrador admin = new Administrador();
		admin.setId(a.getIdAdministrador().longValue());
		admin.setUsuario(gestionUsuarioService.fromUsuarioPersistencia(a.getUsuario()));
		return admin;
	}
	
	public AdministradorPersistencia toAdministradorPersistencia(Administrador a)
	{
		AdministradorPersistencia adminPersistencia = new AdministradorPersistencia();
		adminPersistencia.setIdAdministrador(a.getId()!=null?a.getId().longValue():null);
		adminPersistencia.setUsuario(gestionUsuarioService.toUsuarioPersistencia(a.getUsuario()));
		
		return adminPersistencia;
	}
	
	public Administrador agregarAdministrador(Usuario usuarioSeleccionado)
	{
		AdministradorPersistencia a = new AdministradorPersistencia();
		try
		{
			a = administradorPersistenciaDAO.Crear(gestionUsuarioService.toUsuarioPersistencia(usuarioSeleccionado));
		} catch (Exception e)
		{	
			e.printStackTrace();
		}
		return fromAdministradorPersistencia(a);
	}
	
	public Administrador cambiarRolAdministrador(Usuario usuarioSeleccionado)
	{
		AdministradorPersistencia admin = new AdministradorPersistencia();
		try
		{
			admin = administradorPersistenciaDAO.CrearSolo(gestionUsuarioService.toUsuarioPersistencia(usuarioSeleccionado));
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		return fromAdministradorPersistencia(admin);
	}
	
	public void eliminarSoloAdministrador(Usuario usuarioSeleUsuario)
	{
		try
		{
			administradorPersistenciaDAO.eliminarAdminSolo(gestionUsuarioService.toUsuarioPersistencia(usuarioSeleUsuario));
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}