package com.ags.kata.application.service;

import com.ags.kata.application.dto.request.BlocRequestDto;
import com.ags.kata.application.port.out.ParcQueryRepository;
import com.ags.kata.domain.model.parc.Parc;
import com.ags.kata.domain.model.parc.ParcAvecCapacite;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;

import static com.ags.kata.utils.BlocUtils.*;
import static com.ags.kata.utils.ParcUtils.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ParcQueryServiceTest {
    @Mock
    private ParcQueryRepository parcQueryRepository;

    @InjectMocks
    private ParcQueryService parcQueryService;

    private BlocRequestDto blocRequest;
    private LocalDate jour;
    private List<ParcAvecCapacite> parcsDisponibles;

    @BeforeEach
    void setUp() {
        blocRequest = new BlocRequestDto(BLOC_PRIX_PLANCHER.doubleValue(), BLOC_QUANTITE_ENERGIE_MW, BLOC_POSITION_JOURNEE);
        jour = LocalDate.of(2025, 6, 30);
        parcsDisponibles = List.of(
                new ParcAvecCapacite(creerParc(), 200),
                new ParcAvecCapacite(new Parc(PARC_ID_BIS, PARC_NOM, PARC_TYPE, PARC_CAPACITE_HORAIRE_MW + 5000), 150));
    }

    @Test
    void shouldReturnParcsDisponibles_WhenRecuperantParcsAvecCapaciteDisponiblesPeriode_GivenBlocRequest() {
        when(parcQueryRepository.findParcAvecCapaciteDisponiblesPourBloc(
                blocRequest.quantiteEnergieMW(),
                jour,
                blocRequest.positionJournee()))
                .thenReturn(parcsDisponibles);

        List<ParcAvecCapacite> result = parcQueryService.recupererParcsAvecCapaciteDisponiblesPeriode(blocRequest, jour);

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals(parcsDisponibles, result);
    }

    @Test
    void shouldReturnEmptyList_WhenRecuperantParcsAvecCapaciteDisponiblesPeriode_GivenNoParcsAvailable() {
        when(parcQueryRepository.findParcAvecCapaciteDisponiblesPourBloc(
                blocRequest.quantiteEnergieMW(),
                jour,
                blocRequest.positionJournee()))
                .thenReturn(List.of());

        List<ParcAvecCapacite> result = parcQueryService.recupererParcsAvecCapaciteDisponiblesPeriode(blocRequest, jour);

        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    @Test
    void shouldCallRepository_WhenRecuperantParcsAvecCapaciteDisponiblesPeriode_GivenBlocRequest() {
        when(parcQueryRepository.findParcAvecCapaciteDisponiblesPourBloc(
                blocRequest.quantiteEnergieMW(),
                jour,
                blocRequest.positionJournee()))
                .thenReturn(parcsDisponibles);

        parcQueryService.recupererParcsAvecCapaciteDisponiblesPeriode(blocRequest, jour);

        verify(parcQueryRepository).findParcAvecCapaciteDisponiblesPourBloc(
                blocRequest.quantiteEnergieMW(),
                jour,
                blocRequest.positionJournee()
        );
    }
}