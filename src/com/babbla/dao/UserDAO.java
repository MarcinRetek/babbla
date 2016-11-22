package com.babbla.dao;

import java.util.List;

import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.babbla.models.User;

@Stateful
public class UserDAO{

	@PersistenceContext
	EntityManager entityManager;
	
	public User saveUser(User user) {
		return entityManager.merge(user);
	}

	@SuppressWarnings("unchecked")
	public List<User> getAll() {
		return entityManager.createNamedQuery("User.findAll").getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<User> getListedUserByEmail(String email) {
		System.err.println("getUserByEmail " + email);
		Query findByEmail = entityManager.createNamedQuery("User.findByEmail");
		findByEmail.setParameter("email", email);
		return (List<User>)findByEmail.getResultList();
	}

	public User getUserById(int id) {
		return entityManager.find(User.class, id);
	}
	
	public User getUserByEmail(String email){
		return null;
	}

}
