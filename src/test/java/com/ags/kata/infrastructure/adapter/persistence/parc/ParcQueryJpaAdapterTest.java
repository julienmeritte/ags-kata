package com.ags.kata.infrastructure.adapter.persistence.parc;

import com.ags.kata.application.port.out.ParcQueryRepository;
import com.ags.kata.domain.model.parc.ParcId;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.AUTO_CONFIGURED)
@Import({ParcQueryJpaAdapter.class, ParcMapperImpl.class})
class ParcQueryJpaAdapterTest {

    @Autowired
    private ParcQueryRepository parcQueryRepository;

    @Test
    void shouldReturnParcId_WhenGettingProchainId_GivenNothing() {
        var prochainId = parcQueryRepository.prochainId();
        assertNotNull(prochainId);
        var encoreProchainId = parcQueryRepository.prochainId();
        assertEquals(new ParcId(prochainId.id() + 1L), encoreProchainId);
    }
}