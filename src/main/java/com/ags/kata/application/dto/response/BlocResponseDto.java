package com.ags.kata.application.dto.response;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.util.Set;
import java.util.UUID;

public record BlocResponseDto(@NotNull UUID id, @Positive int quantiteEnergieMW, @Min(0) @Max(7) int positionJournee,
                              @NotNull BigDecimal prixPlancher,
                              @NotEmpty @Valid Set<AllocationParcResponseDto> allocations) {
}
