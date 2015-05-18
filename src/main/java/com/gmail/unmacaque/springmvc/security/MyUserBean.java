package com.gmail.unmacaque.springmvc.security;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

@Component
public class MyUserBean {

	@Autowired
	private SessionFactory sessionFactory;

	@SuppressWarnings("unchecked")
	public User getUserByName(String username) {
		List<User> users = new ArrayList<User>();

		users = sessionFactory.getCurrentSession()
				.createQuery("from User where username=?")
				.setParameter(0, username).list();

		if (users.size() > 0) {
			return users.get(0);
		}
		
		return null;
	}
}
