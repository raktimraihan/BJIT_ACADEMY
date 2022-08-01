package com.bjit.training.secondproject.bwtesting;

import com.bjit.training.secondproject.model.RoleOfStudent;
import static org.junit.Assert.assertEquals;

import com.bjit.training.secondproject.model.Student;
import com.bjit.training.secondproject.repo.RoleRepo;
import com.bjit.training.secondproject.repo.StudentRepo;
import com.bjit.training.secondproject.security.JwtUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Iterator;

public class SpringBlackBoxTesting {

    RoleOfStudent roleOfStudent;

    @Autowired
    RoleRepo roleRepo;

    @Autowired
    StudentRepo studentRepo;

    @Before
    public void setUp(){
        System.out.println("Unit test Starting.........");
    }

    @Test
    public void testRoleOfStudent(){
        roleOfStudent = new RoleOfStudent();
        roleOfStudent.setId(54);
        roleOfStudent.setId(60);
        assertEquals("Checking Role ID: ", 60, roleOfStudent.getId());

    }

    @Test
    public void tokenValidation(){
        JwtUtils jwtUtils = new JwtUtils();
        assertEquals("Validate Admin token", true, jwtUtils.validateJwtToken("eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJBXzAxIiwiaWF0IjoxNjU2Mjc1OTUzLCJleHAiOjE2NTYzNjIzNTN9.ereb6eXBS709ueMNTb5yZwOYAdI1bBsiXr4BotAmwEj-t6GqKLQNU5bzvhvbObED3r2WOXZnCK-Ln1B4iLFAdA"));
    }

    @Test
    public void totalStudentObj(){
        Iterator<Student> countList = studentRepo.findAll().iterator();
        int count = 0;
        while(countList.hasNext()){
            count++;
        }

        assertEquals("Total Student Obj Present: ", 3, count);
    }

    @Test
    public void totalRoleObj(){
        Iterator<RoleOfStudent> countList = roleRepo.findAll().iterator();
        int count = 0;
        while(countList.hasNext()){
            count++;
        }

        assertEquals("Total Role Obj Present: ", 3, count);
    }


    @After
    public void tearDown(){
        System.out.println("Unit test Ended.....");
    }
}
