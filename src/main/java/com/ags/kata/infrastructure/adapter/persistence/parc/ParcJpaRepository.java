package com.ags.kata.infrastructure.adapter.persistence.parc;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface ParcJpaRepository extends CrudRepository<ParcEntity, Long> {

    @Query(value = "SELECT NEXT VALUE FOR parc_seq", nativeQuery = true)
    Long prochainId();
}
