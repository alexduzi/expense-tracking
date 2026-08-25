package com.alexduzi.expensetracking.dto.request;

public record CreateCategoryDTO(String name, String description, String color, String icon, String type) {
}
