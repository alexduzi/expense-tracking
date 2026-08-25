package com.alexduzi.expensetracking.repository;

import com.alexduzi.expensetracking.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    Optional<Category> findByNameEqualsIgnoreCase(String name);
}
