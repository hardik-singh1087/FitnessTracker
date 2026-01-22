package com.hardiksingh.fitnessTracker.repository;

import com.hardiksingh.fitnessTracker.model.Recommendation;
import org.hibernate.dialect.MySQLStorageEngine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecommendationRepository extends JpaRepository<Recommendation, String> {
}
