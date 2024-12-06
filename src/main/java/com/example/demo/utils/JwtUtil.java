package com.example.demo.utils;

import java.security.Key;
import java.util.Date;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

public class JwtUtil {

	private static final Key SECRET_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS256);
	private static final long EXPIRATION_TIME = 1 * 60 * 60 * 1000; //1 hour

	public static String generateToken(String clientId) {
		return Jwts.builder().setSubject(clientId).setIssuedAt(new Date())
				.setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME)).signWith(SECRET_KEY).compact();
	}

	public static boolean validateToken(String token) {
		try {
			Jwts.parserBuilder().setSigningKey(SECRET_KEY).build().parseClaimsJws(token);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public static String getClientIdFromToken(String token) {
		return Jwts.parserBuilder().setSigningKey(SECRET_KEY).build().parseClaimsJws(token).getBody().getSubject();
	}
}
