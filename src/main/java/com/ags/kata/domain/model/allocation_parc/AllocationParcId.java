package com.ags.kata.domain.model.allocation_parc;

import java.util.UUID;

public record AllocationParcId(UUID id) {

    public AllocationParcId {
        if (id == null) {
            id = UUID.randomUUID();
        }
    }
}
