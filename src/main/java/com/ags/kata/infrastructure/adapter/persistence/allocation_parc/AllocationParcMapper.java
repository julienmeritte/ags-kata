package com.ags.kata.infrastructure.adapter.persistence.allocation_parc;

import com.ags.kata.domain.model.allocation_parc.AllocationParc;
import com.ags.kata.domain.model.allocation_parc.AllocationParcId;
import com.ags.kata.domain.model.parc.ParcId;
import com.ags.kata.infrastructure.adapter.persistence.parc.ParcEntity;
import com.ags.kata.infrastructure.adapter.persistence.parc.ParcMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.Named;

import java.util.Set;
import java.util.UUID;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, uses = {ParcMapper.class})
public interface AllocationParcMapper {

    @Mapping(source = "parc", target = "parcId", qualifiedByName = "extractParcId")
    AllocationParc toDomain(AllocationParcEntity entity);

    Set<AllocationParc> toDomain(Set<AllocationParcEntity> entity);

    @Mapping(source = "parcId", target = "parc", qualifiedByName = "createParcReference")
    AllocationParcEntity toEntity(AllocationParc allocationParc);

    Set<AllocationParcEntity> toEntity(Set<AllocationParc> allocationParc);

    default AllocationParcId map(UUID id) {
        return new AllocationParcId(id);
    }

    default UUID map(AllocationParcId allocationParcId) {
        return allocationParcId.id();
    }

    @Named("createParcReference")
    default ParcEntity createParcReference(ParcId parcId) {
        if (parcId == null) return null;
        ParcEntity parc = new ParcEntity();
        parc.setId(parcId.id());
        return parc;
    }

    @Named("extractParcId")
    default ParcId extractParcId(ParcEntity parcEntity) {
        if (parcEntity == null || parcEntity.getId() == null) {
            return null;
        }
        return new ParcId(parcEntity.getId());
    }
}