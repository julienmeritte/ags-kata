package com.ags.kata.domain.model.bloc;

import com.ags.kata.domain.model.parc.Parc;
import com.ags.kata.domain.model.parc.ParcAvecCapacite;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import static com.ags.kata.utils.ParcUtils.*;
import static org.junit.jupiter.api.Assertions.*;

class BlocAllouableTest {

    private Bloc bloc;
    private List<ParcAvecCapacite> parcs;

    @BeforeEach
    void setUp() {
        bloc = new Bloc(
                new BlocId(UUID.randomUUID()),
                100,
                BigDecimal.valueOf(150.0),
                0,
                Set.of()
        );

        parcs = List.of(
                new ParcAvecCapacite(new Parc(PARC_ID, PARC_NOM, PARC_TYPE, PARC_CAPACITE_HORAIRE_MW), 60),
                new ParcAvecCapacite(new Parc(PARC_ID_BIS, PARC_NOM, PARC_TYPE, PARC_CAPACITE_HORAIRE_MW), 80)
        );
    }

    @Test
    void shouldCreateBlocAllouable_WhenCreating_GivenValidBlocAndParcs() {
        BlocAllouable blocAllouable = new BlocAllouable(bloc, parcs);

        assertNotNull(blocAllouable);
        assertEquals(bloc, blocAllouable.getBloc());
        assertEquals(2, blocAllouable.getParcAvecCapacites()
                .size());
    }

    @Test
    void shouldThrowException_WhenCreating_GivenNullBloc() {
        NullPointerException exception = assertThrows(
                NullPointerException.class,
                () -> new BlocAllouable(null, parcs)
        );

        assertEquals("Un bloc à allouer ne peut pas être null", exception.getMessage());
    }

    @Test
    void shouldCreateWithEmptyList_WhenCreating_GivenNullParcs() {
        BlocAllouable blocAllouable = new BlocAllouable(bloc, null);

        assertNotNull(blocAllouable.getParcAvecCapacites());
        assertTrue(blocAllouable.getParcAvecCapacites()
                .isEmpty());
    }

    @Test
    void shouldCreateDefensiveCopy_WhenCreating_GivenParcs() {
        List<ParcAvecCapacite> originalParcs = new ArrayList<>(parcs);

        BlocAllouable blocAllouable = new BlocAllouable(bloc, originalParcs);
        originalParcs.clear();

        assertEquals(2, blocAllouable.getParcAvecCapacites()
                .size());
    }

    @Test
    void shouldExtractBlocs_WhenExtraireBlocs_GivenSetOfBlocAllouables() {
        Bloc bloc2 = new Bloc(
                new BlocId(UUID.randomUUID()),
                80,
                BigDecimal.valueOf(120.0),
                1,
                Set.of()
        );

        BlocAllouable blocAllouable1 = new BlocAllouable(bloc, parcs);
        BlocAllouable blocAllouable2 = new BlocAllouable(bloc2, parcs);
        Set<BlocAllouable> blocAllouables = Set.of(blocAllouable1, blocAllouable2);

        Set<Bloc> result = BlocAllouable.extraireBlocs(blocAllouables);

        assertNotNull(result);
        assertEquals(2, result.size());
        assertTrue(result.contains(bloc));
        assertTrue(result.contains(bloc2));
    }

    @Test
    void shouldReturnEmptySet_WhenExtraireBlocs_GivenEmptySet() {
        Set<BlocAllouable> emptySet = Set.of();

        Set<Bloc> result = BlocAllouable.extraireBlocs(emptySet);

        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    @Test
    void shouldReturnUnmodifiableSet_WhenExtraireBlocs_GivenBlocAllouables() {
        BlocAllouable blocAllouable = new BlocAllouable(bloc, parcs);
        Set<BlocAllouable> blocAllouables = Set.of(blocAllouable);

        Set<Bloc> result = BlocAllouable.extraireBlocs(blocAllouables);

        assertThrows(UnsupportedOperationException.class, () ->
                result.add(bloc)
        );
    }
}