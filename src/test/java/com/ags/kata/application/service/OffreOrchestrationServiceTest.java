package com.ags.kata.application.service;

import com.ags.kata.application.dto.request.BlocRequestDto;
import com.ags.kata.application.dto.request.CreationOffreRequestDto;
import com.ags.kata.application.dto.response.MarcheResponseDto;
import com.ags.kata.application.dto.response.OffreResponseDto;
import com.ags.kata.application.port.in.AllouerBlocUseCase;
import com.ags.kata.application.port.in.ValiderMarcheUseCase;
import com.ags.kata.application.port.out.OffreCommandRepository;
import com.ags.kata.domain.model.bloc.Bloc;
import com.ags.kata.domain.model.marche.Marche;
import com.ags.kata.domain.model.marche.MarcheId;
import com.ags.kata.domain.model.offre.Offre;
import com.ags.kata.infrastructure.adapter.web.offre.OffreWebMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Set;

import static com.ags.kata.utils.BlocUtils.*;
import static com.ags.kata.utils.MarcheUtils.*;
import static com.ags.kata.utils.OffreUtils.OFFRE_ACTEUR;
import static com.ags.kata.utils.OffreUtils.OFFRE_ID;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OffreOrchestrationServiceTest {

    @Mock
    private OffreCommandRepository offreCommandRepository;

    @Mock
    private OffreWebMapper offreWebMapper;

    @Mock
    private AllouerBlocUseCase allouerBlocUseCase;

    @Mock
    private ValiderMarcheUseCase validerMarcheUseCase;

    @InjectMocks
    private OffreOrchestrationService offreOrchestrationService;

    private CreationOffreRequestDto creationRequest;
    private Marche marche;
    private Set<Bloc> blocsAlloues;
    private Offre offre;
    private OffreResponseDto offreResponse;

    @BeforeEach
    void setUp() {
        LocalDate jour = LocalDate.of(2025, 6, 30);
        BlocRequestDto blocRequest = new BlocRequestDto(BLOC_PRIX_PLANCHER.doubleValue(), BLOC_QUANTITE_ENERGIE_MW, BLOC_POSITION_JOURNEE);

        creationRequest = new CreationOffreRequestDto(MARCHE_ID.id(), jour, Set.of(blocRequest));

        marche = creerMarche();

        Bloc bloc = creerBloc();
        blocsAlloues = Set.of(bloc);

        offre = new Offre(OFFRE_ID, OFFRE_ACTEUR, marche, jour, blocsAlloues);

        offreResponse = new OffreResponseDto(OFFRE_ID.id(), OFFRE_ACTEUR, new MarcheResponseDto(MARCHE_ID.id(), MARCHE_NOM), jour, Set.of());
    }

    @Test
    void shouldReturnOffreResponse_WhenCreantOffre_GivenValidCreationRequest() {
        when(validerMarcheUseCase.recupererEtValiderMarche(any(MarcheId.class))).thenReturn(marche);
        when(allouerBlocUseCase.recupererEtAllouerBlocs(creationRequest.blocs(), creationRequest.jour())).thenReturn(blocsAlloues);
        when(offreCommandRepository.save(any(Offre.class))).thenReturn(offre);
        when(offreWebMapper.toDto(offre)).thenReturn(offreResponse);

        OffreResponseDto result = offreOrchestrationService.creerOffre(creationRequest);

        assertNotNull(result);
        assertEquals(offreResponse, result);
    }

    @Test
    void shouldCallValiderMarcheUseCase_WhenCreantOffre_GivenCreationRequest() {
        when(validerMarcheUseCase.recupererEtValiderMarche(any(MarcheId.class))).thenReturn(marche);
        when(allouerBlocUseCase.recupererEtAllouerBlocs(any(), any())).thenReturn(blocsAlloues);
        when(offreCommandRepository.save(any(Offre.class))).thenReturn(offre);
        when(offreWebMapper.toDto(any(Offre.class))).thenReturn(offreResponse);

        offreOrchestrationService.creerOffre(creationRequest);

        verify(validerMarcheUseCase).recupererEtValiderMarche(any(MarcheId.class));
    }

    @Test
    void shouldCallAllouerBlocUseCase_WhenCreantOffre_GivenCreationRequest() {
        when(validerMarcheUseCase.recupererEtValiderMarche(any(MarcheId.class))).thenReturn(marche);
        when(allouerBlocUseCase.recupererEtAllouerBlocs(creationRequest.blocs(), creationRequest.jour())).thenReturn(blocsAlloues);
        when(offreCommandRepository.save(any(Offre.class))).thenReturn(offre);
        when(offreWebMapper.toDto(any(Offre.class))).thenReturn(offreResponse);

        offreOrchestrationService.creerOffre(creationRequest);

        verify(allouerBlocUseCase).recupererEtAllouerBlocs(creationRequest.blocs(), creationRequest.jour());
    }

    @Test
    void shouldSaveOffre_WhenCreantOffre_GivenCreationRequest() {
        when(validerMarcheUseCase.recupererEtValiderMarche(any(MarcheId.class))).thenReturn(marche);
        when(allouerBlocUseCase.recupererEtAllouerBlocs(any(), any())).thenReturn(blocsAlloues);
        when(offreCommandRepository.save(any(Offre.class))).thenReturn(offre);
        when(offreWebMapper.toDto(any(Offre.class))).thenReturn(offreResponse);

        offreOrchestrationService.creerOffre(creationRequest);

        verify(offreCommandRepository).save(any(Offre.class));
        verify(offreWebMapper).toDto(any(Offre.class));
    }
}