package com.ags.kata.application.port.out;

import com.ags.kata.domain.model.offre.Offre;

public interface OffreCommandRepository {
    Offre save(Offre offre);
}
