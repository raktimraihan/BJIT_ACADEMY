package com.bjit.spring.di.services;

public class Student {
	private String Name;

	public Student(String name) {
		super();
		Name = name;
	}

	public String getName() {
		return Name;
	}


	@Override
	public String toString() {
		return "Student [Name=" + Name + "]";
	}
	
}
