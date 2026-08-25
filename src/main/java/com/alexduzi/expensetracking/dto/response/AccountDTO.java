package com.alexduzi.expensetracking.dto.response;

import com.alexduzi.expensetracking.enums.AccountType;

import java.math.BigDecimal;

public record AccountDTO(
        String name,
        BigDecimal balance,
        AccountType accountType,
        String accountNumber,
        String currency
) { }
