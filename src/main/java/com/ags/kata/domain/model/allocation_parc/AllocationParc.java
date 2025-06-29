package com.ags.kata.domain.model.allocation_parc;

import com.ags.kata.domain.model.parc.ParcId;

import java.util.Objects;
import java.util.Optional;

public class AllocationParc {
    private final AllocationParcId id;
    private final ParcId parcId;
    private int quantite;

    public AllocationParc(AllocationParcId id, ParcId parcId, int quantite) {
        this.id = Optional.ofNullable(id)
                .orElseThrow(() -> new IllegalArgumentException("Une Allocation de Parc doit avoir un ID"));
        this.parcId = Optional.ofNullable(parcId)
                .orElseThrow(() -> new IllegalArgumentException("Une allocation de Parc doit être reliée à un Parc"));

        if (quantite <= 0) {
            throw new IllegalArgumentException("La quantité allouée doit être positive");
        }
        this.quantite = quantite;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AllocationParc that = (AllocationParc) o;
        return quantite == that.quantite && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, quantite);
    }
}
