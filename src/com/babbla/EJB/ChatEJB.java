package com.babbla.EJB;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.babbla.dao.ChatDAO;
import com.babbla.interfaces.LocalChat;

import com.babbla.models.Message;



@Stateless
public class ChatEJB implements LocalChat {
	
	@EJB
	ChatDAO chatDao;

	@Override
	public Message saveMessage(Message message) {
		return chatDao.saveMessage(message);
	}




}