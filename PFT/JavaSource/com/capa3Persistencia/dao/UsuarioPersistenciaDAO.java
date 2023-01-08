package com.capa3Persistencia.dao;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import com.capa3Encriptado.Encriptado;
import com.capa3Persistencia.entities.AdministradorPersistencia;
import com.capa3Persistencia.entities.EncargadoPersistencia;
import com.capa3Persistencia.entities.PersonalPersistencia;
import com.capa3Persistencia.entities.UsuarioPersistencia;

/**
 * Session Bean implementation class UsuariosEJBBean
 */
@Stateless
@LocalBean
public class UsuarioPersistenciaDAO
{
	@PersistenceContext
	private EntityManager em;
	
	@EJB
	AdministradorPersistenciaDAO administradorPersistenciaDAO;
	
	@EJB
	EncargadoPersistenciaDAO encargadoPersistenciaDAO;
	
	@EJB
	PersonalPersistenciaDAO personalPersistenciaDAO;
	
	public UsuarioPersistenciaDAO()
	{
		super();
	}
	
	public UsuarioPersistencia agregarUsuario(UsuarioPersistencia usuario)
	{

		try
		{
			String contraPrimerInicio = Encriptado.contraPrimerInicio();
			usuario.setContraseña(Encriptado.ecnode(contraPrimerInicio));
			usuario.setNombreUsuario(nombreUsuario(usuario));
			UsuarioPersistencia usuarioPersistencia = em.merge(usuario);
			em.flush();
			return usuarioPersistencia;
		}
		catch (PersistenceException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public UsuarioPersistencia obtenerNombreUsuario(String nombreUser)
	{
		TypedQuery<UsuarioPersistencia> query = em.createQuery("SELECT u FROM UsuarioPersistencia u WHERE u.nombreUsuario LIKE :nombreUsuario",UsuarioPersistencia.class)
				.setParameter("nombreUsuario", nombreUser);
		
		UsuarioPersistencia usuario = new UsuarioPersistencia();
		
		if(query.getResultList().size() != 0)
		{
			usuario = query.getResultList().get(0);
		}
		else
		{
			usuario = null;
		}
		
		return usuario;
	}
	
	public String obtenerRolNombreUsuario(String nombreUser)
	{
		UsuarioPersistencia usuario = obtenerNombreUsuario(nombreUser);
		String rol = obtenerRol(usuario);
		return rol;
	}
	
	public List<UsuarioPersistencia> obtenerTodos()
	{		
		TypedQuery<UsuarioPersistencia> query = em.createQuery("SELECT u FROM UsuarioPersistencia u",UsuarioPersistencia.class);
		List<UsuarioPersistencia> Usuarios = query.getResultList();
		
		return Usuarios;
	}
	
	public List<UsuarioPersistencia> obtenerTodosConRol()
	{		
		TypedQuery<UsuarioPersistencia> query = em.createQuery("SELECT u FROM UsuarioPersistencia u inner join ",UsuarioPersistencia.class);
		List<UsuarioPersistencia> Usuarios = query.getResultList();
		
		return Usuarios;
	}
	
	
	public void eliminarUsuario(UsuarioPersistencia usuario)
	{
		long idUsuario = usuario.getIdUsuario();
		
		UsuarioPersistencia user = em.find(UsuarioPersistencia.class, idUsuario);
		
		String rol = obtenerRol(user);
		
		if(rol.equals("Administrador"))
		{
			administradorPersistenciaDAO.eliminarAdminSolo(usuario);
		}
		else if(rol.equals("Encargado"))
		{
			encargadoPersistenciaDAO.eliminarEncargadoSolo(usuario);
		}
		else if(rol.equals("Personal"))
		{
			personalPersistenciaDAO.eliminarPersonalSolo(usuario);
		}
		
		em.remove(user);
		em.flush();
	}
	
	public UsuarioPersistencia modificarUsuario(UsuarioPersistencia usuario)
	{
		try
		{
			UsuarioPersistencia usuarioPersistencia = em.merge(usuario);
			em.flush();
			return usuarioPersistencia;
		} catch (PersistenceException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public String obtenerRol(UsuarioPersistencia usuario)
	{
		TypedQuery<AdministradorPersistencia> queryAdmin = em.createQuery("SELECT a FROM AdministradorPersistencia a WHERE a.usuario LIKE :usuario",AdministradorPersistencia.class)
				.setParameter("usuario", usuario);
		
		TypedQuery<EncargadoPersistencia> queryEncargado = em.createQuery("SELECT e FROM EncargadoPersistencia e WHERE e.usuario LIKE :usuario",EncargadoPersistencia.class)
				.setParameter("usuario", usuario);
		
		TypedQuery<PersonalPersistencia> queryPersonal = em.createQuery("SELECT p FROM PersonalPersistencia p WHERE p.usuario LIKE :usuario",PersonalPersistencia.class)
				.setParameter("usuario", usuario);
		
		String rol = "";
		
		if(!queryAdmin.getResultList().isEmpty())
		{
			rol = "Administrador";
		}
		else if(!queryEncargado.getResultList().isEmpty())
		{
			rol = "Encargado";
		}
		else if(!queryPersonal.getResultList().isEmpty())
		{
			rol = "Personal";
		}
		else
		{
			rol = "Desconocido";
		}
		return rol;
	}
	
	public boolean docRepetido(int docEvaluado)
	{
		TypedQuery<UsuarioPersistencia> query= em.createQuery("SELECT u FROM UsuarioPersistencia u WHERE u.documento LIKE :documento",UsuarioPersistencia.class)
				.setParameter("documento", docEvaluado);
		
		if(query.getResultList().isEmpty())
		{
			return false;
		}
		
		return true;
	}
	
	public List<UsuarioPersistencia> seleccionarUsuarios(String criterioNombre,
			String criterioApellido, String criterioDocumento, String criterioRol)
	{
		try {
			String query= 	"Select u from UsuarioPersistencia u  ";
			String queryCriterio="";
			if (criterioNombre!=null && ! criterioNombre.contentEquals("")) {
				queryCriterio+=(!queryCriterio.isEmpty()?" and ":"")+ " lower(u.nombre) like '%"+criterioNombre.toLowerCase() +"%' ";
			} 
			if (criterioApellido!=null && ! criterioApellido.contentEquals("")) {
				queryCriterio+=(!queryCriterio.isEmpty()?" and ":"")+ " lower(u.apellido) like '%"+criterioApellido.toLowerCase() +"%' ";
			} 
			if (criterioDocumento!=null && ! criterioDocumento.equals("")) {
				queryCriterio+=(!queryCriterio.isEmpty()?" and ":"")+" u.documento='"+criterioDocumento+"'  " ;
			}
			if (!queryCriterio.contentEquals("")) {
				query+=" where "+queryCriterio;
			}
			List<UsuarioPersistencia> resultList = (List<UsuarioPersistencia>) em.createQuery(query,UsuarioPersistencia.class)
								 .getResultList();
			int i = 0;
			if(!resultList.isEmpty() && criterioRol!=null)
			{
				while(i < resultList.size())
				{
					if(obtenerRol(resultList.get(i)).equals(criterioRol))
					{
						i++;
					}
					else
					{
						resultList.remove(i);
					}
				}
			}
			return  resultList;
			}catch(PersistenceException e){
				e.printStackTrace();
				return null;
			}
	}
	
	public boolean loginUsuario(String contraseña, String nomUsuario)
	{
		try {
			TypedQuery<UsuarioPersistencia> query = em.createQuery("SELECT u FROM UsuarioPersistencia u WHERE u.nombreUsuario LIKE :nomUsuario",UsuarioPersistencia.class)
			.setParameter("nomUsuario", nomUsuario);
			
			UsuarioPersistencia usuarioAUX = new UsuarioPersistencia();
			
			try
			{
				usuarioAUX = query.getResultList().get(0);
				usuarioAUX = em.find(UsuarioPersistencia.class, usuarioAUX.getIdUsuario());
			} catch (IndexOutOfBoundsException e)
			{
				usuarioAUX = null;
				e.printStackTrace();
			}
			
			if(usuarioAUX != null)
			{
				if(usuarioAUX.getContraseña().equals(contraseña))
				{
					return true;
				}
			}
			}catch(PersistenceException e) {
				e.printStackTrace();
			}
		return false;
	}
	
	public UsuarioPersistencia buscarUsuario(Long id)
	{
		UsuarioPersistencia empleadoEmpresa = em.find(UsuarioPersistencia.class, id);
		return empleadoEmpresa;
	}
	
	public boolean actualizarPassword(UsuarioPersistencia usuario, String contraNueva)
	{
		try
		{
			UsuarioPersistencia user = new UsuarioPersistencia();
			user = em.find(UsuarioPersistencia.class, usuario.getIdUsuario());
			user.setContraseña(Encriptado.ecnode(contraNueva));
			user = em.merge(user);
			em.flush();
			return true;
		} catch (Exception e)
		{
			e.printStackTrace();
			return false;
		}
	}

	
	public String nombreUsuario(UsuarioPersistencia usuario)
    {
    	String nombreSinEspacios = quitarEspacios(usuario.getNombre().toLowerCase());
    	String apellidoSinEspacios = quitarEspacios(usuario.getApellido().toLowerCase());
    	
//    	nombreSinEspacios = Normalizer.normalize(nombreSinEspacios, Normalizer.Form.NFD);
//    	apellidoSinEspacios = Normalizer.normalize(apellidoSinEspacios, Normalizer.Form.NFD);
//    	nombreSinEspacios = nombreSinEspacios.replaceAll("[^\\p{ASCII}]", "");
//    	apellidoSinEspacios = apellidoSinEspacios.replaceAll("[^\\p{ASCII}]", "");
    	
    	String nombreUser = nombreSinEspacios + "." + apellidoSinEspacios;
    	boolean unica = false;
    	int i = 2;
    	while(!unica)
    	{
    		if(!nombreUsuarioRepetido(nombreUser))
        	{
        		nombreUser = nombreSinEspacios + "." + apellidoSinEspacios + "." + i;
        		i++;
        	}
    		else
    		{
    			unica = true;
    		}
    	}
    	return nombreUser;
    }
    
    public boolean nombreUsuarioRepetido(String nombreUsuario)
    {
    	try
    	{
			if(obtenerNombreUsuario(nombreUsuario)==null)
			{
				return true;
			}
		} catch (Exception e)
    	{
			e.printStackTrace();
		}
    	return false;
    }
    
    private String quitarEspacios(String texto)
    {
    	String sinEspacios = "";
    	int i = 0;
    	while(i<=texto.length()-1)
    	{
    		char caracter = texto.charAt(i);
			int valorASCII = (int)caracter;
    		if(valorASCII != 32)
    		{
    			sinEspacios += caracter;
    		}
    		i++;
    	}
    	return sinEspacios;
    }
}
