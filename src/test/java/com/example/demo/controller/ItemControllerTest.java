package com.example.demo.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;

import com.example.demo.config.SecurityConfig;
import com.example.demo.entity.Item;
import com.example.demo.repo.ItemRepo;
import com.example.demo.utils.JwtUtil;

@WebMvcTest(ItemController.class)
@Import(SecurityConfig.class)
public class ItemControllerTest {

	@MockBean
	private ItemRepo itemRepo;

	@Autowired
	private MockMvc mockMvc;

	@Test
	void getAllItemTest() throws Exception {
		// Prepare test data
		Item item1 = new Item(1L, "Samsung", 2464.321);
		List<Item> mockItems = Arrays.asList(item1);

		// Mock the repository to return the test data
		Mockito.when(itemRepo.findAll()).thenReturn(mockItems);

		// Generate a valid token using the clientId (the token generation logic from
		// JwtUtil)
		String token = JwtUtil.generateToken("my-client");

		// Perform the test with the Authorization header containing the Bearer token
		mockMvc.perform(get("/getAllItem").header("Authorization", "Bearer " + token)).andExpect(status().isOk())
				.andExpect(jsonPath("$", Matchers.hasSize(1))).andExpect(jsonPath("$[0].id", Matchers.is(1)))
				.andExpect(jsonPath("$[0].name", Matchers.is("Samsung")))
				.andExpect(jsonPath("$[0].price", Matchers.is(2464.321)));
	}
}
