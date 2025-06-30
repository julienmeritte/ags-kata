package com.ags.kata.domain.model.bloc;

import com.ags.kata.domain.model.allocation_parc.AllocationParc;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

/**
 * Bloc Horaire d'énergie à placer dans une Offre de vente.
 */
public class Bloc {
    private final BlocId id;
    private final int quantiteEnergieMW;
    private final int positionJournee;
    private final BigDecimal prixPlancher;
    private final Set<AllocationParc> allocations;

    public Bloc(BlocId id, int quantiteEnergieMW, BigDecimal prixPlancher, int positionJournee, Set<AllocationParc> allocations) {
        this.id = Objects.requireNonNull(id, "Un Bloc a besoin d'un ID");
        this.prixPlancher = Optional.ofNullable(prixPlancher)
                .filter(p -> p.doubleValue() >= 0)
                .orElseThrow(() -> new IllegalArgumentException("Le prix plancher doit être renseigné et positif"));

        if (quantiteEnergieMW < 0) {
            throw new IllegalArgumentException("Un Bloc ne peut pas avoir une quanité d'énergie négative");
        }
        if (positionJournee < 0 || positionJournee > 7) {
            throw new IllegalArgumentException("Un Parc ne peut pas produire négativement");
        }

        this.allocations = allocations == null ? new HashSet<>() : new HashSet<>(allocations);

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

    public Set<AllocationParc> getAllocations() {
        return allocations;
    }

    public void ajouterAllocation(AllocationParc allocationParc) {
        allocations.add(AllocationParc.copyOf(allocationParc));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bloc bloc = (Bloc) o;
        return quantiteEnergieMW == bloc.quantiteEnergieMW && positionJournee == bloc.positionJournee && Objects.equals(id, bloc.id) && Objects.equals(prixPlancher, bloc.prixPlancher) && Objects.equals(allocations, bloc.allocations);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, quantiteEnergieMW, positionJournee, prixPlancher, allocations);
    }

    @Override
    public String toString() {
        return "Bloc{" +
                "id=" + id +
                ", quantiteEnergieMW=" + quantiteEnergieMW +
                ", positionJournee=" + positionJournee +
                ", prixPlancher=" + prixPlancher +
                ", allocations=" + allocations +
                '}';
    }
}
