package com.alexduzi.expensetracking.mapper;

import com.alexduzi.expensetracking.domain.Account;
import com.alexduzi.expensetracking.domain.User;
import com.alexduzi.expensetracking.dto.request.CreateAccountRequest;
import com.alexduzi.expensetracking.dto.request.CreateUserRequest;
import com.alexduzi.expensetracking.dto.response.AccountResponse;
import com.alexduzi.expensetracking.dto.response.UserResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AccountMapper {
    AccountMapper INSTANCE = Mappers.getMapper(AccountMapper.class);

    AccountResponse accountToDto(Account acc);
    Account accountDtoToAccount(CreateAccountRequest acc);
}
