package com.ags.kata.domain.model.offre;

import org.junit.jupiter.api.Test;

import static com.ags.kata.utils.OffreUtils.OFFRE_ID;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class OffreIdTest {
    @Test
    void shouldBeValid_WhenInstanciatingOffreId_GivenValidData() {
        var offreId = new OffreId(OFFRE_ID.id());
        assertNotNull(offreId);
    }

    @Test
    void shouldNotThrow_WhenInstanciatingOffreId_GivenNegativeValue() {
        var offreId = new OffreId(null);
        assertNotNull(offreId);
    }
}