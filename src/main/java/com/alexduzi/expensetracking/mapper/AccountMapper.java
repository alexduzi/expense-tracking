package com.alexduzi.expensetracking.mapper;

import com.alexduzi.expensetracking.domain.Account;
import com.alexduzi.expensetracking.dto.request.CreateAccountRequest;
import com.alexduzi.expensetracking.dto.response.AccountResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AccountMapper {
    AccountMapper INSTANCE = Mappers.getMapper(AccountMapper.class);

    @Mapping(source = "type", target = "accountType")
    AccountResponse accountToDto(Account acc);

    @Mapping(source = "accountType", target = "type")
    Account accountDtoToAccount(CreateAccountRequest acc);
}
