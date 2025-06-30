package com.ags.kata.application.service;

import com.ags.kata.application.dto.request.BlocRequestDto;
import com.ags.kata.application.port.in.RecupererParcUseCase;
import com.ags.kata.domain.model.bloc.Bloc;
import com.ags.kata.domain.model.parc.ParcAvecCapacite;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import static com.ags.kata.utils.ParcUtils.creerParc;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AllouerServiceTest {


    @Mock
    private RecupererParcUseCase recupererParcUseCase;

    @InjectMocks
    private AllouerService allouerService;

    private BlocRequestDto blocRequest;
    private LocalDate jour;
    private List<ParcAvecCapacite> parcs;

    @BeforeEach
    void setUp() {
        blocRequest = new BlocRequestDto(100, 150, 0);
        jour = LocalDate.of(2025, 6, 30);
        parcs = List.of(new ParcAvecCapacite(creerParc(), 200));
    }

    @Test
    void shouldReturnBlocs_WhenRecupererEtAllouerBlocs_GivenBlocRequest() {
        when(recupererParcUseCase.recupererParcsAvecCapaciteDisponiblesPeriode(blocRequest, jour))
                .thenReturn(parcs);

        Set<Bloc> result = allouerService.recupererEtAllouerBlocs(Set.of(blocRequest), jour);

        assertNotNull(result);
        assertEquals(1, result.size());

        Bloc bloc = result.iterator()
                .next();
        assertEquals(150, bloc.getQuantiteEnergieMW());
        assertEquals(0, bloc.getPositionJournee());
    }

    @Test
    void shouldReturnMultipleBlocs_WhenRecuperantEtAllouantBlocs_GivenMultipleBlocRequests() {
        BlocRequestDto bloc2 = new BlocRequestDto(80, 120, 1);
        when(recupererParcUseCase.recupererParcsAvecCapaciteDisponiblesPeriode(any(), any()))
                .thenReturn(parcs);

        Set<Bloc> result = allouerService.recupererEtAllouerBlocs(Set.of(blocRequest, bloc2), jour);

        assertEquals(2, result.size());
    }

    @Test
    void shouldReturnEmptySet_WhenRecuperantEtAllouantBlocs_GivenEmptyRequests() {
        Set<Bloc> result = allouerService.recupererEtAllouerBlocs(Set.of(), jour);

        assertTrue(result.isEmpty());
        verifyNoInteractions(recupererParcUseCase);
    }

    @Test
    void shouldCallRecupererParcUseCase_WhenRecupererEtAllouerBlocs_GivenBlocRequest() {
        when(recupererParcUseCase.recupererParcsAvecCapaciteDisponiblesPeriode(blocRequest, jour))
                .thenReturn(parcs);

        allouerService.recupererEtAllouerBlocs(Set.of(blocRequest), jour);
        verify(recupererParcUseCase).recupererParcsAvecCapaciteDisponiblesPeriode(blocRequest, jour);
    }
}