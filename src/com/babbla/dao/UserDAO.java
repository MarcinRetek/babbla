package com.babbla.dao;

import java.util.List;

import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.babbla.models.UserModel;

@Stateful
public class UserDAO{

	@PersistenceContext
	EntityManager entityManager;
	
	public UserModel saveUser(UserModel user) {
		entityManager.merge(user);
		return user;
	}

	public List<UserModel> getAll() {
		return entityManager.createNamedQuery("UserModel.findAll").getResultList();
	}

}
