package com.ags.kata.domain.model.bloc;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

class BlocIdTest {

    @Test
    void shouldBeValid_WhenInstanciatingBlocId_GivenValidData() {
        var blocId = new BlocId(1L);
        assertNotNull(blocId);
    }

    @Test
    void shouldFail_WhenInstanciatingBlocId_GivenNegativeValue() {
        assertThrows(IllegalArgumentException.class, () -> new BlocId(-1L));
    }
}