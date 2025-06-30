package com.ags.kata.application.dto.response;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

import java.util.Set;

public record MarcheOffreResponseDto(@NotNull @Valid MarcheResponseDto marche,
                                     @NotNull @Valid Set<OffreResponseDto> offres) {
}
