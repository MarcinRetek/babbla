package com.babbla.interfaces;

import java.util.List;

import javax.ejb.Local;
import com.babbla.models.Message;
import com.babbla.models.User;

@Local
public interface LocalChat {

	Message saveMessage(Message messageToSave);
	
	List<Message> getAll();
	
	Message getMessageByUserId(Message message);
	
}
