package com.ags.kata.application.dto.response;

import jakarta.validation.constraints.NotBlank;

public record BasicMessageResponseDto(@NotBlank String message) {
}
