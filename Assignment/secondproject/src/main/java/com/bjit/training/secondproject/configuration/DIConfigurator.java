package com.bjit.training.secondproject.configuration;

import com.bjit.training.secondproject.finalValues.ConstantValue;
import com.bjit.training.secondproject.model.RoleOfStudent;
import com.bjit.training.secondproject.model.Student;
import com.bjit.training.secondproject.security.WebSecurityConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class DIConfigurator {


    PasswordEncoder encoder = new WebSecurityConfig().passwordEncoder();

    @Bean("s-01")
    public Student getFirstStudent() {
        return new Student("Raktim", "Dhaka", 26, new RoleOfStudent(ConstantValue.ROLE_ADMIN, encoder.encode("1234"), "A_01"));
    }

    @Bean("s-02")
    public Student getSecondStudent() {
        return new Student("Ruksana", "Barishal", 23, new RoleOfStudent(ConstantValue.ROLE_STUDENT, encoder.encode("1234"),"S_01"));
    }
}
