package com.alexduzi.expensetracking.mapper;

import com.alexduzi.expensetracking.domain.User;
import com.alexduzi.expensetracking.dto.request.CreateUserRequest;
import com.alexduzi.expensetracking.dto.response.UserResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserResponse userToUserDto(User user);
    User userDtoToUser(CreateUserRequest user);
}
