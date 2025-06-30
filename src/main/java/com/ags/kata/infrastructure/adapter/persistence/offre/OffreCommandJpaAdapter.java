package com.ags.kata.infrastructure.adapter.persistence.offre;

import com.ags.kata.application.port.out.OffreCommandRepository;
import com.ags.kata.domain.model.offre.Offre;
import org.springframework.stereotype.Repository;

@Repository
public class OffreCommandJpaAdapter implements OffreCommandRepository {

    private final OffreJpaRepository offreJpaRepository;
    private final OffreMapper offreMapper;

    public OffreCommandJpaAdapter(OffreJpaRepository offreJpaRepository, OffreMapper offreMapper) {
        this.offreJpaRepository = offreJpaRepository;
        this.offreMapper = offreMapper;
    }

    @Override
    public Offre save(Offre offre) {
        var offreEntity = offreMapper.toEntity(offre);
        return offreMapper.toDomain(offreJpaRepository.save(offreEntity));
    }
}
