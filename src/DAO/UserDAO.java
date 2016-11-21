package DAO;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import EJB.LocalUser;

public class UserDAO implements LocalUser{

	@PersistenceContext
	EntityManager entityManager;
	
	@Override
	public User saveUser(User user) {
		entityManager.merge(user);
		return user;
	}

}
