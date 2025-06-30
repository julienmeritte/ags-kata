package com.ags.kata.domain.model.allocation_parc;

import com.ags.kata.domain.model.bloc.Bloc;
import com.ags.kata.domain.model.bloc.BlocAllouable;
import com.ags.kata.domain.model.bloc.BlocId;
import com.ags.kata.domain.model.parc.Parc;
import com.ags.kata.domain.model.parc.ParcAvecCapacite;
import com.ags.kata.domain.model.parc.ParcId;
import com.ags.kata.infrastructure.exception.ManqueCapaciteProductionParc;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import static com.ags.kata.utils.AllocationParcUtils.*;
import static com.ags.kata.utils.ParcUtils.*;
import static org.junit.jupiter.api.Assertions.*;

class AllocationParcTest {

    private Bloc bloc;
    private List<ParcAvecCapacite> parcs;
    private BlocAllouable blocAllouable;

    @BeforeEach
    void setUp() {
        bloc = new Bloc(
                new BlocId(UUID.randomUUID()),
                100,
                BigDecimal.valueOf(150.0),
                0,
                Set.of()
        );

        parcs = new ArrayList<>();
        parcs.add(new ParcAvecCapacite(new Parc(PARC_ID, PARC_NOM, PARC_TYPE, PARC_CAPACITE_HORAIRE_MW), 60));
        parcs.add(new ParcAvecCapacite(new Parc(PARC_ID_BIS, PARC_NOM, PARC_TYPE, PARC_CAPACITE_HORAIRE_MW), 80));

        blocAllouable = new BlocAllouable(bloc, parcs);
    }

    @Test
    void shouldBeValid_WhenInstanciatingAllocationParc_GivenValidDate() {
        var allocationParc = new AllocationParc(ALLOCATION_PARC_ID, PARC_ID, ALLOCATION_PARC_QUANTITE);

        assertEquals(creerAllocationParc(), allocationParc);
    }

    @Test
    void shouldFail_WhenInstanciatingAllocationParc_GivenNullId() {
        assertThrows(NullPointerException.class, () -> new AllocationParc(null, PARC_ID, ALLOCATION_PARC_QUANTITE));
    }

    @Test
    void shouldFail_WhenInstanciatingAllocationParc_GivenNullParcId() {
        assertThrows(NullPointerException.class, () -> new AllocationParc(ALLOCATION_PARC_ID, null, ALLOCATION_PARC_QUANTITE));
    }

    @Test
    void shouldFail_WhenInstanciatingAllocationParc_GivenNegativeOrZeroQuantite() {
        assertThrows(IllegalArgumentException.class, () -> new AllocationParc(ALLOCATION_PARC_ID, PARC_ID, 0));
        assertThrows(IllegalArgumentException.class, () -> new AllocationParc(ALLOCATION_PARC_ID, PARC_ID, -1));
    }

    @Test
    void shouldAllouerParcs_WhenAllocationParcsAuBloc_GivenSufficientCapacity() {
        AllocationParc.allouerParcsAuBloc(blocAllouable);

        assertNotNull(bloc.getAllocations());
        assertEquals(2, bloc.getAllocations()
                .size());

        int totalAlloue = bloc.getAllocations()
                .stream()
                .mapToInt(AllocationParc::getQuantite)
                .sum();
        assertEquals(100, totalAlloue);
    }

    @Test
    void shouldThrowException_WhenAllocationParcsAuBloc_GivenInsufficientCapacity() {
        Bloc blocTropGrand = new Bloc(
                new BlocId(UUID.randomUUID()),
                200, // Plus que la capacité totale (60+80=140)
                BigDecimal.valueOf(150.0),
                0,
                Set.of()
        );
        BlocAllouable blocAllouableInsuffisant = new BlocAllouable(blocTropGrand, parcs);

        assertThrows(ManqueCapaciteProductionParc.class, () ->
                AllocationParc.allouerParcsAuBloc(blocAllouableInsuffisant)
        );
    }

    @Test
    void shouldAllouerQuantiteCorrecte_WhenAllocationParcsAuBloc_GivenMultipleParcs() {
        AllocationParc.allouerParcsAuBloc(blocAllouable);

        List<AllocationParc> allocations = new ArrayList<>(bloc.getAllocations());

        boolean foundFirstAllocation = allocations.stream()
                .anyMatch(alloc -> alloc.getQuantite() == 60);
        boolean foundSecondAllocation = allocations.stream()
                .anyMatch(alloc -> alloc.getQuantite() == 40);

        assertTrue(foundFirstAllocation);
        assertTrue(foundSecondAllocation);
    }

    @Test
    void shouldAllouerMontantExact_WhenAllocationParcsAuBloc_GivenParcCapaciteExacte() {
        List<ParcAvecCapacite> singleParc = List.of(
                new ParcAvecCapacite(new Parc(new ParcId(UUID.randomUUID()), PARC_NOM, PARC_TYPE, PARC_CAPACITE_HORAIRE_MW), 100)
        );
        BlocAllouable blocAvecUnParc = new BlocAllouable(bloc, singleParc);

        AllocationParc.allouerParcsAuBloc(blocAvecUnParc);

        assertEquals(1, bloc.getAllocations()
                .size());
        AllocationParc allocation = bloc.getAllocations()
                .iterator()
                .next();
        assertEquals(100, allocation.getQuantite());
    }

    @Test
    void shouldNotAllouerToAllParcs_WhenAllocationParcsAuBloc_GivenFirstParcHasSufficientCapacity() {
        List<ParcAvecCapacite> parcsAvecPremierSuffisant = List.of(
                new ParcAvecCapacite(new Parc(new ParcId(UUID.randomUUID()), PARC_NOM, PARC_TYPE, PARC_CAPACITE_HORAIRE_MW), 150),
                new ParcAvecCapacite(new Parc(new ParcId(UUID.randomUUID()), PARC_NOM, PARC_TYPE, PARC_CAPACITE_HORAIRE_MW), 80)
        );
        BlocAllouable blocAvecCapaciteSuffisante = new BlocAllouable(bloc, parcsAvecPremierSuffisant);

        AllocationParc.allouerParcsAuBloc(blocAvecCapaciteSuffisante);

        assertEquals(1, bloc.getAllocations()
                .size());
        AllocationParc allocation = bloc.getAllocations()
                .iterator()
                .next();
        assertEquals(100, allocation.getQuantite());
    }
}