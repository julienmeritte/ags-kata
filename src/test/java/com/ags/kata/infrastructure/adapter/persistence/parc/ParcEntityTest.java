package com.ags.kata.infrastructure.adapter.persistence.parc;

import org.hibernate.exception.ConstraintViolationException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static com.ags.kata.utils.ParcUtils.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.AUTO_CONFIGURED)
class ParcEntityTest {

    @Autowired
    private TestEntityManager entityManager;

    @Test
    void shouldPersist_WhenSavingParcEntity_GivenValidData() {
        ParcEntity parcEntity = ParcEntity.builder()
                .id(PARC_ID.id())
                .nom(PARC_NOM)
                .type(PARC_TYPE)
                .capaciteHoraireMW(PARC_CAPACITE_HORAIRE_MW)
                .build();

        var creee = entityManager.persistAndFlush(parcEntity);

        assertEquals(parcEntity, creee);
    }

    @Test
    void shouldThrowsConstraintsViolationHibernate_WhenSavingParcEntity_GivenNullNom() {
        ParcEntity parcEntity = ParcEntity.builder()
                .id(PARC_ID.id())
                .nom(null)
                .type(PARC_TYPE)
                .capaciteHoraireMW(PARC_CAPACITE_HORAIRE_MW)
                .build();

        assertThrows(ConstraintViolationException.class, () -> entityManager.persistAndFlush(parcEntity));
    }

    @Test
    void shouldThrowsConstraintsViolationHibernate_WhenSavingParcEntity_GivenNullType() {
        ParcEntity parcEntity = ParcEntity.builder()
                .id(PARC_ID.id())
                .nom(PARC_NOM)
                .type(null)
                .capaciteHoraireMW(PARC_CAPACITE_HORAIRE_MW)
                .build();

        assertThrows(ConstraintViolationException.class, () -> entityManager.persistAndFlush(parcEntity));
    }
}