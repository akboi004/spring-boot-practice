package com.example.demo.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@CrossOrigin(methods = RequestMethod.GET, maxAge = 1800, allowedHeaders = "*", origins = "*")
@RestController
public class CorsController {

	@GetMapping("/fetchCorsUsers")
	public Object fetchUsers() {
		String url = "https://jsonplaceholder.typicode.com/users";
		RestTemplate restTemplate = new RestTemplate();

		return restTemplate.getForObject(url, Object.class);
	}
}
