package com.babbla.dao;

import java.util.List;

import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.babbla.models.UserModel;
import com.sun.tools.xjc.reader.xmlschema.bindinfo.BIConversion.User;

@Stateful
public class UserDAO{

	@PersistenceContext
	EntityManager entityManager;
	
	public UserModel saveUser(UserModel user) {
		return entityManager.merge(user);
	}

	@SuppressWarnings("unchecked")
	public List<UserModel> getAll() {
		return entityManager.createNamedQuery("UserModel.findAll").getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<UserModel> getUserByEmail(String email) {
		System.err.println("getUserByEmail " + email);
		Query findByEmail = entityManager.createNamedQuery("UserModel.findByEmail");
		findByEmail.setParameter("email", email);
		return (List<UserModel>)findByEmail.getResultList();
	}

}
