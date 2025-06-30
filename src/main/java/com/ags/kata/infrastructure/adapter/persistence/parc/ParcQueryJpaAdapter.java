package com.ags.kata.infrastructure.adapter.persistence.parc;

import com.ags.kata.application.port.out.ParcQueryRepository;
import com.ags.kata.domain.model.parc.Parc;
import com.ags.kata.domain.model.parc.ParcId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Set;
import java.util.stream.Collectors;

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
    public ParcId prochainId() {
        return new ParcId(parcJpaRepository.prochainId());
    }

    @Override
    public Set<Parc> findParcsDisponiblesPourBloc(int quantiteRequise, LocalDate jour, int positionJournee) {
        return parcMapper.toDomain(parcJpaRepository.findParcsDisponiblesPourBloc(quantiteRequise, jour, positionJournee)
                .stream()
                .collect(Collectors.toUnmodifiableSet()));
    }
}
