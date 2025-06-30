package com.ags.kata.infrastructure.exception;

import com.ags.kata.domain.model.marche.MarcheId;

public class MarcheNonTrouveException extends RuntimeException {
    public MarcheNonTrouveException(MarcheId marcheId) {
        super("Marché non trouvé pour l'id -> " + marcheId.id());
    }
}