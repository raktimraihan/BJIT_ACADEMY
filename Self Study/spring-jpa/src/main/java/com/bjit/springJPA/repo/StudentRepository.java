package com.bjit.springJPA.repo;

import org.springframework.data.repository.CrudRepository;

import com.bjit.springJPA.model.Student;


public interface StudentRepository extends CrudRepository<Student, String>{
	
}
