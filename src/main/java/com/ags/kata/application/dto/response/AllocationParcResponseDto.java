package com.ags.kata.application.dto.response;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.util.UUID;

public record AllocationParcResponseDto(@NotNull UUID id, @NotNull UUID parcId, @Positive int quantite) {
}
