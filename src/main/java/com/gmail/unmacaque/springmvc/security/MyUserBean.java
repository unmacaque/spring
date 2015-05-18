package com.gmail.unmacaque.springmvc.security;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class MyUserBean {

	private static final Logger logger = LogManager.getLogger();

	@Autowired
	private SessionFactory sessionFactory;

	@SuppressWarnings("unchecked")
	@Transactional
	public User getUserByName(String username) {
		logger.debug("looking for user {}", username);

		List<User> users = new ArrayList<User>();

		users = sessionFactory.getCurrentSession()
				.createQuery("from MyUser where username=:username")
				.setParameter("username", username).list();

		logger.debug("{} users found", users.size());

		if (users.size() > 0) {
			return users.get(0);
		}

		return null;
	}
}
