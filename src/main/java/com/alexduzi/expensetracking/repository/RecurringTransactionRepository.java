package com.alexduzi.expensetracking.repository;

import com.alexduzi.expensetracking.domain.RecurringTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecurringTransactionRepository extends JpaRepository<Long, RecurringTransaction> {
}
