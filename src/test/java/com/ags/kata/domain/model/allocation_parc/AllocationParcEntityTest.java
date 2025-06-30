package com.ags.kata.domain.model.allocation_parc;

import org.junit.jupiter.api.Test;

import static com.ags.kata.utils.AllocationParcUtils.*;
import static com.ags.kata.utils.ParcUtils.PARC_ID;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class AllocationParcEntityTest {

    @Test
    void shouldBeValid_WhenInstanciatingAllocationParc_GivenValidDate() {
        var allocationParc = new AllocationParc(ALLOCATION_PARC_ID, PARC_ID, ALLOCATION_PARC_QUANTITE);

        assertEquals(creerAllocationParc(), allocationParc);
    }

    @Test
    void shouldFail_WhenInstanciatingAllocationParc_GivenNullId() {
        assertThrows(IllegalArgumentException.class, () -> new AllocationParc(null, PARC_ID, ALLOCATION_PARC_QUANTITE));
    }

    @Test
    void shouldFail_WhenInstanciatingAllocationParc_GivenNullParcId() {
        assertThrows(IllegalArgumentException.class, () -> new AllocationParc(ALLOCATION_PARC_ID, null, ALLOCATION_PARC_QUANTITE));
    }

    @Test
    void shouldFail_WhenInstanciatingAllocationParc_GivenNegativeOrZeroQuantite() {
        assertThrows(IllegalArgumentException.class, () -> new AllocationParc(ALLOCATION_PARC_ID, PARC_ID, 0));
        assertThrows(IllegalArgumentException.class, () -> new AllocationParc(ALLOCATION_PARC_ID, PARC_ID, -1));
    }
}