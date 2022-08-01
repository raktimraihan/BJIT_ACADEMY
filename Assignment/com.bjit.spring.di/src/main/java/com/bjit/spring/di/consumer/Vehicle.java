package com.bjit.spring.di.consumer;

import com.bjit.spring.di.services.EngineMood;

public interface Vehicle {
	public String getVehicleName();
	public EngineMood getEngineType();
	public void startTheEngine();
}
