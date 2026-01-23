package com.hardiksingh.fitnessTracker.dto;

import com.hardiksingh.fitnessTracker.enums.WorkoutType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Map;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WorkoutRequest {
    private String userId;
    private WorkoutType workoutType;
    private Long caloriesBurned;
    private Map<String, Object> additionalData;
    private Integer duration;
    private LocalDateTime startTime;
}
