package com.hardiksingh.fitnessTracker.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hardiksingh.fitnessTracker.enums.WorkoutType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.type.SqlTypes;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "workouts")
public class Workout {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private Long caloriesBurned;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    private Integer duration;
    private LocalDateTime startTime;


    @Enumerated(EnumType.STRING)
    private WorkoutType workoutType;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(columnDefinition = "json")
    private Map<String, Object> additionalData;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "uer_id",
            nullable = false,
            foreignKey = @ForeignKey(name = "fk_workout_user")
    )
    @JsonIgnore
    private User user;

    @OneToMany(
            mappedBy = "workout",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @JsonIgnore
    private List<Recommendation> recommendationList = new ArrayList<>();

}













