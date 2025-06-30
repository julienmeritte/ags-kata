package com.ags.kata.application.port.out;

import com.ags.kata.domain.model.marche.MarcheId;
import com.ags.kata.domain.model.parc.Parc;
import com.ags.kata.domain.model.parc.ParcAvecCapacite;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public interface ParcQueryRepository {

    List<ParcAvecCapacite> findParcAvecCapaciteDisponiblesPourBloc(
            int quantiteRequise,
            LocalDate date,
            int positionJournee
    );

    Set<Parc> recupererParcsVendantParMarche(MarcheId marcheId);
}
