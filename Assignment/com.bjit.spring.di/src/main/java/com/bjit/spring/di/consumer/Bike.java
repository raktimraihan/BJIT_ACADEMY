package com.bjit.spring.di.consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.bjit.spring.di.services.EngineMood;

@Component("bike")
public class Bike implements Vehicle{
	private String bikeName;
	@Autowired
	@Qualifier("comEngine")
	private EngineMood engineType;
	
	public Bike() {
		super();
	}

	public Bike(String bikeName, EngineMood engineType) {
		super();
		this.bikeName = bikeName;
		this.engineType = engineType;
	}

	public String getVehicleName() {
		return this.bikeName;
	}

	public EngineMood getEngineType() {
		return this.engineType;
	}

	public void startTheEngine() {
		System.out.println(this.engineType.turnOn());
	}

	public String getBikeName() {
		return bikeName;
	}

	public void setBikeName(String bikeName) {
		this.bikeName = bikeName;
	}
	
	public void setEngineType(EngineMood engineType) {
		this.engineType = engineType;
	}
	
}
