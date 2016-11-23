package com.babbla.backingbeans;

import java.util.List;
import java.util.Map;

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
	private String chatId;
	
	
	@PostConstruct
	public void init(){
		Map<String, String> params =  FacesContext.getCurrentInstance()
				.getExternalContext()
				.getRequestParameterMap();
		if(params.get("username")!=null) {
			chatId = params.get("username"); 
		} else {
			chatId = "none";
		}	}
	
	public User chatWith(){
		if(chatId.substring(0, 1).equals("u")) {
			int userId = Integer.parseInt(chatId.substring(1));
			return userEJB.getUserById(userId);
		}
		return null;
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
