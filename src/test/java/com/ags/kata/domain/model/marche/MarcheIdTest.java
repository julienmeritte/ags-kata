package com.ags.kata.domain.model.marche;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

class MarcheIdTest {
    @Test
    void shouldBeValid_WhenInstanciatingMarcheId_GivenValidData() {
        var marcheId = new MarcheId(1L);
        assertNotNull(marcheId);
    }

    @Test
    void shouldFail_WhenInstanciatingMarcheId_GivenNegativeValue() {
        assertThrows(IllegalArgumentException.class, () -> new MarcheId(-1L));
    }
}