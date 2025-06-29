package com.ags.kata.domain.model.bloc;

public record BlocId(long id) {

    public BlocId {
        if (id < 0) {
            throw new IllegalArgumentException("Id Bloc doit être supérieur ou égal à 0");
        }
    }
}
