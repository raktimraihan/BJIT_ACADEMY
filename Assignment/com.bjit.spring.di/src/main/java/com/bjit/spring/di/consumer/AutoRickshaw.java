package com.bjit.spring.di.consumer;

import org.springframework.stereotype.Component;

import com.bjit.spring.di.services.EngineMood;

public class AutoRickshaw implements Vehicle{
	private String vName;
	private EngineMood engineType;

	public AutoRickshaw() {
		super();
	}

	public AutoRickshaw(String vName, EngineMood engineType) {
		super();
		this.vName = vName;
		this.engineType = engineType;
	}

	public String getVehicleName() {
		return this.vName;
	}

	public EngineMood getEngineType() {
		return this.engineType;
	}

	public void startTheEngine() {
		System.out.println(engineType.turnOn());
	}

	public String getvName() {
		return vName;
	}

	public void setvName(String vName) {
		this.vName = vName;
	}

	public void setEngineType(EngineMood engineType) {
		this.engineType = engineType;
	}
	
}
