package com.ags.kata.domain.model.offre;

public record OffreId(long id) {

    public OffreId {
        if (id <= 0) {
            throw new IllegalArgumentException("Id Offre doit être positif");
        }
    }
}
