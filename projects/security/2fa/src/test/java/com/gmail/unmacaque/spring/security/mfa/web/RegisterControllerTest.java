package com.gmail.unmacaque.spring.security.mfa.web;

import com.gmail.unmacaque.spring.security.mfa.config.SecurityConfiguration;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(RegisterController.class)
@Import(SecurityConfiguration.class)
class RegisterControllerTest {

	@Autowired
	private MockMvc mvc;

	@MockitoBean
	private UserDetailsManager userDetailsManager;

	@Test
	void testGetRegister() throws Exception {
		mvc.perform(get("/register"))
				.andExpectAll(
						status().isOk(),
						model().attributeExists("registerUser")
				);
	}

	@Test
	void testPostRegister() throws Exception {
		mvc.perform(post("/register")
						.param("username", "foo")
						.param("mailAddress", "foo@bar.org")
						.param("password", "springsecurity")
						.param("passwordConfirm", "springsecurity")
						.with(csrf()))
				.andExpectAll(
						status().isOk(),
						model().attributeExists("registerUser"),
						model().attribute("userCreated", "foo"),
						view().name("index")
				);

		verify(userDetailsManager).userExists("foo");
		verify(userDetailsManager).createUser(any());
	}

	@Test
	void testPostRegisterWithIncompleteUserDetails() throws Exception {
		mvc.perform(post("/register")
						.param("username", "")
						.param("mailAddress", "")
						.param("password", "")
						.param("passwordConfirm", "")
						.with(csrf()))
				.andExpectAll(
						status().isOk(),
						model().attributeHasErrors("registerUser"),
						model().attributeHasFieldErrors("registerUser", "username", "mailAddress", "password", "passwordConfirm"),
						view().name("register")
				);
	}

	@Test
	void testPostRegisterWithUsernameAlreadyExists() throws Exception {
		doReturn(true).when(userDetailsManager).userExists("foo");

		mvc.perform(post("/register")
						.param("username", "foo")
						.param("mailAddress", "foo@bar.org")
						.param("password", "springsecurity")
						.param("passwordConfirm", "springsecurity")
						.with(csrf()))
				.andExpectAll(
						status().isOk(),
						model().attributeHasErrors("registerUser"),
						view().name("register")
				);
	}

	@Test
	void testPostRegisterWithInternalError() throws Exception {
		doThrow(RuntimeException.class).when(userDetailsManager).createUser(any());

		mvc.perform(post("/register")
						.param("username", "foo")
						.param("mailAddress", "foo@bar.org")
						.param("password", "springsecurity")
						.param("passwordConfirm", "springsecurity")
						.with(csrf()))
				.andExpectAll(
						status().isOk(),
						model().attributeHasErrors("registerUser"),
						view().name("register")
				);
	}
}
