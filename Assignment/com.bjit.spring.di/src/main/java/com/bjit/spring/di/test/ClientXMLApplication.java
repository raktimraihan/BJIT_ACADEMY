package com.bjit.spring.di.test;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import com.bjit.spring.di.consumer.Bike;
import com.bjit.spring.di.consumer.Car;
import com.bjit.spring.di.services.Student;

@SuppressWarnings("deprecation")
public class ClientXMLApplication {
	public static void main(String[] args) {
		Resource resource = new ClassPathResource("applicationContext.xml");
		BeanFactory beanFactory = new XmlBeanFactory(resource);
		
		Student student = (Student) beanFactory.getBean("studentbean");
		System.out.println(student.toString());
		
		Car car = (Car) beanFactory.getBean("carbean");
		System.out.println("Vehicle Name: "+car.getVehicleName()+", Engine type="+car.getEngineType().getClass().getSimpleName());
		car.startTheEngine();
		
		Car car01 = (Car) beanFactory.getBean("carbean01");
		System.out.println("Vehicle Name: "+car01.getVehicleName()+", Engine type="+car01.getEngineType().getClass().getSimpleName());
		car01.startTheEngine();
		
		Bike bike = (Bike) beanFactory.getBean("bikebean");
		System.out.println("Vehicle Name: "+bike.getVehicleName()+", Engine type="+bike.getEngineType().getClass().getSimpleName());
		bike.startTheEngine();
	}
}
