package com.utils;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

//import com.capa1entities.Usuario;

@SuppressWarnings("cdi-ambiguous-dependency")
@Named(value="gestionSesison")
@SessionScoped
public final class Session implements Serializable{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	private static Session session;

    private String loggedUser;
    
    private String userType;
    
    private Session() {
    	super();
    }
    
    public static Session getCurrentInstance() {
        if (session == null) {
            session = new Session();
          
        }
        return session;
    }
    public void setLoggedUser(String user) {
        loggedUser = user;
    }

    public String getLoggedUser() {
        return loggedUser;
    }

	public String getUserType() {
		return userType;
	}

	public void setUserType(String type) {
		userType = type;
	}
	
	//Método para cerrar sesión
	
		public String logout() {
		        FacesContext facesContext = FacesContext.getCurrentInstance();
		        HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(false);
		        if (session != null) {
		            session.invalidate(); //Cierre de sesion
		        }
		        
		        System.out.println("Entro");
		        return "/login.xhtml";// indicas a donde quieres direccionar después de cerrar sesión 
		    }
	public String devuelvo() {System.out.println("Entre"); return "Entre";}
}
