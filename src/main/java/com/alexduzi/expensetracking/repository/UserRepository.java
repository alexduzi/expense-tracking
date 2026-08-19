package com.alexduzi.expensetracking.repository;

import com.alexduzi.expensetracking.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findUserByEmail(String email);
}
