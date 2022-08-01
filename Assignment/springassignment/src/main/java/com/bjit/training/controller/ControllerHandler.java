package com.bjit.training.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import com.bjit.training.model.Employee;
import com.bjit.training.repo.EmployeeRepository;

import jakarta.transaction.Transactional;

@Transactional
@Controller
public class ControllerHandler {
	
	@Autowired
	EmployeeRepository eRepo;
	
	@GetMapping("/")
	public String getHomePage() {
		return "index";
	}
	
	@GetMapping("/add")
	public String getAdd(Model model) {
		Employee employee = new Employee();
		model.addAttribute("employee",employee);
		System.out.println("User+"+employee.toString());
		return "add";
	}
	@PostMapping("/add")
	public String submitForm(@ModelAttribute("employee") Employee user) throws Exception {
	    System.out.println(user.toString());
	    
	    if(ResponseEntity.ok() != null)
	    try {
	    	if(eRepo.findByUserName(user.getUserName()).isPresent() ||
	    			(user.getUserName()=="" || user.getName() == "" || user.getPassword()==""|| user.getRole()=="")) {
		    	throw new Exception();
		    }
	    	else {
	    		eRepo.save(user);
	    	    return "register_success";
	    	}
	    }
	    catch(Exception ex) {
	    	System.out.println("User Registration Failed. "
	    			+ "Cause: User Exists or Some required field is empty.");
	    }
	    return "error";
	        
	}
	
	@GetMapping("/view")
	public String getView(Model model){
		model.addAttribute("empList", eRepo.findAll() );
		return "view";
	}
	
	@GetMapping("/viewall")
	public String getViewall(Model model){
		model.addAttribute("empList", eRepo.findAll() );
		return "viewall";
	}
	
	@GetMapping("/modify")
	public String getUpdate() {
		return "update";
	}
	
	@GetMapping("/deleteid/{id}")
	public String getDelete(@PathVariable String id) {
		System.out.println("List+++> "+id);
		Optional<Employee> emp = eRepo.findByUserName(id);
		System.out.println("List+++> "+emp.toString());
		eRepo.deleteById(emp.get().getId());
		return "redirect:/viewall";
		
	}
	
	@GetMapping("/edit/{id}")
	public String getEditEmp(@PathVariable String id, Model model) {
		Optional<Employee> emp = eRepo.findByUserName(id);
		model.addAttribute("employeeNew", emp);
		return "edit";
	}
	
	
	@PostMapping("/edit")
	public String updateEmp(@ModelAttribute("employeeNew") Employee emp) {
		Optional<Employee> existingEmployee = eRepo.findByUserName(emp.getUserName());
		existingEmployee.get().setName(emp.getName());
		existingEmployee.get().setAddress(emp.getAddress());
		existingEmployee.get().setDateOfJoining(emp.getDateOfJoining());
		existingEmployee.get().setRole(emp.getRole());
		eRepo.saveAll(existingEmployee.stream().toList());
		//eRepo.save(null);
		return "update_success";
	}
}
