package com.ags.kata.infrastructure.adapter.persistence.offre;

import com.ags.kata.infrastructure.adapter.persistence.marche.MarcheEntity;
import jakarta.validation.ConstraintViolationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.Set;

import static com.ags.kata.utils.EntityUtils.*;
import static com.ags.kata.utils.MarcheUtils.MARCHE_NOM;
import static com.ags.kata.utils.OffreUtils.OFFRE_ACTEUR;
import static com.ags.kata.utils.OffreUtils.OFFRE_JOUR;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.AUTO_CONFIGURED)
class OffreEntityTest {

    @Autowired
    private TestEntityManager entityManager;

    MarcheEntity marche;

    @BeforeEach
    void setUp() {
        marche = entityManager.persistAndFlush(MarcheEntity.builder()
                .id(prochainId(MARCHE_SEQ, entityManager))
                .nom(MARCHE_NOM)
                .build());
    }

    @Test
    void shouldPersist_WhenSavingOffreEntity_GivenValidData() {
        OffreEntity offreEntity = OffreEntity.builder()
                .id(prochainId(OFFRE_SEQ, entityManager))
                .acteur(OFFRE_ACTEUR)
                .marche(marche)
                .jour(OFFRE_JOUR)
                .blocs(Set.of())
                .build();

        var creee = entityManager.persistAndFlush(offreEntity);

        assertEquals(offreEntity, creee);
    }

    @Test
    void shouldThrowsConstraintsViolationJakarta_WhenSavingOffreEntity_GivenNullActeur() {
        OffreEntity offreEntity = OffreEntity.builder()
                .id(prochainId(OFFRE_SEQ, entityManager))
                .acteur(null)
                .marche(marche)
                .jour(OFFRE_JOUR)
                .blocs(Set.of())
                .build();

        assertThrows(ConstraintViolationException.class, () -> entityManager.persistAndFlush(offreEntity));
    }

    @Test
    void shouldThrowsConstraintsViolationHibernate_WhenSavingOffreEntity_GivenNullJour() {
        OffreEntity offreEntity = OffreEntity.builder()
                .id(prochainId(OFFRE_SEQ, entityManager))
                .acteur(OFFRE_ACTEUR)
                .marche(marche)
                .jour(null)
                .blocs(Set.of())
                .build();

        assertThrows(org.hibernate.exception.ConstraintViolationException.class, () -> entityManager.persistAndFlush(offreEntity));
    }

    @Test
    void shouldThrowsConstraintsViolationJakarta_WhenSavingOffreEntity_GivenBlankActeur() {
        OffreEntity offreEntity = OffreEntity.builder()
                .id(prochainId(OFFRE_SEQ, entityManager))
                .acteur(" ")
                .marche(marche)
                .jour(OFFRE_JOUR)
                .blocs(Set.of())
                .build();

        assertThrows(ConstraintViolationException.class, () -> entityManager.persistAndFlush(offreEntity));
    }
}
