package com.ags.kata.utils;

import com.ags.kata.application.dto.request.CreationParcRequestDto;
import com.ags.kata.application.dto.response.ParcResponseDto;
import com.ags.kata.domain.model.parc.Parc;
import com.ags.kata.domain.model.parc.ParcId;
import com.ags.kata.domain.model.parc.ParcType;

public final class ParcUtils {

    private ParcUtils() {
    }

    public static ParcId PARC_ID = new ParcId(54L);
    public static String PARC_NOM = "Parc du test";
    public static ParcType PARC_TYPE = ParcType.HYDRAULIQUE;
    public static int PARC_CAPACITE_HORAIRE_MW = 25684;

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
