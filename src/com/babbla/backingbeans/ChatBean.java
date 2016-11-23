package com.babbla.backingbeans;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import com.babbla.interfaces.LocalChat;
import com.babbla.models.Message;
import com.babbla.models.User;

@Named(value="chatBean")
@RequestScoped
public class ChatBean {
	
	private String content;
	
	@EJB
	LocalChat chatEJB;
	
	public String saveMessage(){
		System.out.println("Inne i save metoden");
		User user = new User();
		user.setEmail("marcin@hehjehj.com");
		user.setName("Marcin");
		
		Message messageToSave = new Message();
		messageToSave.setContent(content);
		messageToSave.setUser(user);
		chatEJB.saveMessage(messageToSave);
		System.out.println("Efter");
		return "";
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}


}
