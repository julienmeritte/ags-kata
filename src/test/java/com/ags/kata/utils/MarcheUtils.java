package com.ags.kata.utils;

import com.ags.kata.domain.model.marche.Marche;
import com.ags.kata.domain.model.marche.MarcheId;

public class MarcheUtils {
    public static final MarcheId MARCHE_ID = new MarcheId(1L);
    public static final String MARCHE_NOM = "Réserve de Test";


    public static Marche creerMarche() {
        return new Marche(MARCHE_ID, MARCHE_NOM);
    }
}
