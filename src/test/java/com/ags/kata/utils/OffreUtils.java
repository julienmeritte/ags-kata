package com.ags.kata.utils;

import com.ags.kata.domain.model.offre.Offre;
import com.ags.kata.domain.model.offre.OffreId;

import java.util.Set;

import static com.ags.kata.utils.BlocUtils.creerBloc;
import static com.ags.kata.utils.MarcheUtils.creerMarche;

public class OffreUtils {

    public static final OffreId OFFRE_ID = new OffreId(33L);

    public static Offre creerOffre() {
        return new Offre(OFFRE_ID, creerMarche(), Set.of(creerBloc()));
    }
}
