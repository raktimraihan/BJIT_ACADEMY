package com.bjit.training.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SuppressWarnings("deprecation")
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{
	
	@Autowired
	UserDetailsService userDetailsService;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService);
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();
		http.authorizeRequests()
		
		.antMatchers("/add/**").hasAnyRole("ADMIN", "EMP")
		.antMatchers("/update/**").hasAnyRole("ADMIN", "EMP")
		.antMatchers("/view/**").hasAnyRole("ADMIN", "EMP")
		.antMatchers("/deleteid/{id}", "/deleteid","/deleteid/**").hasAnyRole("ADMIN","DUMMY")
		.antMatchers("/viewall/**").hasAnyRole("ADMIN", "EMP", "USER")
		.antMatchers("/","/login", "static/css", "static/js").permitAll()
		.and().formLogin();
	}
	
	@Bean
	public PasswordEncoder getPassEncoder() {
		return NoOpPasswordEncoder.getInstance();
	}
}

