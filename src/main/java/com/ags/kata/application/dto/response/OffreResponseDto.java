package com.ags.kata.application.dto.response;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.time.LocalDate;
import java.util.Set;

public record OffreResponseDto(@Positive long id, @NotBlank String acteur, @NotNull @Valid MarcheResponseDto marche,
                               @NotNull LocalDate jour, @NotEmpty @Valid Set<BlocResponseDto> blocs) {
}
