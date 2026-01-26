package com.hardiksingh.fitnessTracker.dto;

import com.hardiksingh.fitnessTracker.model.User;
import com.hardiksingh.fitnessTracker.model.Workout;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RecommendationResponse {
    private String type;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    private String recommendation;

    private List<String> improvements;

    private List<String> suggestions;

    private List<String> safetyTips;

    private User user;

    private Workout workout;


}
