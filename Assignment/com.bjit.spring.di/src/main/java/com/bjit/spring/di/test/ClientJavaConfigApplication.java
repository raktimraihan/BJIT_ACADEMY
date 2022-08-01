package com.bjit.spring.di.test;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.bjit.spring.di.configuration.DIConfiguration;
import com.bjit.spring.di.consumer.AutoRickshaw;
import com.bjit.spring.di.consumer.Bike;

public class ClientJavaConfigApplication {
	
	public static void main(String[] args) {
		AnnotationConfigApplicationContext aAppConn = new AnnotationConfigApplicationContext(DIConfiguration.class);
		
		Bike bike = (Bike) aAppConn.getBean("bikeDI01");
		System.out.println("Vehicle Name: "+bike.getVehicleName()+", Engine type="+bike.getEngineType().getClass().getSimpleName());
		bike.startTheEngine();
		
		AutoRickshaw auto = (AutoRickshaw) aAppConn.getBean("AutoDI01");
		System.out.println("Vehicle Name: "+auto.getVehicleName()+", Engine type="+auto.getEngineType().getClass().getSimpleName());
		auto.startTheEngine();
	}
}
