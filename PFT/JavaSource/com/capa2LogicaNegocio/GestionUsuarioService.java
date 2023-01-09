package com.capa2LogicaNegocio;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.capa2LogicaNegocioEntities.Usuario;
import com.capa3Encriptado.Encriptado;
import com.capa3Persistencia.dao.UsuarioPersistenciaDAO;
import com.capa3Persistencia.entities.UsuarioPersistencia;

@Stateless
@LocalBean

public class GestionUsuarioService implements Serializable
{	
	private static final long serialVersionUID = 1L;
	
	@EJB
	UsuarioPersistenciaDAO usuariosPersistenciaDAO;

	public Usuario fromUsuarioPersistencia(UsuarioPersistencia u)
	{
		Usuario usuario=new Usuario();
		usuario.setId(u.getIdUsuario().longValue());
		usuario.setNombre(u.getNombre());
		usuario.setApellido(u.getApellido());
		usuario.setDocumento(u.getDocumento());
		usuario.setNombreUsuario(u.getNombreUsuario());
		usuario.setContraseña(u.getContraseña());
		return usuario;
	}
	
	public UsuarioPersistencia toUsuarioPersistencia(Usuario u)
	{
		UsuarioPersistencia usuarioPersistencia=new UsuarioPersistencia();
		usuarioPersistencia.setIdUsuario(u.getId()!=null?u.getId().longValue():null);
		usuarioPersistencia.setNombre(u.getNombre());
		usuarioPersistencia.setApellido(u.getApellido());
		usuarioPersistencia.setDocumento(u.getDocumento());
		usuarioPersistencia.setNombreUsuario(u.getNombreUsuario());
		usuarioPersistencia.setContraseña(u.getContraseña());
		return usuarioPersistencia;
	}
	
	public List<Usuario> seleccionarUsuarios()
	{
		List<UsuarioPersistencia> listaUsuarioPersistencia = usuariosPersistenciaDAO.obtenerTodos();
		
		List<Usuario> listaUsuarios=new ArrayList<Usuario>();
		for (UsuarioPersistencia usuarioPersistencia : listaUsuarioPersistencia)
		{
			listaUsuarios.add(fromUsuarioPersistencia(usuarioPersistencia));
		}
		return listaUsuarios;
	}

	public List<Usuario> seleccionarUsuarios(String criterioNombre,String criterioApellido,String criterioDocumento,String criterioRol)
	{
		List<UsuarioPersistencia> listaUsuariosPersistencia = usuariosPersistenciaDAO.seleccionarUsuarios(criterioNombre,criterioApellido,criterioDocumento,criterioRol);
		List<Usuario> listaUsuarios=new ArrayList<Usuario>();
		for (UsuarioPersistencia usuarioPersistencia : listaUsuariosPersistencia) {
			listaUsuarios.add(fromUsuarioPersistencia(usuarioPersistencia));
		}
		return listaUsuarios;
	}
	
	public boolean login(String usuario, String contraseña)
	{
		boolean loginExitoso = false;
		try
		{
			loginExitoso = usuariosPersistenciaDAO.loginUsuario(contraseña, usuario);
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		return loginExitoso;
	}

	public Usuario buscarUsuario(Long i)
	{
		UsuarioPersistencia u = usuariosPersistenciaDAO.buscarUsuario(i);
		return fromUsuarioPersistencia(u);
	}
	
	public Usuario agregarUsuario(Usuario usuarioSeleccionado)
	{
		UsuarioPersistencia u = usuariosPersistenciaDAO.agregarUsuario(toUsuarioPersistencia(usuarioSeleccionado));
		return fromUsuarioPersistencia(u);
	}

	public void actualizarUsuario(Usuario usuarioSeleccionado)
	{
		usuariosPersistenciaDAO.modificarUsuario(toUsuarioPersistencia(usuarioSeleccionado));
	}
	
	public void borrarUsuario(Usuario usuarioSeleccionado)
	{
		usuariosPersistenciaDAO.eliminarUsuario(toUsuarioPersistencia(usuarioSeleccionado));
	}
	
	public String obtenerRol(String nombreUsuario)
	{
		return usuariosPersistenciaDAO.obtenerRolNombreUsuario(nombreUsuario);
	}
	
	public String obtenerRolUsuario(Usuario usuario)
	{
		return usuariosPersistenciaDAO.obtenerRol(toUsuarioPersistencia(usuario));
	}
	
	public String desencriptado(String clave)
	{
		return Encriptado.deecnode(clave);
	}
	
	public boolean cambiarPassword(Usuario usuario, String contraNueva)
	{
		return usuariosPersistenciaDAO.actualizarPassword(toUsuarioPersistencia(usuario), contraNueva);
	}
	
	public boolean documentoRepetido(int documento)
	{
		return usuariosPersistenciaDAO.docRepetido(documento);
	}
}