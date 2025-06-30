package com.ags.kata.infrastructure.adapter.persistence.marche;

import com.ags.kata.infrastructure.adapter.persistence.offre.OffreEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

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
    @Column(columnDefinition = "VARCHAR(36)", unique = true, nullable = false)
    private UUID id;

    @Column(nullable = false)
    private String nom;

    @OneToMany(mappedBy = "marche")
    @ToString.Exclude
    private Set<OffreEntity> offres = new HashSet<>();
}
