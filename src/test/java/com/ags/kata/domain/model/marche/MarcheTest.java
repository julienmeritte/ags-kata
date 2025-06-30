package com.ags.kata.domain.model.marche;

import org.junit.jupiter.api.Test;

import static com.ags.kata.utils.MarcheUtils.*;
import static org.junit.jupiter.api.Assertions.*;

class MarcheTest {

    @Test
    void shouldBeValid_WhenCreatingMarche_GivenValidData() {
        var marche = new Marche(MARCHE_ID, MARCHE_NOM);

        assertNotNull(marche);
        assertEquals(creerMarche(), marche);
    }

    @Test
    void shouldFail_WhenCreatingMarche_GivenNullId() {
        assertThrows(NullPointerException.class, () -> new Marche(null, MARCHE_NOM));
    }

    @Test
    void shouldFail_WhenCreatingMarche_GivenNullOrBlankNom() {
        assertThrows(IllegalArgumentException.class, () -> new Marche(MARCHE_ID, null));
        assertThrows(IllegalArgumentException.class, () -> new Marche(MARCHE_ID, " "));
    }
}