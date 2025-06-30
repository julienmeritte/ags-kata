package com.ags.kata.infrastructure.adapter.persistence.parc;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;
import java.util.List;

public interface ParcJpaRepository extends CrudRepository<ParcEntity, Long> {

    @Query(value = "SELECT NEXT VALUE FOR parc_seq", nativeQuery = true)
    Long prochainId();

    @Query("""
             SELECT p FROM ParcEntity p\s
             WHERE p.id NOT IN (
                 SELECT DISTINCT alloc.parc.id\s
                 FROM AllocationParcEntity alloc\s
                 JOIN alloc.bloc bloc\s
                 JOIN bloc.offre offre
                 WHERE offre.jour = :jour\s
                 AND bloc.positionJournee = :positionJournee
                 AND (
                     SELECT COALESCE(SUM(a.quantite), 0)\s
                     FROM AllocationParcEntity a\s
                     JOIN a.bloc b\s
                     JOIN b.offre o
                     WHERE a.parc.id = p.id\s
                     AND o.jour = :jour\s
                     AND b.positionJournee = :positionJournee
                 ) + :quantiteRequise > p.capaciteHoraireMW
             )
             ORDER BY (
                 p.capaciteHoraireMW - COALESCE((
                     SELECT SUM(a.quantite)\s
                     FROM AllocationParcEntity a\s
                     JOIN a.bloc b\s
                     JOIN b.offre o
                     WHERE a.parc.id = p.id\s
                     AND o.jour = :jour\s
                     AND b.positionJournee = :positionJournee
                 ), 0)
             ) DESC
            \s""")
    List<ParcEntity> findParcsDisponiblesPourBloc(
            int quantiteRequise,
            LocalDate jour,
            int positionJournee
    );
}
