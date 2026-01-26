package com.hardiksingh.fitnessTracker.dto;

import com.hardiksingh.fitnessTracker.model.User;
import com.hardiksingh.fitnessTracker.model.Workout;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RecommendationRequest {
    private String userId;
    private String workoutId;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    private List<String> improvements;
    private List<String> suggestions;
    private List<String> safetyTips;
}

