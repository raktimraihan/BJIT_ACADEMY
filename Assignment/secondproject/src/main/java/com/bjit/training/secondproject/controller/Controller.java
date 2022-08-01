package com.bjit.training.secondproject.controller;

import com.bjit.training.secondproject.model.Student;
import com.bjit.training.secondproject.payload.response.GetStudentResponseAdmin;
import com.bjit.training.secondproject.payload.response.GetStudentResponseUser;
import com.bjit.training.secondproject.payload.response.JwtResponse;
import com.bjit.training.secondproject.payload.response.LoginResponse;
import com.bjit.training.secondproject.repo.StudentRepo;
import com.bjit.training.secondproject.security.JwtUtils;
import com.bjit.training.secondproject.security.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class Controller {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    StudentRepo studentRepo;

    @Autowired
    JwtUtils jwtUtils;

    @RequestMapping("/hello")
    public String getHello(){
        return "Hello World";
    }

    @CrossOrigin("*")
    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginResponse loginResponse){
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginResponse.getUsername(), loginResponse.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        return ResponseEntity.ok(new JwtResponse(jwt,
                userDetails.getUsername(),
                roles));
    }


    @CrossOrigin("*")
    @GetMapping("/getall")
    public ResponseEntity<?> getAllStudentsCustom(){
        List<GetStudentResponseAdmin> getStudentResponsAdmins = new ArrayList<>();
        ArrayList<Student> students = (ArrayList<Student>) studentRepo.findAll();

        for (Student s: students) {
            getStudentResponsAdmins.add(new GetStudentResponseAdmin(s.getId(), s.getRoleOfStudent().getUserName(), s.getName(), s.getAddress(), s.getAge(), s.getRoleOfStudent().getRole().toString()));
        }
        System.out.println(students.toString());
        return  ResponseEntity.ok(getStudentResponsAdmins);
    }

    @CrossOrigin("*")
    @GetMapping("/getallpartial")
    public ResponseEntity<?> getAllStudentsPartial(){
        List<GetStudentResponseUser> getStudentResponsAdmins = new ArrayList<>();
        ArrayList<Student> students = (ArrayList<Student>) studentRepo.findAll();

        for (Student s: students) {
            getStudentResponsAdmins.add(new GetStudentResponseUser(s.getId(), s.getRoleOfStudent().getUserName(), s.getName(), s.getAddress(), s.getRoleOfStudent().getRole().toString()));
        }
        System.out.println(students.toString());
        return  ResponseEntity.ok(getStudentResponsAdmins);
    }
}
