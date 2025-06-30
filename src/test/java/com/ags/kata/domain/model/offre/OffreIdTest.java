package com.ags.kata.domain.model.offre;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

class OffreIdTest {
    @Test
    void shouldBeValid_WhenInstanciatingOffreId_GivenValidData() {
        var offreId = new OffreId(1L);
        assertNotNull(offreId);
    }

    @Test
    void shouldFail_WhenInstanciatingOffreId_GivenNegativeValue() {
        assertThrows(IllegalArgumentException.class, () -> new OffreId(0L));
    }
}