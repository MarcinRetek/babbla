package com.babbla.backingbeans;

import java.util.Map;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;

import com.babbla.interfaces.LocalUser;
import com.babbla.models.User;

public class ChatControllerBean {
	
	private String chatId;
	
	@EJB
	LocalUser userEJB;
	
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

	public String getChatId() {
		return chatId;
	}

	public void setChatId(String chatId) {
		this.chatId = chatId;
	}

}
