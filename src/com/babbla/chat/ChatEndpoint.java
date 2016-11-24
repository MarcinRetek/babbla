package com.babbla.chat;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.EJB;
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
		User user = new User();
		user.setEmail("marcin@hehjehj.com");
		user.setName("Marcin");
		
		Message message = new Message();
		message.setContent(chatMessage.getMessage());
		message.setUser(user);
		System.out.println("Created Message");
		chatEJB.saveMessage(message);
		System.out.println("Saved message");
	}
}


