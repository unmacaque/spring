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

	@GetMapping("/register")
	public String register(ModelMap modelMap) {
		modelMap.addAttribute("registerUser", new RegisterUser());
		return "register";
	}

	@PostMapping("/register")
	public String registerPost(
			@Validated @ModelAttribute RegisterUser registerUser,
			BindingResult result,
			ModelMap modelMap) {
		if (userDetailsManager.userExists(registerUser.username())) {
			result.reject("register.error.usernameexists");
		}
		if (checker.check(registerUser.password()).isCompromised()) {
			result.reject("register.error.passwordcompromised");
		}
		if (result.hasErrors()) {
			return "register";
		}
		try {
			userDetailsManager.createUser(User
					.builder()
					.username(registerUser.username())
					.password(encoder.encode(registerUser.password()))
					.roles("USER").build()
			);
		} catch (RuntimeException e) {
			result.reject("register.error.general", e.getMessage());
			return "register";
		}
		modelMap.addAttribute("userCreated", registerUser.username());
		return "index";
	}
}
