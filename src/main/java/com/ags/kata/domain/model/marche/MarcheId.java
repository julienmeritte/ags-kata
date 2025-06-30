package com.ags.kata.domain.model.marche;

public record MarcheId(long id) {

    public MarcheId {
        if (id <= 0) {
            throw new IllegalArgumentException("Id Marché doit être positif");
        }
    }
}
