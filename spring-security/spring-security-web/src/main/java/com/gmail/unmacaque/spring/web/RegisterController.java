package com.gmail.unmacaque.spring.web;

import com.gmail.unmacaque.spring.domain.RegisterUser;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegisterController {

	private final UserDetailsManager userDetailsManager;

	private final PasswordEncoder passwordEncoder;

	public RegisterController(UserDetailsManager userDetailsManager, PasswordEncoder passwordEncoder) {
		this.userDetailsManager = userDetailsManager;
		this.passwordEncoder = passwordEncoder;
	}

	@ModelAttribute
	public RegisterUser registerUser() {
		return new RegisterUser();
	}

	@GetMapping("/register")
	public String register() {
		return "register";
	}

	@PostMapping("/register")
	public String registerPost(@Validated @ModelAttribute("registerUser") RegisterUser registerUser, BindingResult result,
							   ModelMap modelMap) {
		modelMap.addAttribute("registerUser", registerUser);
		if (userDetailsManager.userExists(registerUser.getUsername())) {
			result.reject("register.error.usernameexists");
		}
		if (result.hasErrors()) {
			return "register";
		}
		try {
			userDetailsManager.createUser(new User(
					registerUser.getUsername(),
					passwordEncoder.encode(registerUser.getPassword()),
					AuthorityUtils.createAuthorityList("ROLE_USER")));
		} catch (RuntimeException e) {
			result.reject("register.error.general", e.getMessage());
			return "register";
		}
		modelMap.addAttribute("userCreated", registerUser.getUsername());
		return "index";
	}
}
