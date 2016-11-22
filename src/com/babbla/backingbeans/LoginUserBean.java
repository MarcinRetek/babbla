package com.babbla.backingbeans;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import com.babbla.interfaces.LocalUser;

@Named(value="loginUserBean")
@SessionScoped
public class LoginUserBean implements Serializable{

	private static final long serialVersionUID = 1899450248532258499L;
	
	private String name;
	private String email;
	private UserBean user;
	
	@EJB
	LocalUser userEJB;
	
	@PostConstruct
	public void init(){
	    FacesContext.getCurrentInstance().getExternalContext().getSession(true);
		System.out.println("HEJ: " + getName());
		doLogin();
	}
	
	public void doLogin(){
		System.out.println("DO LOGIN");
		userEJB.loginUser(name, email,this);
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
	public UserBean getUser() {
		return user;
	}
	public void setUser(UserBean user) {
		this.user = user;
	}
	
	
}
