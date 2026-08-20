package com.alexduzi.expensetracking.service;

import com.alexduzi.expensetracking.domain.User;
import com.alexduzi.expensetracking.dto.request.CreateUserRequest;
import com.alexduzi.expensetracking.dto.response.UserResponse;
import com.alexduzi.expensetracking.exception.DatabaseException;
import com.alexduzi.expensetracking.exception.UserExistsException;
import com.alexduzi.expensetracking.mapper.UserMapper;
import com.alexduzi.expensetracking.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public UserResponse create(CreateUserRequest createUser) {
        Optional<User> optUser = userRepository.findUserByEmail(createUser.email());
        if (optUser.isPresent()) {
            throw new UserExistsException(String.format("User with email %s already exists", createUser.email()));
        }

        User user = UserMapper.INSTANCE.userDtoToUser(createUser);
        user.setPassword(passwordEncoder.encode(createUser.password()));

        try {
            return UserMapper.INSTANCE.userToUserDto(userRepository.save(user));
        } catch (Exception e) {
            throw new DatabaseException(e.getMessage());
        }
    }
}
