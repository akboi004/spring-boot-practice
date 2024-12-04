package com.example.demo.controller;

import java.util.Date;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SecuredServerController {

	@GetMapping("/secured")
	public String secured() {
		System.out.println("Inside secured()");
		return "Hello user !!! : " + new Date();
	}
}
