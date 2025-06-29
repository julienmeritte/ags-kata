package com.ags.kata.domain.model.offre;

import com.ags.kata.domain.model.bloc.Bloc;
import com.ags.kata.domain.model.marche.Marche;

import java.util.Objects;
import java.util.Optional;
import java.util.Set;

public class Offre {
    private final OffreId id;
    private Marche marche;
    private Set<Bloc> blocs;

    public Offre(OffreId id, Marche marche, Set<Bloc> blocs) {
        this.id = Optional.ofNullable(id)
                .orElseThrow(() -> new IllegalArgumentException("Une offre doit posséder un id."));
        this.marche = Optional.ofNullable(marche)
                .orElseThrow(() -> new IllegalArgumentException("Une offre doit impérativement être posée sur un marché"));
        this.blocs = Optional.ofNullable(blocs)
                .filter(set -> !set.isEmpty())
                .map(Set::copyOf)
                .orElseThrow(() -> new IllegalArgumentException("Une offre doit impérativement avoir des blocs d'énergie produite à vendre sur le marché."));
    }

    public OffreId getId() {
        return id;
    }

    public Marche getMarche() {
        return marche.copy();
    }

    public Set<Bloc> getBlocs() {
        return Set.copyOf(blocs);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Offre offre = (Offre) o;
        return Objects.equals(id, offre.id) && Objects.equals(marche, offre.marche) && Objects.equals(blocs, offre.blocs);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, marche, blocs);
    }
}
