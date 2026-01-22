package com.hardiksingh.fitnessTracker.repository;

import com.hardiksingh.fitnessTracker.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
}
