package com.ags.kata.application.dto.response;

import jakarta.validation.constraints.Positive;

public record AllocationParcResponseDto(@Positive long id, @Positive long parcId, @Positive int quantite) {
}
