package com.hardiksingh.fitnessTracker.repository;

import com.hardiksingh.fitnessTracker.model.Workout;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkoutRepository extends JpaRepository<Workout, String> {
}
