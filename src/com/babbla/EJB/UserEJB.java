package com.babbla.EJB;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.babbla.dao.UserDAO;
import com.babbla.exceptions.ValidateException;
import com.babbla.interfaces.LocalUser;
import com.babbla.models.User;

@Stateless
public class UserEJB implements LocalUser {
	
	@EJB
	UserDAO userDao;

	@Override
	public void saveUser(User user) {
		
		userDao.saveUser(user);			
		
			
	}
	
	public boolean validateUser(User user){
		if(hasUniqueEmail(user)){
			saveUser(user);
			return true;
		}
		return false;
		
		
	}
	
	public boolean hasUniqueEmail(User user){
		String compareEmail = user.getEmail();

		for(User compareUser : userDao.getUserByEmail(compareEmail)) {
			if(compareUser!= user) return false;
		}
		return true;
	}

	@Override
	public List<User> getAll() {
		return userDao.getAll();
	}
	
}
