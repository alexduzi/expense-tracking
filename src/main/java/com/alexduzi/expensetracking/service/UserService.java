package com.alexduzi.expensetracking.service;

import com.alexduzi.expensetracking.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void create() {
        throw new UnsupportedOperationException("Method not implemented yet.");
    }
}
