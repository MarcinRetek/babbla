package com.babbla.backingbeans;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import com.babbla.interfaces.LocalUser;
import com.babbla.models.User;

@Named(value="userBean")
@RequestScoped
public class UserBean {
	
	@EJB
	private LocalUser userEJB;	
	
	private String name;
	private String email;
	
	
	public List<User> getAll() {
		return userEJB.getAll();
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
	
}
