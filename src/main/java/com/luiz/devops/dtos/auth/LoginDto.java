package com.luiz.devops.dtos.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record LoginDto(
        @Email String email,
        @NotBlank String password) {
}