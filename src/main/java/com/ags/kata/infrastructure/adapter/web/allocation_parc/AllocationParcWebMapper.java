package com.ags.kata.infrastructure.adapter.web.allocation_parc;

import com.ags.kata.application.dto.response.AllocationParcResponseDto;
import com.ags.kata.domain.model.allocation_parc.AllocationParc;
import com.ags.kata.domain.model.allocation_parc.AllocationParcId;
import com.ags.kata.domain.model.parc.ParcId;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.util.UUID;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface AllocationParcWebMapper {

    AllocationParcResponseDto toDto(AllocationParc allocationParc);

    default UUID map(AllocationParcId allocationParcId) {
        return allocationParcId.id();
    }

    default UUID map(ParcId parcId) {
        return parcId.id();
    }
}
