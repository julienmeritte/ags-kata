package com.ags.kata.infrastructure.adapter.persistence.offre;

import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.UUID;

public interface OffreJpaRepository extends CrudRepository<OffreEntity, Long> {

    List<OffreEntity> findByMarcheIdAndActeur(UUID marcheId, String acteur);
}
