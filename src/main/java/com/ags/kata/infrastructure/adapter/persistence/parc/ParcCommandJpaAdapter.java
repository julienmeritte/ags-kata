package com.ags.kata.infrastructure.adapter.persistence.parc;

import com.ags.kata.application.port.out.ParcCommandRepository;
import com.ags.kata.domain.model.parc.Parc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ParcCommandJpaAdapter implements ParcCommandRepository {

    private final ParcJpaRepository parcJpaRepository;

    private final ParcMapper parcMapper;

    @Autowired
    public ParcCommandJpaAdapter(ParcJpaRepository parcJpaRepository, ParcMapper parcMapper) {
        this.parcJpaRepository = parcJpaRepository;
        this.parcMapper = parcMapper;
    }

    @Override
    public Parc save(Parc parc) {
        return parcMapper.toDomain(parcJpaRepository.save(parcMapper.toEntity(parc)));
    }
}
