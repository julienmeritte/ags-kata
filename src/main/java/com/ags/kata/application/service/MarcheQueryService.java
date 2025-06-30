package com.ags.kata.application.service;

import com.ags.kata.application.port.in.ValiderMarcheUseCase;
import com.ags.kata.application.port.out.MarcheQueryRepository;
import com.ags.kata.domain.model.marche.Marche;
import com.ags.kata.domain.model.marche.MarcheId;
import com.ags.kata.infrastructure.exception.MarcheNonTrouveException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class MarcheQueryService implements ValiderMarcheUseCase {

    private final MarcheQueryRepository marcheQueryRepository;

    @Autowired
    public MarcheQueryService(MarcheQueryRepository marcheQueryRepository) {
        this.marcheQueryRepository = marcheQueryRepository;
    }

    @Override
    public Marche recupererEtValiderMarche(MarcheId marcheId) {

        return marcheQueryRepository.recupererMarcheParId(marcheId)
                .orElseThrow(() -> new MarcheNonTrouveException(marcheId));
    }

    @Override
    public Set<Marche> recupererTousMarche() {
        return marcheQueryRepository.recupererTousMarche();
    }
}
