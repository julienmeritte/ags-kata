package com.ags.kata.infrastructure.adapter.web.offre;

import com.ags.kata.application.dto.response.OffreResponseDto;
import com.ags.kata.domain.model.offre.Offre;
import com.ags.kata.domain.model.offre.OffreId;
import com.ags.kata.infrastructure.adapter.web.MarcheWebMapper;
import com.ags.kata.infrastructure.adapter.web.allocation_parc.AllocationParcWebMapper;
import com.ags.kata.infrastructure.adapter.web.bloc.BlocWebMapper;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.util.Set;
import java.util.UUID;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, uses = {MarcheWebMapper.class, BlocWebMapper.class, AllocationParcWebMapper.class})
public interface OffreWebMapper {

    OffreResponseDto toDto(Offre offre);

    Set<OffreResponseDto> toDto(Set<Offre> offre);

    default UUID map(OffreId offreId) {
        return offreId.id();
    }
}
