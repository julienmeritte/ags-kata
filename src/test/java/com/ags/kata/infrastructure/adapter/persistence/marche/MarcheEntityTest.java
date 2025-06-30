package com.ags.kata.infrastructure.adapter.persistence.marche;

import org.hibernate.exception.ConstraintViolationException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static com.ags.kata.utils.MarcheUtils.MARCHE_ID;
import static com.ags.kata.utils.MarcheUtils.MARCHE_NOM;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.AUTO_CONFIGURED)
class MarcheEntityTest {

    @Autowired
    private TestEntityManager entityManager;

    @Test
    void shouldThrowsConstraintsViolationHibernate_WhenSavingMarcheEntity_GivenValidData() {
        MarcheEntity marcheEntity = MarcheEntity.builder()
                .id(MARCHE_ID.id())
                .nom(MARCHE_NOM)
                .build();

        var creee = entityManager.persistAndFlush(marcheEntity);

        assertEquals(marcheEntity, creee);
    }

    @Test
    void shouldThrowsConstraintsViolationHibernate_WhenSavingMarcheEntity_GivenNullNom() {
        MarcheEntity marcheEntity = MarcheEntity.builder()
                .id(MARCHE_ID.id())
                .nom(null)
                .build();

        assertThrows(ConstraintViolationException.class, () -> entityManager.persistAndFlush(marcheEntity));
    }
}