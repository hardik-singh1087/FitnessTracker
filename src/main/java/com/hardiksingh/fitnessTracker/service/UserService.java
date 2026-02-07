package com.hardiksingh.fitnessTracker.service;

import com.hardiksingh.fitnessTracker.dto.RegisterRequest;
import com.hardiksingh.fitnessTracker.dto.UserResponse;
import com.hardiksingh.fitnessTracker.enums.Role;
import com.hardiksingh.fitnessTracker.model.User;
import com.hardiksingh.fitnessTracker.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserResponse register(RegisterRequest registerRequest) {

        Role role = registerRequest.getRole() != null ? registerRequest.getRole() : Role.USER;

        User user = User.builder()
                .email(registerRequest.getEmail())
                .firstName(registerRequest.getFirstName())
                .lastName(registerRequest.getLastName())
                .password(passwordEncoder.encode(registerRequest.getPassword()))
                .role(role)
                .build();

        User savedUser = userRepository.save(user);
        return mapToResponse(savedUser);
    }

    public UserResponse mapToResponse(User savedUser) {
        return UserResponse.builder()
                .id(savedUser.getId())
                .email(savedUser.getEmail())
                .password(savedUser.getPassword())
                .firstName(savedUser.getFirstName())
                .lastName(savedUser.getLastName())
                .createdAt(savedUser.getCreatedAt())
                .updatedAt(savedUser.getUpdatedAt())
                .build();
    }

}
