package com.expensive.api.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.expensive.api.services.UserService;


import org.springframework.beans.factory.annotation.Autowired;



@RestController
@RequestMapping(path = "/api/users")
public class UserController {

    private final UserService userService;

    @Autowired
    UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(path = "/welcome")
    public String welocmeTest() {
        return new String("The backend is initialized");
    }

    @GetMapping(path = "/user")
    public String getAllUsers() {
        return "List of all users";
    }
    

}
