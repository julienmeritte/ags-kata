package com.ags.kata.domain.model.parc;

import java.util.Objects;
import java.util.Optional;

public class Parc {
    private final ParcId id;
    private String nom;
    private ParcType type;
    private int capaciteHoraireMW;

    public Parc(ParcId id, String nom, ParcType type, int capaciteHoraireMW) {
        this.id = Optional.ofNullable(id)
                .orElseThrow(() -> new IllegalArgumentException("Un Parc doit posséder un ID."));
        this.nom = Optional.ofNullable(nom)
                .orElseThrow(() -> new IllegalArgumentException("Un Parc doit avoir un nom"));
        this.type = Optional.ofNullable(type)
                .orElseThrow(() -> new IllegalArgumentException("Un Parc doit avoir un type"));

        if (capaciteHoraireMW <= 0) {
            throw new IllegalArgumentException("Un Parc doit pouvoir produire");
        }
        this.capaciteHoraireMW = capaciteHoraireMW;
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
}
