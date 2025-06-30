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
import java.util.Set;

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
    @SequenceGenerator(name = "bloc_seq", sequenceName = "bloc_seq", allocationSize = 1)
    private long id;

    @Column(nullable = false)
    @Positive
    private int quantiteEnergieMW;

    @Column(nullable = false)
    @NotNull
    private BigDecimal prixPlancher;

    @Column(nullable = false)
    @Min(0)
    @Max(7)
    private int positionJournee;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_offre", nullable = false)
    @ToString.Exclude
    private OffreEntity offre;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "bloc", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<AllocationParcEntity> allocationsParcs;
}
