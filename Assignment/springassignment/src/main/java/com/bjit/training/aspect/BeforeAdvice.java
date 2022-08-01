package com.bjit.training.aspect;

import java.lang.reflect.Method;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
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

	@Before("execution(* com.bjit.training.security.MyUserDetailsService.loadUserByUsername(..))")
	public void before(JoinPoint joinPoint) throws Throwable {
		System.out.println("User Logging details checking... "+joinPoint.toLongString());
		
	}
	
	@After("execution(* com.bjit.training.security.MyUserDetailsService.loadUserByUsername(..))")
	public void after(JoinPoint joinPoint) throws Throwable {
		System.out.println("Operation performed successfully. "+joinPoint.toLongString());
		
	}
	
	@Before("execution(* com.bjit.training.controller.ControllerHandler.getDelete(..))")
	public void beforeController(JoinPoint joinPoint) {
		System.out.println("Controller... "+joinPoint.toLongString());
	}
	
	@After("execution(* com.bjit.training.controller.ControllerHandler.getDelete(..))")
	public void afterController(JoinPoint joinPoint) {
		System.out.println("Controller... "+joinPoint.toLongString());
	}

}
