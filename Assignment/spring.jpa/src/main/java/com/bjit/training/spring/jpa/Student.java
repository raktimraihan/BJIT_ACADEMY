package com.bjit.training.spring.jpa;

import jakarta.persistence.Entity;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Student {
	
	@Id
    @jakarta.persistence.GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String name;
    private String address;
    
    public Student() {
		
	}
    
	public Student(String name, String address) {
		super();
		this.name = name;
		this.address = address;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Long getId() {
		return id;
	}
	@Override
	public String toString() {
		return "Student [id=" + id + ", name=" + name + ", Address=" + address + "]";
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	
    
    

}
