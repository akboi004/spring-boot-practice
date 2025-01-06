package com.example.demo.reactiveProg;

import org.junit.jupiter.api.Test;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

class WebMonoFluxTest {

	@Test
	void testMono() {
		Mono<String> monoString = Mono.just("Spring Boot").log().then(Mono.error(new RuntimeException()));
		monoString.subscribe(System.out::println);
	}

	@Test
	void testFlux() {
		Flux<String> fluxString = Flux.just("Spring Tool Suite", "Spring Boot", "Microservices").log();
		fluxString.subscribe(System.out::println);
	}
}
