package com.alexduzi.expensetracking.mapper;

import com.alexduzi.expensetracking.domain.User;
import com.alexduzi.expensetracking.dto.request.CreateUserRequest;
import com.alexduzi.expensetracking.dto.response.UserResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserResponse toDto(User entity);
    User toUser(CreateUserRequest dto);
}
