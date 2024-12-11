package com.example.demo.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;

import com.example.demo.config.SecurityConfig;
import com.example.demo.entity.Item;
import com.example.demo.repo.ItemRepo;
import com.example.demo.utils.JwtUtil;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@Import(SecurityConfig.class)
public class ItemControllerIntegrationTest {

	@LocalServerPort
	private int port;

	@Autowired
	TestRestTemplate restTemplate;

	@Autowired
	private ItemRepo itemRepo;

	@BeforeEach
	void setUp() {
		// Clear repository and insert test data
		itemRepo.deleteAll();
		itemRepo.save(new Item(1L, "Sample Item", 2348.09));
	}

	@Test
	void testGetById() {
		// Generate a valid JWT token
		String token = JwtUtil.generateToken("my-client");

		// Set up headers with the token
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", "Bearer " + token);

		// Create an HttpEntity with headers
		HttpEntity<Void> entity = new HttpEntity<>(headers);

		// Call the endpoint
		ResponseEntity<Item> responseEntity = restTemplate.exchange("http://localhost:" + port + "/item/1",
				org.springframework.http.HttpMethod.GET, entity, Item.class);

		assertEquals(200, responseEntity.getStatusCodeValue());

		// Validate response body
		Item item = responseEntity.getBody();
		assertNotNull(item, "Item should not be null");
		assertEquals(1L, item.getId(), "Item ID should match");
		assertEquals("Sample Item", item.getName(), "Item name should match");
	}
}
