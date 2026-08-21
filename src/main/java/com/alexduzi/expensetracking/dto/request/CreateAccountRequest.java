package com.alexduzi.expensetracking.dto.request;

import com.alexduzi.expensetracking.enums.AccountType;

import java.math.BigDecimal;

public record CreateAccountRequest(
        String email,
        String name,
        BigDecimal balance,
        AccountType accountType,
        String accountNumber,
        String currency
) { }
