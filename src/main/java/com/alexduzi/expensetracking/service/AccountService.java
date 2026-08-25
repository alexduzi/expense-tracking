package com.alexduzi.expensetracking.service;

import com.alexduzi.expensetracking.domain.Account;
import com.alexduzi.expensetracking.domain.User;
import com.alexduzi.expensetracking.dto.request.CreateAccountDTO;
import com.alexduzi.expensetracking.dto.response.AccountDTO;
import com.alexduzi.expensetracking.exception.DatabaseException;
import com.alexduzi.expensetracking.exception.EntityAlreadyExistsException;
import com.alexduzi.expensetracking.exception.EntityNotFoundException;
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
    private final AccountMapper accountMapper;

    public AccountService(UserRepository userRepository, AccountRepository accountRepository, AccountMapper accountMapper) {
        this.userRepository = userRepository;
        this.accountRepository = accountRepository;
        this.accountMapper = accountMapper;
    }

    @Transactional
    public AccountDTO create(CreateAccountDTO accRequest) {
        Optional<User> optUser = userRepository.findUserByEmail(accRequest.email());
        if (optUser.isEmpty()) {
            throw new EntityNotFoundException(String.format("User with email %s don't exists", accRequest.email()));
        }

        Optional<Account> optAcc = accountRepository.findAccountByAccountNumber(accRequest.accountNumber());
        if (optAcc.isPresent()) {
            throw new EntityAlreadyExistsException(String.format("Account %s already exists", accRequest.accountNumber()));
        }

        Account acc = accountMapper.toAccount(accRequest);
        User user = optUser.get();
        acc.setUser(user);

        try {
            acc = accountRepository.save(acc);
            user.addAccount(acc);
            return accountMapper.toDto(acc);
        } catch (Exception e) {
            throw new DatabaseException(e.getMessage());
        }
    }
}
