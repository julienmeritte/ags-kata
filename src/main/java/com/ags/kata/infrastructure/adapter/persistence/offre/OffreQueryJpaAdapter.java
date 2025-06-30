package com.ags.kata.infrastructure.adapter.persistence.offre;

import com.ags.kata.application.port.out.OffreQueryRepository;
import com.ags.kata.domain.model.marche.MarcheId;
import com.ags.kata.domain.model.offre.Offre;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public class OffreQueryJpaAdapter implements OffreQueryRepository {

    private final OffreJpaRepository offreJpaRepository;
    private final OffreMapper offreMapper;

    public OffreQueryJpaAdapter(OffreJpaRepository offreJpaRepository, OffreMapper offreMapper) {
        this.offreJpaRepository = offreJpaRepository;
        this.offreMapper = offreMapper;
    }

    @Override
    public Set<Offre> recupererOffresParMarcheSelonActeur(MarcheId marcheId, String acteur) {
        return offreMapper.toDomain(offreJpaRepository.findByMarcheIdAndActeur(marcheId.id(), acteur));
    }
}
