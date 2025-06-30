package com.ags.kata.application.dto.response;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

public record OffreResponseDto(@NotNull UUID id, @NotBlank String acteur, @NotNull @Valid MarcheResponseDto marche,
                               @NotNull LocalDate jour, @NotEmpty @Valid Set<BlocResponseDto> blocs) {
}
