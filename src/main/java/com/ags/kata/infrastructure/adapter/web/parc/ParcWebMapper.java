package com.ags.kata.infrastructure.adapter.web.parc;

import com.ags.kata.application.dto.response.ParcResponseDto;
import com.ags.kata.domain.model.parc.Parc;
import com.ags.kata.domain.model.parc.ParcId;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ParcWebMapper {

    ParcResponseDto toResponseDto(Parc parc);

    default long map(ParcId parcId) {
        return parcId.id();
    }
}
