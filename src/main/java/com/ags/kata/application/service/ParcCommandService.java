package com.ags.kata.application.service;

import com.ags.kata.application.dto.request.CreationParcRequestDto;
import com.ags.kata.application.dto.response.ParcResponseDto;
import com.ags.kata.application.port.in.CreerParcUseCase;
import com.ags.kata.application.port.out.ParcCommandRepository;
import com.ags.kata.application.port.out.ParcQueryRepository;
import com.ags.kata.domain.model.parc.Parc;
import com.ags.kata.domain.model.parc.ParcId;
import com.ags.kata.infrastructure.adapter.web.parc.ParcWebMapper;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class ParcCommandService implements CreerParcUseCase {

    private final ParcQueryRepository parcQueryRepository;
    private final ParcCommandRepository parcCommandRepository;
    private final ParcWebMapper parcWebMapper;

    @Autowired
    public ParcCommandService(ParcQueryRepository parcQueryRepository, ParcCommandRepository parcCommandRepository, ParcWebMapper parcWebMapper) {
        this.parcQueryRepository = parcQueryRepository;
        this.parcCommandRepository = parcCommandRepository;
        this.parcWebMapper = parcWebMapper;
    }

    @Override
    public ParcResponseDto creerParc(@NotNull @Valid CreationParcRequestDto creationParcRequestDto) {

        ParcId parcId = parcQueryRepository.prochainId();

        var parc = new Parc(parcId,
                creationParcRequestDto.nom(),
                creationParcRequestDto.type(),
                creationParcRequestDto.capaciteHoraireMW());

        var parcCree = parcCommandRepository.save(parc);

        return parcWebMapper.toResponseDto(parcCree);
    }
}
