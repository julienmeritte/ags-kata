package com.ags.kata.domain.model.bloc;

import java.util.UUID;

public record BlocId(UUID id) {

    public BlocId {
        if (id == null) {
            id = UUID.randomUUID();
        }
    }
}
