package com.ags.kata.domain.model.offre;

import com.ags.kata.domain.model.bloc.Bloc;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static com.ags.kata.utils.BlocUtils.creerBloc;
import static com.ags.kata.utils.MarcheUtils.creerMarche;
import static com.ags.kata.utils.OffreUtils.*;
import static org.junit.jupiter.api.Assertions.*;

class OffreTest {

    @Test
    void shouldBeValid_WhenCreatingOffre_GivenValidData() {
        Offre offre = new Offre(OFFRE_ID, OFFRE_ACTEUR, creerMarche(), OFFRE_JOUR, Set.of(creerBloc()));

        assertNotNull(offre);
        assertEquals(creerOffre(), offre);
    }

    @Test
    void shouldFail_WhenCreatingOffre_GivenNullId() {
        var marche = creerMarche();
        var blocs = Set.of(creerBloc());
        assertThrows(IllegalArgumentException.class, () -> new Offre(null, OFFRE_ACTEUR, marche, OFFRE_JOUR, blocs));
    }

    @Test
    void shouldFail_WhenCreatingOffre_GivenNullOrBlankActeur() {
        var marche = creerMarche();
        var blocs = Set.of(creerBloc());
        assertThrows(IllegalArgumentException.class, () -> new Offre(OFFRE_ID, null, marche, OFFRE_JOUR, blocs));
        assertThrows(IllegalArgumentException.class, () -> new Offre(OFFRE_ID, " ", marche, OFFRE_JOUR, blocs));

    }

    @Test
    void shouldFail_WhenCreatingOffre_GivenNullMarche() {
        var blocs = Set.of(creerBloc());
        assertThrows(IllegalArgumentException.class, () -> new Offre(OFFRE_ID, OFFRE_ACTEUR, null, OFFRE_JOUR, blocs));
    }

    @Test
    void shouldFail_WhenCreatingOffre_GivenNullJour() {
        var marche = creerMarche();
        var blocs = Set.of(creerBloc());
        assertThrows(IllegalArgumentException.class, () -> new Offre(OFFRE_ID, OFFRE_ACTEUR, marche, null, blocs));
    }

    @Test
    void shouldFail_WhenCreatingOffre_GivenNullBlocs() {
        var marche = creerMarche();
        assertThrows(IllegalArgumentException.class, () -> new Offre(OFFRE_ID, OFFRE_ACTEUR, marche, OFFRE_JOUR, null));
    }

    @Test
    void shouldFail_WhenCreatingOffre_GivenEmptyBlocs() {
        var marche = creerMarche();
        var blocs = Set.<Bloc>of();
        assertThrows(IllegalArgumentException.class, () -> new Offre(OFFRE_ID, OFFRE_ACTEUR, marche, OFFRE_JOUR, blocs));
    }
}