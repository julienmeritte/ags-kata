package com.ags.kata.utils;

import com.ags.kata.application.dto.request.CreationParcRequestDto;
import com.ags.kata.application.dto.response.ParcResponseDto;
import com.ags.kata.domain.model.parc.Parc;
import com.ags.kata.domain.model.parc.ParcId;
import com.ags.kata.domain.model.parc.ParcType;

import java.util.UUID;

public final class ParcUtils {

    private ParcUtils() {
    }

    public static final ParcId PARC_ID = new ParcId(UUID.randomUUID());
    public static final ParcId PARC_ID_BIS = new ParcId(UUID.randomUUID());
    public static final String PARC_NOM = "Parc du test";
    public static final ParcType PARC_TYPE = ParcType.HYDRAULIQUE;
    public static final int PARC_CAPACITE_HORAIRE_MW = 25684;

    public static Parc creerParc() {
        return new Parc(PARC_ID, PARC_NOM, PARC_TYPE, PARC_CAPACITE_HORAIRE_MW);
    }

    public static ParcResponseDto creerParcResponseDto() {
        return new ParcResponseDto(PARC_ID.id(), PARC_NOM, PARC_TYPE, PARC_CAPACITE_HORAIRE_MW);
    }

    public static CreationParcRequestDto creerCreationParcRequestDto() {
        return new CreationParcRequestDto(PARC_NOM, PARC_TYPE, PARC_CAPACITE_HORAIRE_MW);
    }
}
