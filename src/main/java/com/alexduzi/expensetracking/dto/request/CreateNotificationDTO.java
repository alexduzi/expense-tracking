package com.alexduzi.expensetracking.dto.request;

public record CreateNotificationDTO(Long userId, String title, String description) {
}
