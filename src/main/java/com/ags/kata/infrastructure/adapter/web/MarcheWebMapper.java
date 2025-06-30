package com.ags.kata.infrastructure.adapter.web;

import com.ags.kata.application.dto.response.MarcheResponseDto;
import com.ags.kata.domain.model.marche.Marche;
import com.ags.kata.domain.model.marche.MarcheId;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.util.UUID;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface MarcheWebMapper {

    MarcheResponseDto toDto(Marche marche);

    default UUID map(MarcheId marcheId) {
        return marcheId.id();
    }
}
