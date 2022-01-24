/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.peopleinmotion.horizonreinicioremoto.security;

import java.io.Serializable;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author avbravo
 */
@Named
@RequestScoped
public class ErrorHandler implements Serializable{
    private static final long serialVersionUID = 1L;
    public String getStatusCode(){
		String val = String.valueOf((Integer)FacesContext.getCurrentInstance().getExternalContext().
				getRequestMap().get("javax.servlet.error.status_code"));
		return val;
	}

	public String getMessage(){
		String val =  (String)FacesContext.getCurrentInstance().getExternalContext().
			getRequestMap().get("javax.servlet.error.message");
		return val;
	}

	public String getExceptionType(){
		String val = FacesContext.getCurrentInstance().getExternalContext().
			getRequestMap().get("javax.servlet.error.exception_type").toString();
		return val;
	}

	public String getException(){
		String val =  (String)((Exception)FacesContext.getCurrentInstance().getExternalContext().
			getRequestMap().get("javax.servlet.error.exception")).toString();
		return val;
	}

	public String getRequestURI(){
		return (String)FacesContext.getCurrentInstance().getExternalContext().
			getRequestMap().get("javax.servlet.error.request_uri");
	}

	public String getServletName(){
		return (String)FacesContext.getCurrentInstance().getExternalContext().
			getRequestMap().get("javax.servlet.error.servlet_name");
	}
    
}
