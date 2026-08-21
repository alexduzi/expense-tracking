package com.alexduzi.expensetracking.service;

import com.alexduzi.expensetracking.domain.Account;
import com.alexduzi.expensetracking.domain.User;
import com.alexduzi.expensetracking.dto.request.CreateAccountRequest;
import com.alexduzi.expensetracking.dto.response.AccountResponse;
import com.alexduzi.expensetracking.exception.AccountExistsException;
import com.alexduzi.expensetracking.exception.DatabaseException;
import com.alexduzi.expensetracking.exception.UserNotFoundException;
import com.alexduzi.expensetracking.mapper.AccountMapper;
import com.alexduzi.expensetracking.repository.AccountRepository;
import com.alexduzi.expensetracking.repository.UserRepository;
import jakarta.transaction.Transactional;
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

    @Transactional
    public AccountResponse create(CreateAccountRequest accRequest) {
        Optional<User> optUser = userRepository.findUserByEmail(accRequest.email());
        if (optUser.isEmpty()) {
            throw new UserNotFoundException(String.format("User with email %s not exists", accRequest.email()));
        }

        Optional<Account> optAcc = accountRepository.findAccountByAccountNumber(accRequest.accountNumber());
        if (optAcc.isPresent()) {
            throw new AccountExistsException(String.format("Account %s already exists", accRequest.accountNumber()));
        }

        Account acc = AccountMapper.INSTANCE.accountDtoToAccount(accRequest);
        User user = optUser.get();
        acc.setUser(user);

        try {
            acc = accountRepository.save(acc);
            user.addAccount(acc);
            return AccountMapper.INSTANCE.accountToDto(acc);
        } catch (Exception e) {
            throw new DatabaseException(e.getMessage());
        }
    }
}
