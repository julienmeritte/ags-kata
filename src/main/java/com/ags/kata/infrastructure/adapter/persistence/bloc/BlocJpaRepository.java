package com.ags.kata.infrastructure.adapter.persistence.bloc;

import org.springframework.data.repository.CrudRepository;

public interface BlocJpaRepository extends CrudRepository<BlocEntity, Long> {
}
