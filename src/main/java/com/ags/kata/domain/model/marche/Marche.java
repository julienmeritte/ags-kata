package com.ags.kata.domain.model.marche;

import java.util.Objects;
import java.util.Optional;

/**
 * Marché: Entité sur laquelle sont posée des Offres
 */
public class Marche {
    private final MarcheId id;
    private String nom;

    public Marche(MarcheId id, String nom) {
        this.id = Objects.requireNonNull(id, "Un marché doit posséder un id.");
        this.nom = Optional.ofNullable(nom)
                .filter(n -> !n.isBlank())
                .orElseThrow(() -> new IllegalArgumentException("Un Marche doit posséder un nom"));
    }

    public static Marche copyOf(Marche marche) {
        return new Marche(marche.id, marche.nom);
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

    @Override
    public String toString() {
        return "Marche{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                '}';
    }
}
