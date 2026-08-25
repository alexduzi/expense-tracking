package com.alexduzi.expensetracking.mapper;

import com.alexduzi.expensetracking.domain.Account;
import com.alexduzi.expensetracking.dto.request.CreateAccountDTO;
import com.alexduzi.expensetracking.dto.response.AccountDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface AccountMapper {
    AccountMapper INSTANCE = Mappers.getMapper(AccountMapper.class);

    @Mapping(source = "type", target = "accountType")
    AccountDTO toDto(Account entity);

    @Mapping(source = "accountType", target = "type")
    Account toAccount(CreateAccountDTO dto);
}
