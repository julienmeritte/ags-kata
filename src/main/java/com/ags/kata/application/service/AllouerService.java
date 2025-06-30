package com.ags.kata.application.service;

import com.ags.kata.application.dto.request.BlocRequestDto;
import com.ags.kata.application.port.in.AllouerBlocUseCase;
import com.ags.kata.application.port.in.RecupererParcUseCase;
import com.ags.kata.domain.model.allocation_parc.AllocationParc;
import com.ags.kata.domain.model.bloc.Bloc;
import com.ags.kata.domain.model.bloc.BlocAllouable;
import com.ags.kata.domain.model.bloc.BlocId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class AllouerService implements AllouerBlocUseCase {

    private final RecupererParcUseCase recupererParcUseCase;

    @Autowired
    public AllouerService(RecupererParcUseCase recupererParcUseCase) {
        this.recupererParcUseCase = recupererParcUseCase;
    }

    @Override
    public Set<Bloc> recupererEtAllouerBlocs(Set<BlocRequestDto> blocRequestDtos, LocalDate jour) {
        var blocsAllouables = creerBlocsAllouables(blocRequestDtos, jour);

        blocsAllouables.forEach(AllocationParc::allouerParcsAuBloc);

        return BlocAllouable.extraireBlocs(blocsAllouables);
    }

    private Set<BlocAllouable> creerBlocsAllouables(Set<BlocRequestDto> blocsRequest, LocalDate jour) {
        return blocsRequest.stream()
                .map(blocRequest -> creerBlocAllouable(blocRequest, jour))
                .collect(Collectors.toSet());
    }

    private BlocAllouable creerBlocAllouable(BlocRequestDto blocRequest, LocalDate jour) {
        var parcsDisponibles = recupererParcUseCase.recupererParcsAvecCapaciteDisponiblesPeriode(blocRequest, jour);
        var bloc = new Bloc(new BlocId(UUID.randomUUID()),
                blocRequest.quantiteEnergieMW(),
                BigDecimal.valueOf(blocRequest.prixPlancher()),
                blocRequest.positionJournee(), Set.of());
        return new BlocAllouable(bloc, new ArrayList<>(parcsDisponibles));
    }
}
