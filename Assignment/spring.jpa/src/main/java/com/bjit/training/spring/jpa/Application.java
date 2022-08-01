package com.bjit.training.spring.jpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SpringBootApplication
public class Application implements CommandLineRunner{
	private static final Logger log = LoggerFactory.getLogger(Application.class);
	@Autowired
	private StudentRespository sRespository;
	
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		log.info("StartApplication...");
		System.out.println("----------------------------------");
		System.out.println("Deleting Previous Entry");
		sRespository.deleteAll();
		
		sRespository.save(new Student("Hasan", "Dhaka"));
		sRespository.save(new Student("Masud", "Rajshahi"));
		sRespository.save(new Student("Tonmoy", "Baridhara"));
		
		System.out.println("\nfindAll");
        sRespository.findAll().forEach(x -> System.out.println(x));
        
		System.out.println("\nfindAll() by ID");
        sRespository.findById(1l).ifPresent(x -> System.out.println(x));
        
        System.out.println("\nfindAll() by Address");
        sRespository.findByAddress("Rajshahi").forEach(x -> System.out.println(x));
        
        System.out.println("\nfindAll() by Name");
        sRespository.findByName("Hasan").forEach(x -> System.out.println(x));
        
        System.out.println("\nfindAll() by Name and Address");
        sRespository.findByNameAndAddress("Tonmoy", "Baridhara").forEach(x -> System.out.println(x));
		
		
	}

}
 