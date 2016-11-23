package com.babbla.interfaces;

import javax.ejb.Local;

import com.babbla.models.Message;

@Local
public interface LocalChat {

	Message saveMessage(Message message);
	
}
