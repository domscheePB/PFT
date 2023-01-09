package com.capa2LogicaNegocio;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.capa2LogicaNegocioEntities.Personal;
import com.capa2LogicaNegocioEntities.Usuario;
import com.capa3Persistencia.dao.PersonalPersistenciaDAO;
import com.capa3Persistencia.entities.PersonalPersistencia;

@Stateless
@LocalBean
public class GestionPersonalService implements Serializable
{	
	private static final long serialVersionUID = 1L;
	
	@EJB
	PersonalPersistenciaDAO personalPersistenciaDAO;
	
	@EJB
	GestionUsuarioService gestionUsuarioService;

	public Personal fromPersonalPersistencia(PersonalPersistencia p)
	{
		Personal personal = new Personal();
		personal.setId(p.getIdPersonal().longValue());
		personal.setUsuario(gestionUsuarioService.fromUsuarioPersistencia(p.getUsuario()));
		personal.setCantidadDeHoras(p.getCantidadDeHoras());
		
		return personal;
	}
	
	public PersonalPersistencia toPersonalPersistencia(Personal p)
	{
		PersonalPersistencia personalPersistencia = new PersonalPersistencia();
		personalPersistencia.setIdPersonal(p.getId()!=null?p.getId().longValue():null);
		personalPersistencia.setUsuario(gestionUsuarioService.toUsuarioPersistencia(p.getUsuario()));
		personalPersistencia.setCantidadDeHoras(p.getCantidadDeHoras());
		
		return personalPersistencia;
	}
	
	public Personal agregarPersonal(Usuario usuarioSeleccionado, float cantHoras)
	{
		PersonalPersistencia p = new PersonalPersistencia();
		try
		{
			p = personalPersistenciaDAO.Crear(gestionUsuarioService.toUsuarioPersistencia(usuarioSeleccionado), cantHoras);
		} catch (Exception e)
		{	
			e.printStackTrace();
		}
		return fromPersonalPersistencia(p);
	}
	
	public Personal actualizarPersonal(Usuario usuarioSeleUsuario, float cantHoras)
	{
		PersonalPersistencia p = new PersonalPersistencia();
		try
		{
			p = personalPersistenciaDAO.actualizarHoras(gestionUsuarioService.toUsuarioPersistencia(usuarioSeleUsuario), cantHoras);
		} catch (Exception e2)
		{
			e2.printStackTrace();;
		}
		return fromPersonalPersistencia(p);
	}
	
	public Personal cambiarRolPersonal(Usuario usuarioSeleccionado, float cantHoras)
	{
		PersonalPersistencia personal = new PersonalPersistencia();
		try
		{
			personal = personalPersistenciaDAO.CrearSolo(gestionUsuarioService.toUsuarioPersistencia(usuarioSeleccionado), cantHoras);
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		return fromPersonalPersistencia(personal);
	}
	
	public void eliminarSoloPersonal(Usuario usuarioSeleUsuario)
	{
		try
		{
			personalPersistenciaDAO.eliminarPersonalSolo(gestionUsuarioService.toUsuarioPersistencia(usuarioSeleUsuario));
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public Personal obtenerPersonal(Usuario usuarioSeleccionado)
	{
		Personal personal = fromPersonalPersistencia(personalPersistenciaDAO.obtenerPersonal(gestionUsuarioService.toUsuarioPersistencia(usuarioSeleccionado)));
		
		return personal;
	}
}