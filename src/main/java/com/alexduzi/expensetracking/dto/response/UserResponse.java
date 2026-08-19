package com.alexduzi.expensetracking.dto.response;

public record UserResponse(
        String name,
        String email,
        String address,
        String zipcode
) { }
