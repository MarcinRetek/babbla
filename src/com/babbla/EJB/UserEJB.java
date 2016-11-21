package com.babbla.EJB;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.babbla.dao.UserDAO;
import com.babbla.interfaces.LocalUser;
import com.babbla.models.UserModel;

@Stateless
public class UserEJB implements LocalUser {
	
	@EJB
	UserDAO userDao;

	@Override
	public UserModel saveUser(UserModel user) {
		return userDao.saveUser(user);
	}
	
}
