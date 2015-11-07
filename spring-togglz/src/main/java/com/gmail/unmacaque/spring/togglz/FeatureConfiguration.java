package com.gmail.unmacaque.spring.togglz;

import org.springframework.stereotype.Component;
import org.togglz.core.Feature;
import org.togglz.core.manager.TogglzConfig;
import org.togglz.core.repository.StateRepository;
import org.togglz.core.repository.mem.InMemoryStateRepository;
import org.togglz.core.user.UserProvider;
import org.togglz.spring.security.SpringSecurityUserProvider;

@Component
public class FeatureConfiguration implements TogglzConfig {

	@Override
	public Class<? extends Feature> getFeatureClass() {
		return Features.class;
	}

	@Override
	public StateRepository getStateRepository() {
		return new InMemoryStateRepository();
	}

	@Override
	public UserProvider getUserProvider() {
		return new SpringSecurityUserProvider("ROLE_ADMIN");
	}

}
