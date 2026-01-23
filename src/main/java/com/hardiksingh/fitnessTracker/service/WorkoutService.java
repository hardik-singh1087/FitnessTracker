package com.hardiksingh.fitnessTracker.service;


import com.hardiksingh.fitnessTracker.dto.WorkoutReponse;
import com.hardiksingh.fitnessTracker.dto.WorkoutRequest;
import com.hardiksingh.fitnessTracker.model.User;
import com.hardiksingh.fitnessTracker.model.Workout;
import com.hardiksingh.fitnessTracker.repository.UserRepository;
import com.hardiksingh.fitnessTracker.repository.WorkoutRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class WorkoutService {

    @Autowired
    private WorkoutRepository workoutRepository;

    @Autowired
    private UserRepository userRepository;

    public WorkoutReponse trackWorkout(WorkoutRequest workoutRequest) {

        User user = userRepository.findById(workoutRequest.getUserId())
                .orElseThrow(() -> new RuntimeException("Invalid user:" + workoutRequest.getUserId()));

        Workout workout = Workout.builder()
                .caloriesBurned(workoutRequest.getCaloriesBurned())
                .duration((workoutRequest.getDuration()))
                .startTime(workoutRequest.getStartTime())
                .workoutType(workoutRequest.getWorkoutType())
                .additionalData(workoutRequest.getAdditionalData())
                .user(user)
                .build();
        Workout savedWorkout = workoutRepository.save(workout);
        return mapToReponse(savedWorkout);
    }

    private WorkoutReponse mapToReponse(Workout savedWorkout) {
        return WorkoutReponse.builder()
                .id(savedWorkout.getId())
                .updatedAt(savedWorkout.getUpdatedAt())
                .userId(savedWorkout.getUser().getId())
                .additionalData(savedWorkout.getAdditionalData())
                .createdAt(savedWorkout.getCreatedAt())
                .workoutType(savedWorkout.getWorkoutType())
                .startTime(savedWorkout.getStartTime())
                .duration(savedWorkout.getDuration())
                .caloriesBurned(savedWorkout.getCaloriesBurned())
                .build();
    }

    public List<WorkoutReponse> getUserWorkouts(String userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Invalid user:" + userId));
        List<WorkoutReponse> workoutReponseList = new ArrayList<>();
        List<Workout> workoutList = user.getWorkoutList();
        for(Workout workout: workoutList){
            workoutReponseList.add(mapToReponse(workout));
        }
        return workoutReponseList;
    }
}
