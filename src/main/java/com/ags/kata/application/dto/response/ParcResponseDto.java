package com.ags.kata.application.dto.response;

import com.ags.kata.domain.model.parc.ParcType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.util.UUID;

public record ParcResponseDto(@NotNull UUID id, @NotBlank String nom, @NotNull ParcType type,
                              @Positive int capaciteHoraireMW) {
}
