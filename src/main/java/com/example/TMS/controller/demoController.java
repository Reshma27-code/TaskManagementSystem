package com.example.TMS.controller;

import com.example.TMS.JPARepositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class demoController {
    @Autowired
    UserRepository userRepository;

    @GetMapping("/")
    public String greet(){
        return "Hello World";
    }
}
