package com.capa2LogicaNegocio;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.capa2LogicaNegocioEntities.Encargado;
import com.capa2LogicaNegocioEntities.Usuario;
import com.capa3Persistencia.dao.EncargadoPersistenciaDAO;
import com.capa3Persistencia.entities.EncargadoPersistencia;

@Stateless
@LocalBean
public class GestionEncargadoService implements Serializable
{	
	private static final long serialVersionUID = 1L;
	
	@EJB
	EncargadoPersistenciaDAO encargadoPersistenciaDAO;
	
	@EJB
	GestionUsuarioService gestionUsuarioService;
	
	@EJB
	GestionTituloProfesionalService gestionTituloService;

	public Encargado fromEncargadoPersistencia(EncargadoPersistencia e)
	{
		Encargado encargado = new Encargado();
		encargado.setId(e.getIdEncargado().longValue());
		encargado.setTitulo(gestionTituloService.fromTituloProfesionalPersistencia(e.getTitulosProfesionale()));
		encargado.setUsuario(gestionUsuarioService.fromUsuarioPersistencia(e.getUsuario()));
		return encargado;
	}
	
	public EncargadoPersistencia toEncargadoPersistencia(Encargado e)
	{
		EncargadoPersistencia encargadoPersistencia = new EncargadoPersistencia();
		encargadoPersistencia.setIdEncargado(e.getId()!=null?e.getId().longValue():null);
		encargadoPersistencia.setUsuario(gestionUsuarioService.toUsuarioPersistencia(e.getUsuario()));
		encargadoPersistencia.setTitulosProfesionale(gestionTituloService.toTitulosProfesionalPersistencia(e.getTitulo()));
		
		return encargadoPersistencia;
	}
	
	public Encargado agregarEncargado(Usuario usuarioSeleccionado, String titulo)
	{
		EncargadoPersistencia e = new EncargadoPersistencia();
		try
		{
			e = encargadoPersistenciaDAO.Crear(gestionUsuarioService.toUsuarioPersistencia(usuarioSeleccionado), titulo);
		} catch (Exception e1)
		{	
			e1.printStackTrace();
		}
		return fromEncargadoPersistencia(e);
	}
	
	public Encargado actualizarEncargado(Usuario usuarioSeleUsuario, String titulo)
	{
		EncargadoPersistencia e = new EncargadoPersistencia();
		try
		{
			e = encargadoPersistenciaDAO.actualizarTitulo(gestionUsuarioService.toUsuarioPersistencia(usuarioSeleUsuario), titulo);
		} catch (Exception e2)
		{
			e2.printStackTrace();;
		}
		return fromEncargadoPersistencia(e);
	}
	
	public Encargado cambiarRolEncargado(Usuario usuarioSeleccionado, String titulo)
	{
		EncargadoPersistencia encargado = new EncargadoPersistencia();
		try
		{
			encargado = encargadoPersistenciaDAO.CrearSolo(gestionUsuarioService.toUsuarioPersistencia(usuarioSeleccionado), titulo);
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		return fromEncargadoPersistencia(encargado);
	}
	
	public void eliminarSoloEncargado(Usuario usuarioSeleUsuario)
	{
		encargadoPersistenciaDAO.eliminarEncargadoSolo(gestionUsuarioService.toUsuarioPersistencia(usuarioSeleUsuario));
	}
	
	public Encargado obtenerEncargado(Usuario usuarioSeleccionado)
	{
		Encargado encargado = fromEncargadoPersistencia(encargadoPersistenciaDAO.obtenerEncargado(gestionUsuarioService.toUsuarioPersistencia(usuarioSeleccionado)));
		
		return encargado;
	}
}