package com.ags.kata.domain.model.allocation_parc;

import org.junit.jupiter.api.Test;

import static com.ags.kata.utils.AllocationParcUtils.ALLOCATION_PARC_ID;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class AllocationParcIdTest {
    @Test
    void shouldBeValid_WhenInstanciatingAllocationParcId_GivenValidData() {
        var allocationParcId = new AllocationParcId(ALLOCATION_PARC_ID.id());
        assertNotNull(allocationParcId);
    }

    @Test
    void shouldNotThrow_WhenInstanciatingAllocationParcId_GivenNullValue() {
        var allocationParcId = new AllocationParcId(null);
        assertNotNull(allocationParcId);
    }
}