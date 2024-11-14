package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.service.BackendAdapter;

@RestController
public class BackendAdapterController {

	@Autowired
	BackendAdapter backendAdapter;

	@GetMapping("/retry")
	public String retryEndpoint(@RequestParam String param1, @RequestParam String param2) {
		return backendAdapter.getBackendResponse(param1, param2);
	}

}
