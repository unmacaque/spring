package com.gmail.unmacaque.spring.web;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.ldap.userdetails.LdapUserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

	@GetMapping("/")
	public String index(@AuthenticationPrincipal(errorOnInvalidType = true) LdapUserDetails userDetails) {
		return "Hello, " + userDetails.getUsername();
	}

}
