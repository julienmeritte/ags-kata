package com.ags.kata.infrastructure.adapter.persistence.parc;

import com.ags.kata.domain.model.parc.Parc;
import com.ags.kata.domain.model.parc.ParcId;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.util.Set;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ParcMapper {

    Parc toDomain(ParcEntity entity);

    Set<Parc> toDomain(Set<ParcEntity> entities);

    ParcEntity toEntity(Parc parc);

    default ParcId map(long id) {
        return new ParcId(id);
    }

    default long map(ParcId parcId) {
        return parcId.id();
    }
}
