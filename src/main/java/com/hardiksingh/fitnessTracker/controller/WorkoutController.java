package com.hardiksingh.fitnessTracker.controller;

import com.hardiksingh.fitnessTracker.dto.WorkoutReponse;
import com.hardiksingh.fitnessTracker.dto.WorkoutRequest;
import com.hardiksingh.fitnessTracker.service.WorkoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/workouts")
public class WorkoutController {

    @Autowired
    private WorkoutService workoutService;

    @PostMapping("/track-workout")
    public ResponseEntity<WorkoutReponse> trackWorkout(@RequestBody WorkoutRequest workoutRequest){

        return new ResponseEntity<>(workoutService.trackWorkout(workoutRequest),HttpStatus.OK);
    }

    @GetMapping("/retrieve-workout")
    public List<WorkoutReponse> getUserWorkouts(@RequestHeader(value = "X-User-ID") String userId){

        return workoutService.getUserWorkouts(userId);
    }


}
