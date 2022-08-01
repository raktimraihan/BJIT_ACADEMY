package com.bjit.training.secondproject.bwtesting;

import com.bjit.training.secondproject.finalValues.ConstantValue;
import com.bjit.training.secondproject.model.RoleOfStudent;
import com.bjit.training.secondproject.model.Student;
import com.bjit.training.secondproject.payload.response.JwtResponse;
import com.bjit.training.secondproject.repo.RoleRepo;
import com.bjit.training.secondproject.repo.StudentRepo;
import com.bjit.training.secondproject.security.JwtUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Iterator;

import static org.junit.Assert.assertEquals;

public class SpringWhiteBoxTest {

    @Autowired
    RoleRepo roleRepo;

    @Autowired
    StudentRepo studentRepo;



    @Before
    public void setUp(){
        System.out.println("Unit test Starting.........");
    }

    @Test
    public void jwtResponseTest(){
        JwtResponse jwtResponse = new JwtResponse();
        jwtResponse.setTokenType("Bearer");
        assertEquals("Token Type: ", "Bearer", jwtResponse.getTokenType());
    }

    @Test
    public void jwtResponseTest02(){
        JwtResponse jwtResponse = new JwtResponse();
        jwtResponse.setUsername("S-01");
        assertEquals("User Name: ", "S-01", jwtResponse.getUsername());
    }

    @After
    public void tearDown(){
        System.out.println("Unit test Ended.....");
    }

}
