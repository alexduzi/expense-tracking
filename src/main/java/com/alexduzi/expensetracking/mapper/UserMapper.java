package com.alexduzi.expensetracking.mapper;

import com.alexduzi.expensetracking.domain.User;
import com.alexduzi.expensetracking.dto.request.CreateUserDTO;
import com.alexduzi.expensetracking.dto.response.UserDTO;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserDTO toDto(User entity);
    User toUser(CreateUserDTO dto);
}
