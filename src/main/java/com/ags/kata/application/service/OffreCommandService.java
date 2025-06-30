package com.ags.kata.application.service;

import com.ags.kata.application.dto.request.CreationOffreRequestDto;
import com.ags.kata.application.dto.response.OffreResponseDto;
import com.ags.kata.application.port.in.CreerOffreUseCase;
import com.ags.kata.application.port.out.MarcheQueryRepository;
import com.ags.kata.application.port.out.ParcQueryRepository;
import com.ags.kata.domain.model.marche.MarcheId;
import com.ags.kata.infrastructure.exception.MarcheNonTrouveException;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@Transactional
public class OffreCommandService implements CreerOffreUseCase {

    private final MarcheQueryRepository marcheQueryRepository;
    private final ParcQueryRepository parcQueryRepository;

    public OffreCommandService(MarcheQueryRepository marcheQueryRepository, ParcQueryRepository parcQueryRepository) {
        this.marcheQueryRepository = marcheQueryRepository;
        this.parcQueryRepository = parcQueryRepository;
    }

    @Override
    public OffreResponseDto creerOffre(CreationOffreRequestDto creationOffreRequestDto) {

        var marcheId = new MarcheId(creationOffreRequestDto.marcheId());

        // J'ai une offre
        // Cette offre a des blocs

        // Je veux m'assurer que j'ai la capacité de production pour chaque bloc
        // Puis allouer une allocation de parc à chaque bloc

        // -> Vérifier que le marché existe (fail-fast)
        // -> Récupérer la confirmation que chaque bloc peut avoir son énergie allouée
        //      -> Récupérer les parcs ayant de la capacité sur chaque période de bloc, triés par capacité décroissante
        //      -> Générer les allocations parc
        //      -> Générer les blocs
        //      -> Créer l'offre sur le marché
        //      -> save

        var marche = marcheQueryRepository.recupererMarcheParId(marcheId);

        if (marche.isEmpty()) {
            throw new MarcheNonTrouveException(marcheId);
        }

        creationOffreRequestDto.blocs()
                .forEach(
                        blocRequestDto -> {
                            var parcDisponibles = parcQueryRepository.findParcsDisponiblesPourBloc(blocRequestDto.quantiteEnergieMW(), creationOffreRequestDto.jour(), blocRequestDto.positionJournee());
                            log.info("parc dispos  -> {}", parcDisponibles);
                        }
                );


        return null;
    }
}
