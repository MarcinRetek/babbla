package com.babbla.chat;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.inject.Inject;
import javax.websocket.EncodeException;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import com.babbla.backingbeans.LoginUserBean;
import com.babbla.interfaces.LocalChat;
import com.babbla.interfaces.LocalUser;
import com.babbla.models.Message;
import com.babbla.models.User;

@ServerEndpoint(value = "/chat/{room}", encoders = ChatMessageEncoder.class, decoders = ChatMessageDecoder.class)
public class ChatEndpoint {
	private final Logger log = Logger.getLogger(getClass().getName());
	
	@EJB
	LocalChat chatEJB;
	
	@EJB
	LocalUser userEJB;
	
	@Inject
	LoginUserBean loginUserBean;
 
	@OnOpen
	public void open(final Session session, @PathParam("room") final String room) {
		log.info("session openend and bound to room: " + room);
		session.getUserProperties().put("room", room);
	}
 
	@OnMessage
	public void onMessage(final Session session, final ChatMessage chatMessage) {
		String room = (String) session.getUserProperties().get("room");
		save(chatMessage);
		try {
			for (Session s : session.getOpenSessions()) {
				if (s.isOpen() && room.equals(s.getUserProperties().get("room"))) {
					s.getBasicRemote().sendObject(chatMessage);
				}
			}
		} catch (IOException | EncodeException e) {
			log.log(Level.WARNING, "onMessage failed", e);
		}
	}
	

	public void save(ChatMessage chatMessage){		
		User user = loginUserBean.getLoggedInUser();		
				
		List<User> list = userEJB.getAll();		
		
		for(int i = 0; i < list.size(); i++){
			if(list.get(i).getEmail().equals(user.getEmail())){							
				user.setId(list.get(i).getId());
			}else{
				System.out.println("emailen finns ej i databasen");
			}
		}
		
		int tempId = user.getId();
		
		user.setId(tempId);
		user.setEmail(user.getEmail());
		user.setName(user.getName());	
		
		Message message = new Message();
		message.setContent(chatMessage.getMessage());
		message.setUser(user);
		//saveMessage requires logic check
		chatEJB.saveMessage(message);
	}
}
