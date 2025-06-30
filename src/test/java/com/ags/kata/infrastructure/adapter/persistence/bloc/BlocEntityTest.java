package com.ags.kata.infrastructure.adapter.persistence.bloc;

import com.ags.kata.infrastructure.adapter.persistence.marche.MarcheEntity;
import com.ags.kata.infrastructure.adapter.persistence.offre.OffreEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static com.ags.kata.utils.BlocUtils.*;
import static com.ags.kata.utils.MarcheUtils.MARCHE_ID;
import static com.ags.kata.utils.MarcheUtils.MARCHE_NOM;
import static com.ags.kata.utils.OffreUtils.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.AUTO_CONFIGURED)
class BlocEntityTest {

    @Autowired
    private TestEntityManager entityManager;

    @Test
    void shouldBeValid_WhenSavingBlocEntity_GivenValidData() {
        var marche = entityManager.persistAndFlush(MarcheEntity.builder()
                .id(MARCHE_ID.id())
                .nom(MARCHE_NOM)
                .build());
        var offre = entityManager.persistAndFlush(OffreEntity.builder()
                .id(OFFRE_ID.id())
                .acteur(OFFRE_ACTEUR)
                .marche(marche)
                .jour(OFFRE_JOUR)
                .build());

        BlocEntity blocEntity = BlocEntity.builder()
                .id(BLOC_ID.id())
                .quantiteEnergieMW(BLOC_QUANTITE_ENERGIE_MW)
                .prixPlancher(BLOC_PRIX_PLANCHER)
                .positionJournee(BLOC_POSITION_JOURNEE)
                .offre(offre)
                .build();

        var creee = entityManager.persistAndFlush(blocEntity);

        assertEquals(blocEntity, creee);
    }
}