package com.alexduzi.expensetracking.dto.response;

import com.alexduzi.expensetracking.enums.AccountType;

import java.math.BigDecimal;

public record AccountResponse(
        String name,
        BigDecimal balance,
        AccountType type,
        String accountNumber,
        String currency
) { }
