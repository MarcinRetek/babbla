package com.babbla.EJB;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.babbla.dao.UserDAO;
import com.babbla.exceptions.ValidateException;
import com.babbla.interfaces.LocalUser;
import com.babbla.models.UserModel;

@Stateless
public class UserEJB implements LocalUser {
	
	@EJB
	UserDAO userDao;

	@Override
	public UserModel saveUser(UserModel user) {
		if (hasUniqueEmail(user)) {
			return userDao.saveUser(user);			
		}else{
			return null;
		}
			
	}
	
	public void validateUser(UserModel user) throws ValidateException{
		if (!hasUniqueEmail(user)) {
			throw new ValidateException("A user with that email (" +user.getEmail() + ") already exists.");
		}
	}
	
	public boolean hasUniqueEmail(UserModel user){
		String compareEmail = user.getEmail();

		for(UserModel compareUser : userDao.getUserByEmail(compareEmail)) {
			if(compareUser!=user) return false;
		}
		return true;
	}

	@Override
	public List<UserModel> getAll() {
		return userDao.getAll();
	}
	
}
