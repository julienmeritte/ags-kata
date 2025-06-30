package com.ags.kata.application.dto.response;

import com.ags.kata.domain.model.parc.ParcType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record ParcResponseDto(@Positive long id, @NotBlank String nom, @NotNull ParcType type,
                              @Positive int capaciteHoraireMW) {
}
