package com.ags.kata.domain.model.parc;

public record ParcId(long id) {

    public ParcId {
        if (id < 0) {
            throw new IllegalArgumentException("L'Id Parc doit être supérieur ou égal à 0.");
        }
    }
}
