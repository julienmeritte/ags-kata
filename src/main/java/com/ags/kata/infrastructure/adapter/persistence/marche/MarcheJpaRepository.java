package com.ags.kata.infrastructure.adapter.persistence.marche;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface MarcheJpaRepository extends CrudRepository<MarcheEntity, Long> {

    Optional<MarcheEntity> findById(long id);
}
