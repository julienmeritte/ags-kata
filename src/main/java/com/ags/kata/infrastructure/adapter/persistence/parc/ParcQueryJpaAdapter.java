package com.ags.kata.infrastructure.adapter.persistence.parc;

import com.ags.kata.application.port.out.ParcQueryRepository;
import com.ags.kata.domain.model.parc.ParcId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ParcQueryJpaAdapter implements ParcQueryRepository {

    private final ParcJpaRepository parcJpaRepository;

    @Autowired
    public ParcQueryJpaAdapter(ParcJpaRepository parcJpaRepository) {
        this.parcJpaRepository = parcJpaRepository;
    }

    @Override
    public ParcId prochainId() {
        return new ParcId(parcJpaRepository.prochainId());
    }
}
