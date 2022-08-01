package com.bjit.training;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.bjit.training.configurator.DIConfiguration;
import com.bjit.training.model.Employee;
import com.bjit.training.repo.EmployeeRepository;

@SpringBootApplication
public class SpringassignmentApplication implements CommandLineRunner{
	
	@Autowired
	private EmployeeRepository employeeRepo;
	
	public static void main(String[] args) {
		SpringApplication.run(SpringassignmentApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		AnnotationConfigApplicationContext annotationConfigApplicationContext = new
				AnnotationConfigApplicationContext(DIConfiguration.class);
		
		employeeRepo.deleteAll();
		employeeRepo.save((Employee)annotationConfigApplicationContext.getBean("emp-01"));
		employeeRepo.save((Employee)annotationConfigApplicationContext.getBean("adm-01"));
		employeeRepo.save((Employee)annotationConfigApplicationContext.getBean("user-01"));
		
	}

}
