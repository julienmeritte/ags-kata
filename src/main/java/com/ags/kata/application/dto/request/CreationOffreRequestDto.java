package com.ags.kata.application.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.Set;

public record CreationOffreRequestDto(long marcheId, @NotNull LocalDate jour,
                                      @Valid @NotEmpty Set<BlocRequestDto> blocs) {
}
