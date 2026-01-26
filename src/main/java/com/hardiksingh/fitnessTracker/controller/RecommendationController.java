package com.hardiksingh.fitnessTracker.controller;


import com.hardiksingh.fitnessTracker.dto.RecommendationRequest;
import com.hardiksingh.fitnessTracker.dto.RecommendationResponse;
import com.hardiksingh.fitnessTracker.model.Recommendation;
import com.hardiksingh.fitnessTracker.service.RecommendationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/recommendations")
public class RecommendationController {

    @Autowired
    private RecommendationService recommendationService;

    @PostMapping("/generate")
    public ResponseEntity<RecommendationResponse> generateRecommendation(@RequestBody RecommendationRequest recommendationRequest){

        return new ResponseEntity<>(recommendationService.generateRecommendations(recommendationRequest), HttpStatus.OK);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Recommendation>> getUserRecommendation(@PathVariable String userId){
        return new ResponseEntity<>(recommendationService.getUserRecommendation(userId), HttpStatus.OK);
    }

    @GetMapping("/workout/{workoutId}")
    public ResponseEntity<List<Recommendation>> getActivityRecommendation(@PathVariable String workoutId){

        return new ResponseEntity<>(recommendationService.getWorkoutRecommendation(workoutId), HttpStatus.OK);
    }
}