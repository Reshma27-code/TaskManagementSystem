package com.example.TMS.controller;

import com.example.TMS.JPARepositories.UserRepository;
import com.example.TMS.entities.User;
import com.example.TMS.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    @PostMapping("/register")
    public User registerUser(@RequestBody User user){
        user.setPassword(encoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @PostMapping("/login")
    public String loginUser(@RequestBody User user){
         return userService.verify(user);

    }
}
