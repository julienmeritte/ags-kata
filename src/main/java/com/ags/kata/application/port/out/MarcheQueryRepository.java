package com.ags.kata.application.port.out;

import com.ags.kata.domain.model.marche.Marche;
import com.ags.kata.domain.model.marche.MarcheId;

import java.util.Optional;
import java.util.Set;

public interface MarcheQueryRepository {

    Optional<Marche> recupererMarcheParId(MarcheId marcheId);

    Set<Marche> recupererTousMarche();
}
