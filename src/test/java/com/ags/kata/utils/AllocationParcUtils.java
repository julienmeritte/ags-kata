package com.ags.kata.utils;

import com.ags.kata.domain.model.allocation_parc.AllocationParc;
import com.ags.kata.domain.model.allocation_parc.AllocationParcId;
import com.ags.kata.domain.model.parc.ParcId;

import static com.ags.kata.utils.ParcUtils.PARC_ID;

public class AllocationParcUtils {

    public static final AllocationParcId ALLOCATION_PARC_ID = new AllocationParcId(23L);
    public static final int ALLOCATION_PARC_QUANTITE = 8;

    public static AllocationParc creerAllocationParc() {
        return new AllocationParc(ALLOCATION_PARC_ID, PARC_ID, ALLOCATION_PARC_QUANTITE);
    }
}
