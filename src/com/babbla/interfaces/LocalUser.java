package com.babbla.interfaces;

import java.util.List;

import javax.ejb.Local;

import com.babbla.exceptions.ValidateException;
import com.babbla.models.User;

@Local
public interface LocalUser {
	
	User saveUser(User user);

	List<User> getAll();
	
	void validateUser(User user) throws ValidateException;
	
	boolean hasUniqueEmail(User user);

		
}
