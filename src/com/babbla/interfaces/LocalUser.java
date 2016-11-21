package com.babbla.interfaces;

import javax.ejb.Local;

import com.babbla.models.UserModel;

@Local
public interface LocalUser {
	
	public UserModel saveUser(UserModel user);

}
