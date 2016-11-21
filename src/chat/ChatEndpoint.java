package chat;

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

	private final Logger LOG = Logger.getLogger(getClass().getName());

	@EJB
	private LocalUser userEJB;

	@OnOpen
	public void open(final Session session, @PathParam("room") final String room) {
		
		session.getUserProperties().put("room", room);
		
		try {
			session.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

	@OnMessage
	public void onMessage(final Session session, final ChatMessage chatMessage) {

				
		try {
			for (Session s : session.getOpenSessions()) {
				
				if(isMessageForSession(s,chatMessage)) {
					
					s.getBasicRemote().sendObject(chatMessage);					
				}				
			}
		} catch (IOException | EncodeException e) {
			LOG.log(Level.WARNING, "onMessage failed", e);
		}

	}

	private boolean isMessageForSession(Session session, ChatMessage chatMessage) {

		String sessionInRoom = (String) session.getUserProperties().get("room");
		String messageRecipient = chatMessage.getRecipient();
		String messageSender = chatMessage.getSender();
		
		if(sessionInRoom!=null && !sessionInRoom.equals("")) {

			if(chatMessage.getChatRoom().equals(sessionInRoom)){
				return true;
			}
			
		}
		else if(messageRecipient!=null && !messageRecipient.equals("")) {
				return true;
			
		}

		return false;
	}

}
