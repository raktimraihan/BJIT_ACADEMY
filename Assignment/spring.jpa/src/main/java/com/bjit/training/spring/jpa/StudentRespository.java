package com.bjit.training.spring.jpa;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRespository extends CrudRepository<Student, Long>{
	
	List<Student> findByAddress(String address);
	List<Student> findByName(String name);
	List<Student> findByNameAndAddress(String name, String address);
}
