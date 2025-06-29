package com.ags.kata.domain.model.parc;

import org.junit.jupiter.api.Test;

import static com.ags.kata.utils.ParcUtils.*;
import static org.junit.jupiter.api.Assertions.*;

class ParcTest {


    @Test
    void shouldBeValid_WhenInstanciatingParc_GivenValidData() {
        var parc = new Parc(PARC_ID, PARC_TYPE, PARC_CAPACITE_HORAIRE_MW);

        assertNotNull(parc);
        assertEquals(creerParc(), parc);
    }

    @Test
    void shouldFail_WhenInstanciatingParc_GivenNullId() {
        assertThrows(IllegalArgumentException.class, () -> new Parc(null, PARC_TYPE, PARC_CAPACITE_HORAIRE_MW));
    }

    @Test
    void shouldFail_WhenInstanciatingParc_GivenNullType() {
        assertThrows(IllegalArgumentException.class, () -> new Parc(PARC_ID, null, PARC_CAPACITE_HORAIRE_MW));
    }

    @Test
    void shouldFail_WhenInstanciatingParc_GivenInvalidCapacite() {
        assertThrows(IllegalArgumentException.class, () -> new Parc(PARC_ID, PARC_TYPE, -1));
    }
}