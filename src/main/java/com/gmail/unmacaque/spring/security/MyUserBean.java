package com.gmail.unmacaque.spring.security;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.gmail.unmacaque.spring.security.model.MyUser;

@Component
public class MyUserBean {

	private static final Logger logger = LogManager.getLogger();

	@Autowired
	private SessionFactory sessionFactory;

	@SuppressWarnings("unchecked")
	@Transactional
	public MyUser getUserByName(String username) {
		logger.debug("looking for user {}", username);

		List<MyUser> users = new ArrayList<MyUser>();

		users = sessionFactory.getCurrentSession()
				.createQuery("from MyUser where username=:username")
				.setParameter("username", username).list();

		logger.debug("{} users found", users.size());

		if (users.size() > 0) {
			logger.debug(users.get(0).getAuthorities());
			return users.get(0);
		}

		return null;
	}
}
