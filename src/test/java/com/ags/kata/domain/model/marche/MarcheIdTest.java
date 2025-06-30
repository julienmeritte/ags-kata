package com.ags.kata.domain.model.marche;

import org.junit.jupiter.api.Test;

import static com.ags.kata.utils.MarcheUtils.MARCHE_ID;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class MarcheIdTest {
    @Test
    void shouldBeValid_WhenInstanciatingMarcheId_GivenValidData() {
        var marcheId = new MarcheId(MARCHE_ID.id());
        assertNotNull(marcheId);
    }

    @Test
    void shouldNotThrow_WhenInstanciatingMarcheId_GivenNullValue() {
        var marcheId = new MarcheId(null);
        assertNotNull(marcheId);
    }
}