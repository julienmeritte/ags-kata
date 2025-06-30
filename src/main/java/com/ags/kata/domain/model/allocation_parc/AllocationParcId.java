package com.ags.kata.domain.model.allocation_parc;

public record AllocationParcId(long id) {

    public AllocationParcId {
        if (id <= 0) {
            throw new IllegalArgumentException("L'Id Parc doit être positif");
        }
    }
}
