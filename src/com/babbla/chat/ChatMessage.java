package com.babbla.chat;

import java.util.Date;

import com.babbla.models.User;

public class ChatMessage {
	private String message;
	private String sender;
	private Date received;
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Date getReceived() {
		return received;
	}
	public void setReceived(Date received) {
		this.received = received;
	}
	public String getSender() {
		return sender;
	}
	public void setSender(String sender) {
		this.sender = sender;
	}
}
