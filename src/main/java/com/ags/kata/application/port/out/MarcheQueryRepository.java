package com.ags.kata.application.port.out;

import com.ags.kata.domain.model.marche.Marche;
import com.ags.kata.domain.model.marche.MarcheId;

import java.util.Optional;

public interface MarcheQueryRepository {

    Optional<Marche> recupererMarcheParId(MarcheId marcheId);
}
