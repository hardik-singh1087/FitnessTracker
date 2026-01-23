package com.hardiksingh.fitnessTracker.controller;

import com.hardiksingh.fitnessTracker.dto.RegisterRequest;
import com.hardiksingh.fitnessTracker.dto.UserResponse;
import com.hardiksingh.fitnessTracker.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<UserResponse> registerUser(@RequestBody RegisterRequest registerRequest){

        return new ResponseEntity<>(userService.register(registerRequest), HttpStatus.OK);
    }

}
