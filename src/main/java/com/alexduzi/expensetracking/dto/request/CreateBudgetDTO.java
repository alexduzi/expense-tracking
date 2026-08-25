package com.alexduzi.expensetracking.dto.request;

import com.alexduzi.expensetracking.enums.PeriodType;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public record CreateBudgetDTO(Long userId, Long categoryId, BigDecimal amountLimit, PeriodType period, String description, LocalDate startDate, LocalDate endDate) {
}
