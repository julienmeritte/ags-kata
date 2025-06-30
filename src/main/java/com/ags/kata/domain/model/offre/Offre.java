package com.ags.kata.domain.model.offre;

import com.ags.kata.domain.model.bloc.Bloc;
import com.ags.kata.domain.model.marche.Marche;

import java.time.LocalDate;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

/**
 * Offre de vente destinée aux Marchés contenant plusieurs blocs horaires d'énergie produite.
 */
public class Offre {
    private final OffreId id;
    private String acteur;
    private Marche marche;
    private LocalDate jour;
    private Set<Bloc> blocs;

    public Offre(OffreId id, String acteur, Marche marche, LocalDate jour, Set<Bloc> blocs) {
        this.id = Optional.ofNullable(id)
                .orElseThrow(() -> new IllegalArgumentException("Une offre doit posséder un id."));
        this.acteur = Optional.ofNullable(acteur)
                .filter(a -> !a.isBlank())
                .orElseThrow(() -> new IllegalArgumentException("Une offre doit être proposée par un acteur"));
        this.marche = Optional.ofNullable(marche)
                .orElseThrow(() -> new IllegalArgumentException("Une offre doit impérativement être posée sur un marché"));
        this.jour = Optional.ofNullable(jour)
                .orElseThrow(() -> new IllegalArgumentException("Une offre doit être positionnée sur une journée"));
        this.blocs = Optional.ofNullable(blocs)
                .filter(set -> !set.isEmpty())
                .map(Set::copyOf)
                .orElseThrow(() -> new IllegalArgumentException("Une offre doit impérativement avoir des blocs d'énergie produite à vendre sur le marché."));
    }

    public OffreId getId() {
        return id;
    }

    public String getActeur() {
        return acteur;
    }

    public Marche getMarche() {
        return Marche.copyOf(marche);
    }

    public LocalDate getJour() {
        return jour;
    }

    public Set<Bloc> getBlocs() {
        return Set.copyOf(blocs);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Offre offre = (Offre) o;
        return Objects.equals(id, offre.id) && Objects.equals(acteur, offre.acteur) && Objects.equals(marche, offre.marche) && Objects.equals(jour, offre.jour) && Objects.equals(blocs, offre.blocs);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, acteur, marche, jour, blocs);
    }
}
