package com.ags.kata.utils;

import com.ags.kata.application.dto.request.CreationOffreRequestDto;
import com.ags.kata.application.dto.response.OffreResponseDto;
import com.ags.kata.domain.model.offre.Offre;
import com.ags.kata.domain.model.offre.OffreId;

import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

import static com.ags.kata.utils.BlocUtils.*;
import static com.ags.kata.utils.MarcheUtils.*;

public final class OffreUtils {

    private OffreUtils() {
    }

    public static final OffreId OFFRE_ID = new OffreId(UUID.randomUUID());
    public static final String OFFRE_ACTEUR = "Agregio Solutions";
    public static final LocalDate OFFRE_JOUR = LocalDate.now();

    public static Offre creerOffre() {
        return new Offre(OFFRE_ID, OFFRE_ACTEUR, creerMarche(), OFFRE_JOUR, Set.of(creerBloc()));
    }

    public static CreationOffreRequestDto creerCreationOffreRequestDto() {
        return new CreationOffreRequestDto(MARCHE_ID.id(), OFFRE_JOUR, Set.of(creerBlocRequestDto()));
    }

    public static OffreResponseDto creerOffreResponseDto() {
        return new OffreResponseDto(OFFRE_ID.id(), OFFRE_ACTEUR, creerMarcheResponseDto(), OFFRE_JOUR, Set.of(creerBlocResponseDto()));
    }
}
