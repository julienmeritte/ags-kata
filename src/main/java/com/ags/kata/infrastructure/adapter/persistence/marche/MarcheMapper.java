package com.ags.kata.infrastructure.adapter.persistence.marche;

import com.ags.kata.domain.model.marche.Marche;
import com.ags.kata.domain.model.marche.MarcheId;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.util.Optional;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface MarcheMapper {

    Marche toDomain(MarcheEntity entity);

    default MarcheId map(long id) {
        return new MarcheId(id);
    }

    default Optional<Marche> toDomain(Optional<MarcheEntity> marcheEntity) {
        return marcheEntity.map(this::toDomain);
    }
}
