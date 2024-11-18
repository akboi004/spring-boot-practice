package com.example.demo.controller;

import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.EmployeeEntity;
import com.example.demo.serviceImpl.EmployeeService;

@RestController
public class EmployeeController {

	private EmployeeService service;

	public EmployeeController(EmployeeService service) {
		this.service = service;
	}

	@GetMapping("/getAllEmployees")
	public ResponseEntity<List<EmployeeEntity>> getAllEmployees(@RequestParam(defaultValue = "0") Integer pageNo,
			@RequestParam(defaultValue = "10") Integer pageSize, @RequestParam(defaultValue = "id") String sortBy,
			@RequestParam(required = false) String sliceParam) {
		List<EmployeeEntity> employeesLst = service.getAllEmployees(pageNo, pageSize, sortBy, sliceParam);

		return new ResponseEntity<List<EmployeeEntity>>(employeesLst, new HttpHeaders(), HttpStatus.OK);
	}
}
