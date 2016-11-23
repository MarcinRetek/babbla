package com.babbla.dao;

import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.babbla.models.Message;

@Stateful
public class ChatDAO {

	@PersistenceContext
	EntityManager entityManager;
	
	public Message saveMessage(Message message) {
		return entityManager.merge(message);
			
	}

}
