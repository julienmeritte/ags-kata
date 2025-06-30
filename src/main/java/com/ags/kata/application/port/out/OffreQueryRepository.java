package com.ags.kata.application.port.out;

import com.ags.kata.domain.model.marche.MarcheId;
import com.ags.kata.domain.model.offre.Offre;

import java.util.Set;

public interface OffreQueryRepository {

    Set<Offre> recupererOffresParMarcheSelonActeur(MarcheId marcheId, String acteur);
}
