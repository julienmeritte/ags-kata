package com.ags.kata.domain.model.parc;

import java.util.Objects;
import java.util.Optional;

public class Parc {
    private final ParcId id;
    private ParcType type;
    private int capaciteHoraireMW;

    public Parc(ParcId id, ParcType type, int capaciteHoraireMW) {
        this.id = Optional.ofNullable(id)
                .orElseThrow(() -> new IllegalArgumentException("Un Parc doit posséder un ID."));
        this.type = Optional.ofNullable(type)
                .orElseThrow(() -> new IllegalArgumentException("Un Parc doit avoir un type"));

        if (capaciteHoraireMW < 0) {
            throw new IllegalArgumentException("Un Parc ne peut pas produire négativement");
        }
        this.capaciteHoraireMW = capaciteHoraireMW;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Parc parc = (Parc) o;
        return capaciteHoraireMW == parc.capaciteHoraireMW && Objects.equals(id, parc.id) && type == parc.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, type, capaciteHoraireMW);
    }
}
