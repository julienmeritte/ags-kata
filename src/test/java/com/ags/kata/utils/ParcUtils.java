package com.ags.kata.utils;

import com.ags.kata.domain.model.parc.Parc;
import com.ags.kata.domain.model.parc.ParcId;
import com.ags.kata.domain.model.parc.ParcType;

public class ParcUtils {

    public static ParcId PARC_ID = new ParcId(54L);
    public static ParcType PARC_TYPE = ParcType.HYDRAULIQUE;
    public static int PARC_CAPACITE_HORAIRE_MW = 25684;

    public static Parc creerParc() {
        return new Parc(PARC_ID, PARC_TYPE, PARC_CAPACITE_HORAIRE_MW);
    }
}
