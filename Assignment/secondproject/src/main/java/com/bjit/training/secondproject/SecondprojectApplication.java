package com.bjit.training.secondproject;

import com.bjit.training.secondproject.configuration.DIConfigurator;
import com.bjit.training.secondproject.model.Student;
import com.bjit.training.secondproject.repo.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@SpringBootApplication
public class SecondprojectApplication implements CommandLineRunner {
	@Autowired
	private StudentRepo studentRepo;

	public static void main(String[] args) {
		SpringApplication.run(SecondprojectApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		AnnotationConfigApplicationContext annotationConfigApplicationContext =
				new AnnotationConfigApplicationContext(DIConfigurator.class);

		studentRepo.deleteAll();
		studentRepo.save((Student) annotationConfigApplicationContext.getBean("s-01"));
		studentRepo.save((Student) annotationConfigApplicationContext.getBean("s-02"));
//		studentRepo.delete((Student) annotationConfigApplicationContext.getBean("s-01"));

	}
}
