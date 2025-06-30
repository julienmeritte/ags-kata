package com.ags.kata.application.service;

import com.ags.kata.application.dto.request.BlocRequestDto;
import com.ags.kata.application.dto.response.ParcResponseDto;
import com.ags.kata.application.port.in.RecupererParcUseCase;
import com.ags.kata.application.port.out.ParcQueryRepository;
import com.ags.kata.domain.model.marche.MarcheId;
import com.ags.kata.domain.model.parc.ParcAvecCapacite;
import com.ags.kata.infrastructure.adapter.web.parc.ParcWebMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Service
public class ParcQueryService implements RecupererParcUseCase {
    private final ParcQueryRepository parcQueryRepository;
    private final ParcWebMapper parcWebMapper;

    @Autowired
    public ParcQueryService(ParcQueryRepository parcQueryRepository, ParcWebMapper parcWebMapper) {
        this.parcQueryRepository = parcQueryRepository;
        this.parcWebMapper = parcWebMapper;
    }

    public List<ParcAvecCapacite> recupererParcsAvecCapaciteDisponiblesPeriode(BlocRequestDto bloc, LocalDate jour) {
        return parcQueryRepository.findParcAvecCapaciteDisponiblesPourBloc(bloc.quantiteEnergieMW(), jour, bloc.positionJournee());
    }

    public Set<ParcResponseDto> recupererParcsVendantParMarche(MarcheId marcheId) {
        return parcWebMapper.toResponseDto(parcQueryRepository.recupererParcsVendantParMarche(marcheId));
    }
}
