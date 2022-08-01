package com.bjit.spring.di.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.bjit.spring.di.consumer.AutoRickshaw;
import com.bjit.spring.di.consumer.Bike;
import com.bjit.spring.di.services.CombustionEngine;
import com.bjit.spring.di.services.ElectricalEngine;

@Configuration
@ComponentScan(value= {"com.bjit.spring.di"})
public class DIConfiguration {

	@Bean(name="bikeDI01")
	public Bike getBike() {
		return new Bike("Yamaha FZ-X", new CombustionEngine());
	}
	
	@Bean(name="AutoDI01")
	public AutoRickshaw getAuto() {
		return new AutoRickshaw("Tuk-Tuk", new ElectricalEngine());
	}
}
