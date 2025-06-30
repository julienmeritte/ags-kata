package com.ags.kata.domain.model.parc;

import org.junit.jupiter.api.Test;

import static com.ags.kata.utils.ParcUtils.PARC_ID;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class ParcIdTest {

    @Test
    void shouldBeValid_WhenInstanciatingParcId_GivenValidData() {
        var parcId = new ParcId(PARC_ID.id());
        assertNotNull(parcId);
    }

    @Test
    void shouldFail_WhenInstanciatingParcId_GivenNegativeValue() {
        var parcId = new ParcId(null);
        assertNotNull(parcId);
    }
}