package com.bjit.spring.di.services;

import org.springframework.stereotype.Component;

@Component("comEngine")
public class CombustionEngine implements EngineMood{

	public String turnOn() {
		return "Combustion Engine Turned ON";
	}
	

}
