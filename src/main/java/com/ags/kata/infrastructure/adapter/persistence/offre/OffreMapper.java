package com.ags.kata.infrastructure.adapter.persistence.offre;

import com.ags.kata.domain.model.offre.Offre;
import com.ags.kata.domain.model.offre.OffreId;
import com.ags.kata.infrastructure.adapter.persistence.bloc.BlocMapper;
import com.ags.kata.infrastructure.adapter.persistence.marche.MarcheMapper;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, uses = {BlocMapper.class, MarcheMapper.class})
public interface OffreMapper {

    Offre toDomain(OffreEntity entity);

    Set<Offre> toDomain(Set<OffreEntity> entity);

    Set<Offre> toDomain(List<OffreEntity> entity);

    OffreEntity toEntity(Offre offre);

    Set<OffreEntity> toEntity(Set<Offre> offre);

    default OffreId map(UUID id) {
        return new OffreId(id);
    }

    default UUID map(OffreId offreId) {
        return offreId.id();
    }

    @AfterMapping
    default void synchroniserRelations(@MappingTarget OffreEntity target) {
        if (target.getBlocs() != null) {
            target.getBlocs()
                    .forEach(bloc -> {
                        bloc.setOffre(target);

                        if (bloc.getAllocations() != null) {
                            bloc.getAllocations()
                                    .forEach(allocation ->
                                            allocation.setBloc(bloc)
                                    );
                        }
                    });
        }
    }
}
