package com.ags.kata.domain.model.allocation_parc;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

class AllocationParcIdTest {
    @Test
    void shouldBeValid_WhenInstanciatingAllocationParcId_GivenValidData() {
        var allocationParcId = new AllocationParcId(1L);
        assertNotNull(allocationParcId);
    }

    @Test
    void shouldFail_WhenInstanciatingAllocationParcId_GivenNegativeValue() {
        assertThrows(IllegalArgumentException.class, () -> new AllocationParcId(0L));
    }
}