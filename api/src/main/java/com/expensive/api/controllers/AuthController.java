package com.expensive.api.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.expensive.api.dto.LoginReqDto;
import com.expensive.api.dto.LoginResDto;
import com.expensive.api.dto.MessageDto;
import com.expensive.api.dto.RegisterReqDto;
import com.expensive.api.services.AuthenticationService;
import com.expensive.api.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;




@RestController
@RequestMapping(path = "/api/auth")
public class AuthController {
    
    private final AuthenticationService authenticationService;
    private final UserService userService;

    @Autowired
    public AuthController(AuthenticationService authenticationService, UserService userService) {
        this.authenticationService = authenticationService;
        this.userService = userService;
    }

    @PostMapping(path = "/register")
    public ResponseEntity<String> registerUserWith(@RequestBody RegisterReqDto registerReqDto) {
        boolean isRegistered = authenticationService.registerUser(registerReqDto);
        if(isRegistered) {
            String message = new String("Successfully registerd the new user");
            return new ResponseEntity<> (message, HttpStatus.CREATED);
        }    
        else 
            throw new RuntimeException("User Service Error");
    }   

    @GetMapping(path = "/login")
    public LoginResDto loginUser(@RequestBody LoginReqDto loginReqDto) {
        return authenticationService.loginUser(loginReqDto);        
    }


    @PutMapping(path = "/verify")
    public ResponseEntity<MessageDto> verifyUser(@RequestParam("username") String username) {
        return new ResponseEntity<> (userService.verifyUser(username), HttpStatus.ACCEPTED);
    }

    @GetMapping(path = "/enable")
    public ResponseEntity<MessageDto> verifyUserToken(@RequestParam("token") String token, @RequestParam("email") String email) {
        return new ResponseEntity<> (userService.enableUser(email, token), HttpStatus.OK);
    }
    
}
