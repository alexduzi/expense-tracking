package com.alexduzi.expensetracking.dto.response;

public record NotificationDTO(String title, String message, boolean read, String userName) {
}
