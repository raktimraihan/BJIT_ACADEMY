package com.bjit.training.finalproject.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/profile/*")
public class ProfileController {

    @GetMapping("/hello")
    public String getHello(){
        return "Hello World";
    }

}
