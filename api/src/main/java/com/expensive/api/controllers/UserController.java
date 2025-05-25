package com.expensive.api.controllers;

import com.expensive.api.dto.UserDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.expensive.api.services.UserService;


import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


@RestController
@RequestMapping(path = "/api/user")
public class UserController {

    private final UserService userService;

    @Autowired
    UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(path = "/")
    public ResponseEntity<List<UserDto>> getAllUsers() {
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable  long id) {
        return new ResponseEntity<>(userService.getUserById(id), HttpStatus.OK);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<UserDto> updateUser(@PathVariable long id, @RequestBody UserDto userDto) {
        return new ResponseEntity<>(userService.updateUser(id, userDto), HttpStatus.OK);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<UserDto> deleteUser(@PathVariable long id) {
        UserDto userdto = userService.deleteUser(id);
        return new ResponseEntity<>(userdto, HttpStatus.OK);
    }

}
