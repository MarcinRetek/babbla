package com.babbla.backingbeans;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import com.babbla.interfaces.LocalUser;
import com.babbla.models.User;

@Named(value="chatBean")
public class ChatBean {
		
	public class Chat {
			
			private String link;
			private String name;
	
			public Chat(String name, String link) {
				this.link = link;
				this.name = name;
			}
			
			public String getLink() {
				return link;
			}
	
			public String getName() {
				return name;
			}
	
		}
	
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
	
	public List<Chat> getLinks() {
		List<Chat> links = new ArrayList<>();
		links.add(new Chat("main","r"));
		
		for(User user : userEJB.getAll()) {
			links.add(new Chat(user.getName(), "u"+user.getId()));
			for (int i = 0; i < links.size(); i++) {
				System.out.println(links.get(i));
			}
		}
		return links;
	}

}
