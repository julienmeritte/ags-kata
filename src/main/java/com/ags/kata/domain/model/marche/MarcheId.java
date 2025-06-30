package com.ags.kata.domain.model.marche;

import java.util.UUID;

public record MarcheId(UUID id) {

    public MarcheId {
        if (id == null) {
            id = UUID.randomUUID();
        }
    }
}
