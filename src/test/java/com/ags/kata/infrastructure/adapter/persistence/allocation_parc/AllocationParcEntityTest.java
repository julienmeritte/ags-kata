package com.ags.kata.infrastructure.adapter.persistence.allocation_parc;

import com.ags.kata.infrastructure.adapter.persistence.parc.ParcEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static com.ags.kata.utils.AllocationParcUtils.ALLOCATION_PARC_QUANTITE;
import static com.ags.kata.utils.EntityUtils.*;
import static com.ags.kata.utils.ParcUtils.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.AUTO_CONFIGURED)
class AllocationParcEntityTest {

    @Autowired
    private TestEntityManager entityManager;

    @Test
    void shouldPersist_WhenSavingAllocationParcEntity_GivenValidData() {
        var parc = entityManager.persistAndFlush(ParcEntity.builder()
                .id(prochainId(PARC_SEQ, entityManager))
                .nom(PARC_NOM)
                .type(PARC_TYPE)
                .capaciteHoraireMW(PARC_CAPACITE_HORAIRE_MW)
                .build());

        AllocationParcEntity allocationParcEntity = AllocationParcEntity.builder()
                .id(prochainId(ALLOC_PARC_SEQ, entityManager))
                .quantite(ALLOCATION_PARC_QUANTITE)
                .parc(parc)
                .build();

        var creee = entityManager.persistAndFlush(allocationParcEntity);

        assertEquals(allocationParcEntity, creee);
        assertEquals(creee.getParc(), parc);
    }
}