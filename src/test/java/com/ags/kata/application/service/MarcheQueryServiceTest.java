package com.ags.kata.application.service;

import com.ags.kata.application.port.out.MarcheQueryRepository;
import com.ags.kata.domain.model.marche.Marche;
import com.ags.kata.domain.model.marche.MarcheId;
import com.ags.kata.infrastructure.exception.MarcheNonTrouveException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import static com.ags.kata.utils.MarcheUtils.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MarcheQueryServiceTest {

    @Mock
    private MarcheQueryRepository marcheQueryRepository;

    @InjectMocks
    private MarcheQueryService marcheQueryService;

    private Marche marche;
    private Set<Marche> marches;

    @BeforeEach
    void setUp() {
        marche = creerMarche();
        marches = Set.of(
                creerMarche(),
                new Marche(new MarcheId(UUID.randomUUID()), "Réserve Secondaire")
        );
    }

    @Test
    void shouldReturnMarche_WhenRecuperantEtValidantMarche_GivenExistingMarcheId() {
        when(marcheQueryRepository.recupererMarcheParId(MARCHE_ID))
                .thenReturn(Optional.of(marche));

        Marche result = marcheQueryService.recupererEtValiderMarche(MARCHE_ID);

        assertNotNull(result);
        assertEquals(marche, result);
        assertEquals(MARCHE_NOM, result.getNom());
    }

    @Test
    void shouldThrowMarcheNonTrouveException_WhenRecuperantEtValidantMarche_GivenNonExistingMarcheId() {
        when(marcheQueryRepository.recupererMarcheParId(MARCHE_ID))
                .thenReturn(Optional.empty());

        MarcheNonTrouveException exception = assertThrows(
                MarcheNonTrouveException.class,
                () -> marcheQueryService.recupererEtValiderMarche(MARCHE_ID)
        );

        assertNotNull(exception);
    }

    @Test
    void shouldCallRepository_WhenRecuperantEtValidantMarche_GivenMarcheId() {
        when(marcheQueryRepository.recupererMarcheParId(MARCHE_ID))
                .thenReturn(Optional.of(marche));

        marcheQueryService.recupererEtValiderMarche(MARCHE_ID);

        verify(marcheQueryRepository).recupererMarcheParId(MARCHE_ID);
    }

    @Test
    void shouldReturnAllMarches_WhenRecuperantTousMarche_GivenNothing() {
        when(marcheQueryRepository.recupererTousMarche()).thenReturn(marches);

        Set<Marche> result = marcheQueryService.recupererTousMarche();

        assertEquals(marches, result);
        assertEquals(2, result.size());
    }

    @Test
    void shouldCallRepository_WhenRecuperantTousMarche_GivenNothing() {
        when(marcheQueryRepository.recupererTousMarche()).thenReturn(marches);

        marcheQueryService.recupererTousMarche();

        verify(marcheQueryRepository).recupererTousMarche();
    }
}