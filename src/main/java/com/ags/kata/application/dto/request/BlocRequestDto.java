package com.ags.kata.application.dto.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Positive;

public record BlocRequestDto(@Positive double prixPlancher, @Positive int quantiteEnergieMW,
                             @Min(0) @Max(7) int positionJournee) {
}
