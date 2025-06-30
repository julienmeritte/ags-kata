package com.ags.kata.infrastructure.adapter.persistence.offre;

import com.ags.kata.domain.model.offre.Offre;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import static com.ags.kata.utils.MarcheUtils.MARCHE_ID;
import static com.ags.kata.utils.OffreUtils.OFFRE_ACTEUR;
import static com.ags.kata.utils.OffreUtils.creerOffre;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OffreQueryJpaAdapterTest {

    @Mock
    private OffreJpaRepository offreJpaRepository;

    @Mock
    private OffreMapper offreMapper;

    @InjectMocks
    private OffreQueryJpaAdapter offreQueryJpaAdapter;

    private List<OffreEntity> offreEntities;
    private Set<Offre> offres;

    @BeforeEach
    void setUp() {
        OffreEntity offreEntity = new OffreEntity();
        offreEntity.setId(UUID.randomUUID());
        offreEntity.setActeur(OFFRE_ACTEUR);
        offreEntity.setJour(LocalDate.of(2025, 6, 30));
        offreEntities = List.of(offreEntity);

        Offre offre = creerOffre();
        offres = Set.of(offre);
    }

    @Test
    void shouldReturnOffres_WhenRecuperantOffresParMarcheSelonActeur_GivenValidMarcheIdAndActeur() {
        when(offreJpaRepository.findByMarcheIdAndActeur(MARCHE_ID.id(), OFFRE_ACTEUR))
                .thenReturn(offreEntities);
        when(offreMapper.toDomain(offreEntities))
                .thenReturn(offres);

        Set<Offre> result = offreQueryJpaAdapter.recupererOffresParMarcheSelonActeur(MARCHE_ID, OFFRE_ACTEUR);

        assertNotNull(result);
        assertEquals(offres, result);
        assertEquals(1, result.size());
    }

    @Test
    void shouldReturnEmptySet_WhenRecuperantOffresParMarcheSelonActeur_GivenNoMatchingOffres() {
        when(offreJpaRepository.findByMarcheIdAndActeur(MARCHE_ID.id(), OFFRE_ACTEUR))
                .thenReturn(List.of());
        when(offreMapper.toDomain(List.of()))
                .thenReturn(Set.of());

        Set<Offre> result = offreQueryJpaAdapter.recupererOffresParMarcheSelonActeur(MARCHE_ID, OFFRE_ACTEUR);

        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    @Test
    void shouldCallRepositoryAndMapper_WhenRecuperantOffresParMarcheSelonActeur_GivenParameters() {
        when(offreJpaRepository.findByMarcheIdAndActeur(MARCHE_ID.id(), OFFRE_ACTEUR))
                .thenReturn(offreEntities);
        when(offreMapper.toDomain(offreEntities))
                .thenReturn(offres);

        offreQueryJpaAdapter.recupererOffresParMarcheSelonActeur(MARCHE_ID, OFFRE_ACTEUR);

        verify(offreJpaRepository).findByMarcheIdAndActeur(MARCHE_ID.id(), OFFRE_ACTEUR);
        verify(offreMapper).toDomain(offreEntities);
    }
}