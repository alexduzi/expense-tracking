package com.alexduzi.expensetracking.dto.request;

public record UpdateCategoryDTO(String name, String description, String color, String icon, String type) {
}
