package com.ags.kata.domain.model.bloc;

import com.ags.kata.domain.model.allocation_parc.AllocationParc;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

public class Bloc {
    private final BlocId id;
    private int quantiteEnergieMW;
    private int positionJournee;
    private BigDecimal prixPlancher;
    private LocalDate jour;

    private Set<AllocationParc> allocations;

    public Bloc(BlocId id, int quantiteEnergieMW, BigDecimal prixPlancher, int positionJournee, LocalDate jour, Set<AllocationParc> allocations) {
        this.id = Optional.ofNullable(id)
                .orElseThrow(() -> new IllegalArgumentException("Un Bloc a besoin d'un ID"));
        this.prixPlancher = Optional.ofNullable(prixPlancher)
                .filter(p -> p.doubleValue() >= 0)
                .orElseThrow(() -> new IllegalArgumentException("Le prix plancher doit être renseigné et positif"));
        this.jour = Optional.ofNullable(jour)
                .orElseThrow(() -> new IllegalArgumentException("Un Bloc doit être relié à une Date"));

        if (quantiteEnergieMW < 0) {
            throw new IllegalArgumentException("Un Bloc ne peut pas avoir une quanité d'énergie négative");
        }
        if (positionJournee < 0 || positionJournee > 7) {
            throw new IllegalArgumentException("Un Parc ne peut pas produire négativement");
        }

        this.allocations = Optional.ofNullable(allocations)
                .map(Set::copyOf)
                .orElse(Set.of());

        this.quantiteEnergieMW = quantiteEnergieMW;
        this.positionJournee = positionJournee;

    }

    public BlocId getId() {
        return id;
    }

    public int getQuantiteEnergieMW() {
        return quantiteEnergieMW;
    }

    public int getPositionJournee() {
        return positionJournee;
    }

    public BigDecimal getPrixPlancher() {
        return prixPlancher;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bloc bloc = (Bloc) o;
        return quantiteEnergieMW == bloc.quantiteEnergieMW && positionJournee == bloc.positionJournee && Objects.equals(id, bloc.id) && Objects.equals(prixPlancher, bloc.prixPlancher) && Objects.equals(jour, bloc.jour) && Objects.equals(allocations, bloc.allocations);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, quantiteEnergieMW, positionJournee, prixPlancher, jour, allocations);
    }
}
