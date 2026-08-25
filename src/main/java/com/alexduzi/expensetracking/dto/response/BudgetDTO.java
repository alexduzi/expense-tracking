package com.alexduzi.expensetracking.dto.response;

import com.alexduzi.expensetracking.enums.PeriodType;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public record BudgetDTO(Long id, BigDecimal amountLimit, PeriodType period, String description, LocalDate startDate, LocalDate endDate) {
}
