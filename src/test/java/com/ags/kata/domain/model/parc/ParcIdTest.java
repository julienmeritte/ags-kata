package com.ags.kata.domain.model.parc;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ParcIdTest {

    @Test
    void shouldBeValid_WhenInstanciatingParcId_GivenValidData() {
        var parcId = new ParcId(1L);
        assertNotNull(parcId);
    }

    @Test
    void shouldFail_WhenInstanciatingParcId_GivenNegativeValue() {
        assertThrows(IllegalArgumentException.class, () -> new ParcId(-1L));
    }
}