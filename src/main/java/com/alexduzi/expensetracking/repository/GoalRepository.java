package com.alexduzi.expensetracking.repository;

import com.alexduzi.expensetracking.domain.Goal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GoalRepository extends JpaRepository<Long, Goal> {
}
