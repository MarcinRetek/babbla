package com.babbla.EJB;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.babbla.dao.ChatDAO;
import com.babbla.interfaces.LocalChat;
import com.babbla.models.Message;

@Stateless
public class ChatEJB implements LocalChat{

	@EJB
	ChatDAO chatDAO;

	@Override
	public Message saveMessage(Message messageToSave) {
		
		return chatDAO.saveMessage(messageToSave);		
		
	}

	@Override
	public List<Message> getAll() {
		return chatDAO.getAll();
	}

	@Override
	public Message getMessageByUserId(Message message) {
		return chatDAO.getMessageByUserId(message);
	}
	
	
}
