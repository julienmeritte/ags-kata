package com.ags.kata.application.port.out;

import com.ags.kata.domain.model.parc.Parc;
import com.ags.kata.domain.model.parc.ParcId;

import java.time.LocalDate;
import java.util.Set;

public interface ParcQueryRepository {

    ParcId prochainId();

    Set<Parc> findParcsDisponiblesPourBloc(
            int quantiteRequise,
            LocalDate date,
            int positionJournee
    );
}
