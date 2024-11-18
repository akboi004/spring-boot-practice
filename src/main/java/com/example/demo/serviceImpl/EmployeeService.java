package com.example.demo.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.demo.entity.EmployeeEntity;
import com.example.demo.repo.EmployeeRepo;

@Service
public class EmployeeService {

	private EmployeeRepo repo;

	public EmployeeService(EmployeeRepo repo) {
		this.repo = repo;
	}

	public List<EmployeeEntity> getAllEmployees(Integer pageNo, Integer pageSize, String sortBy, String sliceParam) {

		Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));

		Page<EmployeeEntity> pagedResult = repo.findAll(paging);

		if (sliceParam != null) {
			Slice<EmployeeEntity> slicedResult = repo.findByFirstName(sliceParam, paging);

			return slicedResult.getContent();
		} else {
			return pagedResult.getContent();
		}

	}
}
