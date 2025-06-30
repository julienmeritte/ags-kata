package com.ags.kata.infrastructure.adapter.web.bloc;

import com.ags.kata.application.dto.response.BlocResponseDto;
import com.ags.kata.domain.model.bloc.Bloc;
import com.ags.kata.domain.model.bloc.BlocId;
import com.ags.kata.infrastructure.adapter.web.allocation_parc.AllocationParcWebMapper;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.util.UUID;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, uses = {AllocationParcWebMapper.class})
public interface BlocWebMapper {

    BlocResponseDto toResponseDto(Bloc bloc);

    default UUID map(BlocId blocId) {
        return blocId.id();
    }
}
