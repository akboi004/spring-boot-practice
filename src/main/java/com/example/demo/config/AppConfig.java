package com.example.demo.config;

import java.util.Locale;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;

@Configuration
public class AppConfig {

	/* For i18n implementation */
	@Bean
	ReloadableResourceBundleMessageSource messageSource() {
		ReloadableResourceBundleMessageSource mesSor = new ReloadableResourceBundleMessageSource();
		mesSor.setBasename("classpath:messages");
		mesSor.setCacheSeconds(3600);
		mesSor.setDefaultEncoding("UTF-8");
		mesSor.setFallbackToSystemLocale(false);
		return mesSor;
	}

	@Bean
	LocaleResolver localeResolver() {
		AcceptHeaderLocaleResolver lr = new AcceptHeaderLocaleResolver();
		lr.setDefaultLocale(Locale.US);
		return lr;
	}
}
