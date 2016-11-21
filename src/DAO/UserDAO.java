package DAO;

import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import EJB.LocalUser;

@Stateful
public class UserDAO{

	@PersistenceContext
	EntityManager entityManager;
	
	public User saveUser(User user) {
		entityManager.merge(user);
		return user;
	}

}
