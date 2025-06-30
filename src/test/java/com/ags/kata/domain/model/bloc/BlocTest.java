package com.ags.kata.domain.model.bloc;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Set;

import static com.ags.kata.utils.AllocationParcUtils.creerAllocationParc;
import static com.ags.kata.utils.BlocUtils.*;
import static org.junit.jupiter.api.Assertions.*;

class BlocTest {

    @Test
    void shouldBeValid_WhenInstanciatingBloc_GivenValidData() {
        var bloc = new Bloc(BLOC_ID, BLOC_QUANTITE_ENERGIE_MW, BLOC_PRIX_PLANCHER, BLOC_POSITION_JOURNEE, Set.of());

        assertNotNull(bloc);
        assertEquals(creerBloc(), bloc);
    }

    @Test
    void shouldFail_WhenInstanciatingBloc_GivenNullId() {
        var allocations = Set.of(creerAllocationParc());
        assertThrows(NullPointerException.class, () -> new Bloc(null, BLOC_QUANTITE_ENERGIE_MW, BLOC_PRIX_PLANCHER, BLOC_POSITION_JOURNEE, allocations));
    }

    @Test
    void shouldFail_WhenInstanciatingBloc_GivenNullPrixPlancher() {
        var prix = BigDecimal.valueOf(-1);
        var allocations = Set.of(creerAllocationParc());
        assertThrows(IllegalArgumentException.class, () -> new Bloc(BLOC_ID, BLOC_QUANTITE_ENERGIE_MW, null, BLOC_POSITION_JOURNEE, allocations));
        assertThrows(IllegalArgumentException.class, () -> new Bloc(BLOC_ID, BLOC_QUANTITE_ENERGIE_MW, prix, BLOC_POSITION_JOURNEE, allocations));
    }

    @Test
    void shouldFail_WhenInstanciatingBloc_GivenNegativeQuantiteEnergieMW() {
        var allocations = Set.of(creerAllocationParc());
        assertThrows(IllegalArgumentException.class, () -> new Bloc(BLOC_ID, -1, BLOC_PRIX_PLANCHER, BLOC_POSITION_JOURNEE, allocations));
    }

    @Test
    void shouldFail_WhenInstanciatingBloc_GivenInvalidPositionJournee() {
        var allocations = Set.of(creerAllocationParc());
        assertThrows(IllegalArgumentException.class, () -> new Bloc(BLOC_ID, BLOC_QUANTITE_ENERGIE_MW, BLOC_PRIX_PLANCHER, -1, allocations));
        assertThrows(IllegalArgumentException.class, () -> new Bloc(BLOC_ID, BLOC_QUANTITE_ENERGIE_MW, BLOC_PRIX_PLANCHER, 8, allocations));
    }
}