package com.babbla.interfaces;

import java.util.List;

import javax.ejb.Local;

import com.babbla.exceptions.ValidateException;
import com.babbla.models.UserModel;

@Local
public interface LocalUser {
	
	UserModel saveUser(UserModel user);

	List<UserModel> getAll();
	
	void validateUser(UserModel user) throws ValidateException;
		
}
