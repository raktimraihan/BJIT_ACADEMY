package com.bjit.training.configurator;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.bjit.training.model.Employee;

@Configuration
public class DIConfiguration {
	 public static Date parseDate(String date) {
	     try {
	         return new SimpleDateFormat("yyyy-MM-dd").parse(date);
	     } catch (ParseException e) {
	         return null;
	     }
	  }
	 
	@Bean("user-01")
	public Employee getUser01() {
		return new Employee("r_12","Raktim","Dhaka","2018-02-14","1234","ROLE_USER");
	}
	
	@Bean("adm-01")
	public Employee getAdm01() {
		return new Employee("r_123","Raihan","Dhaka","2020-02-14","1234","ROLE_ADMIN");
	}
	
	@Bean("emp-01")
	public Employee getEmp01() {
		return new Employee("r_1234","Rahat","Dhaka","2021-02-14","1234","ROLE_EMP");
	}
}
