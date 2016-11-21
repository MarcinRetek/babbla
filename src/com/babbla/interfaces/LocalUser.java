package com.babbla.interfaces;

import java.util.List;

import javax.ejb.Local;

import com.babbla.models.UserModel;

@Local
public interface LocalUser {
	
	public UserModel saveUser(UserModel user);

	public List<UserModel> getAll();
		
}
