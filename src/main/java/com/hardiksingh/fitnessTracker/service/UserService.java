package com.hardiksingh.fitnessTracker.service;

import com.hardiksingh.fitnessTracker.dto.RegisterRequest;
import com.hardiksingh.fitnessTracker.dto.UserResponse;
import com.hardiksingh.fitnessTracker.model.User;
import com.hardiksingh.fitnessTracker.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public UserResponse register(RegisterRequest registerRequest) {
        User user = User.builder()
                .email(registerRequest.getEmail())
                .firstName(registerRequest.getFirstName())
                .lastName(registerRequest.getLastName())
                .password(registerRequest.getPassword())
                .build();

        User savedUser = userRepository.save(user);
        return mapToResponse(savedUser);
    }

    private UserResponse mapToResponse(User savedUser) {
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
