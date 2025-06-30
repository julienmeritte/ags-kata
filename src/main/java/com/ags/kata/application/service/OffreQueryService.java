package com.ags.kata.application.service;

import com.ags.kata.application.dto.response.MarcheOffreResponseDto;
import com.ags.kata.application.port.in.RecupererOffreUseCase;
import com.ags.kata.application.port.in.ValiderMarcheUseCase;
import com.ags.kata.application.port.out.OffreQueryRepository;
import com.ags.kata.domain.model.offre.Offre;
import com.ags.kata.infrastructure.adapter.web.MarcheWebMapper;
import com.ags.kata.infrastructure.adapter.web.offre.OffreWebMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class OffreQueryService implements RecupererOffreUseCase {

    private final OffreQueryRepository offreQueryRepository;
    private final OffreWebMapper offreWebMapper;
    private final MarcheWebMapper marcheWebMapper;
    private final ValiderMarcheUseCase validerMarcheUseCase;

    @Autowired
    public OffreQueryService(OffreQueryRepository offreQueryRepository, OffreWebMapper offreWebMapper, MarcheWebMapper marcheWebMapper, ValiderMarcheUseCase validerMarcheUseCase) {
        this.offreQueryRepository = offreQueryRepository;
        this.offreWebMapper = offreWebMapper;
        this.marcheWebMapper = marcheWebMapper;
        this.validerMarcheUseCase = validerMarcheUseCase;
    }

    @Override
    public Set<MarcheOffreResponseDto> recupererOffresAgregioParMarche() {

        var marches = validerMarcheUseCase.recupererTousMarche();

        return marches.stream()
                .map(marche -> {
                    var offres = offreQueryRepository.recupererOffresParMarcheSelonActeur(
                            marche.getId(),
                            Offre.ACTEUR_AGREGIO
                    );

                    return new MarcheOffreResponseDto(marcheWebMapper.toDto(marche), offreWebMapper.toDto(offres));
                })
                .collect(Collectors.toSet());
    }
}
