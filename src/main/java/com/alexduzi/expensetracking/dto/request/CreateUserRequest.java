package com.alexduzi.expensetracking.dto.request;

import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.Length;

public record CreateUserRequest(
        @NotBlank(message = "Username is required")
        @Size(min = 5, max = 20, message = "Name must have 5 to 20 caracters")
        String name,
        @Email(message = "Please provide a valid email address")
        @NotNull(message = "Email is required")
        @Size(min = 3, max = 255, message = "Email must have 3 to 255 characters")
        String email,
        @NotNull(message = "Password is required")
        String password,
        @NotNull(message = "Address is required")
        @Size(min = 3, max = 255, message = "Address must have 3 to 255 caracters")
        String address,
        @NotNull(message = "Zipcode is required")
        @Size(min = 3, max = 20, message = "Zipcode must have 3 to 20 caracters")
        String zipcode
) { }
