package com.ags.kata.infrastructure.adapter.persistence.bloc;

import com.ags.kata.infrastructure.adapter.persistence.allocation_parc.AllocationParcEntity;
import com.ags.kata.infrastructure.adapter.persistence.offre.OffreEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "bloc")
@Builder
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class BlocEntity {

    @Id
    @Column(columnDefinition = "VARCHAR(36)", unique = true, nullable = false)
    private UUID id;

    @Column(name = "quantite_energie_mw", nullable = false)
    @Positive
    private int quantiteEnergieMW;

    @Column(name = "prix_plancher", nullable = false)
    @NotNull
    private BigDecimal prixPlancher;

    @Column(name = "position_journee", nullable = false)
    @Min(0)
    @Max(7)
    private int positionJournee;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_offre")
    @ToString.Exclude
    private OffreEntity offre;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "bloc", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<AllocationParcEntity> allocations = new HashSet<>();
}
