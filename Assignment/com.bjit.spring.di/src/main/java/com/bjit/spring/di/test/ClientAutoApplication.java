package com.bjit.spring.di.test;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.bjit.spring.di.configuration.DIConfiguration;
import com.bjit.spring.di.consumer.Bike;
import com.bjit.spring.di.services.ElectricalEngine;

public class ClientAutoApplication {
	public static void main(String[] args) {
		AnnotationConfigApplicationContext aAppConn = new AnnotationConfigApplicationContext(DIConfiguration.class);
		
		Bike bike = (Bike) aAppConn.getBean("bike");
		bike.setBikeName("Honda CSX-750");
		//bike.setEngineType(new ElectricalEngine());
		System.out.println("Vehicle Name: "+bike.getVehicleName()+", Engine type="+bike.getEngineType().getClass().getSimpleName());
		bike.startTheEngine();
	}
}
