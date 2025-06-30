package com.ags.kata.infrastructure.adapter.persistence.offre;

import com.ags.kata.infrastructure.adapter.persistence.bloc.BlocEntity;
import com.ags.kata.infrastructure.adapter.persistence.marche.MarcheEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "offre")
@Builder
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class OffreEntity {

    @Id
    @Column(columnDefinition = "VARCHAR(36)", unique = true, nullable = false)
    private UUID id;

    @NotBlank
    @Column(nullable = false)
    private String acteur;

    @Column(nullable = false)
    private LocalDate jour;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_marche", nullable = false)
    private MarcheEntity marche;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "offre", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<BlocEntity> blocs = new HashSet<>();
}
