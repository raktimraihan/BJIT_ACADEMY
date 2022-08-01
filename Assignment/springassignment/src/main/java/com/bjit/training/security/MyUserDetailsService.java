package com.bjit.training.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.bjit.training.model.Employee;
import com.bjit.training.repo.EmployeeRepository;



@Service
public class MyUserDetailsService implements UserDetailsService{
	
	@Autowired
	private EmployeeRepository eRepo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<Employee> emp =  eRepo.findByUserName(username);
		System.out.println(emp.toString());
		return emp.map(MyUserDetails::new).get();
	}

}
