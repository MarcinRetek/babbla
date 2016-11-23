package com.babbla.backingbeans;

import java.util.Map;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import com.babbla.interfaces.LocalChat;
import com.babbla.interfaces.LocalUser;
import com.babbla.models.Message;
import com.babbla.models.User;

/**
 * @author Marcin Retek
 *
 */
@Named(value="chatBean")
@RequestScoped
public class ChatBean {	
	
	@EJB
	private LocalChat chatEJB;
	
	private String content;
	
	

	
	public Message saveMessage(){
		Message message = new Message();
		message.setContent(content);
		return chatEJB.saveMessage(message);
	}
	
	
	
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}


}
