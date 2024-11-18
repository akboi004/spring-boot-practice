package com.example.demo.repo;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.EmployeeEntity;

@Repository
public interface EmployeeRepo extends PagingAndSortingRepository<EmployeeEntity, Long> {

	Slice<EmployeeEntity> findByFirstName(String firstName, Pageable paging);

}
