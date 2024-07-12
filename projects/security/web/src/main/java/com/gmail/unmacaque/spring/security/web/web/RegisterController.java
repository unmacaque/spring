package com.gmail.unmacaque.spring.security.web.web;

import com.gmail.unmacaque.spring.security.web.domain.RegisterUser;
import org.springframework.security.authentication.password.CompromisedPasswordChecker;
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

	private final PasswordEncoder encoder;

	private final CompromisedPasswordChecker checker;

	public RegisterController(UserDetailsManager userDetailsManager, PasswordEncoder encoder,
							  CompromisedPasswordChecker checker) {
		this.userDetailsManager = userDetailsManager;
		this.encoder = encoder;
		this.checker = checker;
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
		if (checker.check(registerUser.getPassword()).isCompromised()) {
			result.reject("register.error.passwordcompromised");
		}
		if (result.hasErrors()) {
			return "register";
		}
		try {
			userDetailsManager.createUser(User
					.builder()
					.username(registerUser.getUsername())
					.password(encoder.encode(registerUser.getPassword()))
					.roles("USER").build()
			);
		} catch (RuntimeException e) {
			result.reject("register.error.general", e.getMessage());
			return "register";
		}
		modelMap.addAttribute("userCreated", registerUser.getUsername());
		return "index";
	}
}
