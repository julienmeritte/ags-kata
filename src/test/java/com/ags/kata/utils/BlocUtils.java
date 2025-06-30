package com.ags.kata.utils;

import com.ags.kata.application.dto.request.BlocRequestDto;
import com.ags.kata.application.dto.response.BlocResponseDto;
import com.ags.kata.domain.model.bloc.Bloc;
import com.ags.kata.domain.model.bloc.BlocId;

import java.math.BigDecimal;
import java.util.Set;
import java.util.UUID;

import static com.ags.kata.utils.AllocationParcUtils.creerAllocationParcResponseDto;

public final class BlocUtils {

    private BlocUtils() {
    }

    public static final BlocId BLOC_ID = new BlocId(UUID.randomUUID());
    public static final int BLOC_QUANTITE_ENERGIE_MW = 8;
    public static final int BLOC_POSITION_JOURNEE = 0;
    public static final BigDecimal BLOC_PRIX_PLANCHER = BigDecimal.valueOf(200);

    public static Bloc creerBloc() {
        return new Bloc(BLOC_ID, BLOC_QUANTITE_ENERGIE_MW, BLOC_PRIX_PLANCHER, BLOC_POSITION_JOURNEE, Set.of());
    }

    public static BlocRequestDto creerBlocRequestDto() {
        return new BlocRequestDto(BLOC_PRIX_PLANCHER.doubleValue(), BLOC_QUANTITE_ENERGIE_MW, BLOC_POSITION_JOURNEE);
    }

    public static BlocResponseDto creerBlocResponseDto() {
        return new BlocResponseDto(BLOC_ID.id(), BLOC_QUANTITE_ENERGIE_MW, BLOC_POSITION_JOURNEE, BLOC_PRIX_PLANCHER, Set.of(creerAllocationParcResponseDto()));
    }
}
