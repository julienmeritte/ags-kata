package com.ags.kata.infrastructure.adapter.persistence.offre;

import com.ags.kata.infrastructure.adapter.persistence.bloc.BlocEntity;
import com.ags.kata.infrastructure.adapter.persistence.marche.MarcheEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.time.LocalDate;
import java.util.Set;

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
    @SequenceGenerator(name = "offre_seq", sequenceName = "offre_seq", allocationSize = 1)
    private long id;

    @NotBlank
    @Column(nullable = false)
    private String acteur;

    @Column(nullable = false)
    private LocalDate jour;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_marche", nullable = false)
    private MarcheEntity marche;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "offre", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<BlocEntity> blocs = Set.of();
}
