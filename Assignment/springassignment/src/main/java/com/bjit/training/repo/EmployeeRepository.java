package com.bjit.training.repo;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.bjit.training.model.Employee;

public interface EmployeeRepository extends CrudRepository<Employee, Integer>{
	public Optional<Employee> findByUserName(String userName);
	public void deleteByUserName(String userName);

}
