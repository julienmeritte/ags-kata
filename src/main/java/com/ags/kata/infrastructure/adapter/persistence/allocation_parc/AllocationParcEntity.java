package com.ags.kata.infrastructure.adapter.persistence.allocation_parc;

import com.ags.kata.infrastructure.adapter.persistence.bloc.BlocEntity;
import com.ags.kata.infrastructure.adapter.persistence.parc.ParcEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "allocation_parc")
@Builder
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class AllocationParcEntity {

    @Id
    @Column(columnDefinition = "VARCHAR(36)", unique = true, nullable = false)
    private UUID id;

    @Column(nullable = false)
    private int quantite;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_parc")
    private ParcEntity parc;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_bloc")
    private BlocEntity bloc;
}
