package com.alexduzi.expensetracking.repository;

import com.alexduzi.expensetracking.domain.Budget;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BudgetRepository extends JpaRepository<Long, Budget> {
}
