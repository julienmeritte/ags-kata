package com.ags.kata.application.service;

import com.ags.kata.application.dto.request.CreationOffreRequestDto;
import com.ags.kata.application.dto.response.OffreResponseDto;
import com.ags.kata.application.port.in.AllouerBlocUseCase;
import com.ags.kata.application.port.in.CreerOffreUseCase;
import com.ags.kata.application.port.in.ValiderMarcheUseCase;
import com.ags.kata.application.port.out.OffreCommandRepository;
import com.ags.kata.domain.model.marche.MarcheId;
import com.ags.kata.domain.model.offre.Offre;
import com.ags.kata.domain.model.offre.OffreId;
import com.ags.kata.infrastructure.adapter.web.offre.OffreWebMapper;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Transactional
public class OffreOrchestrationService implements CreerOffreUseCase {

    private final OffreCommandRepository offreCommandRepository;
    private final OffreWebMapper offreWebMapper;
    private final AllouerBlocUseCase allouerBlocUseCase;
    private final ValiderMarcheUseCase validerMarcheUseCase;

    @Autowired
    public OffreOrchestrationService(OffreCommandRepository offreCommandRepository, OffreWebMapper offreWebMapper, AllouerBlocUseCase allouerBlocUseCase, ValiderMarcheUseCase validerMarcheUseCase) {
        this.validerMarcheUseCase = validerMarcheUseCase;
        this.offreCommandRepository = offreCommandRepository;
        this.offreWebMapper = offreWebMapper;
        this.allouerBlocUseCase = allouerBlocUseCase;
    }

    @Override
    public OffreResponseDto creerOffre(CreationOffreRequestDto creationOffreRequestDto) {
        var marche = validerMarcheUseCase.recupererEtValiderMarche(new MarcheId(creationOffreRequestDto.marcheId()));

        var blocsAlloues = allouerBlocUseCase.recupererEtAllouerBlocs(creationOffreRequestDto.blocs(), creationOffreRequestDto.jour());

        var offre = new Offre(new OffreId(UUID.randomUUID()), Offre.ACTEUR_AGREGIO,
                marche, creationOffreRequestDto.jour(), blocsAlloues);

        return offreWebMapper.toDto(offreCommandRepository.save(offre));
    }
}
