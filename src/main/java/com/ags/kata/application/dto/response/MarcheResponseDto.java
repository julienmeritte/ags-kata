package com.ags.kata.application.dto.response;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

public record MarcheResponseDto(@Positive long id, @NotBlank String nom) {
}
