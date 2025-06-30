package com.ags.kata.infrastructure.adapter.web.offre;

import com.ags.kata.application.dto.request.CreationOffreRequestDto;
import com.ags.kata.application.dto.response.MarcheOffreResponseDto;
import com.ags.kata.application.dto.response.OffreResponseDto;
import com.ags.kata.application.port.in.CreerOffreUseCase;
import com.ags.kata.application.port.in.RecupererOffreUseCase;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/offre")
public class OffreController {

    private final CreerOffreUseCase creerOffreUseCase;
    private final RecupererOffreUseCase recupererOffreUseCase;

    @Autowired
    public OffreController(CreerOffreUseCase creerOffreUseCase, RecupererOffreUseCase recupererOffreUseCase) {
        this.creerOffreUseCase = creerOffreUseCase;
        this.recupererOffreUseCase = recupererOffreUseCase;
    }

    @GetMapping("/marche/agregio")
    public ResponseEntity<Set<MarcheOffreResponseDto>> recupererOffresAgregioParMarche() {

        return ResponseEntity.ok(recupererOffreUseCase.recupererOffresAgregioParMarche());
    }

    @PostMapping
    public ResponseEntity<OffreResponseDto> creerOffre(@RequestBody @Valid CreationOffreRequestDto creationOffreRequestDto) {

        var offreCreee = creerOffreUseCase.creerOffre(creationOffreRequestDto);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(offreCreee);
    }
}
