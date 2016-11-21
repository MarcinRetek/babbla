package com.babbla.backingbeans;

import java.util.List;

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
	
	public void save(String username, String userEmail) {
		System.out.println(userEmail + username);
		UserModel user = new UserModel();
		user.setName(username);
		user.setEmail(userEmail);
		System.out.println("USER: " + user.getName());
		userEJB.saveUser(user);
		
		
	}
	
	public List<UserModel> getAll() {
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
