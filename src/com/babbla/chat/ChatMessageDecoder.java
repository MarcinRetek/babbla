package com.babbla.chat;

import java.io.StringReader;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.json.Json;
import javax.json.JsonObject;
import javax.websocket.DecodeException;
import javax.websocket.Decoder;
import javax.websocket.EndpointConfig;

public class ChatMessageDecoder implements Decoder.Text<ChatMessage> {
	
		
	@Override
	public void init(final EndpointConfig config) {
	}
 
	@Override
	public void destroy() {
	}
	
	
	@Override
	public ChatMessage decode(final String textMessage) throws DecodeException {
		ChatMessage chatMessage = new ChatMessage();
		JsonObject obj = Json.createReader(new StringReader(textMessage))
				.readObject();
		chatMessage.setMessage(obj.getString("message"));
		chatMessage.setSender(obj.getString("sender"));
		chatMessage.setReceived(formatTime());
		chatMessage.setPublicKey(obj.getString("publicKey"));
		return chatMessage;
	}
 
	@Override
	public boolean willDecode(final String s) {
		return true;
 	}
	
	public String formatTime(){
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd HH:mm");
		String format = simpleDateFormat.format(new Date());
		return format;
	
	}
}