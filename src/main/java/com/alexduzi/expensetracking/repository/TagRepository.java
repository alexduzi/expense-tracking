package com.alexduzi.expensetracking.repository;

import com.alexduzi.expensetracking.domain.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TagRepository extends JpaRepository<Tag, Long> {
    Optional<Tag> findTagByNameEqualsIgnoreCase(String name);
}
