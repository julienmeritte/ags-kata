package com.ags.kata.domain.model.marche;

import java.util.Objects;
import java.util.Optional;

public class Marche {
    private final MarcheId id;
    private String nom;

    public Marche(MarcheId id, String nom) {
        this.id = Optional.ofNullable(id)
                .orElseThrow(() -> new IllegalArgumentException("Un marché doit posséder un id."));
        this.nom = Optional.ofNullable(nom)
                .filter(n -> !n.isBlank())
                .orElseThrow(() -> new IllegalArgumentException("Un Marche doit posséder un nom"));
    }

    public Marche(Marche marche) {
        this(marche.id, marche.nom);
    }

    public Marche copy() {
        return new Marche(this);
    }

    public MarcheId getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Marche marche = (Marche) o;
        return Objects.equals(id, marche.id) && Objects.equals(nom, marche.nom);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nom);
    }
}
