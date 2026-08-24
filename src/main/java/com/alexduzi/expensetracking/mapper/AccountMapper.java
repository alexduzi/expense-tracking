package com.alexduzi.expensetracking.mapper;

import com.alexduzi.expensetracking.domain.Account;
import com.alexduzi.expensetracking.dto.request.CreateAccountRequest;
import com.alexduzi.expensetracking.dto.response.AccountResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface AccountMapper {
    AccountMapper INSTANCE = Mappers.getMapper(AccountMapper.class);

    @Mapping(source = "type", target = "accountType")
    AccountResponse toDto(Account entity);

    @Mapping(source = "accountType", target = "type")
    Account toAccount(CreateAccountRequest dto);
}
