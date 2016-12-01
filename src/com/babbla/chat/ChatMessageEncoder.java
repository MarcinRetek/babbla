package com.babbla.chat;

import javax.ejb.EJB;
import javax.json.Json;
import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;

import com.babbla.interfaces.LocalChat;
import com.babbla.models.Message;
import com.babbla.models.User;

public class ChatMessageEncoder implements Encoder.Text<ChatMessage> {
	
	@EJB
	LocalChat chatEJB;
	
	@Override
	public void init(final EndpointConfig config) {
	}
 
	@Override
	public void destroy() {
	}
 
	@Override
	public String encode(final ChatMessage chatMessage) throws EncodeException {
		System.out.println("PUBKEY: " + chatMessage.getPublicKey());
		return Json.createObjectBuilder()
				.add("message", chatMessage.getMessage())
				.add("sender", chatMessage.getSender())
				.add("publicKey", chatMessage.getPublicKey())
				.add("received", chatMessage.getReceived().toString())
				.add("publicKey", chatMessage.getPublicKey()).build().toString();
	}
}