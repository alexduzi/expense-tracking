package com.alexduzi.expensetracking.dto.response;

public record UserDTO(
        String name,
        String email,
        String address,
        String zipcode
) { }
