package com.ags.kata.infrastructure.adapter.persistence.parc;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface ParcJpaRepository extends CrudRepository<ParcEntity, Long> {

    @Query("""
                SELECT p as parc,
                       COALESCE(p.capaciteHoraireMW - COALESCE((
                           SELECT SUM(a.quantite)\s
                           FROM AllocationParcEntity a\s
                           JOIN a.bloc b\s
                           JOIN b.offre o
                           WHERE a.parc.id = p.id\s
                           AND o.jour = :jour\s
                           AND b.positionJournee = :positionJournee
                       ), 0), p.capaciteHoraireMW) as capaciteRestante
                FROM ParcEntity p
                WHERE (
                    COALESCE((
                        SELECT SUM(a.quantite)\s
                        FROM AllocationParcEntity a\s
                        JOIN a.bloc b\s
                        JOIN b.offre o
                        WHERE a.parc.id = p.id\s
                        AND o.jour = :jour\s
                        AND b.positionJournee = :positionJournee
                    ), 0)
                ) < p.capaciteHoraireMW
                ORDER BY capaciteRestante DESC
            """)
    List<ParcCapaciteProjection> findParcsAvecCapaciteRestante(@Param("jour") LocalDate jour, @Param("positionJournee") int positionJournee);

    @Query("""
             SELECT DISTINCT p FROM ParcEntity p
             JOIN AllocationParcEntity ap ON ap.parc.id = p.id
             JOIN BlocEntity b ON b.id = ap.bloc.id \s
             JOIN OffreEntity o ON o.id = b.offre.id
             WHERE o.marche.id = :marcheId
            \s""")
    List<ParcEntity> recupererParcsVendantParMarche(@Param("marcheId") UUID marcheId);
}
