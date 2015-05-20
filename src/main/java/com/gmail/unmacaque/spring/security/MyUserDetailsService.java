package com.gmail.unmacaque.spring.security;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.gmail.unmacaque.spring.security.model.MyAuthority;
import com.gmail.unmacaque.spring.security.model.MyUser;

public class MyUserDetailsService implements UserDetailsService {

	@Autowired
	private MyUserBean myUserBean;

	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		MyUser user = myUserBean.getUserByName(username);

		if (user == null) {
			throw new UsernameNotFoundException(username);
		}

		List<GrantedAuthority> authorities = buildUserAuthority(user.getAuthorities());

		return new User(user.getUsername(), user.getPassword(),
				user.isEnabled(), true, true, true, authorities);
	}

	private List<GrantedAuthority> buildUserAuthority(Set<MyAuthority> authorities) {
		Set<GrantedAuthority> grantedAuthorities = new HashSet<GrantedAuthority>();

		// Build user's authorities
		for (MyAuthority authority : authorities) {
			grantedAuthorities.add(new SimpleGrantedAuthority(authority.getAuthority()));
		}

		List<GrantedAuthority> result = new ArrayList<GrantedAuthority>(
				grantedAuthorities);

		return result;
	}
}
