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

import com.babbla.interfaces.LocalUser;

@ServerEndpoint(value = "/chat/{room}", encoders = ChatMessageEncoder.class, decoders = ChatMessageDecoder.class)
public class ChatEndpoint {

	private final Logger log = Logger.getLogger(getClass().getName());

	@EJB
	LocalUser userEJB;

	@OnOpen
	public void open(final Session session, @PathParam("room") final String room) {
		for (Session s : session.getOpenSessions()) {
			if (s.isOpen() && s.getOpenSessions().size() == 3) {
				try {
					s.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}	
			session.getUserProperties().put("room", room);
		}
	}

	@OnMessage
	public void onMessage(final Session session, final ChatMessage chatMessage) {
		String room = (String) session.getUserProperties().get("room");
		
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
}
