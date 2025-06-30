package com.ags.kata.infrastructure.adapter.persistence.bloc;

import com.ags.kata.domain.model.bloc.Bloc;
import com.ags.kata.domain.model.bloc.BlocId;
import com.ags.kata.infrastructure.adapter.persistence.allocation_parc.AllocationParcMapper;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.util.Set;
import java.util.UUID;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, uses = {AllocationParcMapper.class})
public interface BlocMapper {

    Bloc toDomain(BlocEntity entity);

    Set<Bloc> toDomain(Set<BlocEntity> entities);

    BlocEntity toEntity(Bloc bloc);

    Set<BlocEntity> toEntity(Set<Bloc> bloc);

    default BlocId map(UUID id) {
        return new BlocId(id);
    }

    default UUID map(BlocId blocId) {
        return blocId.id();
    }
}