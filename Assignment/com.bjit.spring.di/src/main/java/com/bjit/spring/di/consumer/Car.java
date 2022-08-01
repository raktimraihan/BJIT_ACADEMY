package com.bjit.spring.di.consumer;

import com.bjit.spring.di.services.EngineMood;

public class Car implements Vehicle{
	private String carName;
	private EngineMood engineType;

	public Car() {
		super();
	}

	public Car(String carName, EngineMood engineType) {
		super();
		this.carName = carName;
		this.engineType = engineType;
	}
	
	public String getVehicleName() {
		return this.carName;
	}

	public EngineMood getEngineType() {
		return this.engineType;
	}

	public void startTheEngine() {
		System.out.println(engineType.turnOn());
	}

	public String getCarName() {
		return carName;
	}

	public void setCarName(String carName) {
		this.carName = carName;
	}

	public void setEngineType(EngineMood engineType) {
		this.engineType = engineType;
	}
	
}
