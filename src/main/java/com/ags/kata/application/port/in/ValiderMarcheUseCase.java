package com.ags.kata.application.port.in;

import com.ags.kata.domain.model.marche.Marche;
import com.ags.kata.domain.model.marche.MarcheId;

import java.util.Set;

public interface ValiderMarcheUseCase {

    Marche recupererEtValiderMarche(MarcheId marcheId);

    Set<Marche> recupererTousMarche();
}
