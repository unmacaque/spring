package com.gmail.unmacaque.spring.web;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class RegisterControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private UserDetailsManager userDetailsManager;

	@Test
	public void testGetRegister() throws Exception {
		mockMvc.perform(get("/register"))
				.andExpect(status().isOk())
				.andExpect(model().attributeExists("registerUser"));
	}

	@Test
	public void testPostRegister() throws Exception {
		mockMvc.perform(post("/register")
				.param("username", "foo")
				.param("mailAddress", "foo@bar.org")
				.param("password", "springsecurity")
				.param("passwordConfirm", "springsecurity")
				.with(csrf()))
				.andExpect(status().isOk())
				.andExpect(model().attributeExists("registerUser"))
				.andExpect(model().attribute("userCreated", "foo"))
				.andExpect(view().name("index"));

		verify(userDetailsManager).userExists("foo");
		verify(userDetailsManager).createUser(any());
	}

	@Test
	public void testPostRegister_withIncompleteUserDetails() throws Exception {
		mockMvc.perform(post("/register")
				.param("username", "")
				.param("mailAddress", "")
				.param("password", "")
				.param("passwordConfirm", "")
				.with(csrf()))
				.andExpect(status().isOk())
				.andExpect(model().attributeHasErrors("registerUser"))
				.andExpect(model().attributeHasFieldErrors("registerUser", "username", "mailAddress", "password", "passwordConfirm"))
				.andExpect(view().name("register"));
	}

	@Test
	public void testPostRegister_withUsernameAlreadyExists() throws Exception {
		doReturn(true).when(userDetailsManager).userExists("foo");

		mockMvc.perform(post("/register")
				.param("username", "foo")
				.param("mailAddress", "foo@bar.org")
				.param("password", "springsecurity")
				.param("passwordConfirm", "springsecurity")
				.with(csrf()))
				.andExpect(status().isOk())
				.andExpect(model().attributeHasErrors("registerUser"))
				.andExpect(view().name("register"));
	}

	@Test
	public void testPostRegister_withInternalError() throws Exception {
		doThrow(RuntimeException.class).when(userDetailsManager).createUser(any());

		mockMvc.perform(post("/register")
				.param("username", "foo")
				.param("mailAddress", "foo@bar.org")
				.param("password", "springsecurity")
				.param("passwordConfirm", "springsecurity")
				.with(csrf()))
				.andExpect(status().isOk())
				.andExpect(model().attributeHasErrors("registerUser"))
				.andExpect(view().name("register"));
	}
}
