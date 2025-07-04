package com.ags.kata.application.dto.request;

import com.ags.kata.domain.model.parc.ParcType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record CreationParcRequestDto(@NotBlank String nom, @NotNull ParcType type, @Positive int capaciteHoraireMW) {
}
