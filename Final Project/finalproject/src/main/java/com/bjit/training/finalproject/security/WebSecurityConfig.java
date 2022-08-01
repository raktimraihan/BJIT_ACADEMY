package com.bjit.training.finalproject.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    UserServiceImpl userService;

    @Autowired
    AuthEntryPointJWT authEntryPointJWT;

    @Bean
    public AuthTokenFilter getAuthTokenFilter(){
        return new AuthTokenFilter();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService).passwordEncoder(getPasswordEncoder());
    }

    @Bean
    public PasswordEncoder getPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    @Bean
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable()
                .exceptionHandling().authenticationEntryPoint(authEntryPointJWT).and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .authorizeRequests().antMatchers("/profile/helloA").hasAnyRole("ADMIN").and()
                .authorizeRequests().antMatchers("batch/batch_register","batch/*").hasAnyRole("ADMIN").and()
                .authorizeRequests().antMatchers("course/course_register","course/*").hasAnyRole("ADMIN").and()
                .authorizeRequests().antMatchers("trainee/trainee_register").hasAnyRole("ADMIN").and()
                .authorizeRequests().antMatchers("trainer/*").hasAnyRole("ADMIN","MODERATOR").and()
                .authorizeRequests().antMatchers("quiz/**").hasAnyRole("ADMIN","MODERATOR", "USER").and()
                .authorizeRequests().antMatchers("/login").permitAll().and()
                .authorizeRequests().antMatchers("/profile/hello").permitAll().and();

        http.addFilterBefore(getAuthTokenFilter(), UsernamePasswordAuthenticationFilter.class);
    }
}
