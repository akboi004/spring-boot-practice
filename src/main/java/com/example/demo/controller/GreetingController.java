package com.example.demo.controller;

import java.util.Locale;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

/* For i18n implementation */
@RestController
public class GreetingController {

	private final MessageSource messageSource;

	public GreetingController(MessageSource messageSource) {
		this.messageSource = messageSource;
	}

	@GetMapping("/api/greet/{username}")
	public String greet(@PathVariable String username,
			@RequestHeader(name = "Accept-Language", required = false) Locale locale) {
		String greeting = messageSource.getMessage("greeting.message", new Object[] { username }, "Default Greeting",
				locale);
		return String.format("Locale: %s, Message: %s", locale, greeting);
	}

}
