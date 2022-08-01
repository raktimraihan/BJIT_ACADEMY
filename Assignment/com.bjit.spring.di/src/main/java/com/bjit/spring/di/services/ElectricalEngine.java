package com.bjit.spring.di.services;

import org.springframework.stereotype.Component;

@Component("elecEngine")
public class ElectricalEngine implements EngineMood{

	public String turnOn() {
		return "Electrical Engine Switched on";
	}

	
}
