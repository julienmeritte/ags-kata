package com.ags.kata.application.dto.response;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record MarcheResponseDto(@NotNull UUID id, @NotBlank String nom) {
}
