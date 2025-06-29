package com.ags.kata.domain.model.allocation_parc;

import org.junit.jupiter.api.Test;

import static com.ags.kata.utils.AllocationParcUtils.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class AllocationParcTest {

    @Test
    void shouldBeValid_WhenInstanciatingAllocationParc_GivenValidDate() {
        var allocationParc = new AllocationParc(ALLOCATION_PARC_ID, ALLOCATION_PARC_QUANTITE);

        assertEquals(creerAllocationParc(), allocationParc);
    }

    @Test
    void shouldFail_WhenInstanciatingAllocationParc_GivenNullId() {
        assertThrows(IllegalArgumentException.class, () -> new AllocationParc(null, ALLOCATION_PARC_QUANTITE));
    }

    @Test
    void shouldFail_WhenInstanciatingAllocationParc_GivenNegativeOrZeroQuantite() {
        assertThrows(IllegalArgumentException.class, () -> new AllocationParc(ALLOCATION_PARC_ID, 0));
        assertThrows(IllegalArgumentException.class, () -> new AllocationParc(ALLOCATION_PARC_ID, -1));
    }
}