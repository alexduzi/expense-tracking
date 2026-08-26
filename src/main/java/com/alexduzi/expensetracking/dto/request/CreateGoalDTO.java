package com.alexduzi.expensetracking.dto.request;

import java.math.BigDecimal;
import java.time.LocalDate;

public record CreateGoalDTO(Long userId, String title, BigDecimal targetAmount, BigDecimal currentAmount, LocalDate targetDate) {

}
