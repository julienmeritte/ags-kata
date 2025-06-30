package com.ags.kata.infrastructure.adapter.persistence.parc;

import com.ags.kata.application.port.out.ParcQueryRepository;
import com.ags.kata.domain.model.marche.MarcheId;
import com.ags.kata.domain.model.parc.Parc;
import com.ags.kata.domain.model.parc.ParcAvecCapacite;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Repository
public class ParcQueryJpaAdapter implements ParcQueryRepository {

    private final ParcJpaRepository parcJpaRepository;
    private final ParcMapper parcMapper;

    @Autowired
    public ParcQueryJpaAdapter(ParcJpaRepository parcJpaRepository, ParcMapper parcMapper) {
        this.parcJpaRepository = parcJpaRepository;
        this.parcMapper = parcMapper;
    }

    @Override
    public List<ParcAvecCapacite> findParcAvecCapaciteDisponiblesPourBloc(int quantiteRequise, LocalDate jour, int positionJournee) {
        return parcJpaRepository.findParcsAvecCapaciteRestante(jour, positionJournee)
                .stream()
                .map(p -> new ParcAvecCapacite(parcMapper.toDomain(p.getParc()), p.getCapaciteRestante()))
                .toList();
    }

    @Override
    public Set<Parc> recupererParcsVendantParMarche(MarcheId marcheId) {
        return parcMapper.toDomain(parcJpaRepository.recupererParcsVendantParMarche(marcheId.id()));
    }
}
