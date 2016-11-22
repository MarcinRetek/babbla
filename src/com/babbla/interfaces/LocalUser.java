package com.babbla.interfaces;

import java.util.List;

import javax.ejb.Local;

import com.babbla.backingbeans.LoginUserBean;
import com.babbla.models.User;

@Local
public interface LocalUser {
	
	void saveUser(User user);

	List<User> getAll();
	
	boolean hasUniqueEmail(User user);
	
	User getUserById(int id);

	boolean validateUser(User user);

	void loginUser(String name, String email, LoginUserBean loginUserBean);
	
	User getUserByEmail(String email);
	
	List<User> getListedUsersByEmail(String email);
		
}
