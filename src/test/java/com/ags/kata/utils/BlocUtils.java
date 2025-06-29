package com.ags.kata.utils;

import com.ags.kata.domain.model.bloc.Bloc;
import com.ags.kata.domain.model.bloc.BlocId;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

public class BlocUtils {

    public static BlocId BLOC_ID = new BlocId(109L);
    public static int BLOC_QUANTITE_ENERGIE_MW = 8;
    public static int BLOC_POSITION_JOURNEE = 0;
    public static BigDecimal BLOC_PRIX_PLANCHER = BigDecimal.valueOf(200);
    public static LocalDate BLOC_JOUR = LocalDate.of(2025, 7, 2);

    public static Bloc creerBloc() {
        return new Bloc(BLOC_ID, BLOC_QUANTITE_ENERGIE_MW, BLOC_PRIX_PLANCHER, BLOC_POSITION_JOURNEE, BLOC_JOUR, Set.of());
    }
}
