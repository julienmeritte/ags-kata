package com.ags.kata.infrastructure.adapter.persistence.parc;

import com.ags.kata.domain.model.parc.Parc;
import com.ags.kata.domain.model.parc.ParcId;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ParcMapper {

    Parc toDomain(ParcEntity entity);

    Set<Parc> toDomain(Set<ParcEntity> entities);

    Set<Parc> toDomain(List<ParcEntity> entities);

    ParcEntity toEntity(Parc parc);

    default ParcId map(UUID id) {
        return new ParcId(id);
    }

    default UUID map(ParcId parcId) {
        return parcId.id();
    }
}
