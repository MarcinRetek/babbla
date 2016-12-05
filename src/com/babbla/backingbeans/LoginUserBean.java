package com.babbla.backingbeans;

import java.io.Serializable;
import java.util.Map;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import com.babbla.interfaces.LocalUser;
import com.babbla.models.User;


@Named(value="loginUserBean")
@SessionScoped
public class LoginUserBean implements Serializable{
	

	private static final long serialVersionUID = 1899450248532258499L;
	
	private String name;
	private String email;
	private static User loggedInUser;
	
	@EJB
	LocalUser userEJB;
	

	
	public void doLogin(){
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		Map<String, Object> sessionMap = externalContext.getSessionMap();
		sessionMap.put("user", loggedInUser);
	}
	
	public void doLogout(){
		//TODO: handle this
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	public static User getLoggedInUser() {
		return loggedInUser;
	}

	public void setLoggedInUser(User loggedInUser) {
		this.loggedInUser = loggedInUser;
	}

}
