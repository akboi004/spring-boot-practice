package com.example.demo.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.utils.JwtUtil;

@RestController
@RequestMapping("/oauth")
public class TokenController {

	private final AuthenticationManager authenticationManager;

	public TokenController(AuthenticationManager authenticationManager) {
		this.authenticationManager = authenticationManager;
	}

	@PostMapping("/token")
	public Map<String, Object> getToken(@RequestHeader("Authorization") String authHeader,
			@RequestParam("grant_type") String grantType) {
		if (!"client_credentials".equals(grantType)) {
			throw new IllegalArgumentException("Unsupported Grant Type");
		}

		String[] credentials = decodeBasicAuth(authHeader);
		String clientId = credentials[0];
		String clientSecret = credentials[1];

		Authentication authentication = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(clientId, clientSecret));

		if (authentication.isAuthenticated()) {
			String token = JwtUtil.generateToken(clientId);

			Map<String, Object> response = new HashMap<>();
			response.put("access_token", token);
			response.put("token_type", "Bearer");
			response.put("expires_in", 120);

			return response;
		} else {
			throw new RuntimeException("Invalid client credentials");
		}
	}

	private String[] decodeBasicAuth(String authHeader) {
		if (!authHeader.startsWith("Basic ")) {
			throw new IllegalArgumentException("Invalid Authorization Header");
		}

		String base64Credentials = authHeader.substring(6);
		String credentials = new String(java.util.Base64.getDecoder().decode(base64Credentials));
		return credentials.split(":", 2);
	}
}
