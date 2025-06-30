package com.ags.kata.infrastructure.adapter.persistence.marche;

import com.ags.kata.infrastructure.adapter.persistence.offre.OffreEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Table(name = "marche")
@Builder
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class MarcheEntity {

    @Id
    @SequenceGenerator(name = "marche_seq", sequenceName = "marche_seq", allocationSize = 1)
    private long id;

    @Column(nullable = false)
    private String nom;

    @OneToMany(mappedBy = "marche")
    @ToString.Exclude
    private Set<OffreEntity> offres = Set.of();
}
