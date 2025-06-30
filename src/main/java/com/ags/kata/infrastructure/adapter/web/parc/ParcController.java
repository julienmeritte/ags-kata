package com.ags.kata.infrastructure.adapter.web.parc;

import com.ags.kata.application.dto.request.CreationParcRequestDto;
import com.ags.kata.application.dto.response.ParcResponseDto;
import com.ags.kata.application.port.in.CreerParcUseCase;
import com.ags.kata.application.port.in.RecupererParcUseCase;
import com.ags.kata.domain.model.marche.MarcheId;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;
import java.util.UUID;

@RestController
@RequestMapping("/parc")
public class ParcController {

    private final CreerParcUseCase creerParcUseCase;
    private final RecupererParcUseCase recupererParcUseCase;

    @Autowired
    public ParcController(CreerParcUseCase creerParcUseCase, RecupererParcUseCase recupererParcUseCase) {
        this.creerParcUseCase = creerParcUseCase;
        this.recupererParcUseCase = recupererParcUseCase;
    }

    @GetMapping("/marche/{marcheId}")
    public ResponseEntity<Set<ParcResponseDto>> recupererParcsSelonMarche(@PathVariable("marcheId") UUID marcheID) {
        return ResponseEntity.ok(recupererParcUseCase.recupererParcsVendantParMarche(new MarcheId(marcheID)));
    }

    @PostMapping
    public ResponseEntity<ParcResponseDto> creerParc(@Valid @RequestBody CreationParcRequestDto creationParcRequestDto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(creerParcUseCase.creerParc(creationParcRequestDto));
    }
}
