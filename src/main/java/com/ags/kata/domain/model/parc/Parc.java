package com.ags.kata.domain.model.parc;

import java.util.Objects;
import java.util.Set;

/**
 * Parc de production électrique.
 */
public class Parc {
    private final ParcId id;
    private final String nom;
    private final ParcType type;
    private final int capaciteHoraireMW;

    public Parc(ParcId id, String nom, ParcType type, int capaciteHoraireMW) {
        this.id = Objects.requireNonNull(id, "Un Parc doit posséder un ID.");
        this.nom = Objects.requireNonNull(nom, "Un Parc doit avoir un nom");
        this.type = Objects.requireNonNull(type, "Un Parc doit avoir un type");

        if (capaciteHoraireMW <= 0) {
            throw new IllegalArgumentException("Un Parc doit pouvoir produire");
        }
        this.capaciteHoraireMW = capaciteHoraireMW;
    }

    public static int recupererCapaciteMultipleParc(Set<Parc> parcs) {
        return parcs.stream()
                .mapToInt(Parc::getCapaciteHoraireMW)
                .sum();
    }

    public static Parc copyOf(Parc parc) {
        return new Parc(parc.id, parc.nom, parc.type, parc.capaciteHoraireMW);
    }

    public ParcId getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public ParcType getType() {
        return type;
    }

    public int getCapaciteHoraireMW() {
        return capaciteHoraireMW;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Parc parc = (Parc) o;
        return capaciteHoraireMW == parc.capaciteHoraireMW && Objects.equals(id, parc.id) && Objects.equals(nom, parc.nom) && type == parc.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nom, type, capaciteHoraireMW);
    }

    @Override
    public String toString() {
        return "Parc{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", type=" + type +
                ", capaciteHoraireMW=" + capaciteHoraireMW +
                '}';
    }
}
