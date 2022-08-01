package com.example.RESTDemo;

import java.lang.reflect.Method;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
@Aspect
@EnableAspectJAutoProxy
public class BeforeAdvice{

	@Before("execution(* com.example.RESTDemo.MyUserDetailsService.loadUserByUsername(..))")
	public void before(JoinPoint joinPoint) throws Throwable {
		System.out.println("Sfpring is running "+joinPoint.toLongString());
		
	}

}
