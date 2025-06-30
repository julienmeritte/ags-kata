package com.ags.kata.domain.model.parc;

import java.util.List;
import java.util.Objects;

public class ParcAvecCapacite {
    private final Parc parc;
    private final int capaciteRestante;

    public ParcAvecCapacite(Parc parc, int capaciteRestante) {
        this.parc = parc;
        this.capaciteRestante = capaciteRestante;
    }

    public static int calculerCapaciteMultipleParc(List<ParcAvecCapacite> parcAvecCapacites) {
        return parcAvecCapacites.stream()
                .mapToInt(p -> p.capaciteRestante)
                .sum();
    }

    public static ParcAvecCapacite copyOf(ParcAvecCapacite parcAvecCapacite) {
        return new ParcAvecCapacite(parcAvecCapacite.parc, parcAvecCapacite.capaciteRestante);
    }

    public Parc getParc() {
        return parc;
    }

    public int getCapaciteRestante() {
        return capaciteRestante;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ParcAvecCapacite that = (ParcAvecCapacite) o;
        return capaciteRestante == that.capaciteRestante && Objects.equals(parc, that.parc);
    }

    @Override
    public int hashCode() {
        return Objects.hash(parc, capaciteRestante);
    }

    @Override
    public String toString() {
        return "ParcAvecCapacite{" +
                "parc=" + parc +
                ", capaciteRestante=" + capaciteRestante +
                '}';
    }
}
