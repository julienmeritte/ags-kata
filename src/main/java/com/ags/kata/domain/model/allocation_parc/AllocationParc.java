package com.ags.kata.domain.model.allocation_parc;

import com.ags.kata.domain.model.bloc.BlocAllouable;
import com.ags.kata.domain.model.parc.ParcAvecCapacite;
import com.ags.kata.domain.model.parc.ParcId;
import com.ags.kata.infrastructure.exception.ManqueCapaciteProductionParc;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

/**
 * Allocation entre un bloc et un parc permettant de combler la demande des blocs en production électrique.
 */
public class AllocationParc {
    private final AllocationParcId id;
    private final ParcId parcId;
    private final int quantite;

    public AllocationParc(AllocationParcId id, ParcId parcId, int quantite) {
        this.id = Objects.requireNonNull(id, "Une Allocation de Parc doit avoir un ID");
        this.parcId = Objects.requireNonNull(parcId, "Une allocation de Parc doit être reliée à un Parc");

        if (quantite <= 0) {
            throw new IllegalArgumentException("La quantité allouée doit être positive");
        }
        this.quantite = quantite;
    }

    public AllocationParcId getId() {
        return id;
    }

    public ParcId getParcId() {
        return parcId;
    }

    public int getQuantite() {
        return quantite;
    }

    public static void allouerParcsAuBloc(BlocAllouable blocAllouable) {
        List<ParcAvecCapacite> parcs = new ArrayList<>(blocAllouable.getParcAvecCapacites());
        var bloc = blocAllouable.getBloc();

        if (ParcAvecCapacite.calculerCapaciteMultipleParc(parcs) < bloc.getQuantiteEnergieMW()) {
            throw new ManqueCapaciteProductionParc();
        }

        int energieRestante = bloc.getQuantiteEnergieMW();

        for (int i = 0; i < parcs.size() && energieRestante > 0; i++) {
            ParcAvecCapacite parc = parcs.get(i);
            int capaciteAllouee = Math.min(parc.getCapaciteRestante(), energieRestante);

            bloc.ajouterAllocation(new AllocationParc(
                    new AllocationParcId(UUID.randomUUID()),
                    parc.getParc()
                            .getId(),
                    capaciteAllouee
            ));

            energieRestante -= capaciteAllouee;
        }
    }

    public static AllocationParc copyOf(AllocationParc allocationParc) {
        return new AllocationParc(allocationParc.id, allocationParc.parcId, allocationParc.quantite);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AllocationParc that = (AllocationParc) o;
        return quantite == that.quantite && Objects.equals(id, that.id) && Objects.equals(parcId, that.parcId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, parcId, quantite);
    }

    @Override
    public String toString() {
        return "AllocationParc{" +
                "id=" + id +
                ", parcId=" + parcId +
                ", quantite=" + quantite +
                '}';
    }
}
