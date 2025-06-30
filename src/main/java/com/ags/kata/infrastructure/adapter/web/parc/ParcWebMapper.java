package com.ags.kata.infrastructure.adapter.web.parc;

import com.ags.kata.application.dto.response.ParcResponseDto;
import com.ags.kata.domain.model.parc.Parc;
import com.ags.kata.domain.model.parc.ParcId;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.util.Set;
import java.util.UUID;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ParcWebMapper {

    ParcResponseDto toResponseDto(Parc parc);

    Set<ParcResponseDto> toResponseDto(Set<Parc> parc);


    default UUID map(ParcId parcId) {
        return parcId.id();
    }
}
