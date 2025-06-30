package com.ags.kata.infrastructure.adapter.persistence.marche;

import com.ags.kata.domain.model.marche.Marche;
import com.ags.kata.domain.model.marche.MarcheId;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface MarcheMapper {

    Marche toDomain(MarcheEntity entity);

    Set<Marche> toDomain(List<MarcheEntity> entity);

    MarcheEntity toEntity(Marche marche);

    default MarcheId map(UUID id) {
        return new MarcheId(id);
    }

    default UUID map(MarcheId marcheId) {
        return marcheId.id();
    }

    default Optional<Marche> toDomain(Optional<MarcheEntity> marcheEntity) {
        return marcheEntity.map(this::toDomain);
    }
}
