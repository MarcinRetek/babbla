package com.babbla.backingbeans;

import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import com.babbla.interfaces.LocalUser;
import com.babbla.models.User;

@Named(value="userBean")
@RequestScoped
public class UserControllerBean {
	
	@EJB
	private LocalUser userEJB;	
	
	private String name;
	private String email;
	
	public void save(String username, String userEmail) {
		System.out.println("String " + userEmail + "  " + username);
		User user = new User();
		user.setName(username);
		user.setEmail(userEmail);
		System.out.println("USER: " + user.getName() + "  " + user.getEmail());
		userEJB.saveUser(user);
		
	}
	
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
