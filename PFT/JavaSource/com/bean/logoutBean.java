package com.bean;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import com.utils.Session;

@Named("gestionLogout")
@SessionScoped
public class logoutBean implements Serializable
{
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	public logoutBean() {
		//TODO Auto-generated constructor stub
	}
	
	public String cerrarSesion() {
		
		try {
			System.out.println("Entroi2222");			
			Session.getCurrentInstance().setLoggedUser(null);
			return "index";
			
		} catch (Exception e) {
			e.printStackTrace();
			return"";
		}
	}
}
