package com.alexduzi.expensetracking.service;

import com.alexduzi.expensetracking.domain.User;
import com.alexduzi.expensetracking.dto.request.CreateAccountRequest;
import com.alexduzi.expensetracking.dto.response.AccountResponse;
import com.alexduzi.expensetracking.exception.UserExistsException;
import com.alexduzi.expensetracking.repository.AccountRepository;
import com.alexduzi.expensetracking.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AccountService {
    private final UserRepository userRepository;
    private final AccountRepository accountRepository;

    public AccountService(UserRepository userRepository, AccountRepository accountRepository) {
        this.userRepository = userRepository;
        this.accountRepository = accountRepository;
    }

    public AccountResponse create(CreateAccountRequest accRequest) {
        Optional<User> optUser = userRepository.findUserByEmail(accRequest.email());
        if (optUser.isPresent()) {
            throw new UserExistsException(String.format("User with email %s already exists", accRequest.email()));
        }

        return null;
    }
}
