package com.ags.kata.domain.model.bloc;

import org.junit.jupiter.api.Test;

import static com.ags.kata.utils.BlocUtils.BLOC_ID;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class BlocIdTest {

    @Test
    void shouldBeValid_WhenInstanciatingBlocId_GivenValidData() {
        var blocId = new BlocId(BLOC_ID.id());
        assertNotNull(blocId);
    }

    @Test
    void shouldNotThrow_WhenInstanciatingBlocId_GivenNullValue() {
        var blocId = new BlocId(null);
        assertNotNull(blocId);
    }
}