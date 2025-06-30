package com.ags.kata.domain.model.parc;

import java.util.UUID;

public record ParcId(UUID id) {

    public ParcId {
        if (id == null) {
            id = UUID.randomUUID();
        }
    }
}
