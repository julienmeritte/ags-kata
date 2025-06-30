package com.ags.kata.infrastructure.adapter.persistence.parc;

import com.ags.kata.domain.model.parc.ParcType;
import com.ags.kata.infrastructure.adapter.persistence.allocation_parc.AllocationParcEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "parc")
@Builder
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ParcEntity {

    @Id
    @Column(columnDefinition = "VARCHAR(36)", unique = true, nullable = false)
    private UUID id;

    @Column(nullable = false)
    private String nom;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ParcType type;

    @Column(name = "capacite_horaire_mw", nullable = false)
    private int capaciteHoraireMW;

    @OneToMany(mappedBy = "parc")
    @ToString.Exclude
    private Set<AllocationParcEntity> allocationParcs = new HashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ParcEntity that = (ParcEntity) o;
        return id == that.id && capaciteHoraireMW == that.capaciteHoraireMW && Objects.equals(nom, that.nom) && type == that.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nom, type, capaciteHoraireMW);
    }
}
