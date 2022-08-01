package com.bjit.springJPA.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.bjit.springJPA.model.Student;
import com.bjit.springJPA.repo.StudentRepository;

public class StudentServices {
	
	@Autowired
	public StudentRepository sRepository;
	public List<Student> getAllStudent(){
		List<Student> list = new ArrayList<>();
		sRepository.findAll().forEach(list::add);
		return list;
	}
	
	public void addStudent(Student student) {
		sRepository.save(student);
	}
}
 