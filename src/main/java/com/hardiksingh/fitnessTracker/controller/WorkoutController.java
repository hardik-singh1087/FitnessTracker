package com.hardiksingh.fitnessTracker.controller;

import com.hardiksingh.fitnessTracker.dto.WorkoutReponse;
import com.hardiksingh.fitnessTracker.dto.WorkoutRequest;
import com.hardiksingh.fitnessTracker.model.Workout;
import com.hardiksingh.fitnessTracker.service.WorkoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/workouts")
public class WorkoutController {

    @Autowired
    private WorkoutService workoutService;

    @PostMapping("/track-workout")
    public WorkoutReponse trackWorkout(@RequestBody WorkoutRequest workoutRequest){

        return workoutService.trackWorkout(workoutRequest);
    }

    @GetMapping("/retrieve-workout")
    public List<WorkoutReponse> getUserWorkouts(@RequestHeader(value = "X-User-ID") String userId){

        return workoutService.getUserWorkouts(userId);
    }


}
