package com.alexduzi.expensetracking.repository;

import com.alexduzi.expensetracking.domain.Budget;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BudgetRepository extends JpaRepository<Budget, Long> {
    Optional<Budget> findByDescriptionEqualsIgnoreCase(String description);
}
