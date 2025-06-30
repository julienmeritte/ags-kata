package com.ags.kata.domain.model.offre;

import java.util.UUID;

public record OffreId(UUID id) {

    public OffreId {
        if (id == null) {
            id = UUID.randomUUID();
        }
    }
}
