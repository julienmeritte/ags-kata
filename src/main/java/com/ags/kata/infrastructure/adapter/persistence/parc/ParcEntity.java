package com.ags.kata.infrastructure.adapter.persistence.parc;

import com.ags.kata.domain.model.parc.ParcType;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "parc")
@Getter
@Setter
@ToString
@NoArgsConstructor
public class ParcEntity {

    @Id
    @SequenceGenerator(name = "parc_seq", sequenceName = "parc_seq", allocationSize = 1)
    @Column(name = "id")
    private long id;

    @Column(nullable = false)
    private String nom;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ParcType type;

    @Column(name = "capacite_horaire_mw", nullable = false)
    private int capaciteHoraireMW;
}
