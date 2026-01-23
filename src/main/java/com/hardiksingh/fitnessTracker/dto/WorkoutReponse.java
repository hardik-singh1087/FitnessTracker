package com.hardiksingh.fitnessTracker.dto;

import com.hardiksingh.fitnessTracker.enums.WorkoutType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import java.time.LocalDateTime;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WorkoutReponse {

    private String id;
    private String userId;
    private WorkoutType workoutType;
    private Long caloriesBurned;
    private Map<String, Object> additionalData;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    private Integer duration;
    private LocalDateTime startTime;

}
