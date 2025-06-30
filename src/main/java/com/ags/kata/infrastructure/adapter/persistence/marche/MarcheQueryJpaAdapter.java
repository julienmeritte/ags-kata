package com.ags.kata.infrastructure.adapter.persistence.marche;

import com.ags.kata.application.port.out.MarcheQueryRepository;
import com.ags.kata.domain.model.marche.Marche;
import com.ags.kata.domain.model.marche.MarcheId;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public class MarcheQueryJpaAdapter implements MarcheQueryRepository {

    private final MarcheJpaRepository marcheJpaRepository;
    private final MarcheMapper marcheMapper;

    public MarcheQueryJpaAdapter(MarcheJpaRepository marcheJpaRepository, MarcheMapper marcheMapper) {
        this.marcheJpaRepository = marcheJpaRepository;
        this.marcheMapper = marcheMapper;
    }

    @Override
    public Optional<Marche> recupererMarcheParId(MarcheId marcheId) {
        return marcheMapper.toDomain(marcheJpaRepository.findById(marcheId.id()));
    }

    @Override
    public Set<Marche> recupererTousMarche() {
        return marcheMapper.toDomain(marcheJpaRepository.findAll());
    }
}
