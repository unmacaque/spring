package com.gmail.unmacaque.spring.web;

import com.gmail.unmacaque.spring.domain.RegisterUser;
import com.gmail.unmacaque.spring.security.OtpSecretRegistry;
import org.jboss.aerogear.security.otp.api.Base32;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Controller
public class RegisterController {

	public static final String OTPAUTH_CODE = "otpauth://totp/%s?secret=%s";

	private final UserDetailsManager userDetailsManager;

	private final OtpSecretRegistry otpSecretRegistry;

	public RegisterController(UserDetailsManager userDetailsManager, OtpSecretRegistry otpSecretRegistry) {
		this.userDetailsManager = userDetailsManager;
		this.otpSecretRegistry = otpSecretRegistry;
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
							   ModelMap modelMap) throws UnsupportedEncodingException {
		modelMap.addAttribute("registerUser", registerUser);
		if (userDetailsManager.userExists(registerUser.getUsername())) {
			result.reject("register.error.usernameexists");
		}
		if (result.hasErrors()) {
			return "register";
		}
		final String secret;
		try {
			secret = createNewOtpUser(registerUser);
			final String code = URLEncoder.encode(String.format(OTPAUTH_CODE, "spring-security-2fa", secret), StandardCharsets.UTF_8);
			modelMap.addAttribute("code", code);
		} catch (RuntimeException e) {
			result.reject("register.error.general", e.getMessage());
			return "register";
		}
		modelMap.addAttribute("userCreated", registerUser.getUsername());
		return "index";
	}

	private String createNewOtpUser(RegisterUser registerUser) {
		final String secret = Base32.random();
		final var otpUser = new User(
				registerUser.getUsername(),
				registerUser.getPassword(),
				AuthorityUtils.createAuthorityList("ROLE_USER"));
		otpSecretRegistry.addUser(registerUser.getUsername(), secret);
		userDetailsManager.createUser(otpUser);
		return secret;
	}
}
