package com.babbla.backingbeans;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import com.babbla.interfaces.LocalUser;
import com.babbla.models.UserModel;

@Named(value="userBean")
@RequestScoped
public class UserControllerBean {
	
	@EJB
	private LocalUser userEJB;	
	
	private String name;
	private String email;
	
	public String save() {
		System.out.println("Inside USER SAVE");
		UserModel user = new UserModel();
		user.setName(name);
		user.setEmail(email);
		
		userEJB.saveUser(user);
		
		return "";
		
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
