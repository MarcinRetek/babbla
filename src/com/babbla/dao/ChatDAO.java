package com.babbla.dao;

import java.util.List;

import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.babbla.models.Message;
import com.babbla.models.User;

@Stateful
public class ChatDAO {
	
	@PersistenceContext
	EntityManager entityManager;
	
	public Message saveMessage(Message messageToSave) {
		return entityManager.merge(messageToSave);
	}
	
	@SuppressWarnings("unchecked")
	public List<Message> getAll(){
		return entityManager.createNamedQuery("Message.findAll").getResultList();
	}
	
	public Message getMessageByUserId(Message message){
		Query findByUserId = entityManager.createNamedQuery("Message.getMessageByUserId");
		findByUserId.setParameter("userid", message.getId());
		return message;
	}
}
