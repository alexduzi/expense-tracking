package com.alexduzi.expensetracking.dto.response;

import java.math.BigDecimal;
import java.time.LocalDate;

public record GoalDTO(Long id, String title, BigDecimal targetAmount, BigDecimal currentAmount, LocalDate targetDate) {
}
