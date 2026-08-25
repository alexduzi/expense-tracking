package com.alexduzi.expensetracking.service;

import com.alexduzi.expensetracking.domain.User;
import com.alexduzi.expensetracking.dto.request.CreateUserDTO;
import com.alexduzi.expensetracking.dto.response.UserDTO;
import com.alexduzi.expensetracking.exception.DatabaseException;
import com.alexduzi.expensetracking.exception.EntityAlreadyExistsException;
import com.alexduzi.expensetracking.mapper.UserMapper;
import com.alexduzi.expensetracking.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.userMapper = userMapper;
    }

    public UserDTO create(CreateUserDTO createUser) {
        userRepository.findUserByEmail(createUser.email())
                .ifPresent((u) -> {
                            throw new EntityAlreadyExistsException(String.format("User with email %s already exists", createUser.email()));
                });

        User user = userMapper.toUser(createUser);
        user.setPassword(passwordEncoder.encode(createUser.password()));

        try {
            return userMapper.toDto(userRepository.save(user));
        } catch (Exception e) {
            throw new DatabaseException(e.getMessage());
        }
    }
}
