package com.babbla.chat;

import java.io.StringReader;
import java.util.Date;

import javax.ejb.EJB;
import javax.json.Json;
import javax.json.JsonObject;
import javax.websocket.DecodeException;
import javax.websocket.Decoder;
import javax.websocket.EndpointConfig;

import com.babbla.interfaces.LocalChat;
import com.babbla.models.Message;
import com.babbla.models.User;

public class ChatMessageDecoder implements Decoder.Text<ChatMessage> {
	
	@EJB
	LocalChat chatEJB;
	
	@Override
	public void init(final EndpointConfig config) {
	}
 
	@Override
	public void destroy() {
	}
	
	
	//Tar emot meddelnade från frontend o skickar rill encoder
	@Override
	public ChatMessage decode(final String textMessage) throws DecodeException {
		ChatMessage chatMessage = new ChatMessage();
		JsonObject obj = Json.createReader(new StringReader(textMessage))
				.readObject();
		chatMessage.setMessage(obj.getString("message"));
		chatMessage.setSender(obj.getString("sender"));
		chatMessage.setReceived(new Date());
	
		return chatMessage;
	}
 
	@Override
	public boolean willDecode(final String s) {
		return true;
 	}

}