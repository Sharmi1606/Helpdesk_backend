package com.springboot.helpdesk_backend.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CustomerDto(

        @NotNull(message = "Name is required")
        @NotBlank(message = "Name is required")
        String name,
        @NotNull(message = "City is required")
        @NotBlank(message = "City is required")
        String city,
        @NotNull(message = "Email is required")
        @NotBlank(message = "Email is required")
        @Email(message = "Email seems invalid")
        String email,
        @NotNull
        @NotBlank
        @Size(min=3,max=15)
        String username,
        @NotNull
        @NotBlank
        String password) {

}
