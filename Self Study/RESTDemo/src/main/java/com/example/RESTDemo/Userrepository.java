package com.example.RESTDemo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface Userrepository extends JpaRepository<User, Integer>{
	
	Optional<User> findByUserName(String username);
	
}
