package com.ags.kata.domain.model.bloc;

import com.ags.kata.domain.model.parc.ParcAvecCapacite;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public class BlocAllouable {
    private final Bloc bloc;
    private final List<ParcAvecCapacite> parcAvecCapacites;

    public BlocAllouable(Bloc bloc, List<ParcAvecCapacite> parcAvecCapacites) {
        this.bloc = Objects.requireNonNull(bloc, "Un bloc à allouer ne peut pas être null");
        this.parcAvecCapacites = parcAvecCapacites == null ? new ArrayList<>() : new ArrayList<>(parcAvecCapacites);
    }

    public static Set<Bloc> extraireBlocs(Set<BlocAllouable> blocAllouables) {
        return blocAllouables.stream()
                .map(BlocAllouable::getBloc)
                .collect(Collectors.toUnmodifiableSet());
    }

    public Bloc getBloc() {
        return bloc;
    }

    public List<ParcAvecCapacite> getParcAvecCapacites() {
        return parcAvecCapacites;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BlocAllouable that = (BlocAllouable) o;
        return Objects.equals(bloc, that.bloc) && Objects.equals(parcAvecCapacites, that.parcAvecCapacites);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bloc, parcAvecCapacites);
    }

    @Override
    public String toString() {
        return "BlocAllouable{" +
                "bloc=" + bloc +
                ", parcAvecCapacites=" + parcAvecCapacites +
                '}';
    }
}
