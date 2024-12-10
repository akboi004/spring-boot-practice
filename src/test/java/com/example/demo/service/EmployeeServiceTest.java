package com.example.demo.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.example.demo.entity.EmployeeEntity;
import com.example.demo.repo.EmployeeRepo;
import com.example.demo.serviceImpl.EmployeeService;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceTest {

	@InjectMocks
	EmployeeService employeeService;

	@Mock
	EmployeeRepo employeeRepo;

	@Test
	void getAllEmployeesTest() {
		EmployeeEntity employee = new EmployeeEntity();
		employee.setId(1L);
		employee.setFirstName("Alwin");
		employee.setLastName("Kurian");
		employee.setEmail("imalwinkurian4@gmail.com");

		List<EmployeeEntity> employeelst = new ArrayList<>();
		employeelst.add(employee);

		Pageable pageable = PageRequest.of(0, 2, Sort.by("firstName"));

		// Mock the Page response for findAll
		Page<EmployeeEntity> pagedResponse = new PageImpl<>(employeelst, pageable, employeelst.size());

		// Return the mocked Page when the repo method is called
		when(employeeRepo.findAll(pageable)).thenReturn(pagedResponse);

		// Call the service method
		List<EmployeeEntity> response = employeeService.getAllEmployees(0, 2, "firstName", null);

		// Assertions
		assertEquals(1, response.size());
		verify(employeeRepo, times(1)).findAll(pageable);
	}
}
