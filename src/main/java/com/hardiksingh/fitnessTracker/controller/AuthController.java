package com.hardiksingh.fitnessTracker.controller;

import com.hardiksingh.fitnessTracker.dto.LoginReponse;
import com.hardiksingh.fitnessTracker.dto.LoginRequest;
import com.hardiksingh.fitnessTracker.dto.RegisterRequest;
import com.hardiksingh.fitnessTracker.dto.UserResponse;
import com.hardiksingh.fitnessTracker.model.User;
import com.hardiksingh.fitnessTracker.repository.UserRepository;
import com.hardiksingh.fitnessTracker.security.JwtUtils;
import com.hardiksingh.fitnessTracker.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtils jwtUtils;

    @PostMapping("/register")
    public ResponseEntity<UserResponse> registerUser(@RequestBody RegisterRequest registerRequest) {
        return new ResponseEntity<>(userService.register(registerRequest), HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody LoginRequest loginRequest) {
        return new ResponseEntity<>(userService.login(loginRequest), HttpStatus.OK);
    }

}
