package com.ags.kata.utils;

import com.ags.kata.application.dto.response.AllocationParcResponseDto;
import com.ags.kata.domain.model.allocation_parc.AllocationParc;
import com.ags.kata.domain.model.allocation_parc.AllocationParcId;

import java.util.UUID;

import static com.ags.kata.utils.ParcUtils.PARC_ID;

public final class AllocationParcUtils {

    private AllocationParcUtils() {
    }

    public static final AllocationParcId ALLOCATION_PARC_ID = new AllocationParcId(UUID.randomUUID());
    public static final int ALLOCATION_PARC_QUANTITE = 8;

    public static AllocationParc creerAllocationParc() {
        return new AllocationParc(ALLOCATION_PARC_ID, PARC_ID, ALLOCATION_PARC_QUANTITE);
    }

    public static AllocationParcResponseDto creerAllocationParcResponseDto() {
        return new AllocationParcResponseDto(ALLOCATION_PARC_ID.id(), PARC_ID.id(), ALLOCATION_PARC_QUANTITE);
    }
}
