package com.gmail.unmacaque.springmvc.security;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class MyUserDetailsService implements UserDetailsService {

	@Autowired
	private MyUserBean myUserBean;

	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		User user = myUserBean.getUserByName(username);
		
		if (user == null) {
			throw new UsernameNotFoundException(username);
		}
		
		List<GrantedAuthority> authorities = null;
		
		return new User(user.getUsername(), user.getPassword(), user.isEnabled(), true, true, true, authorities);
	}

}
