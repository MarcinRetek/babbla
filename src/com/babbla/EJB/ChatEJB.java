package com.babbla.EJB;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.babbla.dao.ChatDAO;
import com.babbla.interfaces.LocalChat;
import com.babbla.models.Message;
import com.babbla.models.User;

@Stateless
public class ChatEJB implements LocalChat{

	@EJB
	ChatDAO chatDAO;

	@Override
	public Message saveMessage(Message messageToSave) {
		
		return chatDAO.saveMessage(messageToSave);
		
		
		
	}
	
	
}
