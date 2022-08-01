package com.bjit.springJPA.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bjit.springJPA.model.Student;
import com.bjit.springJPA.services.StudentServices;

@RestController
public class StudentController {
	
	@Autowired
	private StudentServices sServices;
	
	@RequestMapping("/")
	public List<Student> getAllStudents(){
		return sServices.getAllStudent();
	}
	
	@RequestMapping(value="/add-user", method = RequestMethod.POST)
	public void addStudent(@RequestBody Student student) {
		sServices.addStudent(student);
	}
}
