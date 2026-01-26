package com.hardiksingh.fitnessTracker.service;

import com.hardiksingh.fitnessTracker.dto.RecommendationRequest;
import com.hardiksingh.fitnessTracker.dto.RecommendationResponse;
import com.hardiksingh.fitnessTracker.model.Recommendation;
import com.hardiksingh.fitnessTracker.model.User;
import com.hardiksingh.fitnessTracker.model.Workout;
import com.hardiksingh.fitnessTracker.repository.RecommendationRepository;
import com.hardiksingh.fitnessTracker.repository.UserRepository;
import com.hardiksingh.fitnessTracker.repository.WorkoutRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecommendationService {

    @Autowired
    private RecommendationRepository recommendationRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private WorkoutRepository workoutRepository;

    public RecommendationResponse generateRecommendations(RecommendationRequest recommendationRequest) {
        User user = userRepository.findById(recommendationRequest.getUserId()).orElseThrow(
                () -> new RuntimeException("Invalid user:" + recommendationRequest.getUserId())
        );

        Workout workout = workoutRepository.findById(recommendationRequest.getWorkoutId()).orElseThrow(
                () -> new RuntimeException("Invalid workout:" + recommendationRequest.getWorkoutId())
        );

        Recommendation recommendation = Recommendation.builder()
                .user(user)
                .workout(workout)
                .improvements(recommendationRequest.getImprovements())
                .improvements(recommendationRequest.getImprovements())
                .safetyTips(recommendationRequest.getSafetyTips())
                .build();

        user.getRecommendationList().add(recommendation);
        userRepository.save(user);

        workout.getRecommendationList().add(recommendation);
        workoutRepository.save(workout);

        Recommendation savedRecommendation = recommendationRepository.save(recommendation);
        return mapToReponse(savedRecommendation);
    }

    private RecommendationResponse mapToReponse(Recommendation savedRecommendation) {
        return RecommendationResponse.builder()
                .type(savedRecommendation.getType())
                .suggestions(savedRecommendation.getSuggestions())
                .improvements(savedRecommendation.getImprovements())
                .safetyTips(savedRecommendation.getSafetyTips())
                .user(savedRecommendation.getUser())
                .workout(savedRecommendation.getWorkout())
                .createdAt(savedRecommendation.getCreatedAt())
                .updatedAt(savedRecommendation.getUpdatedAt())
                .build();
    }

    public List<Recommendation> getUserRecommendation(String userId) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new RuntimeException("Invalid user:" + userId)
        );
        return user.getRecommendationList();
    }

    public List<Recommendation> getWorkoutRecommendation(String workoutId) {
        Workout workout = workoutRepository.findById(workoutId).orElseThrow(
                () -> new RuntimeException("Invalid user:" + workoutId)
        );
        return workout.getRecommendationList();
    }
}
