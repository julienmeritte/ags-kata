package com.ags.kata.infrastructure.adapter.persistence.marche;

import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface MarcheJpaRepository extends CrudRepository<MarcheEntity, Long> {

    Optional<MarcheEntity> findById(UUID id);

    List<MarcheEntity> findAll();
}
